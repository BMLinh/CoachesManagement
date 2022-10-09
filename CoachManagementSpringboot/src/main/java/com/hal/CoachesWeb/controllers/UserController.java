package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.User.UserDto;
import com.hal.CoachesWeb.model.User.UserSignIn;
import com.hal.CoachesWeb.service.RoleService;
import com.hal.CoachesWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

//    @Autowired
//    private PasswordEncoder encoder;

    @GetMapping("/getall")
    ResponseEntity<ResponseObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả người dùng thành công", userService.getAllUser())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy người dùng thành công", userDto)
            );
        } else
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy người dùng", "")
            );
    }

    @PostMapping("/signin")
    ResponseEntity<ResponseObject> signIn(@Valid @RequestBody UserSignIn userSignin) {
        if (userService.existsByPhone(userSignin.getPhone())){
            if (userService.isCorrectPassword(userSignin.getPhone(), userSignin.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Đăng nhập thành công", userService.getUserByPhone(userSignin.getPhone()))
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Mật khẩu không đúng","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy số điện thoại","")
        );
    }

    @PostMapping("/signup")
    ResponseEntity<ResponseObject> signUp(@RequestBody User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thiếu trường dữ liệu", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        if (userService.getUserByPhone(newUser.getPhone())==null){
            newUser.setRoleId(1);
            newUser.setStatus(1);
            if (userService.addUser(newUser)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200,"Đăng ký thành công",userService.getUserByPhone(newUser.getPhone()))
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Đăng ký thất bại, không thể lưu mới người dùng", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Số điện thoại đã có tài khoản", "")
        );

    }

    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thiếu trường dữ liệu", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        UserDto userDto = userService.getUserById(newUser.getId());
        if (userDto!=null){
            if (!userDto.getPhone().equals(newUser.getPhone())){
               if (userService.existsByPhone(newUser.getPhone())){
                   return ResponseEntity.status(HttpStatus.OK).body(
                           new ResponseObject(400, "Số điện thoại đã có tài khoản", "")
                   );
               }
            }
            newUser.setRoleId(roleService.getRoleByName(userDto.getRole()).getId());
            newUser.setStatus(userDto.getStatus());
            if (userService.updateUser(newUser)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật thành công", userService.getUserById(newUser.getId()))
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy user", "")
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable int id){
        if (userService.getUserById(id)!=null){
            if (userService.deleteUser(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa người dùng thành công","")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa người dùng thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy người dùng", "")
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
