package com.DBProject.security;

import com.DBProject.security.handlers.AuthenticationFailureHandler;
import com.DBProject.security.handlers.AuthenticationSuccessHandler;
import com.DBProject.security.handlers.RestUnauthorisedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Jatin on 15/10/17.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private RestUnauthorisedEntryPoint restUnauthorisedEntryPoint;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/student/", "/ic/","/logout").permitAll();
        http.exceptionHandling().authenticationEntryPoint(restUnauthorisedEntryPoint);
        http.formLogin()
                .loginProcessingUrl("/app/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
        http.logout().logoutUrl("/logout").deleteCookies("JSESSIONID");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/student/home").access("hasRole('ROLE_STUDENT')");
        http.authorizeRequests().antMatchers("/ic/home").access("hasRole('ROLE_COORDINATOR')");
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

}
