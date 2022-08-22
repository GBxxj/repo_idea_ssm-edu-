package com.Controller;

import com.dao.PromotionAdMapper;
import com.domain.PromotionAd;
import com.domain.PromotionAdVo;
import com.domain.ResponseResult;
import com.github.pagehelper.PageInfo;
import com.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;
    /*
     * 分页查询广告信息
     * */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){
        PageInfo pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true,200,"分页查询广告成功",pageInfo);

    }

    /**
     * 图片上传接口
     * */ @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空if(file.isEmpty()){
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm_web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
        //ssm-web中间划线要与tomcat部署路径保持一致
        String substring = realPath.substring(0,realPath.indexOf("ssm-web"));

        //3.获取原文件名
        //lagou.jpg
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        //12354.jpg
        String newFileName =System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.上传文件
        //D:\apache-tomcat-8.5.56\webapps\
        String uploadPath = substring+"upload\\";
        File filePath = new File(uploadPath,newFileName);

        //如果目录不存在就创建目录
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath); }

        //图片就进行了真正的上传
        file.transferTo(filePath);

        //6. 将 文 件 名 和 文 件 路 径 返 回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        /*注释中若出现\字母u则会出现非法转义*/
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true,200,"图片上传成功",map);

        return result;

    }

    /*
     * 广告动态上下线
     * */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);

        return new ResponseResult(true,200,"广告状态修改成功",null);
    }
    /*
    * 增加或更新广告
    * */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        //通过id值来判断是更新操作还是新增操作
        if(promotionAd.getId()==null){
            //新增
            Date date=new Date();
            promotionAd.setCreateTime(date);
            promotionAd.setUpdateTime(date);
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        }else{
            promotionAd.setUpdateTime(new Date());
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"更新广告成功",null);
        }
    }
    /*
     * 根据id回显广告信息
     * */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"根据id回显广告信息成功",promotionAd);
    }
}
