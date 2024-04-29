package com.example.carsharing.service.impl;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.User;
import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.exception.UserAlreadyExistException;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.UserMapper;
import com.example.carsharing.repository.UserInfoRepository;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserInfoRepository userInfoRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public User getUserById(UUID id) {
        User user =  userRepository.getUserByUserId(id);
        if (user == null){
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteUserById(UUID id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotExistException(ErrorMessage.USER_NOT_EXIST));
        userRepository.deleteUserByUserId(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(userRegistrationDto.getEmail());
        if(userInfo.isPresent()){
            throw new UserAlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }

        User entity = userMapper.toEntity(userRegistrationDto);
        User userAfterCreation = userRepository.save(entity);
        return userMapper.toDto(userAfterCreation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User updateUserById(UUID id, UserRegistrationDto userRegistrationDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotExistException(ErrorMessage.USER_NOT_EXIST));

        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());

        //TODO как обновлять UserInfo
        return userRepository.saveAndFlush(user);    }
}
