package com.dao;

import com.domain.Course;
import com.domain.CourseLesson;
import com.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /*
    * 根据课程id查询关联的章节信息以及章节信息关联的课时信息
    * */
    public List<CourseSection> findSectionAndLessonCourseId(Integer courseId);
    /*
    *回显章节对应的课程信息
    * */
    public Course findCourseByCourseId(int courseId);
    /*
     * 新增章节信息
     * */
    public void saveSection(CourseSection courseSection);
    /*
     * 更新章节信息
     * */
    void updateSection(CourseSection courseSection);
    /*
    * 修改章节状态
    * */
    public void updateSectionStatus(CourseSection courseSection);
    /*
    * 新建课时信息
    * */
    public void saveCourseLesson(CourseLesson courseLesson);
}
