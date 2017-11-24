package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jatin on 23/11/17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	String cid;
    String name;
    String contact;
    String email;
    String stage;
}
