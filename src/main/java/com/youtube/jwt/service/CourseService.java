package com.youtube.jwt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.jwt.dao.CourseRepository;
import com.youtube.jwt.dto.CourseDto;
import com.youtube.jwt.entity.Course;
import com.youtube.jwt.entity.Quiz;
import com.youtube.jwt.entity.Quiz2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addNewCourse(String courseString, MultipartFile file) throws IOException {
        Course course = new ObjectMapper().readValue(courseString, Course.class);
        course.setCourseFile(file.getBytes());
        course.setFileType(StringUtils.getFilenameExtension(file.getOriginalFilename()));
        return this.courseRepository.save(course);
    }

    public List<CourseDto> getAllCourses(){
        return CourseDto.RubricQuestionEntityToDtos(this.courseRepository.findAll());
    }

    @Transactional
    public Course associateQuizToCourse(Quiz2 quiz, Integer courseId){
        Course course = this.courseRepository.findById(courseId).get();
        course.setQuiz2(quiz);
        return course;
    }

    @Transactional
    public void deleteCourse(int courseId){
        this.courseRepository.deleteById(courseId);
    }
}
