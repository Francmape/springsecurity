package com.skilciti.client.event.listener;

import com.skilciti.client.entity.User;
import com.skilciti.client.event.RegistrationCompleteEvent;
import com.skilciti.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j // just to log
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //Create link with verification token for the User
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveUserVerificationToken(user, token);

        //Send Mail
        String url = event.getApplicationUrl()
                + "/verifyRegistration?token="
                + token;
        // Send verificationMail()
        log.info("Click the link to verify your account: {}", url);
    }
}
