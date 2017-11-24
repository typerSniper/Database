package com.DBProject.repository;

import com.DBProject.Controller.ajax.CompanyController;
import com.DBProject.Controller.ajax.SelectionController;
import com.DBProject.domain.Company;
import com.DBProject.domain.Jaf;

import java.util.List;

/**
 * Created by Jatin on 23/11/17.
 */

public interface CompanyDAO {
     boolean registerCompany(CompanyController.CompanyRegisterRequest companyRegisterRequest, String stage);
     boolean registerJob (String companyID, String stage, CompanyController.JobRegisterRequest jobRegisterRequest);
     Company getCompany(String username);
     boolean setJafStage(String jafID, String stage);
     boolean deleteJaf(String jafID);
     boolean setJobDeadline(String jafID, String jobDeadline);
     List<Jaf> getCompanyJafs(String companyID);
     List<Jaf> getAllJafs();
     boolean getEligible(String studentID, String jid);
     Jaf getJaf(String jafID);
     boolean getIfSigned(String studentID, String jid);
     boolean signJaf(String studentID, String jid);
     boolean unSignJaf(String studentID, String jid);
     List<Jaf> getJafsWithStage(String coordinatorName, String stage);
     boolean setJobStages(SelectionController.JobRegisterRequest jobRegisterRequest);
}
