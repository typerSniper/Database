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
	private static final long serialVersionUID = 1L;
	private String username;
    private String name;
    private String deptName;
    private int totalCredits;
    private int stage;
}
