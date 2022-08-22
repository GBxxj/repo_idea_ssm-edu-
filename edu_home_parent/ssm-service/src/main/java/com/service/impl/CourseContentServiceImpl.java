package com.service.impl;

import com.dao.CourseContentMapper;
import com.domain.Course;
import com.domain.CourseLesson;
import com.domain.CourseSection;
import com.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {

    /*调用dao方法，注入*/
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonCourseId(courseId);
        return list;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course= courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        //1.补全信息
        Date date=new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        //调用mapeper
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        //1.补全信息
        courseSection.setUpdateTime(new Date());

        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        //封装数据
        CourseSection courseSection = new CourseSection();
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);

        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void saveCourseLesson(CourseLesson courseLesson) {
        //补全信息
        Date date=new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.saveCourseLesson(courseLesson);
    }
}
