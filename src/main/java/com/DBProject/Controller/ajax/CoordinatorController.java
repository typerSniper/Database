package com.DBProject.Controller.ajax;

import com.DBProject.domain.Student;
import com.DBProject.repository.CoordinatorDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.DBProject.Controller.ajax.StudentController.StageUpdateResponse;
import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

public class CoordinatorController {
	@Autowired
    private CoordinatorDAOImpl coordinatorDAO;

    @SneakyThrows
    @RequestMapping(value = "/student/ic_fee_students", method = RequestMethod.GET)
    @ResponseBody
    public GetFeeStudents getFeeStudents() {
        final String coordinatorName = getUsername();
        return new GetFeeStudents(coordinatorDAO.getHerStudentsLessThanAStage(coordinatorName, String.valueOf(3)));
    }

    @SneakyThrows
    @RequestMapping(value = "/student/ic_advance_fee", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse advanceFeeStudents(@RequestBody AdvanceFeeStudent advanceFeeStudent) {
        final String coordinatorName = getUsername();
        coordinatorDAO.advanceHerStudents(coordinatorName, String.valueOf(4));
        return new StageUpdateResponse(4, true);
    }

    @Data
    @AllArgsConstructor
    public class AdvanceFeeStudent {
        private String student;
    }
    @Data
    @AllArgsConstructor
    public class GetFeeStudents {
        List<Student> students;
    }
}
