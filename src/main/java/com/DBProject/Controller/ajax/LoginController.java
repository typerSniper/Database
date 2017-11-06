package com.DBProject.Controller.ajax;


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
    @RequestMapping(value = "/student/is_authenticated", method = RequestMethod.GET)
    @ResponseBody
    public validateRepsonse validateStudentCredentials() {
        return new validateRepsonse(true);
    }


    @SneakyThrows
    @RequestMapping(value = "/coordinator/is_authenticated", method = RequestMethod.GET)
    @ResponseBody
    public validateRepsonse validateCoordinatorCredentials() {
        return new validateRepsonse(true);
    }


    @SneakyThrows
    @RequestMapping(value = "/company/is_authenticated", method = RequestMethod.GET)
    @ResponseBody
    public validateRepsonse validateCompanyCredentials() {
        return new validateRepsonse(true);
    }


    @Data
    @AllArgsConstructor
    public static class validateRepsonse{
        boolean authenticated;
    }
}