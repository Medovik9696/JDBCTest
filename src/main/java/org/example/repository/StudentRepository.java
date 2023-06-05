package org.example.repository;

import org.example.domain.Student;

import java.util.List;

public interface StudentRepository {
    void saveStudent(Student student);
    List<Student> findAll();
}