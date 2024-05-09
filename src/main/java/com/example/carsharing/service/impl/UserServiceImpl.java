package com.example.carsharing.service.impl;

import com.example.carsharing.dto.UserAfterRegistrationDto;
import com.example.carsharing.dto.UserRegistrationDto;
import com.example.carsharing.entity.Address;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserInfoRepository userInfoRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public User getUserById(UUID id) {
        User user = userRepository.getUserByUserId(id);
        if (user == null) {
            throw new UserNotExistException(ErrorMessage.USER_NOT_EXIST);
        }
        return user;
    }

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

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserAfterRegistrationDto createUser(UserRegistrationDto userRegistrationDto) {
        if (userInfoRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new UserAlreadyExistException(ErrorMessage.USER_ALREADY_EXIST);
        }
        Role role = roleRepository.findByRoleName(RoleName.USER);
        if (role == null) {
            throw new RoleNotFoundException(ErrorMessage.ROLE_NOT_FOUND);
        }

        User entity = userMapper.toEntity(userRegistrationDto);

        UserInfo userInfo = entity.getUserInfo();
        userInfo.setRoles(Collections.singleton(role));

        User userAfterCreation = userRepository.save(entity);
        return userMapper.toDto(userAfterCreation);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public User updateUserById(UUID id, User user) {
        User getUser = getUserById(id);
        if (getUser != null) {
            User comparedUser = compareUsers(getUser, user);

            return userRepository.save(comparedUser);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User compareUsers(User updated, User updating) {
        User comparedUser = new User();

        comparedUser.setUserId(Objects.equals(updated.getUserId(), updating.getUserId()) ? updated.getUserId() : updating.getUserId());
        comparedUser.setFirstName(Objects.equals(updated.getFirstName(), updating.getFirstName()) ? updated.getFirstName() : updating.getFirstName());
        comparedUser.setLastName(Objects.equals(updated.getLastName(), updating.getLastName()) ? updated.getLastName() : updating.getLastName());

        UserInfo comparedUserInfo = compareUserInfo(updated.getUserInfo(), updating.getUserInfo());
        comparedUser.setUserInfo(comparedUserInfo);

        return comparedUser;
    }

    public UserInfo compareUserInfo(UserInfo updated, UserInfo updating) {
        UserInfo comparedUserInfo = new UserInfo();

        comparedUserInfo.setDateOfBirth(Objects.equals(updated.getDateOfBirth(), updating.getDateOfBirth()) ? updated.getDateOfBirth() : updating.getDateOfBirth());
        comparedUserInfo.setPhoneNumber(Objects.equals(updated.getPhoneNumber(), updating.getPhoneNumber()) ? updated.getPhoneNumber() : updating.getPhoneNumber());
        comparedUserInfo.setEmail(Objects.equals(updated.getEmail(), updating.getEmail()) ? updated.getEmail() : updating.getEmail());
        comparedUserInfo.setLogin(Objects.equals(updated.getLogin(), updating.getLogin()) ? updated.getLogin() : updating.getLogin());
        comparedUserInfo.setPassword(Objects.equals(updated.getPassword(), updating.getPassword()) ? updated.getPassword() : updating.getPassword());
        comparedUserInfo.setCardNumber(Objects.equals(updated.getCardNumber(), updating.getCardNumber()) ? updated.getCardNumber() : updating.getCardNumber());
        comparedUserInfo.setDriverLicense(Objects.equals(updated.getDriverLicense(), updating.getDriverLicense()) ? updated.getDriverLicense() : updating.getDriverLicense());
        comparedUserInfo.setDriverLicenseIdentifier(Objects.equals(updated.getDriverLicenseIdentifier(), updating.getDriverLicenseIdentifier()) ? updated.getDriverLicenseIdentifier() : updating.getDriverLicenseIdentifier());

        Address comparedAddress = compareAddress(updated.getAddress(), updating.getAddress());
        comparedUserInfo.setAddress(comparedAddress);

        return comparedUserInfo;
    }

    private Address compareAddress(Address updated, Address updating) {
        Address comparedAddress = new Address();

        comparedAddress.setStreet(Objects.equals(updated.getStreet(), updating.getStreet()) ? updated.getStreet() : updating.getStreet());
        comparedAddress.setHouseNumber(Objects.equals(updated.getHouseNumber(), updating.getHouseNumber()) ? updated.getHouseNumber() : updating.getHouseNumber());
        comparedAddress.setCity(Objects.equals(updated.getCity(), updating.getCity()) ? updated.getCity() : updating.getCity());
        comparedAddress.setZipCode(Objects.equals(updated.getZipCode(), updating.getZipCode()) ? updated.getZipCode() : updating.getZipCode());
        comparedAddress.setCountry(Objects.equals(updated.getCountry(), updating.getCountry()) ? updated.getCountry() : updating.getCountry());

        return comparedAddress;
    }
}
