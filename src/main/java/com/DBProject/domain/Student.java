package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jatin on 06/10/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
	private String username;
    private String name;
    private String deptName;
    private int totalCredits;
    private int stage;
    public static enum studentStages{
         DetailsPending ,ResumePending, FeePending, JafEligible;
        public static String getNextStage(String currentStage) {
            if(currentStage.equals(studentStages.values()[studentStages.values().length-1].toString()))
                return currentStage;
            boolean found = false;
            for(studentStages stage : studentStages.values()) {
                if(stage.toString().equals(currentStage)) {
                    found = true;
                }
                else if(found) {
                    return stage.toString();
                }
            }
            return "DetailsPending";
        }

    };
}
