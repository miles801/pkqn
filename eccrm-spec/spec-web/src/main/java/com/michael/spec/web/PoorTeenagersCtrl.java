package com.michael.spec.web;

import com.ycrl.core.web.BaseController;
import com.ycrl.base.common.JspAccessType;
import com.ycrl.core.pager.PageVo;
import com.ycrl.utils.gson.GsonUtils;
import com.michael.spec.bo.PoorTeenagersBo;
import com.michael.spec.domain.PoorTeenagers;
import com.michael.spec.service.PoorTeenagersService;
import com.michael.spec.vo.PoorTeenagersVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Michael
 */
@Controller
@RequestMapping(value = {"/spec/poorTeenagers"})
public class PoorTeenagersCtrl extends BaseController {
    @Resource
    private PoorTeenagersService poorTeenagersService;
    @RequestMapping(value = {""}, method=RequestMethod.GET )
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

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        poorTeenagersService.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

}
