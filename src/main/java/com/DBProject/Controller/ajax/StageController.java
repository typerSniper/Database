package com.DBProject.Controller.ajax;


import com.DBProject.Controller.ajax.LoginController.LoginRequest;
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
public class StageController {

    @Autowired
    private StudentDAOImpl studentDAO;

    @SneakyThrows
    @RequestMapping(value = "/student/stage", method = RequestMethod.POST)
    @ResponseBody
    public StageResponse validateCredentials(@RequestBody final LoginRequest request) {
        System.out.println(request.getUsername() + " " + request.getPassword());
        Student student = studentDAO.getStudent(request.getUsername(), request.getPassword());
        if (student != null) {
            return new StageResponse(true, student.getStage());
        }else {
        	return new StageResponse(false, String.valueOf(-1));
        }
    }

    @Data
    @AllArgsConstructor
    public static class StageResponse {
        private boolean authenticated;
        private String stage;
    }


}