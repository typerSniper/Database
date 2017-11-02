package com.DBProject.security;

import com.DBProject.domain.Password;
import com.DBProject.repository.PasswordDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * Created by Jatin on 15/10/17.
 */
@Component
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    PasswordDAOImpl passwordDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        List<GrantedAuthority> authorities;
        if(username.equals("Student")) {
            authorities = buildUserAuthority("STUDENT");
            return new User (username, "student@123", authorities);

        }
        else if(username.equals("Coordinator")) {
            authorities = buildUserAuthority("COORDINATOR");
            return new User (username, "coordinator@123", authorities);
        }
        else {
            Password password  = passwordDAO.getByUsername(username);
            if(password!=null)
                return new User (username, password.getPassword(), buildUserAuthority(password.getRole()));
            return new User(username, UUID.randomUUID().toString(), buildUserAuthority("ANONYMOUS"));
            //                return new User("Anonymous", "Anonymous",buildUserAuthority( "ANONYMOUS"));
        }
    }

    private List<GrantedAuthority> buildUserAuthority(String roleString) {
//        String roleString ="ROLE_"+ "ADMIN"; //get role of a known user
        return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_" + roleString));
    }
}
