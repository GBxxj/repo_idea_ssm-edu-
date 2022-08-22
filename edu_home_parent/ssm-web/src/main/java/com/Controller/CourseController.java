package com.Controller;

import com.domain.Course;
import com.domain.CourseVo;
import com.domain.ResponseResult;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;



    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){
        List<Course> list = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", list);
        return responseResult;
    }

    /**
     * 图片上传接口
     * */ @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {

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
        /*新增课程信息以及讲师信息
        * 新增课程信息和修改课程信息要写在同一个方法中*/
    @RequestMapping(path = "/saveOrUpdateCourse")
     public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo){

        //通过id值来判断是更新操作还是新增操作
        if (courseVo.getId()==null){
            //调用service
            courseService.saveCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "新增成功", null);
            return responseResult;
        }else {
            courseService.updateCourseOrTeacher(courseVo);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }





    }
        /*
        *回显课程信息（根据课程id查询对应课程信息以及关联的讲师信息）
        * */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseVo = courseService.findCourseById(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据课程id查询信息成功", courseVo);

        return responseResult;
    }
    /*
     * 课程状态管理
     * */
    @RequestMapping("/updateCourseStatus")

    /*传递的参数看接口文档*/
   public ResponseResult updateCourseStatus(Integer id,Integer status){
        courseService.updateCourseStatus(id,status);

        /*x响应格式参考接口文档*/
        //响应数据
        Map<String,Object> map=new HashMap<>();
        map.put("status",status);

        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);

        return responseResult;
    }
}