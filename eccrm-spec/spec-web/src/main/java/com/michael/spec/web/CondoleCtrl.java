package com.michael.spec.web;

import com.michael.spec.bo.CondoleBo;
import com.michael.spec.domain.Condole;
import com.michael.spec.service.CondoleService;
import com.michael.spec.vo.CondoleVo;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.GsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/spec/condole"})
public class CondoleCtrl extends BaseController {
    @Resource
    private CondoleService condoleService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "spec/condole/list/condole_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.ADD);
        return "spec/condole/edit/condole_edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Condole condole = GsonUtils.wrapDataToEntity(request, Condole.class);
        condoleService.save(condole);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = "/modify", params = {"id"}, method = RequestMethod.GET)
    public String toModify(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.MODIFY);
        request.setAttribute("id", id);
        return "spec/condole/edit/condole_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Condole condole = GsonUtils.wrapDataToEntity(request, Condole.class);
        condoleService.update(condole);
        GsonUtils.printSuccess(response);
    }

    @RequestMapping(value = {"/detail"}, params = {"id"}, method = RequestMethod.GET)
    public String toDetail(@RequestParam String id, HttpServletRequest request) {
        request.setAttribute(JspAccessType.PAGE_TYPE, JspAccessType.DETAIL);
        request.setAttribute("id", id);
        return "spec/condole/edit/condole_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get", params = {"id"}, method = RequestMethod.GET)
    public void findById(@RequestParam String id, HttpServletResponse response) {
        CondoleVo vo = condoleService.findById(id);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/pageQuery", method = RequestMethod.POST)
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) {
        CondoleBo bo = GsonUtils.wrapDataToEntity(request, CondoleBo.class);
        PageVo pageVo = condoleService.pageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    @ResponseBody
    @RequestMapping(value = "/queryByTeenager", params = "teenagerId", method = RequestMethod.GET)
    public void pageQuery(@RequestParam String teenagerId, HttpServletResponse response) {
        List<CondoleVo> vos = condoleService.queryByTeenager(teenagerId);
        GsonUtils.printData(response, vos);
    }
    @ResponseBody
    @RequestMapping(value = "/analysis", params = "year", method = RequestMethod.GET)
    public void analysis(@RequestParam int year, HttpServletResponse response) {
        List<Object[]> vos = condoleService.analysisCondole(year);
        GsonUtils.printData(response, vos);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        condoleService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
