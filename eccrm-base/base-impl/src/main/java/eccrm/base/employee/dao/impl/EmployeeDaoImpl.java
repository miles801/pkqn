package eccrm.base.employee.dao.impl;

import com.ycrl.core.HibernateDaoHelper;
import com.ycrl.core.exception.Argument;
import com.ycrl.core.hibernate.criteria.CriteriaUtils;
import com.ycrl.core.hibernate.filter.FilterFieldType;
import com.ycrl.utils.string.StringUtils;
import eccrm.base.employee.bo.EmployeeBo;
import eccrm.base.employee.dao.EmployeeDao;
import eccrm.base.employee.domain.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Generated by yanhx on 2014-10-13.
 */

@Repository("employeesDao")
public class EmployeeDaoImpl extends HibernateDaoHelper implements EmployeeDao {
    public static final String FILTER_NAME = "QUERY_EMPLOYEE";

    @Override
    public String save(Employee employee) {
        return (String) getSession().save(employee);
    }

    @Override
    public void update(Employee employee) {
        getSession().update(employee);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> query(EmployeeBo bo) {
        if (bo == null) {
            bo = new EmployeeBo();
        }
        Criteria criteria = getDefaultCriteria(bo);
        return criteria.list();
    }

    @Override
    public long getTotal(EmployeeBo bo) {
        Criteria criteria = null;
        if (bo.getPermission() != null && bo.getPermission()) {
            criteria = createRowCountsCriteria(Employee.class, FILTER_NAME, "orgId", FilterFieldType.ORG);
        } else {
            criteria = createRowCountsCriteria(Employee.class);
        }
        initCriteria(criteria, bo);
        return (Long) criteria.uniqueResult();
    }


    @Override
    public int deleteById(String id) {
        return getSession().createQuery("delete from " + Employee.class.getName() + " e where e.id=?")
                .setParameter(0, id)
                .executeUpdate();
    }

    @Override
    public Employee findById(String id) {
        return (Employee) getSession().get(Employee.class, id);
    }

    @Override
    public Employee findByCode(String id) {
        String sql = "from " + Employee.class.getName() + " e where e.employeeCode='" + id + "'";

        return (Employee) getSession().createQuery(sql).uniqueResult();
    }

    /**
     * 获得默认的org.hibernate.Criteria对象,并根据bo进行初始化（如果bo为null，则会新建一个空对象）
     * 为了防止新的对象中有数据，建议实体/BO均采用封装类型
     */
    private Criteria getDefaultCriteria(EmployeeBo bo) {
        Criteria criteria = null;
        if (bo.getPermission() != null && bo.getPermission()) {
            criteria = createCriteria(Employee.class, FILTER_NAME, "orgId", FilterFieldType.ORG);
        } else {
            criteria = createCriteria(Employee.class);
        }
        initCriteria(criteria, bo);
        return criteria;
    }

    /**
     * 根据BO初始化org.hibernate.Criteria对象
     * 如果org.hibernate.Criteria为null，则抛出异常
     * 如果BO为null，则新建一个空的对象
     */
    private void initCriteria(Criteria criteria, EmployeeBo bo) {
        if (criteria == null) {
            throw new IllegalArgumentException("criteria must not be null!");
        }
        if (bo == null) bo = new EmployeeBo();
        if (bo.getValid() != null && bo.getValid()) {
            // 状态为正式、调动中、实习
            criteria.add(Restrictions.in("status", new String[]{"2", "9", "11"}));
        }
        if (StringUtils.isNotEmpty(bo.getNotPosition())) {
            criteria.add(Restrictions.or(
                    Restrictions.ne("positionCode", bo.getNotPosition()),
                    Restrictions.isNull("positionCode")
            ));
        }
        if (bo.getBirthday1() != null) {
            criteria.add(Restrictions.le("birthday", bo.getBirthday1()));
        }

        CriteriaUtils.addCondition(criteria, bo);
    }

    @Override
    public String findNameById(String employeeId) {
        Argument.isEmpty(employeeId, "根据ID查找员工姓名时,员工ID不能为空!");
        return (String) createCriteria(Employee.class)
                .setProjection(Projections.property("employeeName"))
                .add(Restrictions.idEq(employeeId))
                .uniqueResult();
    }

    @Override
    public boolean isExists(String rtxId) {
        Argument.isEmpty(rtxId, "查询员工是否存在时,rtxId不能为空!");
        long count = (Long) createRowCountsCriteria(Employee.class)
                .add(Restrictions.eq("extensionNumber", rtxId))
                .uniqueResult();
        return count > 0;
    }

    @Override
    public Employee findByRtxId(String rtxId) {
        Argument.isEmpty(rtxId, "查询员工时,rtxId不能为空!");
        return (Employee) createCriteria(Employee.class)
                .add(Restrictions.eq("extensionNumber", rtxId))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public List<Object[]> memberAnalysis() {
        return getSession()
                .createQuery("select e.orgId,e.orgName,count(e.id) from " + Employee.class.getName() + " e where e.positionCode='TY' group by e.orgId,e.orgName")
                .list();
    }

    @Override
    public List<Object[]> memberAnalysis2() {
        return getSession()
                .createQuery("select e.orgId,e.orgName,count(e.id) from " + Employee.class.getName() + " e where e.positionCode='LDTY' group by e.orgId,e.orgName")
                .list();
    }

    @Override
    public List<Object[]> memberAnalysisTotal(Integer year) {
        List<Object[]> result = new ArrayList<Object[]>();

        Map<String, Object[]> keys = new HashMap<String, Object[]>(12);
        keys.put("11", new Object[]{"1", "1", null, null, null, null, null, null, null});
        keys.put("12", new Object[]{"1", "2", null, null, null, null, null, null, null});
        keys.put("13", new Object[]{"1", "3", null, null, null, null, null, null, null});
        keys.put("24", new Object[]{"2", "4", null, null, null, null, null, null, null});
        keys.put("25", new Object[]{"2", "5", null, null, null, null, null, null, null});
        keys.put("26", new Object[]{"2", "6", null, null, null, null, null, null, null});
        keys.put("3", new Object[]{"3", "", null, null, null, null, null, null, null});
        keys.put("4", new Object[]{"4", "", null, null, null, null, null, null, null});
        keys.put("5", new Object[]{"5", "", null, null, null, null, null, null, null});
        keys.put("6", new Object[]{"6", "", null, null, null, null, null, null, null});
        keys.put("7", new Object[]{"7", "", null, null, null, null, null, null, null});
        keys.put("8", new Object[]{"8", "", null, null, null, null, null, null, null});


        // 查询各个团组织的数量(3-5)
        List<Object> data = getSession().createSQLQuery("select t.ly,t.ly2,tzz,count(t.tzz_name) from (select ly, ly2,tzz,tzz_name" +
                " from sys_emp where tzz is not null and tzz <> '' and ly is not null group by tzz,tzz_name,ly,ly2) t group by t.ly,t.ly2,t.tzz").list();
        if (data != null && data.size() > 0) {
            for (Object o : data) {
                Object arr[] = (Object[]) o;
                String key = arr[0].toString() + (arr[1] == null ? "" : arr[1]);
                Object[] value = keys.get(key);
                String tzz = arr[2].toString();
                Object tzzCount = arr[3];
                if ("1".equals(tzz)) {
                    value[2] = tzzCount;
                } else if ("2".equals(tzz)) {
                    value[3] = tzzCount;
                } else if ("3".equals(tzz)) {
                    value[4] = tzzCount;
                } else if ("4".equals(tzz)) {
                    value[5] = tzzCount;
                }

            }
        }


        // 查询团员数量（6）
        if (year != null) {
            data = getSession().createSQLQuery("select ly ,ly2 ,count(position_code) from sys_emp where position_code ='TY' and year(start_work_date)=" + year + " and ly is not null group by ly,ly2").list();
        } else {
            data = getSession().createSQLQuery("select ly ,ly2 ,count(position_code) from sys_emp where position_code ='TY' and ly is not null group by ly,ly2").list();
        }

        if (data != null && data.size() > 0) {
            for (Object o : data) {
                Object arr[] = (Object[]) o;
                String key = arr[0].toString() + (arr[1] == null ? "" : arr[1]);
                Object[] value = keys.get(key);
                value[6] = arr[2];
            }
        }

        // 干部
        data = getSession().createSQLQuery("select ly ,ly2 ,sum(ZZTGB_COUNTS),sum(JZTGB_COUNTS) from sys_emp where position_code ='NORMAL_MANAGER' and ly is not null group by ly,ly2").list();
        if (data != null && data.size() > 0) {
            for (Object o : data) {
                Object arr[] = (Object[]) o;
                String key = arr[0].toString() + (arr[1] == null ? "" : arr[1]);
                Object[] value = keys.get(key);
                value[7] = arr[2];
                value[8] = arr[3];
            }
        }
        result.addAll(keys.values());
        return result;
    }

    @Override
    public List<Object[]> memberAnalysisSex() {
        return getSession().createSQLQuery("select sex,count(id) from sys_emp where position_code='TY' group by sex")
                .list();
    }

    @Override
    public List<Object[]> memberAnalysisLY() {
        return getSession().createSQLQuery("select ly,count(id) from sys_emp where position_code='TY' group by ly")
                .list();
    }

    @Override
    public List<Object[]> memberAnalysisAge() {
        return getSession().createSQLQuery("select " +
                "(select count(id) from sys_emp where position_code='TY' AND age BETWEEN 14 AND 18 ) as age1," +
                "(select count(id) from sys_emp where position_code='TY' AND age BETWEEN 19 AND 23 ) as age2," +
                "(select count(id) from sys_emp where position_code='TY' AND age BETWEEN 24 AND 28 ) as age3," +
                "(select count(id) from sys_emp where position_code='TY' AND age > 28 ) as age4 " +
                " from sys_emp limit 1")
                .list();
    }
}