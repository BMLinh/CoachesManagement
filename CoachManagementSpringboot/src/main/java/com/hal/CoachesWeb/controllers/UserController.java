package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.models.ResponseObject;
import com.hal.CoachesWeb.models.User;
import com.hal.CoachesWeb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "CoachManagement/User")
public class UserController {
    @Autowired
    private UserRepository userRes;
    @GetMapping("")
    List<User> getAllUser() {
        return userRes.findAll();
    }

    @GetMapping("/id")
    ResponseEntity<ResponseObject> getUserById(@RequestBody int id){
        Optional<User> user = userRes.findById(id);
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user sucessfully", user)
            );
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not found user id= "+id, "")
            );
    }

    @GetMapping("/phone")
    ResponseEntity<ResponseObject> getUserByPhone(@RequestBody String phone){
        Optional<User> user = userRes.findByPhone(phone);
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user sucessfully", user)
            );
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not found user phone= "+phone, "")
            );
    }

    @PostMapping("/addUser")
    ResponseEntity<ResponseObject> addUser(@RequestBody User user){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Add user sucessfully",userRes.save(user))
            );
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser){
        Optional<User> user = userRes.findById(newUser.getId())
                .map(u -> {
                    u.setAvata(newUser.getAvata());
                    u.setDob(newUser.getDob());
                    u.setEmail(newUser.getEmail());
                    u.setFullname(newUser.getFullname());
                    u.setGender(newUser.getGender());
                    u.setPassword(newUser.getPassword());
                    u.setPhone(newUser.getPhone());
                    return userRes.save(u);
                });
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query user sucessfully", user)
            );
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not found user id= "+newUser.getId(), "")
            );
    }

    @DeleteMapping("/updateUserStatus")
    ResponseEntity<ResponseObject> updateUserStatus(@RequestBody int id){
        Optional<User> user = userRes.findById(id).map(u->{u.setStatus(0);
            return userRes.save(u);});
        if (user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user sucessfully",""));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not found user id= "+id, "")
            );

    }

    @DeleteMapping("/deleteUser")
    ResponseEntity<ResponseObject> deleteUser(@RequestBody int id){
        boolean isExist = userRes.existsById(id);
        if (isExist){
            userRes.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete user sucessfully",""));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Can not found user id= "+id, "")
            );

    }
}
