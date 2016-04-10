package eccrm.base.employee.web;

import com.ycrl.core.pager.PageVo;
import com.ycrl.core.web.BaseController;
import com.ycrl.utils.gson.GsonUtils;
import com.ycrl.utils.string.StringUtils;
import eccrm.base.employee.bo.EmployeeBo;
import eccrm.base.employee.domain.Employee;
import eccrm.base.employee.service.EmployeeService;
import eccrm.base.employee.vo.EmployeeVo;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Generated by yanhx on 2014-10-13.
 */

@Controller
@RequestMapping(value = {"/base/employee"})
public class EmployeeCtrl extends BaseController {
    @Resource
    private EmployeeService employeeServices;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String toList() {
        return "employee/list/employee_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(HttpServletRequest request) {
        request.setAttribute("pageType", "add");
        return "employee/edit/employee_edit";
    }

    /**
     * 员工信息页面（用于仪表盘显示）
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String toInfo() {
        return "employee/list/employee";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public void save(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = GsonUtils.wrapDataToEntity(request, Employee.class);
        employeeServices.save(employee);
        GsonUtils.printSuccess(response);
    }


    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String toModify(@PathVariable String id, HttpServletRequest request) {
        request.setAttribute("pageType", "modify");
        request.setAttribute("id", id);
        return "employee/edit/employee_edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public void update(HttpServletRequest request, HttpServletResponse response) {
        Employee employee = GsonUtils.wrapDataToEntity(request, Employee.class);
        employeeServices.update(employee);
        GsonUtils.printSuccess(response);
    }


    @RequestMapping(value = {"/detail/{id}"}, method = RequestMethod.GET)
    public String toDetail(@PathVariable String id, HttpServletRequest request) {
        request.setAttribute("pageType", "detail");
        request.setAttribute("id", id);
        return "employee/edit/employee_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public void findById(@PathVariable String id, HttpServletResponse response) {
        EmployeeVo vo = employeeServices.findById(id);
        GsonUtils.printData(response, vo);
    }


    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public void query(HttpServletRequest request, HttpServletResponse response) {
        EmployeeBo bo = GsonUtils.wrapDataToEntity(request, EmployeeBo.class);
        PageVo pageVo = employeeServices.query(bo);
        GsonUtils.printData(response, pageVo);
    }

    /**
     * 带权限的高级分页查询
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/permissionPageQuery", method = RequestMethod.POST)
    public void permissionPageQuery(HttpServletRequest request, HttpServletResponse response) {
        EmployeeBo bo = GsonUtils.wrapDataToEntity(request, EmployeeBo.class);
        PageVo pageVo = employeeServices.permissionPageQuery(bo);
        GsonUtils.printData(response, pageVo);
    }

    /**
     * 只查询员工状态为（正式、调动中、实习）的数据
     */
    @ResponseBody
    @RequestMapping(value = "/queryValid", method = RequestMethod.POST)
    public void queryValid(HttpServletRequest request, HttpServletResponse response) {
        EmployeeBo bo = GsonUtils.wrapDataToEntity(request, EmployeeBo.class);
        PageVo pageVo = employeeServices.queryValid(bo);
        GsonUtils.printData(response, pageVo);
    }

    //判断指定名称是否存在，返回{exists:true/false}
    @ResponseBody
    @RequestMapping(value = "/exists", params = {"extensionNumber"}, method = RequestMethod.GET)
    public void hasExists(@RequestParam String extensionNumber, HttpServletRequest request, HttpServletResponse response) {
        String decodedName = StringUtils.decodeByUTF8(extensionNumber);
        GsonUtils.printData(response, employeeServices.isExists(decodedName));
    }

    @ResponseBody
    @RequestMapping(value = "/queryByOrgId", params = {"id"}, method = RequestMethod.GET)
    public void queryByOrgId(@RequestParam String id, HttpServletResponse response) {
        List<Employee> employeeList = employeeServices.queryByOrgId(id);
        PageVo vo = new PageVo();
        vo.setTotal(Long.valueOf(employeeList.size()));
        vo.setData(employeeList);
        GsonUtils.printData(response, vo);
    }

    @ResponseBody
    @RequestMapping(value = "/queryByRuleId", params = {"id", "orgId"}, method = RequestMethod.GET)
    public void queryByRuleId(@RequestParam String id, @RequestParam String orgId, HttpServletResponse response) {
        List<EmployeeVo> employeeList = employeeServices.queryByRuleId(id, orgId);
        GsonUtils.printData(response, employeeList);
    }

    @ResponseBody
    @RequestMapping(value = "/template", method = RequestMethod.GET)
    public void downloadTemplate(HttpServletResponse response) {
        InputStream input = EmployeeCtrl.class.getClassLoader().getResourceAsStream("employee_import.xlsx");
        response.setContentType("application/vnd.ms-excel");
        String disposition = null;//
        try {
            disposition = "attachment;filename=" + URLEncoder.encode("团员数据导入模板.xlsx", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-disposition", disposition);
        try {
            IOUtils.copy(input, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/import", params = {"attachmentIds"}, method = RequestMethod.POST)
    public void importData(@RequestParam String attachmentIds, HttpServletResponse response) {
        employeeServices.importData(attachmentIds.split(","));
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public void clear(HttpServletResponse response) {
        int total = employeeServices.clear();
        GsonUtils.printData(response, total);
    }

    // 申请团员证信息审核
    @ResponseBody
    @RequestMapping(value = "/apply/{id}", method = RequestMethod.POST)
    public void apply(@PathVariable String id, HttpServletResponse response) {
        employeeServices.apply(id);
        GsonUtils.printSuccess(response);
    }

    // 申请通过
    @ResponseBody
    @RequestMapping(value = "/applyAccept/{id}", method = RequestMethod.POST)
    public void applyAccept(@PathVariable String id, HttpServletResponse response) {
        employeeServices.applyAccept(id);
        GsonUtils.printSuccess(response);
    }

    // 审核拒绝
    @ResponseBody
    @RequestMapping(value = "/applyDeny/{id}", method = RequestMethod.POST)
    public void applyDeny(@PathVariable String id, HttpServletResponse response) {
        employeeServices.applyDeny(id);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/delete", params = {"ids"}, method = RequestMethod.DELETE)
    public void deleteByIds(@RequestParam String ids, HttpServletResponse response) {
        String[] idArr = ids.split(",");
        employeeServices.deleteByIds(idArr);
        GsonUtils.printSuccess(response);
    }

    @ResponseBody
    @RequestMapping(value = "/memberAnalysis", method = RequestMethod.GET)
    public void memberAnalysis(HttpServletResponse response) {
        List<Object[]> data = employeeServices.memberAnalysis();
        GsonUtils.printData(response, data);
    }

    // 流动团员统计
    @ResponseBody
    @RequestMapping(value = "/memberAnalysis2", method = RequestMethod.GET)
    public void memberAnalysis2(HttpServletResponse response) {
        List<Object[]> data = employeeServices.memberAnalysis2();
        GsonUtils.printData(response, data);
    }

    //
    @ResponseBody
    @RequestMapping(value = "/memberAnalysisYear", params = "year", method = RequestMethod.GET)
    public void memberAnalysisYear(@RequestParam Integer year, HttpServletResponse response) {
        List<Object[]> data = employeeServices.memberAnalysisTotal(year);
        GsonUtils.printData(response, data);
    }

    //
    @ResponseBody
    @RequestMapping(value = "/memberAnalysisTotal", method = RequestMethod.GET)
    public void memberAnalysisTotal(HttpServletResponse response) {
        List<Object[]> data = employeeServices.memberAnalysisTotal(null);
        GsonUtils.printData(response, data);
    }

}
