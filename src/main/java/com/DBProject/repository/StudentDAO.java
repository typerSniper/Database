package com.DBProject.repository;

import com.DBProject.domain.Student;

import java.util.List;

/**
 * Created by Jatin on 06/10/17.
 */
public interface StudentDAO {
    public List<Student> getStudents();
    public Student getStudent(String username, String password);
    public void saveStudent(Student student);
    public void saveResume(Student student, String unicode, String type);
}
