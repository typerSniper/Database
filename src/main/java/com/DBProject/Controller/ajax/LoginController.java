package com.DBProject.Controller.ajax;


import com.DBProject.repository.StudentDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import static com.DBProject.Controller.DefaultController.isAnonymous;

@Lazy
@RestController
public class LoginController {

    @Autowired
    private StudentDAOImpl studentDAO;
//
//    public LoginResponse validateCredentials(@RequestBody final LoginRequest request) {
//        if (request.getType().equals("Student")) {
//            System.out.println(request.getUsername() + " " + request.getPassword());
//            Student student = studentDAO.getStudent(request.getUsername(), request.getPassword());
//            if (student != null) {
//                return new LoginResponse(true, student.getStage());
//            }
//        }
//        return new LoginResponse(false, -1);
//    }

    @SneakyThrows
    @RequestMapping(value = "/is_authenticated", method = RequestMethod.GET)
    @ResponseBody
    public validateRepsonse validateCredentials() {
        System.out.println(isAnonymous());
        return new validateRepsonse(!isAnonymous());
    }

    @Data
    @AllArgsConstructor
    public static class validateRepsonse{
        boolean authenticated;
    }
}