package com.DBProject.Controller.ajax;

import com.DBProject.domain.Resume;
import com.DBProject.domain.Student;
import com.DBProject.repository.CoordinatorDAOImpl;
import com.DBProject.repository.ResumeDAOImpl;
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
import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

@Controller
public class CoordinatorController {
	@Autowired
    private CoordinatorDAOImpl coordinatorDAO;

	@Autowired
    private StudentStageManager stageManager;

	@Autowired
    private ResumeDAOImpl resumeDAO;

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
