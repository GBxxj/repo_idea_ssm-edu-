package com.Controller;

import com.domain.Course;
import com.domain.CourseLesson;
import com.domain.CourseSection;
import com.domain.ResponseResult;
import com.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;


    /*
     * 根据课程id查询关联的章节信息以及章节信息关联的课时信息
     * */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "章节以及课时内容查询成功", list);

        return responseResult;
    }

    /*
     *回显章节对应的课程信息
     * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){
        Course course = courseContentService.findCourseByCourseId(courseId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询课程信息成功", course);

        return  responseResult;

    }
    /*
     * 新增或更新章节信息
     * */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveSection(@RequestBody CourseSection courseSection){
        //判断是否携带章节id
        if(courseSection.getId()==null){
            //更新
        courseContentService.saveSection(courseSection);
        return new ResponseResult(true,200,"新增章节成功",null);
    }else {
            //新增
         courseContentService.updateSection(courseSection);
         return new ResponseResult(true,200,"更新章节成功",null);
        }
    }

    /*
     * 修改章节状态
     * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(int id,int status){
        courseContentService.updateSectionStatus(id,status);

        Map<Object,Object> map=new HashMap<>();
        map.put("status",status);

        return new ResponseResult(true,200,"修改章节状态成功",map);

    }
    /*
     * 新建课时信息
     * */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveCourseLesson(courseLesson);

        return new ResponseResult(true,200,"新增课时信息成功",null);
    }

}
