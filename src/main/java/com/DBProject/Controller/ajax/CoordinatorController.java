package com.DBProject.Controller.ajax;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.DBProject.Controller.DefaultController.StageUpdateResponse;
import java.util.List;

import static com.DBProject.Controller.DefaultController.getUsername;

/**
 * Created by Jatin on 28/10/17.
 */
public class CoordinatorController {


    //TODO: Make a DAOImpl object for ic.

    @SneakyThrows
    @RequestMapping(value = "/student/ic_fee_students", method = RequestMethod.GET)
    @ResponseBody
    public GetFeeStudents getFeeStudents() {
        final String coordinatorName = getUsername();
        //TODO: Get pending students for this IC from DB and send.
        return new GetFeeStudents(Lists.newArrayList("Student"));
    }

    @SneakyThrows
    @RequestMapping(value = "/student/ic_advance_fee", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse advanceFeeStudents(@RequestBody AdvanceFeeStudent advanceFeeStudent) {
        final String coordinatorName = getUsername();
        //TODO: Update stage in DB and send.
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
        List<String> students;
    }
}
