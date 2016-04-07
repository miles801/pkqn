package eccrm.base.employee.service.impl;

import com.michael.poi.adapter.AnnotationCfgAdapter;
import com.michael.poi.core.Handler;
import com.michael.poi.core.ImportEngine;
import com.michael.poi.core.RuntimeContext;
import com.michael.poi.imp.cfg.Configuration;
import com.ycrl.core.SystemContainer;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import com.ycrl.core.pager.Pager;
import com.ycrl.utils.md5.MD5Utils;
import com.ycrl.utils.string.StringUtils;
import com.ycrl.utils.uuid.UUIDGenerator;
import eccrm.base.attachment.AttachmentProvider;
import eccrm.base.attachment.utils.AttachmentHolder;
import eccrm.base.attachment.vo.AttachmentVo;
import eccrm.base.employee.bo.EmployeeBo;
import eccrm.base.employee.dao.EmployeeDao;
import eccrm.base.employee.domain.Employee;
import eccrm.base.employee.service.ContactType;
import eccrm.base.employee.service.EmployeeOrgRelService;
import eccrm.base.employee.service.EmployeeService;
import eccrm.base.employee.vo.EmployeeVo;
import eccrm.base.org.dao.OrganizationDao;
import eccrm.base.org.domain.Organization;
import eccrm.base.parameter.dao.BusinessParamItemDao;
import eccrm.base.parameter.service.ParameterContainer;
import eccrm.base.position.dao.PositionDao;
import eccrm.base.position.dao.PositionEmpDao;
import eccrm.base.position.domain.Position;
import eccrm.base.position.domain.PositionEmp;
import eccrm.base.position.service.PositionEmpService;
import eccrm.base.user.dao.UserDao;
import eccrm.base.user.domain.User;
import eccrm.base.user.enums.UserStatus;
import eccrm.base.user.enums.UserType;
import eccrm.utils.BeanCopyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Generated by yanhx on 2014-10-13.
 */

@Service("employeeServices")
public class EmployeeServiceImpl implements EmployeeService, BeanWrapCallback<Employee, EmployeeVo> {
    @Resource
    private EmployeeDao employeesDao;
    @Resource
    private PositionEmpDao positionEmpDao;
    @Resource
    private EmployeeOrgRelService employeeOrgRelService;
    @Resource
    private PositionEmpService positionEmpService;
    @Resource
    private PositionDao positionDao;

    @Override
    public String save(Employee employee) {

        // 设置年龄
        setAge(employee);
        String positionCode = employee.getPositionCode();
        if (Employee.POSITION_MEMBER.equals(positionCode) || Employee.POSITION_MEMBER_TEMP.equals(positionCode)) {
            Assert.isTrue(employee.getAge() > 11 && employee.getAge() < 29, "年龄超出范围!");
        }

        // 设置岗位信息
        if (StringUtils.isNotEmpty(positionCode)) {
            PositionDao positionDao = SystemContainer.getInstance().getBean(PositionDao.class);
            List<Position> positions = positionDao.findByCode(positionCode);
            if (positions != null && !positions.isEmpty()) {
                employee.setPositionId(positions.get(0).getId());
                employee.setPositionName(positions.get(0).getName());
            }
        }

        String id = employeesDao.save(employee);
        // 保存关联关系
        String positionId = employee.getPositionId();
        String orgId = employee.getOrgId();
        if (!StringUtils.hasEmpty(positionId, orgId)) {
            PositionEmp pe = new PositionEmp();
            pe.setPositionId(positionId);
            pe.setOrgId(orgId);
            pe.setEmpId(id);
            positionEmpDao.save(pe);
        }

        String employeeCode = employee.getEmployeeCode();
        if (StringUtils.isNotEmpty(employeeCode)) {
            UserDao userDao = SystemContainer.getInstance().getBean(UserDao.class);
            User user = new User();
            user.setPassword(MD5Utils.encode("111111"));
            user.setUsername(employeeCode);
            user.setType(UserType.OFFICIAL.getValue());
            user.setStatus(UserStatus.ACTIVE.getValue());
            user.setEmployeeId(id);
            user.setEmployeeName(employee.getEmployeeName());
            ValidatorUtils.validate(user);
            // 验证用户名是否重复
            User oldUser = userDao.findByUsername(user.getUsername());
            Assert.isNull(oldUser, "注册失败：用户名已经存在!");
            userDao.save(user);
        }
        return id;
    }

    private void setAge(Employee employee) {
        Date date = null;
        if (StringUtils.isNotEmpty(employee.getIdNo())) {
            Assert.isTrue(employee.getIdNo().length() == 18, "错误的身份证号码!身份证号码的长度必须是18位!");
            String birthdayStr = employee.getIdNo().substring(6, 14);
            try {
                date = new SimpleDateFormat("yyyyMMdd").parse(birthdayStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (employee.getBirthday() != null) {
            date = employee.getBirthday();
        }
        if (date != null) {
            employee.setBirthday(date);
            int age = Integer.parseInt((System.currentTimeMillis() - date.getTime()) / (1000 * 60 * 60 * 24 * 365l) + "");
            employee.setAge(age);
        }

    }

    @Override
    public void update(Employee employee) {
        // 设置年龄
        setAge(employee);
        String positionCode = employee.getPositionCode();
        if (Employee.POSITION_MEMBER.equals(positionCode) || Employee.POSITION_MEMBER_TEMP.equals(positionCode)) {
            Assert.isTrue(employee.getAge() > 11 && employee.getAge() < 29, "年龄超出范围!");
        }

        // 保存关联关系
        String positionId = employee.getPositionId();
        String orgId = employee.getOrgId();
        if (!StringUtils.hasEmpty(positionId, orgId)) {
            boolean exists = positionEmpDao.exists(orgId, positionId, employee.getId());
            if (!exists) {
                PositionEmp pe = new PositionEmp();
                pe.setPositionId(positionId);
                pe.setOrgId(orgId);
                pe.setEmpId(employee.getId());
                positionEmpDao.save(pe);
            }
        }
        employeesDao.update(employee);
    }

    @Override
    public PageVo query(EmployeeBo bo) {
        PageVo vo = new PageVo();
        Long total = employeesDao.getTotal(bo);
        vo.setTotal(total);
        if (total == 0) return vo;
        List<Employee> employees = employeesDao.query(bo);
        vo.setData(BeanWrapBuilder.newInstance().setCallback(this).wrapList(employees, EmployeeVo.class));
        return vo;
    }

    @Override
    public PageVo queryValid(EmployeeBo bo) {
        PageVo vo = new PageVo();
        if (bo == null) {
            bo = new EmployeeBo();
        }
        bo.setValid(true);
        Long total = employeesDao.getTotal(bo);
        vo.setTotal(total);
        if (total == 0) return vo;
        List<Employee> employees = employeesDao.query(bo);
        vo.setData(BeanWrapBuilder.newInstance().setCallback(this).wrapList(employees, EmployeeVo.class));
        return vo;
    }

    @Override
    public EmployeeVo findById(String id) {
        return BeanWrapBuilder.newInstance().setCallback(this)
                .wrap(employeesDao.findById(id), EmployeeVo.class);
    }


    @Override
    public List<Employee> queryByOrgId(String id) {
        List<Employee> employeeList = employeeOrgRelService.findByOrgId(id);
        return employeeList;
    }

    @Override
    public List<EmployeeVo> queryByRuleId(String id, String orgId) {
        List<EmployeeVo> employeeList = positionEmpService.findByEmployee(id, orgId);
        return employeeList;
    }

    @Override
    public void deleteByIds(String... ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            employeesDao.deleteById(id);
        }
    }


    @Override
    public void doCallback(Employee employee, EmployeeVo vo) {
        ParameterContainer parameterContainer = ParameterContainer.getInstance();
        // 职务
        vo.setDutyName(parameterContainer.getBusinessName(ContactType.BP_ZHIW, vo.getDuty()));
        // 工作类型
        vo.setWorkTypeName(parameterContainer.getBusinessName(ContactType.BP_EMPTYPE, vo.getWorkType()));
        // 状态
        vo.setStatusName(parameterContainer.getSystemName(Employee.STATE, vo.getStatus()));
        // 学历
        vo.setXueliName(parameterContainer.getBusinessName("BP_EDU", vo.getXueli()));
        // 性别
        vo.setGenderName(parameterContainer.getBusinessName("BP_SEX", employee.getGender()));
        // 民族
        vo.setNationName(parameterContainer.getBusinessName("BP_NATION", employee.getNation()));
        // 政治面貌
        vo.setZzmmName(parameterContainer.getBusinessName("BP_ZZMM", employee.getZzmm()));
        // 领域
        vo.setLyName(parameterContainer.getBusinessName("SPEC_LY", employee.getLy()));
        vo.setLy2Name(parameterContainer.getBusinessName("SPEC_LY2", employee.getLy2()));
        // 荣誉称号
        vo.setHonorName(parameterContainer.getBusinessName("SPEC_HONOR", employee.getHonor()));

    }

    @Override
    public boolean isExists(String rtxId) {
        return employeesDao.isExists(rtxId);
    }

    @Override
    public String findNameById(String employeeId) {
        return employeesDao.findNameById(employeeId);
    }

    @Override
    public Employee findById2(String employID) {
        return employeesDao.findById(employID);
    }


    @Override
    public PageVo permissionPageQuery(EmployeeBo bo) {
        if (bo == null) {
            bo = new EmployeeBo();
        }
        bo.setPermission(true);
        return query(bo);
    }

    @Override
    public void importData(String[] attachmentIds) {
        Logger logger = Logger.getLogger(EmployeeServiceImpl.class);
        Assert.notEmpty(attachmentIds, "数据文件不能为空，请重试!");

        for (String id : attachmentIds) {
            AttachmentVo vo = AttachmentProvider.getInfo(id);
            File file = AttachmentHolder.newInstance().getTempFile(id);
            logger.info("准备导入数据：" + file.getAbsolutePath());
            logger.info("初始化导入引擎....");
            long start = System.currentTimeMillis();

            //根据黑名单类型选择对应的DTO
            Configuration configuration = new AnnotationCfgAdapter(EmployeeDTO.class).parse();
            configuration.setStartRow(1);
            String newFilePath = file.getAbsolutePath() + vo.getFileName().substring(vo.getFileName().lastIndexOf(".")); //获取路径
            try {
                FileUtils.copyFile(file, new File(newFilePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            configuration.setPath(newFilePath);
            final BusinessParamItemDao bpiDao = SystemContainer.getInstance().getBean(BusinessParamItemDao.class);
            configuration.setHandler(new Handler<EmployeeDTO>() {
                @Override
                public void execute(EmployeeDTO dto) {
                    String orgName = dto.getOrgName();
                    Assert.hasText(orgName, String.format("数据错误!请指明团员所属的县区,第%d行!", RuntimeContext.get().getRowIndex() + 1));

                    Employee employee = new Employee();
                    employee.setId(UUIDGenerator.generate());
                    // 领域
                    if (StringUtils.isNotEmpty(dto.getLy())) {
                        dto.setLy(bpiDao.queryCode("SPEC_LY", dto.getLy()));
                    }
                    // 民族
                    if (StringUtils.isNotEmpty(dto.getNation())) {
                        dto.setNation(bpiDao.queryCode("BP_NATION", dto.getNation()));
                    }
                    // 政治面貌
                    if (StringUtils.isNotEmpty(dto.getZzmm())) {
                        dto.setZzmm(bpiDao.queryCode("BP_ZZMM", dto.getZzmm()));
                    }
                    // 学历
                    if (StringUtils.isNotEmpty(dto.getXueli())) {
                        dto.setXueli(bpiDao.queryCode("BP_EDU", dto.getXueli()));
                    }
                    // 荣誉称号
                    if (StringUtils.isNotEmpty(dto.getHonor())) {
                        dto.setHonor(bpiDao.queryCode("SPEC_HONOR", dto.getHonor()));
                    }
                    BeanCopyUtils.copyProperties(dto, employee);
                    employee.setPositionCode("TY"); // 导入的都是团员
                    OrganizationDao orgDao = SystemContainer.getInstance().getBean(OrganizationDao.class);
                    List<Organization> orgs = orgDao.findByName(orgName);
                    Assert.notEmpty(orgs, "县区[" + orgName + "]不存在!");
                    employee.setOrgId(orgs.get(0).getId());
                    employee.setStatus("2");
                    save(employee);
                }
            });
            logger.info("开始导入数据....");
            ImportEngine engine = new ImportEngine(configuration);
            engine.execute();
            logger.info(String.format("导入数据成功,用时(%d)s....", (System.currentTimeMillis() - start) / 1000));
            new File(newFilePath).delete();
        }
    }

    @Override
    public int clear() {
        int counts = 0;
        EmployeeBo bo = new EmployeeBo();
        bo.setValid(true);
        long total = employeesDao.getTotal(bo);
        long nums = total / 20 + 1;
        Pager.setLimit(20);
        for (int i = 0; i < nums; i++) {
            Pager.setStart(i * 20);
            List<Employee> employees = employeesDao.query(bo);
            for (Employee employee : employees) {
                setAge(employee);
                if ("TY".equals(employee.getPositionCode()) && (employee.getAge() < 12 || employee.getAge() > 28)) {
                    employee.setPositionCode("FTY");
                    employee.setPositionId(null);
                    employee.setPositionName(null);
                    counts++;
                }
            }
        }
        return counts;
    }

    @Override
    public List<Object[]> memberAnalysis() {
        return employeesDao.memberAnalysis();
    }

    @Override
    public List<Object[]> memberAnalysis2() {
        return employeesDao.memberAnalysis2();
    }

    @Override
    public void apply(String id) {
        Assert.hasText(id, "申请打印团员证失败!ID不能为空!");
        Employee employee = employeesDao.findById(id);
        Assert.notNull(employee, "申请打印团员证失败!该团员已经不存在，请刷新后重试!");
        Assert.isTrue(employee.getPositionCode().equals(Employee.POSITION_MEMBER_TEMP), "申请打印团员证失败!当前用户不是“流动团员”!");
        Assert.isTrue(Employee.STATE_INACTIVE.equals(employee.getStatus()), "申请打印团员证失败!当前用户的状态不能申请!");
        employee.setStatus(Employee.STATE_WAIT);
    }

    @Override
    public void applyDeny(String id) {
        Assert.hasText(id, "审核流动团员失败!ID不能为空!");
        Employee employee = employeesDao.findById(id);
        Assert.notNull(employee, "审核流动团员失败!该团员已经不存在，请刷新后重试!");
        Assert.isTrue(employee.getPositionCode().equals(Employee.POSITION_MEMBER_TEMP), "审核流动团员失败!当前用户不是“流动团员”!");
        Assert.isTrue(Employee.STATE_WAIT.equals(employee.getStatus()), "审核流动团员失败!当前用户的状态不能进行审核!");
        employee.setStatus(Employee.STATE_DENY);
    }

    @Override
    public void applyAccept(String id) {
        Assert.hasText(id, "审核流动团员失败!ID不能为空!");
        Employee employee = employeesDao.findById(id);
        Assert.notNull(employee, "审核流动团员失败!该团员已经不存在，请刷新后重试!");
        Assert.isTrue(employee.getPositionCode().equals(Employee.POSITION_MEMBER_TEMP), "审核流动团员失败!当前用户不是“流动团员”!");
        Assert.isTrue(Employee.STATE_WAIT.equals(employee.getStatus()), "审核流动团员失败!当前用户的状态不能进行审核!");
        employee.setStatus(Employee.STATE_NORMAL);
    }
}
