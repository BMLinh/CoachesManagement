package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hal.CoachesWeb.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<ResponseObject> logIn(String phone, String password){
        if (userRepository.existsByPhone(phone)){
            User user = userRepository.findUserByPhone(phone);
            if (user.getPassword().equals(password)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Log in success", user)
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponseObject("failed", "Password not correct", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Can not found user phone= " + phone, "")
            );
    }

    public ResponseEntity<ResponseObject> signUp(User newUser){
        if (!userRepository.existsByPhone(newUser.getPhone())){
            User user = new User();
            user.setAvata(newUser.getAvata());
            user.setDob(newUser.getDob());
            user.setEmail(newUser.getEmail());
            user.setFullname(newUser.getFullname());
            user.setGender(newUser.getGender());
            user.setPassword(newUser.getPassword());
            user.setPhone(newUser.getPhone());
            user.setRoleId(1);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Sign up successfully",userRepository.save(user))
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseObject("failed", "Phone number already taken", "")
        );
    }
}
