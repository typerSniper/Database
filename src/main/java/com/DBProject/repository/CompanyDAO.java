package com.DBProject.repository;

import com.DBProject.Controller.ajax.CompanyController;
import com.DBProject.domain.Company;

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
}
