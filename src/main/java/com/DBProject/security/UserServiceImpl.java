package com.DBProject.security;

import com.DBProject.repository.StudentDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.google.common.collect.Lists;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by Jatin on 15/10/17.
 */
@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    StudentDAOImpl studentDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        List<GrantedAuthority> authorities;
        if(username.equals("Student")) {
            authorities = buildUserAuthority("ROLE_"+"STUDENT");
            return new User (username, "student@123", authorities);

        }
        else if(username.equals("Coordinator")) {
            authorities = buildUserAuthority("ROLE_"+"COORDINATOR");
            return new User (username, "coordinator@123", authorities);
        }
        else {
            return new User ("Jatin", "jatina", null);
        }
    }

    private User buildUserForAuthentication(final List<GrantedAuthority> authorities) {
        return new User("Student", "student@123", authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String roleString) {
//        String roleString ="ROLE_"+ "ADMIN"; //get role of a known user
        return Lists.newArrayList(new SimpleGrantedAuthority(roleString));
    }
}
