package com.example.carsharing.service.impl;

import com.example.carsharing.entity.User;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id) {
        User user =  userRepository.getUserByUserId(id);
        if (user == null){
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return user;
    }
}
