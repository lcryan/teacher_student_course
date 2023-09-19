package com.example.les13relations.repository;

import com.example.les13relations.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByDobAfter(LocalDate date);
}
