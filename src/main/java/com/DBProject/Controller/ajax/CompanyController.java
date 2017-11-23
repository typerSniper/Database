package com.DBProject.Controller.ajax;

import com.DBProject.domain.Company;
import com.DBProject.domain.Student;
import com.DBProject.repository.CompanyDAOImpl;
import com.DBProject.service.JAFStageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

/**
 * Created by Jatin on 23/11/17.
 */
@Controller

public class CompanyController {

    @Autowired
    private CompanyDAOImpl companyDAO;

    @Autowired
    private JAFStageManager jafStageManager;

    @SneakyThrows
    @RequestMapping(value = "/company/register", method = RequestMethod.POST)
    @ResponseBody
    public CompanyRegisterResponse companyRegister(@RequestBody final CompanyRegisterRequest companyRegisterRequest) {
        return new CompanyRegisterResponse(companyDAO.registerCompany(companyRegisterRequest, "unregistered"));
    }

    @SneakyThrows
    @RequestMapping(value = "/company/stage", method = RequestMethod.GET)
    @ResponseBody
    public StudentController.StageResponse getStage() {
        Company company = companyDAO.getCompany(getUsername());
        int valid = 0;
        if(company==null)
            return new StudentController.StageResponse(false, -1);
        if(company.getStage().toLowerCase().equals("registered"))
            valid = 1;
        return new StudentController.StageResponse(true, valid);
    }

    @SneakyThrows
    @RequestMapping(value = "/company/create_job", method = RequestMethod.POST)
    @ResponseBody
    public JobRegisterResponse jobRegister (@RequestBody final JobRegisterRequest jobRegisterRequest) {
        String companyId = getUsername();
        return new JobRegisterResponse(companyDAO.registerJob(companyId, jafStageManager.getCurrentRep(1), jobRegisterRequest));
    }

    @Data
    @AllArgsConstructor
    public static class JobRegisterResponse {
        boolean success;
    }

    @Data
    @AllArgsConstructor
    public  static class JobRegisterRequest {
        String jname;
        String salary;
        String location;
        String description;
        List<Eligiblity> eligiblities;
        String datetime;
    }

    @Data
    @AllArgsConstructor
    public static class Eligiblity{
        String programid;
        String deptid;
        String cpicutoff;
    }

    @Data
    @AllArgsConstructor
    public static class CompanyRegisterResponse {
        boolean success;
    }

    @Data
    @AllArgsConstructor
    public static class CompanyRegisterRequest {
        String name;
        String contact;
        String email;
        String representative;
        String password;
    }
}
