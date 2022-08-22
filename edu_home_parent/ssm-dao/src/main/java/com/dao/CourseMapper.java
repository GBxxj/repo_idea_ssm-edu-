package com.dao;

import com.domain.Course;
import com.domain.CourseSection;
import com.domain.CourseVo;
import com.domain.Teacher;


import java.util.List;

public interface CourseMapper {
    /*
    * 多条件课程列表查询
    * */
    public List<Course> findCourseByCondition(CourseVo courseVo);
    /*
     * 新增课程信息
     * */
    public void saveCourse(Course course);
    /*
     * 新增讲师信息
     * */
    public void saveTeacher(Teacher teacher);
    /*
    * 回显课程信息（根据课程id查询对应课程信息以及关联的讲师信息）
    * */
    public CourseVo findCourseById(Integer id);
    /*
    * 更新课程信息
    * */
    public void updateCourse(Course course);
    /*
     * 更新讲师信息
     * */
    public void updateTeacher(Teacher teacher);
    /*
    * 课程状态管理
    * */
    public void updateCourseStatus(Course course);

}