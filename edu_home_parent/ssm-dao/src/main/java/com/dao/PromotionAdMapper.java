package com.dao;

import com.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
     /*
     * 分页查询广告信息
     * */
    public List<PromotionAd> findAllPromotionAdByPage();
    /*
    * 广告动态上下线
    * */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
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
