package com.dao;

import com.domain.PromotionSpace;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;

public interface PromotionSpaceMapper {
    /*
    * 获取所有广告位的方法
    * */
    public List<PromotionSpace> findAllPromotionSpace();
    /*
    * 添加广告位
    * */
    public void savePromotionSpace(PromotionSpace promotionSpace);
    /*
    * 根据id查询广告位信息
    * */
    public PromotionSpace findPromotionSpaceById(int id);
    /*
    * 修改广告位
    * */
    public void updatePromotionSpace(PromotionSpace promotionSpace);

}
