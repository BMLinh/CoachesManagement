package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.UserLogin;
import com.hal.CoachesWeb.repositories.UserRepository;
import com.hal.CoachesWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/signin")
    ResponseEntity<ResponseObject> logIn(@RequestBody UserLogin userLogin){
        return userService.logIn(userLogin.getPhone(), userLogin.getPassword());
    }

    @PostMapping("/signup")
    ResponseEntity<ResponseObject> signUp(@RequestBody User newUser){
        return userService.signUp(newUser);
    }


//    @GetMapping("/id")
//    ResponseEntity<ResponseObject> getUserById(@RequestBody int id){
//        Optional<User> user = userRes.findById(id);
//        if (user.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query user successfully", user)
//            );
//        }
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Can not found user id= "+id, "")
//            );
//    }
//
//    @GetMapping("/phone")
//    ResponseEntity<ResponseObject> getUserByPhone(@RequestBody String phone){
//        Optional<User> user = userRes.findByPhone(phone);
//        if (user.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query user successfully", user)
//            );
//        }
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Can not found user phone= "+phone, "")
//            );
//    }
//
//
//    @PutMapping("/update")
//    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser){
//        Optional<User> user = userRes.findById(newUser.getId())
//                .map(u -> {
//                    u.setAvata(newUser.getAvata());
//                    u.setDob(newUser.getDob());
//                    u.setEmail(newUser.getEmail());
//                    u.setFullname(newUser.getFullname());
//                    u.setGender(newUser.getGender());
//                    u.setPassword(newUser.getPassword());
//                    u.setPhone(newUser.getPhone());
//                    return userRes.save(u);
//                });
//        if (user.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Query user successfully", user)
//            );
//        }
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Can not found user id= "+newUser.getId(), "")
//            );
//    }
//
//    @DeleteMapping("/updateUserStatus")
//    ResponseEntity<ResponseObject> updateUserStatus(@RequestBody int id, int status){
//        Optional<User> user = userRes.findById(id).map(u->{u.setStatus(status);
//            return userRes.save(u);});
//        if (user.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete user sucessfully",""));
//        }
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Can not found user id= "+id, "")
//            );
//
//    }
//
//    @DeleteMapping("/deleteUser")
//    ResponseEntity<ResponseObject> deleteUser(@RequestBody int id){
//        boolean isExist = userRes.existsById(id);
//        if (isExist){
//            userRes.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Delete user successfully",""));
//        }
//        else
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("failed", "Can not found user id= "+id, "")
//            );
//    }
}
