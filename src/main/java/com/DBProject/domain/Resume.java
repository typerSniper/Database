package com.DBProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Jatin on 04/11/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resume implements Serializable {
    String resume;
    String proofs;
    String rtype;
    String username;
}
