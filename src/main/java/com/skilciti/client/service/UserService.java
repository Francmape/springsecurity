package com.skilciti.client.service;

import com.skilciti.client.entity.User;
import com.skilciti.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveUserVerificationToken(User user,String token);
}
