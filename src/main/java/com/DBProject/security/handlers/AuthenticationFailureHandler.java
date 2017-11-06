package com.DBProject.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jatin on 15/10/17.
 */
@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse servletResponse, final AuthenticationException exception) throws IOException {
        servletResponse.setContentType("application/json;charset=UTF-8");
        int status = HttpServletResponse.SC_OK;
        servletResponse.setStatus(status);
        PrintWriter writer = servletResponse.getWriter();
        AuthenticationFailureResponse response = new AuthenticationFailureResponse(status, false, exception.getMessage());
        System.out.println("failed");
        String jsonResponse = new ObjectMapper().writeValueAsString(response);
        writer.write(jsonResponse);
        writer.flush();
        writer.close();
    }

    @Data
    @AllArgsConstructor
    public static class AuthenticationFailureResponse {
        private int status;
        private boolean authenticated;
        private String reason;
    }

}
