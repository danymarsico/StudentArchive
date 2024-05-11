package com.studendarchive.StudentsApp.dao;

import com.studendarchive.StudentsApp.model.Student;

import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    List<Student> listStudents();
    Student getStudentById(Long id);
    void updateStudent(Long id, Student student);
    void deleteStudent(Long id);

}
