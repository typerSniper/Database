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
public class LoginController {

    @Autowired
    private StudentDAOImpl studentDAO;

    @SneakyThrows
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse validateCredentials(@RequestBody final LoginRequest request) {
        if (request.getType().equals("Student")) {
            Student student = studentDAO.getStudent(request.getUsername(), request.getPassword());
            if (student != null) {
                return new LoginResponse(true, student.getStage());
            }
        }
        return new LoginResponse(false, -1);
    }

    @Data
    @AllArgsConstructor
    public static class LoginResponse {
        private boolean authenticated;
        private int stage;
    }

    @Data
    @AllArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
        private String type;
    }


}