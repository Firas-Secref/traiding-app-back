package com.youtube.jwt.controller;

import com.youtube.jwt.dto.CourseDto;
import com.youtube.jwt.entity.Course;
import com.youtube.jwt.entity.Quiz;
import com.youtube.jwt.entity.Quiz2;
import com.youtube.jwt.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses/new")
    public Course addNewCourse(@RequestParam("courseDetails") String course, @RequestParam("file") MultipartFile file) throws IOException {
        return this.courseService.addNewCourse(course, file);
    }

    @GetMapping("/courses/all")
    public List<CourseDto> getAllCourses(){
        return this.courseService.getAllCourses();
    }

    @PostMapping("/courses/addQuiz/{courseId}")
    public Course associateQuizToCourse(@RequestBody Quiz2 quiz, @PathVariable Integer courseId){
        return courseService.associateQuizToCourse(quiz, courseId);
    }

    @DeleteMapping("/coursed/delete/{id}")
    public void deleteCourse(@PathVariable int id){
        this.courseService.deleteCourse(id);
    }
}
