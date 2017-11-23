package com.DBProject.repository;

import com.DBProject.Controller.ajax.CompanyController;
import com.DBProject.domain.Company;
import org.springframework.stereotype.Repository;

/**
 * Created by Jatin on 23/11/17.
 */

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Override
    public boolean registerCompany(CompanyController.CompanyRegisterRequest companyRegisterRequest, String stage) {
        String sql = "insert into company values (?, ?, ?, ?);";
        // TODO: Allocate IC to company and set its stage and enter it in
        return true;
    }

    @Override
    public boolean registerJob (String companyId, String stage, CompanyController.JobRegisterRequest jobRegisterRequest) {
       //TODO: register job with company deadline specified and job deadline as null
        return true;
    }

    @Override
    public Company getCompany(String username) {
//        TODO: get company map and return
        return  null;
    }

    @Override
    public  boolean setJafStage(String jafID, String stage) {
        //TODO : set the jaf stage to input
        return true;
    }

    @Override
    public boolean deleteJaf(String jafID) {
//        TODO: delete this jaf
        return true;
    }

    @Override
    public boolean setJobDeadline(String jafID, String jobDeadline) {
        //TODO: set the job deadline of jaf
        return true;
    }

}
