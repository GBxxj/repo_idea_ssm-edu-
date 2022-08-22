package com.service;

import com.domain.Course;
import com.domain.CourseVo;
import com.domain.Teacher;

import java.util.List;

public interface CourseService {
    /*
     * 多条件课程列表查询
     * */
    public List<Course> findCourseByCondition(CourseVo courseVo);
    /*
     * 新增课程及讲师信息
     * */
    public void saveCourseOrTeacher(CourseVo courseVo);
    /*
     * 回显课程信息（根据课程id查询对应课程信息以及关联的讲师信息）
     * */
    public CourseVo findCourseById(Integer id);
    /*
     * 更新课程及讲师信息
     * */
    public void updateCourseOrTeacher(CourseVo courseVo);
    /*
     * 课程状态管理
     * */
    public void updateCourseStatus(int courseid,int status);
}


