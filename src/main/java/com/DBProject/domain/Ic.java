package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ic implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ic_id; // primary key
    private String name;
}
