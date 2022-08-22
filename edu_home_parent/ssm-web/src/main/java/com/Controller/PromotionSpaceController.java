package com.Controller;

import com.domain.PromotionSpace;
import com.domain.ResponseResult;
import com.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {


    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /*
     * 获取所有广告位的方法
     * */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();

        return new ResponseResult(true,200,"查询所有广告位成功",allPromotionSpace);
    }

    /*
     * 添加或修改广告位
     * */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        if(promotionSpace.getId()==null) {
            //id为空，表示新增
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "添加广告位成功", null);
        }else{
            //id不为空，表示更新
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true, 200, "更新广告位名称成功", null);
        }
    }
    /*
     * 根据id查询广告位信息
     * */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true,200,"根据id查询广告位信息成功",promotionSpace);
    }
}
