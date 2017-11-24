package com.DBProject.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jatin on 23/11/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jaf implements Serializable {
	private static final long serialVersionUID = 1L;
	String jid;
    String jname;
    String cid;
    String salary;
    String location;
    String description;
    String stage;
    Date companyDeadline;
    Date jafDeadline;
}
