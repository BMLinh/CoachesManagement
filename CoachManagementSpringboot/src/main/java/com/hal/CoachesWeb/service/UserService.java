package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.response.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUser();

    UserDto getUserById(int id);

    UserDto getUserDtoByPhone(String phone);
    User getUserByPhone(String phone);

    boolean addUser(User user);

    boolean updateUser(User user);

//    boolean updateStatus(User user);

    boolean deleteUser(int id);

    boolean existsByPhone(String phone);

    boolean existsById(int id);

    boolean isCorrectPassword(String phone, String password);

    boolean isActiveByPhone(String phone);
    boolean isActiveById(int id);
}