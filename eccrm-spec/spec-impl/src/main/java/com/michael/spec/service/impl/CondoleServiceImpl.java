package com.michael.spec.service.impl;

import com.ycrl.core.pager.PageVo;
import com.ycrl.base.common.CommonStatus;
import com.michael.spec.bo.CondoleBo;
import com.michael.spec.domain.Condole;
import com.michael.spec.vo.CondoleVo;
import com.michael.spec.dao.CondoleDao;
import com.michael.spec.service.CondoleService;
import org.springframework.stereotype.Service;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.beans.BeanWrapCallback;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Michael
 */
@Service("condoleService")
public class CondoleServiceImpl implements CondoleService {
    @Resource
    private CondoleDao condoleDao;

    @Override
    public String save(Condole condole) {
        String id = condoleDao.save(condole);
        return id;
    }

    @Override
    public void update(Condole condole) {
        condoleDao.update(condole);
    }

    @Override
    public PageVo pageQuery(CondoleBo bo) {
        PageVo vo = new PageVo();
        Long total = condoleDao.getTotal(bo);
        vo.setTotal(total);
        if (total==null || total == 0) return vo;
        List<Condole> condoleList = condoleDao.query(bo);
        List<CondoleVo> vos = BeanWrapBuilder.newInstance()
            .wrapList(condoleList,CondoleVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public CondoleVo findById(String id) {
        Condole condole = condoleDao.findById(id);
        return BeanWrapBuilder.newInstance()
            .wrap(condole, CondoleVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            condoleDao.deleteById(id);
        }
    }


}
