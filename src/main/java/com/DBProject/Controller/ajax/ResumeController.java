package com.DBProject.Controller.ajax;


import com.DBProject.domain.Student;
import com.DBProject.repository.StudentDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@Lazy
@RestController
public class ResumeController {

    @Autowired
    private StudentDAOImpl studentDAO;

    @SneakyThrows
    @RequestMapping(value = "/student/saveResume", method = RequestMethod.POST)
    @ResponseBody
    public ResumeSaveResponse validateCredentials(@RequestBody final ResumeSave request) {
        System.out.println(request.getUsername() + " " + request.getPassword());
        Student student = studentDAO.getStudent(request.getUsername(), request.getPassword());

        if (student != null) {
        	studentDAO.saveResume(student, request.getResumeData(), request.getType());
            return new ResumeSaveResponse(true, student.getStage());
        } else {
        	return new ResumeSaveResponse(false, -1);
        }
    }

    @Data
    @AllArgsConstructor
    public static class ResumeSaveResponse {
        private boolean authenticated;
        private int stage;
    }

    @Data
    @AllArgsConstructor
    public static class ResumeSave {
        private String username;
        private String password;
        private String type;
        private String resumeData;
    }


}