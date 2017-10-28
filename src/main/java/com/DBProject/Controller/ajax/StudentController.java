package com.DBProject.Controller.ajax;

import com.DBProject.repository.StudentDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.DBProject.Controller.DefaultController.isAnonymous;

/**
 * Created by Jatin on 28/10/17.
 */


@Lazy
@RestController
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;


    @SneakyThrows
    @RequestMapping(value = "/student/save_details", method = RequestMethod.POST)
    @ResponseBody
    public SaveDetailsResponse saveDetails(@RequestBody final SaveDetailsRequest saveDetailsRequest) {
        //TODO: save those details to the database and check it before sending;
        System.out.println(saveDetailsRequest);
        return new SaveDetailsResponse(2, true);
    }

    @Data
    @AllArgsConstructor
    public static class SaveDetailsResponse {
        private int stage;
        private boolean success;
    }

    @Data
    @AllArgsConstructor
    public static class SaveDetailsRequest {
        private String name;
        private String dob;
        private String sex;
        private String category;
        private String nationality;
        private String univemail;
        private String peremail;
        private String hosteladdress;
        private String contact1;
        private String contact2;
        private String homecontact;
        private HomeAddress homeaddress;
        private String skypeid;
        private CollegeDetails details10th;
        private CollegeDetails details12th;
        private CollegeDetails collegeDetails;
        private CollegeDetails other;
     }

    @Data
    @AllArgsConstructor
    public class CollegeDetails {
        private String university;
        private String institute;
        private String year;
        private String cpi;
    }

    @Data
    @AllArgsConstructor
    public static class HomeAddress {
        private String state;
        private String city;
        private String pin;
        private String locality;
        private String country;
    }


}
