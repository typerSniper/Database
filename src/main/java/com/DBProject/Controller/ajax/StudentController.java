package com.DBProject.Controller.ajax;

import com.DBProject.domain.Student;
import com.DBProject.repository.StudentDAOImpl;
import com.DBProject.service.StudentStageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import static com.DBProject.Controller.DefaultController.getUsername;
import static com.DBProject.Controller.DefaultController.isAnonymous;

@Lazy
@RestController
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;

    @Autowired
    StudentStageManager stageManager;

    @SneakyThrows
    @RequestMapping(value = "/student/save_details", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse saveDetails(@RequestBody final SaveDetailsRequest saveDetailsRequest) {
        final String username = getUsername();
        int nextStage = stageManager.getNextStage(1);
        //TODO: Don't update stage inside this studentDAO.saveDetails function
        // Use the existing update Stage in the StudentDAOImpl and call it here explicitly
        //This is because all stage related logic should be either here or there,
        //Also take a look at StudentStageManager, we will save those strings in database instead of 1, 2, 3, 4 so that its recoverable
        studentDAO.saveDetails(username, saveDetailsRequest, stageManager.getCurrentRep(nextStage));
        System.out.println(saveDetailsRequest);
        return new StageUpdateResponse(nextStage, true);
    }

    @SneakyThrows
    @RequestMapping(value = "/student/fee_payment", method = RequestMethod.GET)
    @ResponseBody
    public StageUpdateResponse FeePayment() {
    	String username = getUsername();
    	int nextStage = stageManager.getNextStage(4);
    	studentDAO.updateStage(username, stageManager.getCurrentRep(nextStage));
    	studentDAO.allocateIc(username);
        return new StageUpdateResponse(nextStage, true);
    }



    //TODO: Logic of why I did this  is unclear.
    @SneakyThrows
    @RequestMapping(value = "/student/stage", method = RequestMethod.POST)
    @ResponseBody
    public StageResponse getStage(@RequestBody final LoginController.LoginRequest request) {
        Student student = studentDAO.getStudent(getUsername());
        if(student!= null)
            return new StageResponse(true, stageManager.getCurrentStage(student.getStage()));
        return new StageResponse(false, -1);
    }

    @SneakyThrows
    @RequestMapping(value = "/student/save_resume", method = RequestMethod.POST)
    @ResponseBody
    public ResumeSaveResponse saveResume(@RequestBody final ResumeSave request) {
        if(!isAnonymous()) {
            Student student = studentDAO.getStudent(getUsername());
            if (student != null) { //Don't need this check in principle
                studentDAO.saveResume(student, request.getResumeData(), request.getType());
                int nextStage = stageManager.getNextStage(2);
                //TODO: save this next stage into DB
                return new ResumeSaveResponse(true, nextStage);
            }
        }
        return new ResumeSaveResponse(false, -1);
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

    @Data
    @AllArgsConstructor
    public static class StageUpdateResponse {
        private int stage;
        private boolean success;
    }

    @Data
    @AllArgsConstructor
    public static class StageResponse {
        private boolean authenticated;
        private int stage;
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
        private String type;
        private String resumeData;
    }

}
