package com.DBProject.Controller.ajax;

import com.DBProject.domain.Coordinator;
import com.DBProject.domain.Jaf;
import com.DBProject.domain.Student;
import com.DBProject.repository.CompanyDAOImpl;
import com.DBProject.repository.StudentDAOImpl;
import com.DBProject.service.JAFStageManager;
import com.DBProject.service.StudentStageManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.DBProject.Controller.DefaultController.getUsername;

@Lazy
@RestController
public class StudentController {
    @Autowired
    private StudentDAOImpl studentDAO;

    @Autowired
    private StudentStageManager stageManager;

    @Autowired
    private CompanyDAOImpl companyDAO;

    @Autowired
    private JAFStageManager jafStageManager;

    @SneakyThrows
    @RequestMapping(value = "/student/save_details", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse saveDetails(@RequestBody final SaveDetailsRequest saveDetailsRequest) {
        System.out.println(saveDetailsRequest);
        Student student = studentDAO.getStudent(getUsername());
        String currentStage = student.getStage();
        if(!currentStage.toLowerCase().equals("registered")) {
            return new StageUpdateResponse(-1, false);
        }
        String nextStage = stageManager.getNextStage(currentStage);
        if(nextStage!=null){
            boolean success = studentDAO.saveDetails(student.getUsername(), saveDetailsRequest, nextStage);
            return new StageUpdateResponse(stageManager.getCurrentStage(nextStage), success);
        }
        else {
            return new StageUpdateResponse(-1, false);
        }
    }

    @SneakyThrows
    @RequestMapping(value = "/student/fee_payment", method = RequestMethod.GET)
    @ResponseBody
    public FeePayment FeePayment() {
    	String username = getUsername();
        Student student = studentDAO.getStudent(getUsername());
        String currentStage = student.getStage();
        if(!currentStage.toLowerCase().equals("feepending")){
            return new FeePayment(-1, false, null, null);
        }
        String nextStage = stageManager.getNextStage(currentStage);
         Coordinator coordinator = studentDAO.allocateIc(username, nextStage);
        if(coordinator==null) {
            System.out.println("We are in trouble, coordinator not allocated");
            return new FeePayment(-1, false, null, null);
        }
        return new FeePayment(stageManager.getCurrentStage(nextStage), true, coordinator.getName(),coordinator.getContactNumber());
    }

    @SneakyThrows
    @RequestMapping(value = "/student/stage", method = RequestMethod.GET)
    @ResponseBody
    public StageResponse getStage() {
        Student student = studentDAO.getStudent(getUsername());
        if(student!= null)
            return new StageResponse(true, stageManager.getCurrentStage(student.getStage()));
        return new StageResponse(false, -1);
    }

    @SneakyThrows
    @RequestMapping(value = "/student/save_resume", method = RequestMethod.POST)
    @ResponseBody
    public ResumeSaveResponse saveResume(@RequestBody final ResumeSave request) {
        Student student = studentDAO.getStudent(getUsername());
        String currentStage = student.getStage();
        if(!currentStage.toLowerCase().equals("resumepending")) {
            return new ResumeSaveResponse(false, -1);
        }
        String nextStage = stageManager.getNextStage(currentStage);
        boolean success = studentDAO.saveResume(student, request.getResumeData(), request.getType(), nextStage);
        return new ResumeSaveResponse(success, stageManager.getCurrentStage(nextStage));
    }




    @SneakyThrows
    @RequestMapping(value = "/student/get_resume_deadline", method = RequestMethod.GET)
    @ResponseBody
    public GetDeadLineResponse getDeadLineResponse() {
        return new GetDeadLineResponse(studentDAO.getResumeDeadline());
    }


   @SneakyThrows
   @RequestMapping(value = "/student/allocated_ic", method = RequestMethod.GET)
   @ResponseBody
   public AllocatedIcResponse getAlloactedIc() {
       String username = getUsername();
       Coordinator coordinator = studentDAO.getAllocatedIc(username);
       if(coordinator!=null)
           return new AllocatedIcResponse(true, coordinator.getName(), coordinator.getContactNumber());
       else
           return new AllocatedIcResponse(false, null, null);
   }

    @SneakyThrows
    @RequestMapping(value = "/student/get_all_jafs", method = RequestMethod.GET)
    @ResponseBody
    public GetJafsResponse getJafsResponse() {
        return new GetJafsResponse(companyDAO.getAllJafs().stream()
                .filter(t-> jafStageManager.getCurrentStage(t.getStage())==3)
                .filter(t->t.getJafDeadline().after(Calendar.getInstance().getTime()))
                .map(t->new JafRepresentative(t.getJname(), t.getJafDeadline(),
                        companyDAO.getEligible(getUsername(), t.getJid()),t.getCid(),
                        companyDAO.getIfSigned(getUsername(), t.getJid()), t.getJid())).collect(Collectors.toList()));
    }

    @SneakyThrows
    @RequestMapping(value ="/student/sign_jaf", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse signJaf(@RequestBody JafRequest request) {
        return new StageUpdateResponse(-1, companyDAO.signJaf(getUsername(), request.getJafID()));
    }

    @SneakyThrows
    @RequestMapping(value ="/student/unsign_jaf", method = RequestMethod.POST)
    @ResponseBody
    public StageUpdateResponse unSignJaf(@RequestBody JafRequest request) {
        return new StageUpdateResponse(-1, companyDAO.unSignJaf(getUsername(), request.getJafID()));
    }


    @SneakyThrows
    @RequestMapping(value="/student/get_jaf_details", method = RequestMethod.POST)
    @ResponseBody
    public JafResponse jafResponse(@RequestBody JafRequest jafRequest) {
        return new JafResponse(companyDAO.getJaf(jafRequest.getJafID()));
    }
    
    
    @SneakyThrows
    @RequestMapping(value="/student/elig_jafs", method = RequestMethod.GET)
    @ResponseBody
    public EligJafsResponse eligJafResponse() {
    	String username = getUsername();
        return new EligJafsResponse(studentDAO.getEligJafsSIGN(username), studentDAO.getEligJafsUNSIGN(username), studentDAO.getUneligJafs(username));
    }
    
    @Data
    @AllArgsConstructor
    public static class EligJafsResponse {
        List<Jaf> elig_jafs_sign;
        List<Jaf> elig_jafs_unsign;
        List<Jaf> unelig_jafs;
    }

    @Data
    @AllArgsConstructor
    public static class JafResponse {
        Jaf jaf;
    }

    @Data
    @AllArgsConstructor
    public static class JafRequest {
        String jafID;
    }


    @Data
    @AllArgsConstructor
    public static class GetJafsResponse {
        List<JafRepresentative> jafRepresentatives;
    }

    @Data
    @AllArgsConstructor
    public static class JafRepresentative {
        private String jname;
        private Date jafDeadline;
        private boolean eligible;
        private String company;
        private boolean isSigned;
        private String jid;
    }

    @Data
    @AllArgsConstructor
    public static class AllocatedIcResponse {
        private boolean status;
        private String coordinatorName;
        private String phoneNumber;
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
        private CollegeDetails detail10th;
        private CollegeDetails detail12th;
        private CollegeDetails others;
     }

    @Data
    @AllArgsConstructor
    public static class GetDeadLineResponse {
        private Date deadline;
    }

    @Data
    @AllArgsConstructor
    public static class CollegeDetails {
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
        private boolean success;
        private int stage;
    }

    @Data
    @AllArgsConstructor
    public static class ResumeSave {
        private String type;
        private String resumeData;
    }

    @Data
    @AllArgsConstructor
    public static class FeePayment {
        private int stage;
        private boolean success;
        private String coordinatorName;
        private String phoneNumber;
    }

}
