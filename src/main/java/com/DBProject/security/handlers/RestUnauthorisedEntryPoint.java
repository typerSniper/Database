package com.DBProject.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jatin on 15/10/17.
 */

@Lazy
@Component
public class RestUnauthorisedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final AuthenticationException exception) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        int status = HttpServletResponse.SC_FORBIDDEN;
        httpServletResponse.setStatus(status);

        PrintWriter writer = httpServletResponse.getWriter();

        AccessDeniedResponse response = new AccessDeniedResponse(status, "Access Denied.", exception.getMessage());
        if(httpServletRequest.getContextPath().contains("student"))
            httpServletResponse.sendRedirect("/student");
        else if(httpServletRequest.getContextPath().contains("ic"))
            httpServletResponse.sendRedirect("/ic");
        else if(httpServletRequest.getContextPath().contains("company"))
            httpServletResponse.sendRedirect("/company");
        else {
            String jsonResponse = new ObjectMapper().writeValueAsString(response);
            writer.write(jsonResponse);
            writer.flush();
            writer.close();
        }
        //        writer.write(jsonResponse);
        //        writer.flush();
        //        writer.close();
    }

    @Data
    @AllArgsConstructor
    public static class AccessDeniedResponse {
        private int status;
        private String message;
        private String reason;
    }


}
