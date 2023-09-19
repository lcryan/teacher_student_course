package com.example.les13relations.controller;

import com.example.les13relations.dto.CourseDto;
import com.example.les13relations.model.Course;
import com.example.les13relations.model.Teacher;
import com.example.les13relations.repository.CourseRepository;
import com.example.les13relations.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository courseRepos;
    private final TeacherRepository teacherRepos;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepos) {
        this.courseRepos = courseRepository;
        this.teacherRepos = teacherRepos;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        Course course = new Course();
        course.setTitle(courseDto.title);
        course.setSp(courseDto.sp);
        for (Long id : courseDto.teacherIds) {
            Optional<Teacher> ot = teacherRepos.findById(id);
            if (ot.isPresent()) {
                course.getTeachers().add(ot.get());
            }
        }
        courseRepos.save(course);
        courseDto.id = course.getId();
        return new ResponseEntity<>(courseDto, HttpStatus.CREATED);
    }
}
