package com.service.impl;

import com.dao.PromotionSpaceMapper;
import com.domain.PromotionSpace;
import com.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    /*调用dao方法，注入*/
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> list = promotionSpaceMapper.findAllPromotionSpace();
        return list;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //1.封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date=new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setId(0);


        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceById;
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //1.封装数据
        promotionSpace.setUpdateTime(new Date());

        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
