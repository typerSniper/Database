package com.DBProject.Controller.ajax;

import com.DBProject.repository.StudentDAOImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import static com.DBProject.Controller.DefaultController.getUsername;
import static com.DBProject.Controller.DefaultController.StageUpdateResponse;

@Lazy
@RestController
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;

    @SneakyThrows
    @RequestMapping(value = "/student/save_details", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse saveDetails(@RequestBody final SaveDetailsRequest saveDetailsRequest) {
        final String username = getUsername();
        studentDAO.saveDetails(username, saveDetailsRequest, String.valueOf(2));
        // ASSUMPTION: '2' stands for the stage FEE PENDING
        System.out.println(saveDetailsRequest);
        return new StageUpdateResponse(2, true);
    }

    @SneakyThrows
    @RequestMapping(value = "/student/fee_payment", method = RequestMethod.GET)
    @ResponseBody
    public StageUpdateResponse FeePayment() {
    	// PROBLEM: which student???
    	String username = "null";
    	// precondition: username variable has the username of the concerned student.
    	studentDAO.updateStage(username, String.valueOf(3));
    	studentDAO.allocateIc(username);
    	// ASSUMPTION: '3' stands for the stage FEE VERIFICATION
        return new StageUpdateResponse(3, true);
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
