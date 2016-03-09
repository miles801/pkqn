package com.michael.spec.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.michael.poi.exp.ExportEngine;
import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.domain.YouthHelp;
import com.michael.spec.service.YouthHelpService;
import com.michael.spec.vo.YouthHelpVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.DateStringConverter;
import com.ycrl.utils.gson.GsonUtils;
import eccrm.core.security.LoginInfo;
import org.springframework.stereotype.Controller;
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
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/spec/youth/help"})
public class YouthHelpCtrl extends BaseController {
    @Resource
    private YouthHelpService youthHelpService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "spec/youth/list/youthHelp_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "spec/youth/edit/youthHelp_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        YouthHelp youthHelp = GsonUtils.wrapDataToEntity(request, YouthHelp.class);
        youthHelpService.save(youthHelp);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "spec/youth/edit/youthHelp_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        YouthHelp youthHelp = GsonUtils.wrapDataToEntity(request, YouthHelp.class);
        youthHelpService.update(youthHelp);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "spec/youth/edit/youthHelp_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        YouthHelpVo vo = youthHelpService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        YouthHelpBo bo = GsonUtils.wrapDataToEntity(request, YouthHelpBo.class);
        PageVo pageVo = youthHelpService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/queryByYouth", params = "youthId", method = RequestMethod.GET)
    public void queryByYouth(@RequestParam String youthId, HttpServletResponse response) {
        YouthHelpBo bo = new YouthHelpBo();
        bo.setYouthId(youthId);
        PageVo pageVo = youthHelpService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        youthHelpService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }


    @RequestMapping(value = "/exportInfo", params = {"year", "month"}, method = RequestMethod.GET)
    public void exportInfo(HttpServletRequest request,
                           @RequestParam int year,
                           @RequestParam int month, HttpServletResponse response) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringConverter("yyyy年MM月dd日"))
                .create();
        JsonObject o = new JsonObject();
        o.addProperty("orgName", (String) request.getSession().getAttribute(LoginInfo.ORG_NAME));
        String title = "结对帮扶社会闲散青少年工作进度月报表(" + year + "年" + month + "月)";
        o.addProperty("title", title);
        List<Object[]> data = youthHelpService.workMonthReport(year, month);
        JsonArray arr = new JsonArray();
        for (Object[] obj : data) {
            JsonObject jo = new JsonObject();
            jo.addProperty("ownerName", (String) obj[0]);
            jo.addProperty("ownerDuty", (String) obj[1]);
            jo.addProperty("name", (String) obj[2]);
            jo.addProperty("sex", (String) obj[3]);
            jo.addProperty("age", (Integer) obj[4]);
            jo.addProperty("mobile", (String) obj[5]);
            jo.addProperty("title", (String) obj[6]);
            jo.addProperty("continue", (String) obj[7]);
            arr.add(jo);
        }
        o.add("c", arr);
        String disposition = null;//
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("结对帮扶社会闲散青少年工作进度月报表-" + year + "年" + month + "月.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        try {
            new ExportEngine().export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("youth-help.xlsx"), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/workReport", params = {"year", "month"}, method = RequestMethod.GET)
    public void exportInfo(@RequestParam int year,
                           @RequestParam int month, HttpServletResponse response) {
        List<Object[]> data = youthHelpService.workMonthReport(year, month);
        GsonUtils.printData(response, data);

    }

}
