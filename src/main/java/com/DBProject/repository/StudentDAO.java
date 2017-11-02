package com.DBProject.repository;

import com.DBProject.Controller.ajax.StudentController.SaveDetailsRequest;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Student;

import java.util.List;

public interface StudentDAO {
     List<Student> getStudents();
     Student getStudent(String username);
     void saveStudent(Student student);
     void saveResume(Student student, String unicode, String type);
     void saveDetails(String username, SaveDetailsRequest saveDetailsRequest, String stage);
     void updateStage(String username, String stage);
     Coordinator allocateIc(String username);
}
