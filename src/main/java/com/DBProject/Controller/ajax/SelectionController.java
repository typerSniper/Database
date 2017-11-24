package com.DBProject.Controller.ajax;

import com.DBProject.repository.CompanyDAOImpl;
import com.DBProject.service.JAFStageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

/**
 * Created by Jatin on 25/11/17.
 */
public class SelectionController {
    @Autowired
    CompanyDAOImpl companyDAO;

    @Autowired
    private JAFStageManager jafStageManager;

    public CompanyController.JobRegisterResponse jobRegister (@RequestBody final JobRegisterRequest jobRegisterRequest) {
        String companyId = getUsername();
        companyDAO.registerJob(companyId, jafStageManager.getCurrentRep(1);
        companyDAO.setJobStages(jobRegisterRequest);
        return null;
    }


    @Data
    @AllArgsConstructor
    public static class JobRegisterRequest {
        String jname;
        String salary;
        String location;
        String description;
        List<CompanyController.Eligiblity> eligibilities;
        String comp_deadline;
        List<JobStages>  selectionProcedure;
    }

    @Data
    @AllArgsConstructor
    public static class JobStages {
        String stage;
        String description;
    }
}


