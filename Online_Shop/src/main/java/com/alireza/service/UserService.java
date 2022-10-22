package com.alireza.service;

import com.alireza.model.User;
import com.alireza.repository.UserRepository;

public class UserService {
    public Integer createUser(User user) {
        return UserRepository.create(user);
    }

    public User findUser(String username, String nationalCode) {
        return UserRepository.find(username, nationalCode);
    }
}
