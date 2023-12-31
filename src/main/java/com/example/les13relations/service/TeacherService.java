package com.example.les13relations.service;

import com.example.les13relations.dto.TeacherDto;
import com.example.les13relations.model.Teacher;
import com.example.les13relations.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }

    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);
        repos.save(teacher);
        teacherDto.id = teacher.getId();
        return teacherDto;
    }
}
