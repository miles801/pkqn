package com.michael.spec.service.impl;

import com.michael.spec.bo.YouthRelationBo;
import com.michael.spec.dao.YouthRelationDao;
import com.michael.spec.domain.YouthRelation;
import com.michael.spec.service.YouthRelationService;
import com.michael.spec.vo.YouthRelationVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("youthRelationService")
public class YouthRelationServiceImpl implements YouthRelationService {
    @Resource
    private YouthRelationDao youthRelationDao;

    @Override
    public String save(YouthRelation youthRelation) {
        String id = youthRelationDao.save(youthRelation);
        return id;
    }

    @Override
    public void update(YouthRelation youthRelation) {
        youthRelationDao.update(youthRelation);
    }

    @Override
    public PageVo pageQuery(YouthRelationBo bo) {
        PageVo vo = new PageVo();
        Long total = youthRelationDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<YouthRelation> youthRelationList = youthRelationDao.query(bo);
        List<YouthRelationVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(youthRelationList, YouthRelationVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public YouthRelationVo findById(String id) {
        YouthRelation youthRelation = youthRelationDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(youthRelation, YouthRelationVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {
            youthRelationDao.deleteById(id);
        }
    }


}
