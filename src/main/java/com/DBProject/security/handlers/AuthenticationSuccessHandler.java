package com.DBProject.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jatin on 15/10/17.
 */
@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse servletResponse, final Authentication authentication) throws IOException {
        servletResponse.setContentType("application/json;charset=UTF-8");

        int status = HttpServletResponse.SC_OK;
        servletResponse.setStatus(status);
        PrintWriter writer = servletResponse.getWriter();
        AuthenticationSuccessResponse response = new AuthenticationSuccessResponse(status, "Authentication success.", true);
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

}
