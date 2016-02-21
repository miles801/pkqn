package eccrm.login;

import eccrm.base.user.service.LoginSuccessEvent;
import eccrm.base.user.vo.UserVo;
import eccrm.utils.NetUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Michael
 */
@Service("loginSuccessEvent")
public class LoginSuccessEventImpl implements LoginSuccessEvent {
    private Logger logger = Logger.getLogger(LoginSuccessEventImpl.class);

    @Override
    public void execute(HttpServletRequest request, UserVo userVo) {

        String employeeName = userVo.getEmployeeName();
        // 设置在线用户数
        String ip = NetUtils.getClientIpAddress(request);
        logger.info("[" + ip + ":" + userVo.getUsername() + "(" + employeeName + ")] login success!");

    }
}
