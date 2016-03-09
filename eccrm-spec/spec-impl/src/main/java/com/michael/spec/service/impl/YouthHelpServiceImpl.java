package com.michael.spec.service.impl;

import com.michael.spec.bo.YouthHelpBo;
import com.michael.spec.dao.YouthDao;
import com.michael.spec.dao.YouthHelpDao;
import com.michael.spec.domain.Youth;
import com.michael.spec.domain.YouthHelp;
import com.michael.spec.service.YouthHelpService;
import com.michael.spec.vo.YouthHelpVo;
import com.ycrl.core.beans.BeanWrapBuilder;
import com.ycrl.core.hibernate.validator.ValidatorUtils;
import com.ycrl.core.pager.PageVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Michael
 */
@Service("youthHelpService")
public class YouthHelpServiceImpl implements YouthHelpService {
    @Resource
    private YouthHelpDao youthHelpDao;

    @Resource
    private YouthDao youthDao;

    @Override
    public String save(YouthHelp youthHelp) {
        ValidatorUtils.validate(youthHelp);
        String id = youthHelpDao.save(youthHelp);
        // 更新帮扶次数
        Youth youth = youthDao.findById(youthHelp.getYouthId());
        Assert.notNull(youth, "添加帮扶记录失败!闲散青年不存在!请刷新后重试!");
        Integer times = youth.getHelpTimes();
        youth.setHelpTimes(times + 1);
        youth.setLastHelpDate(youthHelp.getOccurDate());
        return id;
    }

    @Override
    public void update(YouthHelp youthHelp) {
        youthHelpDao.update(youthHelp);
    }

    @Override
    public PageVo pageQuery(YouthHelpBo bo) {
        PageVo vo = new PageVo();
        Long total = youthHelpDao.getTotal(bo);
        vo.setTotal(total);
        if (total == null || total == 0) return vo;
        List<YouthHelp> youthHelpList = youthHelpDao.query(bo);
        List<YouthHelpVo> vos = BeanWrapBuilder.newInstance()
                .wrapList(youthHelpList, YouthHelpVo.class);
        vo.setData(vos);
        return vo;
    }


    @Override
    public YouthHelpVo findById(String id) {
        YouthHelp youthHelp = youthHelpDao.findById(id);
        return BeanWrapBuilder.newInstance()
                .wrap(youthHelp, YouthHelpVo.class);
    }

    @Override
    public void deleteByIds(String[] ids) {
        if (ids == null || ids.length == 0) return;
        for (String id : ids) {

            YouthHelp youthHelp = youthHelpDao.findById(id);
            Assert.notNull(youthHelp, "删除帮扶记录失败!数据不存在，请刷新后再试!");
            // 更新帮扶次数
            String youthId = youthHelp.getYouthId();
            Youth youth = youthDao.findById(youthId);
            Assert.notNull(youth, "删除帮扶记录失败!闲散青年不存在，请刷新后再试!");
            youth.setHelpTimes(youth.getHelpTimes() - 1);

            // 删除本身
            youthHelpDao.delete(youthHelp);
        }
    }


}
