package eccrm.base.employee.service.impl;

import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.pager.PageVo;
import eccrm.base.employee.bo.BlankListBo;
import eccrm.base.employee.dao.BlankListDao;
import eccrm.base.employee.domain.BlankList;
import eccrm.base.employee.domain.Employee;
import eccrm.base.employee.service.BlankListService;
import eccrm.base.employee.service.BlankListType;
import eccrm.base.employee.vo.BlankListVo;
import eccrm.base.employee.vo.EmployeeVo;
import eccrm.base.org.domain.Organization;
import eccrm.base.parameter.service.ParameterContainer;
import eccrm.base.position.service.PositionService;
import eccrm.base.position.vo.PositionVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Generated by liuxq on 2014-10-22.
 */

@Service("blankListService")
public class BlankListServiceImpl implements BlankListService, BeanWrapCallback<BlankList, BlankListVo> {
    @Resource
    private BlankListDao blankListDao;
    ParameterContainer parameterContainer = ParameterContainer.getInstance();
    @Resource
    private PositionService positionService;
    private Logger logger = Logger.getLogger(BlankListServiceImpl.class);

    @Override
    public String save(BlankList blankList) {
        String id = blankListDao.save(blankList);
        return id;
    }

    @Override
    public void update(BlankList blankList) {
        blankListDao.update(blankList);
    }

    @Override
    public PageVo query(BlankListBo bo) {
        PageVo vo = new PageVo();
        Long total = blankListDao.getTotal(bo);
        vo.setTotal(total);
        if (total == 0) return vo;
        List<BlankList> blankLists = blankListDao.query(bo);
        vo.setData(BeanWrapBuilder.newInstance()
                .setCallback(this)
                .wrapList(blankLists, BlankListVo.class));
        return vo;
    }

    @Override
    public BlankListVo findById(String id) {
        return wrap(blankListDao.findById(id));
    }


    @Override
    public void deleteByIds(String... ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            blankListDao.deleteById(id);
        }
    }

    @Override
    public Boolean isBlankList(String address) {
        //arrAddress[0]区号  arrAddress[1]号码 arrAddress[2]分机号
        /*String[] arrAddress = address.split("-");
        if(StringUtils.isBlank(arrAddress[1])){
            throw  new NullParamException("号码不能为空!");
        }
        BlankListBo bo = new BlankListBo();
        if(arrAddress[1].contains("+86")){
            bo.setAddress(arrAddress[1].replace("+86", ""));
        }else{
            bo.setAddress(arrAddress[1]);
        }
        //区号 arrAddress[0].length()>2,因为区号一般会大于2，加长度判断是为了防止区号值为""
        if(!StringUtils.isBlank(arrAddress[0]) && arrAddress[0].length()>2){
            bo.setPreAddr(arrAddress[0]);
        }
        //座机号处理
        Integer len = arrAddress[1].length()+arrAddress[0].length()+2;
        logger.info(address.substring(len, address.length()) + "====获取分机号");
        String aftAdd = address.substring(len, address.length()) ;
        if(!StringUtils.isBlank(aftAdd) && aftAdd.length()>2){
            bo.setAftAddr(aftAdd);
        }
        List<BlankList> list = blankListDao.query(bo);
        if(list.size()>0){
            return true;
        }else{
            return false;
        }*/
        return false;
    }

    public BlankListVo wrap(BlankList blankList) {
        if (blankList == null) return null;
        BlankListVo vo = new BlankListVo();
        BeanUtils.copyProperties(blankList, vo);
        if (!StringUtils.isBlank(blankList.getDutyId())) {
            PositionVo positionVo = positionService.findById(blankList.getDutyId());
            vo.setDutyName(positionVo.getName());
        }
        return vo;
    }

    @Override
    public void doCallback(BlankList blankList, BlankListVo blankListVo) {
        if (!StringUtils.isBlank(blankList.getDutyId())) {
            PositionVo positionVo = positionService.findById(blankList.getDutyId());
            if (positionVo != null) {
                blankListVo.setDutyName(positionVo.getName());
            }
        }
        if (!StringUtils.isBlank(blankList.getBusMatter())) {
            blankListVo.setBusMatterName(parameterContainer.getBusinessName(BlankListType.BUSINESS_MATTER, blankList.getBusMatter()));
        }
        if (!StringUtils.isBlank(blankList.getStatus())) {
            blankListVo.setStatusName(parameterContainer.getSystemName(BlankListType.STATUS, blankList.getStatus()));
        }
    }

    @Override
    public List<EmployeeVo> queryEmployees(BlankListBo bo) {
        List<Employee> employees = blankListDao.queryEmployees(bo);
        return wrapEmployeeToVo(employees);
    }

    private List<EmployeeVo> wrapEmployeeToVo(List<Employee> employees) {
        return BeanWrapBuilder.newInstance()
                .addProperties(new String[]{"id", "employeeName", "employeeCode", "mobile", "duty"})
                .setCallback(new BeanWrapCallback<Employee, EmployeeVo>() {
                    @Override
                    public void doCallback(Employee emp, EmployeeVo vo) {
                        // 设置组织机构
                        List<Organization> organizations = blankListDao.queryBlankListEmpDept(emp.getId());
                        if (organizations != null && !organizations.isEmpty()) {
                            Organization org = organizations.get(0);
                            vo.setOrganizationId(org.getId());
                            vo.setOrganizationName(org.getName());
                        }
                        // 职务
                        if (org.apache.commons.lang3.StringUtils.isNotEmpty(emp.getDuty())) {
                            vo.setDutyName(ParameterContainer.getInstance().getBusinessName(Employee.PARAM_DUTY, emp.getDuty()));
                        }
                    }
                })
                .wrapList(employees, EmployeeVo.class);
    }

    @Override
    public PageVo pageQueryEmployees(BlankListBo bo) {
        long total = blankListDao.getEmployeesCount(bo);
        PageVo vo = new PageVo();
        vo.setTotal(total);
        if (total == 0) {
            return vo;
        }
        List<Employee> employees = blankListDao.queryEmployees(bo);
        List<EmployeeVo> employeeVos = wrapEmployeeToVo(employees);
        vo.setData(employeeVos);
        return vo;
    }
}
