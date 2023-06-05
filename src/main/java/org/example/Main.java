package org.example;

import org.example.domain.Student;
import org.example.repository.StudentMysqlRepository;
import org.example.repository.StudentRepository;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        StudentRepository studentsRepository = new StudentMysqlRepository();
        List<Student> students = studentsRepository.findAll();
        System.out.println(students);
        Student student = Student.builder()
                .Age(30)
                .Firstname("Ivan")
                .Lastname("Smereka")
                .build();
        studentsRepository.saveStudent(student);
        List<Student> students2 = studentsRepository.findAll();
        System.out.println(students2);
  }
}
