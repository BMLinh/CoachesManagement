package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.User.UserDto;
import com.hal.CoachesWeb.model.User.UserLogin;
import com.hal.CoachesWeb.repositories.UserRepository;
import com.hal.CoachesWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getall")
    List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Query user successfully", new UserDto().userToDto(user.get()))
            );
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(400, "Can not found user id= " + id, "")
            );
    }

    @PostMapping("/signin")
    ResponseEntity<ResponseObject> signIn(@RequestBody UserLogin userSignin) {
        return userService.signIn(userSignin.getPhone(), userSignin.getPassword());
    }

    @PostMapping("/signup")
    ResponseEntity<ResponseObject> signUp(@RequestBody User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(400, "Required field is missing", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(400, "Require field data is missing", "")
            );
        }
        return userService.signUp(newUser);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(400, "Required field is missing", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(400, "Require field data is missing", "")
            );
        }
        return userService.updateUser(newUser);
    }

    @PutMapping("/updateStatus/{id}")
    ResponseEntity<ResponseObject> updateUserStatus(@PathVariable int id, @RequestBody int status){
        return userService.updateUserStatus(id, status);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Delete user successfully",""));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(400, "Can not found user id= "+id, "")
            );
    }

    private boolean isFieldMissing(User user) {
        if (user.getPhone() == null || user.getPassword() == null
                || user.getEmail() == null || user.getFullname() == null) {
            return true;
        }
        return false;
    }

    private boolean isDataMissing(User user){
        if (user.getPhone().isBlank() || user.getPassword().isBlank()
                || user.getEmail().isBlank() || user.getFullname().isBlank()) {
            return true;
        }
        return false;
    }
}
