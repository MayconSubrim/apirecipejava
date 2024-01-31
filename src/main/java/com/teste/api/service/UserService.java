package com.teste.api.service;

import org.springframework.stereotype.Service;

import com.teste.api.dtos.UserDto;
import com.teste.api.models.UserModel;
import com.teste.api.repository.UserRepository;

@Service
public class UserService {

    final UserRepository userRepository;

    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserModel save(UserDto dto) {
        UserModel user = new UserModel(dto);
        return userRepository.save(user);
    }
}
