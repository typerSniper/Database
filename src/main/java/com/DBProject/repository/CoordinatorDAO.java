package com.DBProject.repository;

import java.util.List;

import com.DBProject.Controller.ajax.CoordinatorController;
import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Resume;
import com.DBProject.domain.Student;

public interface CoordinatorDAO {
	 Coordinator getAFreeIc();
	 List<Student> getStudentsWithStage(String ic_id, String stage);
	 boolean advanceHerStudents(String ic_id, String stage, List<Student> students);
	 List<CoordinatorController.ResumeStudents> getResumeStudents(String ic_id);
}
