package com.DBProject.Controller.ajax;

import com.DBProject.domain.Company;
import com.DBProject.domain.Jaf;
import com.DBProject.domain.Resume;
import com.DBProject.domain.Student;
import com.DBProject.repository.CompanyDAOImpl;
import com.DBProject.repository.CoordinatorDAOImpl;
import com.DBProject.repository.ResumeDAOImpl;
import com.DBProject.service.JAFStageManager;
import com.DBProject.service.StudentStageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import static com.DBProject.Controller.ajax.StudentController.StageUpdateResponse;

import java.util.Date;
import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

@Controller
public class CoordinatorController {
	@Autowired
    private CoordinatorDAOImpl coordinatorDAO;

	@Autowired
    private StudentStageManager stageManager;

    @Autowired
    private JAFStageManager jafStageManager;

	@Autowired
    private ResumeDAOImpl resumeDAO;

    @Autowired
    CompanyDAOImpl companyDAO;


    @SneakyThrows
    @RequestMapping(value = "/ic/fee_students", method = RequestMethod.GET)
    @ResponseBody
    public GetFeeStudents getFeeStudents() {
        final String coordinatorName = getUsername();
        System.out.println(coordinatorName);
        return new GetFeeStudents(coordinatorDAO.getStudentsWithStage(coordinatorName, "feeverification"));
    }

    @SneakyThrows
    @RequestMapping(value = "/ic/advance_fee", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse advanceFeeStudents(@RequestBody final AdvanceFeeStudent advanceFeeStudent) {
        final String coordinatorName = getUsername();
        String nextStage = "resumepending";
        boolean success = coordinatorDAO.advanceHerStudents(coordinatorName, nextStage, advanceFeeStudent.getStudents());
        return new StageUpdateResponse(stageManager.getCurrentStage(nextStage), success);
//                return new StageUpdateResponse(-1, false);

    }


    @SneakyThrows
    @RequestMapping(value = "/ic/get_student_resume", method = RequestMethod.GET)
    @ResponseBody
    public  GetResumeStudentsResponse getResumeStudents() {
        String username  = getUsername();
        List<ResumeStudents> resumes = coordinatorDAO.getResumeStudents(username);
        return new GetResumeStudentsResponse(resumes);
    }

    @SneakyThrows
    @RequestMapping(value = "/ic/get_resume", method = RequestMethod.POST)
    @ResponseBody
    public  GetResumeResponse getResume(@RequestBody final GetResumeRequest getResumeRequest) {
        System.out.println("here");
        return new GetResumeResponse(resumeDAO.getByUsername(getResumeRequest.getUsername(), getResumeRequest.getRType()));
    }


    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "ic/get_pending_company", method =  RequestMethod.GET)
    public GetCompanyResponse getCompanyResponse (){
        return new GetCompanyResponse(coordinatorDAO.getAllocatedCompanies(getUsername()));
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "ic/get_pending_jaf", method =  RequestMethod.GET)
    public GetRegisteredJafs getRegisteredJafs (){
        return new GetRegisteredJafs(coordinatorDAO.getJafsWithStage(getUsername(), jafStageManager.getCurrentRep(1)));
    }

    @SneakyThrows
    @ResponseBody
    @RequestMapping(value = "ic/verify_jaf", method = RequestMethod.POST)
    public ChangeJafResponse changeJafResponse(@RequestBody final ChangeJafRequest changeJafRequest) {
        if(changeJafRequest.advance) {
            if(companyDAO.setJobDeadline(changeJafRequest.getJafID(), changeJafRequest.getDeadline()))
                return new ChangeJafResponse(companyDAO.setJafStage(changeJafRequest.getJafID(), jafStageManager.getCurrentRep(2)));
        }

        return new ChangeJafResponse(companyDAO.deleteJaf(changeJafRequest.getJafID()));
    }

    @Data
    @AllArgsConstructor
    public static class ChangeJafResponse {
        boolean success;
    }
    @Data
    @AllArgsConstructor
    public static class ChangeJafRequest {
        boolean advance;
        String jafID;
        String deadline;
    }



    @Data
    @AllArgsConstructor
    public static class GetRegisteredJafs {
        List<Jaf> jafs;
    }
    @Data
    @AllArgsConstructor
    public static class GetCompanyResponse {
        List<Company> companies;
    }

    @Data
    @AllArgsConstructor
    public static class AdvanceFeeStudent {
        List<Student> students;
    }
    @Data
    @AllArgsConstructor
    public static class GetFeeStudents {
        List<Student> students;
    }

    @Data
    @AllArgsConstructor
    public static class GetResumeResponse {
        private Resume resume;
    }

    @Data
    @AllArgsConstructor
    public static class GetResumeStudentsResponse{
        List<ResumeStudents> students;
    }

    @Data
    @AllArgsConstructor
    public static class ResumeStudents {
        String username;
        String rType;
    }


    @Data
    @AllArgsConstructor
    public static class GetResumeRequest {
        private String username;
        private String rType;
    }
}
