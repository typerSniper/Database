package com.DBProject.repository;

import com.DBProject.domain.Resume;

import java.util.List;

/**
 * Created by Jatin on 04/11/17.
 */
public interface ResumeDAO {
    List<Resume> getByUsername(String username);
}
