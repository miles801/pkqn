package eccrm.base.user.web;

import com.google.gson.JsonObject;
import com.michael.cache.redis.RedisServer;
import com.ycrl.core.SystemContainer;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.utils.gson.GsonUtils;
import com.ycrl.utils.gson.JsonObjectUtils;
import com.ycrl.utils.string.RandomUtils;
import eccrm.base.auth.domain.AccreditData;
import eccrm.base.auth.service.AccreditDataService;
import eccrm.base.auth.service.AccreditFuncService;
import eccrm.base.user.service.*;
import eccrm.base.user.vo.UserVo;
import eccrm.core.security.LoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author miles
 * @datetime 2014/3/20 11:06
 */
@Controller
@Scope("prototype")
public class LoginCtrl {
    private Logger logger = Logger.getLogger(LoginCtrl.class);
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;


    /**
     * 用户登录
     * 必须传入username和password属性
     * 默认为admin/eccrm
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletResponse response, HttpServletRequest request) {
        // 获得登录信息
        JsonObject param = GsonUtils.wrapDataToEntity(request, JsonObject.class);
        String username = JsonObjectUtils.getStringProperty(param, "username");
        if (username == null) {
            throw new IllegalArgumentException("用户登录时,没有获得用户名!");
        }
        String password = JsonObjectUtils.getStringProperty(param, "password");
        if (password == null) {
            throw new IllegalArgumentException("用户登录时,没有获得密码!");
        }
        String code = JsonObjectUtils.getStringProperty(param, "code");
        if (code == null) {
            throw new IllegalArgumentException("用户登录时,没有获得租户编号!");
        }

        // 验证登录
        ValidateResult result = userService.validate(username, password, code);
        LoginErrorCode loginCode = result.getCode();
        if (loginCode == LoginErrorCode.SUCCESS) {
            // 登录成功

            // 获得员工信息
            UserVo userVo = userService.findById(result.getUserId());
            String empId = userVo.getEmployeeId();
            if (StringUtils.isEmpty(empId)) {
                throw new RuntimeException("当前登录用户[" + username + "]没有关联员工!请与管理员联系!");
            }
            String employeeName = userVo.getEmployeeName();
            // 设置登录信息到session以及上下文中
            HttpSession session = request.getSession();
            session.setAttribute(LoginInfo.HAS_LOGIN, true);
            session.setAttribute(LoginInfo.TENEMENT, result.getTenementId());
            session.setAttribute(LoginInfo.USER, empId);
            session.setAttribute(LoginInfo.USERNAME, username);
            session.setAttribute(LoginInfo.EMPLOYEE, empId);
            session.setAttribute(LoginInfo.EMPLOYEE_NAME, employeeName);
            session.setAttribute(LoginInfo.LOGIN_DATETIME, new Date().getTime());
            SecurityContext.set(empId, username, "1");
            SecurityContext.setEmpId(empId);
            SecurityContext.setEmpName(employeeName);


            // 加入缓存
            SystemContainer systemContainer = SystemContainer.getInstance();
            RedisServer redisServer = systemContainer.getBean(RedisServer.class);

            ShardedJedis redisClient = redisServer.getRedisClient();
            // 生成登录token
            String token = RandomUtils.generate(6);
            session.setAttribute("token", token);
            redisClient.hset("login:", token, empId);

            // 保存用户信息
            redisClient.del("user:" + empId);
            redisClient.hset("user:" + empId, empId, String.valueOf(new Date().getTime()));

            // 保存操作授权：PF,empId,资源编号的json
            AccreditFuncService funcService = systemContainer.getBean(AccreditFuncService.class);
            if (funcService == null) {
                throw new RuntimeException("无法获得" + AccreditFuncService.class.getName() + "的实例对象，无法查询个人权限!");
            }
            redisClient.del("PF:" + empId);
            List<String> resourceCodes = funcService.queryPersonalResourceCode();
            if (resourceCodes != null && !resourceCodes.isEmpty()) {
                redisClient.sadd("PF:" + empId, resourceCodes.toArray(new String[resourceCodes.size()]));
            }

            // 保存数据授权：PD:empId,资源编号,授权明细
            redisClient.del("PD:" + empId);
            AccreditDataService ads = systemContainer.getBean(AccreditDataService.class);
            if (ads != null) {
                List<AccreditData> accreditData = ads.queryPersonalAllDataResource(empId);
                // key为数据资源的编号，value为数据资源对应的授权信息
                Map<String, List<AccreditData>> accreditDataMap = new HashMap<String, List<AccreditData>>();
                if (accreditData != null && !accreditData.isEmpty()) {
                    for (AccreditData foo : accreditData) {
                        String rc = foo.getResourceCode();
                        List<AccreditData> tmp = accreditDataMap.get(rc);
                        if (tmp == null) {
                            tmp = new ArrayList<AccreditData>();
                            accreditDataMap.put(rc, tmp);
                        }
                        tmp.add(foo);
                    }
                }
                // 将授权信息缓存到redis中
                for (Map.Entry<String, List<AccreditData>> entry : accreditDataMap.entrySet()) {
                    String key = entry.getKey();
                    String value = GsonUtils.toJson(entry.getValue());
                    redisClient.hset("PD:" + empId, key, value);
                }
            }
            redisClient.close();
            // 加载角色的全部菜单和权限
            // 查询登录用户权限
            LoginSuccessEvent event = systemContainer.getBean(LoginSuccessEvent.class);
            if (event != null) {
                event.execute(request, userVo);
            }

            //写入Cookie
            response.addCookie(new Cookie("eccrmContext.id", empId));
            try {
                // 员工名称
                response.addCookie(new Cookie("eccrmContext.employeeName", URLEncoder.encode(URLEncoder.encode(employeeName, "utf-8"), "utf-8")));
                // 用户名称
                response.addCookie(new Cookie("eccrmContext.username", URLEncoder.encode(URLEncoder.encode(username, "utf-8"), "utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            GsonUtils.printJson(response, "success", true);
        } else {
            // 登录失败，写入失败信息
            int errorCode = loginCode.getValue();
            JsonObject json = new JsonObject();
            json.addProperty("code", errorCode);
            json.addProperty("fail", loginCode.getMessage());
            GsonUtils.printJsonObject(response, json);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:/index.html";
    }
}
