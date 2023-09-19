package com.example.les13relations.controller;

import com.example.les13relations.dto.TeacherDto;
import com.example.les13relations.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

//    @GetMapping
//    public ResponseEntity<List<Teacher>> getTeachers() {
//        return ResponseEntity.ok(repos.findAll());
//    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        else {
            service.createTeacher(teacherDto);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + teacherDto.id).toUriString());

            return ResponseEntity.created(uri).body(teacherDto);
        }
    }

//    @GetMapping("/after")
//    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(repos.findByDobAfter(date));
//    }
}
