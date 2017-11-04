package com.DBProject.repository;

import com.DBProject.domain.Password;

/**
 * Created by Jatin on 01/11/17.
 */
public interface PasswordDAO {
    public Password getByUsername(final String username);
}
