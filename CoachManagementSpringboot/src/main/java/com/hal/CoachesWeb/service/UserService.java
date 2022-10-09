package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.User.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUser();

    UserDto getUserById(int id);

    UserDto getUserByPhone(String phone);

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(int id);

    boolean existsByPhone(String phone);
    boolean existsById(int id);

    boolean isCorrectPassword(String phone, String password);

}