package com.service.impl;

import com.dao.CourseMapper;
import com.domain.Course;
import com.domain.CourseVo;
import com.domain.Teacher;
import com.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service  //生成该类的实例存到ioc容器中
public class CourseServiceImpl implements CourseService {

    /*调用dao方法，注入*/
    @Autowired
    private CourseMapper courseMapper;


    /*
     * 多条件课程列表查询
     * */
    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {
        List<Course> list = courseMapper.findCourseByCondition(courseVo);
        return list;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) {
        //封装课程信息
        Course course=new Course();

        BeanUtils.copyProperties(course,courseVo);

        //补全课程信息
        Date date=new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);

        //保持课程
        courseMapper.saveCourse(course);

        //获取新插入数据的id值
        int id = course.getId();
        //根据Id值封装到teacher实体中
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全讲师信息
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setIsDel(0);
        teacher.setCourseId(id);

        //保存讲师信息
        courseMapper.saveTeacher(teacher);



    }

    @Override
    public CourseVo findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) {
        //封装课程信息
        Course course=new Course();
        BeanUtils.copyProperties(course,courseVo);

        //补全更新时间信息
        Date date=new Date();
        course.setUpdateTime(date);

        //保持课程
        courseMapper.updateCourse(course);

        //封装讲师信息
        Teacher teacher=new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);

        //补全信息
        teacher.setCourseId(course.getId());
        teacher.setUpdateTime(date);

        //保存讲师信息
        courseMapper.updateTeacher(teacher);


    }

    @Override
    public void updateCourseStatus(int courseid, int status) {
        //1.封装数据
        Course course=new Course();
        course.setId(courseid);
        course.setStatus(status);
        course.setUpdateTime(new Date());

        ///2.调用mapper
        courseMapper.updateCourseStatus(course);
    }

}
