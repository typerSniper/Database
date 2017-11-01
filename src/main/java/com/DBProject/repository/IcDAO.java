package com.DBProject.repository;

import java.util.List;

import com.DBProject.domain.Ic;
import com.DBProject.domain.Student;

public interface IcDAO {
	public Ic getAFreeIc();
	// Danger! The stage comparison is done as strings. That is, '10' < '2'!
	public List<Student> getHerStudentsLessThanAStage(String ic_id, String stage);
	public void advanceHerStudents(String ic_id, String stage);
}
