package com.skilciti.client.controller;

import com.skilciti.client.entity.User;
import com.skilciti.client.event.RegistrationCompleteEvent;
import com.skilciti.client.model.UserModel;
import com.skilciti.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);

        applicationEventPublisher.publishEvent(new RegistrationCompleteEvent(user, getApplicationUrl(request)));
        return "User has been saved successfully";
    }

    private String getApplicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
