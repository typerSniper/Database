package com.DBProject.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@Lazy
@RestController
public class LoginController {

    @SneakyThrows
    @RequestMapping(value = "/app/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginResponse getHomeJSON(@RequestBody final LoginRequest request) {
        return new LoginResponse(true);
    }

    @Data
    @AllArgsConstructor
    public static class LoginResponse {
        private boolean authenticated;
    }
    @Data
    @AllArgsConstructor
    public static class LoginRequest {
        private String username;
        private String password;
        private String type;
    }




}
