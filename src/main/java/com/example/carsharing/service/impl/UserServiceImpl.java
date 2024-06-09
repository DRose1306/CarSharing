package com.example.carsharing.service.impl;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.Role;
import com.example.carsharing.entity.User;
import com.example.carsharing.entity.UserInfo;
import com.example.carsharing.entity.enums.RoleName;
import com.example.carsharing.exception.RoleNotFoundException;
import com.example.carsharing.exception.UserAlreadyExistException;
import com.example.carsharing.exception.UserNotExistException;
import com.example.carsharing.exception.message.ErrorMessage;
import com.example.carsharing.mapper.UserMapper;
import com.example.carsharing.repository.RoleRepository;
import com.example.carsharing.repository.UserInfoRepository;
import com.example.carsharing.repository.UserRepository;
import com.example.carsharing.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;

    /**
     * Retrieves a user by its identifier.
     *
     * @param id User identifier.
     * @return User object.
     * @throws UserNotExistException if the user does not exist.
     */
    @Override
    @Transactional
    public User getUserById(UUID id) {
        User user = userRepository.getUserByUserId(id);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return user;
    }

    /**
     * Retrieves all users.
     *
     * @return List of all users.
     */
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Deletes a user by its identifier.
     *
     * @param id User identifier.
     * @return A message about the successful deletion of the user.
     * @throws UserNotExistException if the user does not exist.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String deleteUserById(UUID id) {
        User user = userRepository.getUserByUserId(id);
        if (user != null) {
            userRepository.deleteById(id);
            return "User with this ID was deleted SUCCESSFULLY";
        } else {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
    }

    /**
     * Creates a new user.
     *
     * @param userRegistrationDto Data about the user to be created.
     * @return Data about the created user.
     * @throws UserAlreadyExistException if the user already exists.
     * @throws RoleNotFoundException     if the role does not exist.
     */
    @Override
    @Transactional
    public UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        if (userInfoRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }
        Role role = roleRepository.findByRoleName(RoleName.ROLE_USER);
        if (role == null) {
            throw new RoleNotFoundException(ErrorMessage.ROLE_NOT_FOUND);
        }

        User entity = userMapper.toEntity(userRegistrationDto);

        UserInfo userInfo = entity.getUserInfo();
        userInfo.setRoles(Collections.singleton(role));

        User userAfterCreation = userRepository.save(entity);
        return userMapper.toDto(userAfterCreation);
    }

    /**
     * Updates user information by its identifier.
     *
     * @param id       User identifier.
     * @param updating Updated user data.
     * @return Updated user object.
     * @throws UserNotExistException if the user does not exist.
     */
    @Override
    @Transactional
    public User updateUserById(UUID id, User updating) {
        User updated = userRepository.getUserByUserId(id);
        if (updated == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }

        Set<String> nullProperties = getNullPropertyNames(updating);
        BeanUtils.copyProperties(updating, updated, nullProperties.toArray(new String[0]));

        updated = userRepository.save(updated);

        return updated;
    }

    private Set<String> getNullPropertyNames(Object object) {
        final BeanWrapper src = new BeanWrapperImpl(object);
        PropertyDescriptor[] descriptors = src.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        Arrays.stream(descriptors).forEach(descriptor -> {
            Object propertyValue = src.getPropertyValue(descriptor.getName());
            if (propertyValue == null) {
                nullProperties.add(descriptor.getName());
            }
        });
        return nullProperties;
    }
}
