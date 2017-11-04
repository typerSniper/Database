package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
    private String name;
    private String deptId;
    private String progId;
    private String cpi;
    private String stage;
}
