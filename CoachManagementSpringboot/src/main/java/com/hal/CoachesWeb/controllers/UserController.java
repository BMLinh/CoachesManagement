package com.hal.CoachesWeb.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.request.UserSignIn;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.model.response.UserDto;
import com.hal.CoachesWeb.service.RoleService;
import com.hal.CoachesWeb.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    AuthenticationManager authenticationManager;


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
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(userSignin.getPhone(), userSignin.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                User user = userService.getUserByPhone(userSignin.getPhone());
                Algorithm algorithm = Algorithm.HMAC256("hieuAndLinhSecret".getBytes());
                List<String> roles = new ArrayList<>();
                roles.add(user.getRoleByRoleId().getName());
                Date expiredTime = new Date(System.currentTimeMillis()+ 10*60*1000);
                String access_token = JWT.create()
                        .withSubject(user.getPhone())
                        .withExpiresAt(expiredTime)
                        .withClaim("role", roles)
                        .sign(algorithm);
                UserRes userRes = new UserRes();
                userRes.setId(user.getId());
                userRes.setFullname(user.getFullname());
                userRes.setAvartar(user.getAvatar());
                userRes.setPhone(user.getPhone());
                userRes.setRole(user.getRoleByRoleId().getName());
                userRes.setAccessToken(access_token);
                userRes.setExpiredTime(expiredTime.getTime());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Đăng nhập thành công", userRes)
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
        if (userService.getUserDtoByPhone(newUser.getPhone())==null){
            newUser.setRoleId(1);
            newUser.setStatus(1);
            if (userService.addUser(newUser)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200,"Đăng ký thành công",userService.getUserDtoByPhone(newUser.getPhone()))
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
            newUser.setCreatedDate(userDto.getCreatedDate());
            newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
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

    @DeleteMapping("/delete/{id}")
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
    @PostMapping("/token/refresh")
    ResponseEntity<ResponseObject> refreshToken(@RequestBody String accessToken){
        if (accessToken != null){
            try {
                Algorithm algorithm = Algorithm.HMAC256("hieuAndLinhSecret".getBytes());
                String[] parts = accessToken.split("\\.");
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String payload = new String(decoder.decode(parts[1]));
                String phone = payload.substring(payload.indexOf(":")+2, payload.indexOf(",")-1);
                User user = userService.getUserByPhone(phone);
                List<String> roles = new ArrayList<>();
                roles.add(user.getRoleByRoleId().getName());
                Date expiredTime = new Date(System.currentTimeMillis()+ 10*60*1000);
                String access_token = JWT.create()
                        .withSubject(user.getPhone())
                        .withExpiresAt(expiredTime)
                        .withClaim("role", roles)
                        .sign(algorithm);
                TokenRes tokenRes = new TokenRes();
                tokenRes.setAccessToken(access_token);
                tokenRes.setExpiredTime(expiredTime.getTime());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Get token success", tokenRes)
                );
            } catch (Exception ex){
                System.out.println(ex.getMessage());
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Get token failed", ex.getMessage())
                );
            }

        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Refresh token is missing", "")
            );
        }
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
@Data
class UserRes {
    private int id;
    private String fullname;
    private String avartar;
    private String phone;
    private String role;
    private String accessToken;
    private Long expiredTime;
}
@Data
class TokenRes {
    private String accessToken;
    private Long expiredTime;
}
