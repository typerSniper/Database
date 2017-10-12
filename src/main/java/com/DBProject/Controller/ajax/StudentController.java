package com.DBProject.Controller.ajax;

import com.DBProject.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.DBProject.repository.StudentDAOImpl;

import java.util.List;

/**
 * Created by Jatin on 12/10/17.
 */
@Lazy
@Controller
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;

    @RequestMapping(value = "/app/students", method = RequestMethod.GET)
    @ResponseBody
    public GetStudentResponse getStudents() {
        return new GetStudentResponse(studentDAO.getStudents());
    }


    @Data
    @AllArgsConstructor
    public class GetStudentResponse{
        List<Student> studentList;
    }


}
