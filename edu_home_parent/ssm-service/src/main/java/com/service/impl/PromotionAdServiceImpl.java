package com.service.impl;

import com.dao.PromotionAdMapper;
import com.domain.PromotionAd;
import com.domain.PromotionAdVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    /*调用dao方法，注入*/
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {

        //设置分页
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());
        List<PromotionAd> allPromotionAdByPage = promotionAdMapper.findAllPromotionAdByPage();

        //获取分页参数信息
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(allPromotionAdByPage);

        return pageInfo;
    }

    @Override
    public void updatePromotionAdStatus(int id, int status) {
        //封装数据
        PromotionAd promotionAd=new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());

        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAd= promotionAdMapper.findPromotionAdById(id);
        return promotionAd;
    }

}
