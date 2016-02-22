package com.michael.spec.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.michael.poi.exp.ExportEngine;
import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.service.PoorTeenagersService;
import com.michael.spec.vo.PoorTeenagersVo;
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
@RequestMapping(value = {"/spec/poorTeenagers"})
public class PoorTeenagersCtrl extends BaseController {
    @Resource
    private PoorTeenagersService poorTeenagersService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "spec/poorTeenagers/list/poorTeenagers_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "spec/poorTeenagers/edit/poorTeenagers_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        PoorTeenagers poorTeenagers = GsonUtils.wrapDataToEntity(request, PoorTeenagers.class);
        poorTeenagersService.save(poorTeenagers);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "spec/poorTeenagers/edit/poorTeenagers_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        PoorTeenagers poorTeenagers = GsonUtils.wrapDataToEntity(request, PoorTeenagers.class);
        poorTeenagersService.update(poorTeenagers);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "spec/poorTeenagers/edit/poorTeenagers_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        PoorTeenagersVo vo = poorTeenagersService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        PoorTeenagersBo bo = GsonUtils.wrapDataToEntity(request, PoorTeenagersBo.class);
        PageVo pageVo = poorTeenagersService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response) {
        PoorTeenagersBo bo = GsonUtils.wrapDataToEntity(request, PoorTeenagersBo.class);
        PageVo pageVo = poorTeenagersService.pageQuery(bo);
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
            disposition = "attachment;filename=" + URLEncoder.encode("贫困青年数据.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", disposition);
        try {
            new ExportEngine().export(response.getOutputStream(), this.getClass().getClassLoader().getResourceAsStream("poorTeenagers.xlsx"), o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        poorTeenagersService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
