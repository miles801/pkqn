package com.michael.spec.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michael.poi.exp.ExportEngine;
import com.michael.spec.bo.YouthBo;
import com.michael.spec.domain.Youth;
import com.michael.spec.domain.YouthRelation;
import com.michael.spec.service.YouthService;
import com.michael.spec.vo.YouthVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.DateStringConverter;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.attachment.AttachmentProvider;
import eccrm.core.security.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/spec/youth"})
public class YouthCtrl extends BaseController {
    @Resource
    private YouthService youthService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "spec/youth/list/youth_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "spec/youth/edit/youth_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Youth youth = GsonUtils.wrapDataToEntity(request, Youth.class);
        youthService.save(youth);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "spec/youth/edit/youth_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Youth youth = GsonUtils.wrapDataToEntity(request, Youth.class);
        youthService.update(youth);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "spec/youth/edit/youth_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        YouthVo vo = youthService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        YouthBo bo = GsonUtils.wrapDataToEntity(request, YouthBo.class);
        PageVo pageVo = youthService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/hasMatched", params = "ownerId", method = RequestMethod.GET)
    public void hasMatched(@RequestParam String ownerId, HttpServletResponse response) {
        boolean matched = youthService.hasMatched(ownerId);
        GsonUtils.printData(response, matched);
    }

    @ResponseBody
    @RequestMapping(value = "/clearOwner", params = "id", method = RequestMethod.POST)
    public void clearOwner(@RequestParam String id, HttpServletResponse response) {
        youthService.clearOwner(id);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/matchOwner", method = RequestMethod.POST)
    public void matchOwner(HttpServletRequest request, HttpServletResponse response) {
        Youth youth = GsonUtils.wrapDataToEntity(request, Youth.class);
        Assert.notNull(youth, "数据不能为空!");
        youthService.match(youth.getId(), youth.getOwnerId(), youth.getOwnerName());
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        youthService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        YouthBo bo = GsonUtils.wrapDataToEntity(request, YouthBo.class);
        PageVo pageVo = youthService.pageQuery(bo);
        List data = pageVo.getData();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringConverter())
                .create();
        String json = gson.toJson(data);

        JsonElement element = gson.fromJson(json, JsonElement.class);
        JsonObject o = new JsonObject();
        o.add("c", element);
        o.addProperty("orgName", (String) request.getSession().getAttribute(LoginInfo.ORG_NAME));
        String disposition = null;//
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("闲散青年数据.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        try {
            new ExportEngine().export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("youth.xlsx"), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/exportInfo", params = "id", method = RequestMethod.GET)
    public void exportInfo(@RequestParam String id, HttpServletResponse response) {
        YouthVo vo = youthService.findById(id);
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringConverter("yyyy年MM月dd日"))
                .create();
        JsonObject o = new JsonObject();
        o.addProperty("name", vo.getName());
        o.addProperty("sexName", vo.getSexName());
        o.addProperty("educationName", vo.getEducationName());
        o.addProperty("nationName", vo.getNationName());
        o.addProperty("mobile", vo.getMobile());
        o.addProperty("tel", vo.getTel());
        o.addProperty("qq", vo.getQq());
        o.addProperty("email", vo.getEmail());
        o.addProperty("orgName", vo.getOrgName());
        o.addProperty("address", vo.getAddress());
        o.addProperty("content", vo.getContent());
        o.addProperty("birthday", new SimpleDateFormat("yyyy.MM.dd").format(vo.getBirthday()));
        String picture = vo.getPicture();
        boolean hasFile = AttachmentProvider.getFile(picture) != null;
        if (picture != null && hasFile) {
            o.addProperty("picture", AttachmentProvider.getFile(picture).getAbsolutePath());
        }

        // 加载家庭成员关系
        List<YouthRelation> relations = vo.getRelations();
        if (relations == null) {
            relations = new ArrayList<YouthRelation>();
        }
        JsonElement element = gson.fromJson(gson.toJson(relations), JsonElement.class);
        o.add("c", element);
        String disposition = null;//
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("闲散青年个人信息-" + vo.getName() + ".xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        try {
            new ExportEngine().export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("youthInfo.xlsx"), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ResponseBody
    @RequestMapping(value = "/confirmSuccess", params = {"youthId"}, method = RequestMethod.POST)
    public void confirmSuccess(@RequestParam String youthId, HttpServletResponse response) {
        youthService.confirmSuccess(youthId);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/confirmFail", params = {"youthId"}, method = RequestMethod.POST)
    public void confirmFail(@RequestParam String youthId, HttpServletResponse response) {
        youthService.confirmFail(youthId);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/success", params = {"youthId"}, method = RequestMethod.POST)
    public void success(@RequestParam String youthId, HttpServletResponse response) {
        youthService.success(youthId);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/fail", params = {"youthId"}, method = RequestMethod.POST)
    public void fail(@RequestParam String youthId, HttpServletResponse response) {
        youthService.fail(youthId);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/back", method = RequestMethod.POST)
    public void back(HttpServletRequest request, HttpServletResponse response) {
        Youth youth = GsonUtils.wrapDataToEntity(request, Youth.class);
        Assert.notNull(youth, "操作失败!数据不能为空!");
        youthService.back(youth.getId(), youth.getReason());
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public void analysis(HttpServletRequest request, HttpServletResponse response) {
        List<Object[]> data = youthService.analysis();
        GsonUtils.printData(response, data);
    }
}
