package com.service;

import com.domain.PromotionAd;
import com.domain.PromotionAdVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PromotionAdService {
    /*
     * 分页查询广告信息
     * 接受Controller传递过来的参数promotionAdVo
     * */
    public PageInfo findAllPromotionAdByPage(PromotionAdVo promotionAdVo);
    /*
     * 广告动态上下线
     * */
    public void updatePromotionAdStatus(int id,int status);
    /*
     * 新建广告
     * */
    public void savePromotionAd(PromotionAd promotionAd);
    /*
     * 更新广告
     * */
    public void updatePromotionAd(PromotionAd promotionAd);
    /*
     * 根据id回显广告信息
     * */
    public PromotionAd findPromotionAdById(int id);
}
