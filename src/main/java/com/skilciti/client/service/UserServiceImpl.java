package com.skilciti.client.service;

import com.skilciti.client.entity.TokenVerification;
import com.skilciti.client.entity.User;
import com.skilciti.client.model.UserModel;
import com.skilciti.client.repository.TokenVerificationRepository;
import com.skilciti.client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenVerificationRepository tokenVerificationRepository;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public void saveUserVerificationToken( User user,String token) {
        TokenVerification tokenVerification = new TokenVerification(user,token);
        tokenVerificationRepository.save(tokenVerification);
    }
}
