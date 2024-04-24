package com.example.carsharing.service.impl;

import com.example.carsharing.dto.UserAfterCreationDto;
import com.example.carsharing.dto.UserCreateDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.exception.UserAlreadyExistException;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.UserMapper;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserById(UUID id) {
        User user =  userRepository.getUserByUserId(id);
        if (user == null){
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        userRepository.deleteUserByUserId(id);
    }

    @Override
    public UserAfterCreationDto createUser(UserCreateDto userCreateDto) {
        User user = userRepository.findUserByFirstNameAndLastName(userCreateDto.getFirstName(), userCreateDto.getLastName());
        if (user != null){
            throw new UserAlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }
        User entity = userMapper.toEntity(userCreateDto);
        User userAfterCreation = userRepository.save(entity);
        return userMapper.toDto(userAfterCreation);
    }

    @Override
    @Transactional
    public User updateUserById(UUID id, User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setUserInfo(updatedUser.getUserInfo());

        return userRepository.saveAndFlush(user);    }
}
