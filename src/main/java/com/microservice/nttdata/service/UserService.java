package com.microservice.nttdata.service;

import com.microservice.nttdata.dto.SignUpUserDto;
import com.microservice.nttdata.entity.User;

import java.util.Optional;

public interface UserService {
    public User createUser(SignUpUserDto user);
    public Optional<User> getUserByEmail(String email);
    public User updateLastLogin(User user);
}
