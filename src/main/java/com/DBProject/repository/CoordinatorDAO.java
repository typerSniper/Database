package com.DBProject.repository;

import java.util.List;

import com.DBProject.Controller.ajax.CoordinatorController;
import com.DBProject.domain.*;

public interface CoordinatorDAO {
	 Coordinator getAFreeIc();
	 List<Student> getStudentsWithStage(String ic_id, String stage);
	 List<Jaf> getJafsWithStage(String ic_id, String stage);
	 boolean advanceHerStudents(String ic_id, String stage, List<Student> students);
	 List<CoordinatorController.ResumeStudents> getResumeStudents(String ic_id);
	 List<Company> getAllocatedCompanies(String ic_id);
	boolean setCompanyStage(String companyID, String stage);

}
