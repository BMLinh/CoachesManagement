package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.response.UserDto;
import com.hal.CoachesWeb.repositories.RoleResponsitory;
import com.hal.CoachesWeb.repositories.UserRepository;
import com.hal.CoachesWeb.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleResponsitory roleResponsitory;

    @Override
    public List<UserDto> getAllUser(){
        List<UserDto> userDto = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDto newUserDto = new UserDto().userToDto(user);
            newUserDto.setRole(roleResponsitory.getById(user.getRoleId()).getName());
            userDto.add(newUserDto);
        });
        return userDto;
    }
    @Override
    public UserDto getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            UserDto userDto = new UserDto().userToDto(user.get());
            userDto.setRole(roleResponsitory.getById(user.get().getRoleId()).getName());
            return userDto;
        }
        return null;
    }
    @Override
    public UserDto getUserByPhone(String phone){
        Optional<User> user = userRepository.getUserByPhone(phone);
        if (user.isPresent()){
            UserDto userDto = new UserDto().userToDto(user.get());
            userDto.setRole(roleResponsitory.getById(user.get().getRoleId()).getName());
            return userDto;
        }
        return null;
    }
    @Override
    public boolean addUser(User user){
        try {
            System.out.println(user.getRoleId());
            userRepository.save(new User(user.getPassword(),user.getFullname(), user.getEmail(), user.getPhone()
                    , user.getGender(), user.getAvatar(), user.getRoleId(), user.getStatus()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateUser(User user){
        try {
            userRepository.save(user);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteUser(int id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }
    @Override
    public boolean existsById(int id){
        return userRepository.existsById(id);
    }
    @Override
    public boolean isCorrectPassword(String phone, String password){
        return userRepository.getUserByPhone(phone).get().getPassword().equals(password);
    }

}
