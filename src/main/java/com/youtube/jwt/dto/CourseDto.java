package com.youtube.jwt.dto;

import com.youtube.jwt.entity.Course;
import com.youtube.jwt.entity.RubricQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
@AllArgsConstructor
public class CourseDto {
    private Integer id;
    private String courseName;
    private String description;
    private int price;
    private String level;
    private byte[] courseFile;
    private String fileType;

    private QuizDto quiz2;

    public static CourseDto courseEntityToDto(Course course){
        if (course == null)return null;
        else return CourseDto.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .price(course.getPrice())
                .level(course.getLevel())
                .courseFile(course.getCourseFile())
                .fileType(course.getFileType())
                .quiz2(QuizDto.quizEntityToDto(course.getQuiz2())).build();
    }
    public static List<CourseDto> RubricQuestionEntityToDtos(List<Course> course){
        return course.stream().map(x-> courseEntityToDto(x)).collect(Collectors.toList());
    }
}
