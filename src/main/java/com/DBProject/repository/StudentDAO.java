package com.DBProject.repository;

import com.DBProject.Controller.ajax.StudentController.SaveDetailsRequest;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Student;

import java.sql.Connection;
import java.util.List;

public interface StudentDAO {
     List<Student> getStudents();
     Student getStudent(String username);
     void saveStudent(Student student);
     boolean saveResume(Student student, String unicode, String type, String stage);
     boolean saveDetails(String username, SaveDetailsRequest saveDetailsRequest, String stage);
     void updateStage(Connection conn, String stage, String username);
     Coordinator allocateIc(String username, String stage);
}
