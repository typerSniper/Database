package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jatin on 01/11/17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password implements Serializable {
    private String username;
    private String password;
    private String role;

}
