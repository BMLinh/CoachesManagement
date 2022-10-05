package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.model.UserDto;
import com.hal.CoachesWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.hal.CoachesWeb.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<ResponseObject> signIn(String phone, String password){
        if (userRepository.existsByPhone(phone)){
            User user = userRepository.getUserByPhone(phone);
            if (user.getPassword().equals(password)){
                if (user.getStatus()==0){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("400", "Log in failed, your account is banned", "")
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("200", "Log in success", new UserDto().userToDto(user))
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponseObject("400", "Password not correct", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("400", "Can not found user phone= " + phone, "")
            );
    }

    public ResponseEntity<ResponseObject> signUp(User newUser){
        if (!userRepository.existsByPhone(newUser.getPhone())){
            User user = new User();
            user.setAvatar(newUser.getAvatar());
            user.setDob(newUser.getDob());
            user.setEmail(newUser.getEmail());
            user.setFullname(newUser.getFullname());
            user.setGender(newUser.getGender());
            user.setPassword(newUser.getPassword());
            user.setPhone(newUser.getPhone());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Sign up successfully",user.getStatus())
            );
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseObject("400", "Phone number already taken", "")
        );
    }
    
    public  ResponseEntity<ResponseObject> updateUser(User newUser){
        Optional<User> user = userRepository.findById(newUser.getId());
        if (user.isPresent()){
            if (!newUser.getPhone().equals(user.get().getPhone())) {
                if (userRepository.existsByPhone(newUser.getPhone())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                            new ResponseObject("400", "Phone number already taken", "")
                    );
                }
            }
            user.get().setPhone(newUser.getPhone());
            user.get().setAvatar(newUser.getAvatar());
            user.get().setDob(newUser.getDob());
            user.get().setEmail(newUser.getEmail());
            user.get().setFullname(newUser.getFullname());
            user.get().setGender(newUser.getGender());
            user.get().setPassword(newUser.getPassword());
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Update user successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("400", "Can not found user id= "+newUser.getId(), "")
        );
    }
    public ResponseEntity<ResponseObject> updateUserStatus(int id, int status){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setStatus(status);
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Update user successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("400", "Can not found user id= "+id, "")
        );
    }
}
