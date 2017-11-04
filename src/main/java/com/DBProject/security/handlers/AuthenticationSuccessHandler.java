package com.DBProject.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

/**
 * Created by Jatin on 15/10/17.
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse servletResponse, final Authentication authentication) throws IOException {
        servletResponse.setContentType("application/json;charset=UTF-8");
        int status;
        AuthenticationSuccessResponse response;
        if(isValid(request)) {
            status = HttpServletResponse.SC_OK;
            servletResponse.setStatus(status);
            response = new AuthenticationSuccessResponse(status, "Authentication success.", true);
        }
        else {
            status = HttpServletResponse.SC_FORBIDDEN;
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
            response = new AuthenticationSuccessResponse(status, "Access Denied", false);
        }
        PrintWriter writer = servletResponse.getWriter();
        String jsonResponse = new ObjectMapper().writeValueAsString(response);
        writer.write(jsonResponse);
        writer.flush();
        writer.close();
    }

    @Data
    @AllArgsConstructor
    public static class AuthenticationSuccessResponse {
        private int status;
        private String message;
        boolean authenticated;
    }

    public boolean isValid(final HttpServletRequest request) {
        String type = request.getParameter("type");
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        switch (type) {
            case "student":
                return authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"));
            case "coordinator" :
                return authorities.contains(new SimpleGrantedAuthority("ROLE_COORDINATOR"));
            case "company" :
                return authorities.contains(new SimpleGrantedAuthority("ROLE_COMPANY"));
            default:
                return false;

        }
    }

}
