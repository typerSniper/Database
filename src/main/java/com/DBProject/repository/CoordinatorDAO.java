package com.DBProject.repository;

import java.util.List;

import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Student;

public interface CoordinatorDAO {
	 Coordinator getAFreeIc();
	// Danger! The stage comparison is done as strings. That is, '10' < '2'!
	 List<Student> getHerStudentsLessThanAStage(String ic_id, String stage);
	 void advanceHerStudents(String ic_id, String stage);
}
