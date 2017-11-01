package com.DBProject.repository;

import com.DBProject.Controller.ajax.StudentController.SaveDetailsRequest;
import com.DBProject.domain.Ic;
import com.DBProject.domain.Student;

import java.util.List;

public interface StudentDAO {
    public List<Student> getStudents();
    public Student getStudent(String username, String password);
    public void saveStudent(Student student);
    public void saveResume(Student student, String unicode, String type);
    public void saveDetails(String username, SaveDetailsRequest saveDetailsRequest, String stage);
    public void updateStage(String username, String stage);
    public Ic allocateIc(String username);
}
