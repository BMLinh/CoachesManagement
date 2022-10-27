package com.hal.CoachesWeb.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.request.UserSignIn;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.model.response.UserDto;
import com.hal.CoachesWeb.service.*;
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
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private CoachesStopByService coachesStopByService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private CoachGarageService coachGarageService;
    @Autowired
    private CommentService commentService;

    //Comment
    @PostMapping("comment/add")
    ResponseEntity<ResponseObject> addComment(@RequestBody Comment comment){
        if (commentService.addComment(comment)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm bình luận thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm bình luận thất bại", "")
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

    //Ticket
    @PostMapping("/ticket/add")
    ResponseEntity<ResponseObject> addTicket(@Valid @RequestBody Ticket ticket){
        if (!coachesService.existsById(ticket.getCoachesId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy chuyến xe id", "")
            );
        }
        if (!userService.existsById(ticket.getUserId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy người dùng id", "")
            );
        }
        if(!coachesStopByService.existsByCoachesAndStopBy(ticket.getCoachesId(), ticket.getPickUpId())
                ||!coachesStopByService.existsByCoachesAndStopBy(ticket.getCoachesId(), ticket.getDropOffId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy điểm dừng/trả id", "")
            );
        }
        if (ticket.getAmount()>coachesService.getEmptySeatByCoachesId(ticket.getCoachesId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Số lượng ghế còn lại không đủ", "")
            );
        }
        if (!ticketService.addTicket(ticket)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Đặt vé xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Đặt vé xe thành công", "")
        );
    }
    @PutMapping("/refund/{id}")
    ResponseEntity<ResponseObject> requestRefundTicket(@PathVariable int id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()){
            if (ticket.get().getStatus()==2){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Thông tin đã được gửi", "")
                );
            }
            if (ticket.get().getCoachesByCoachesId().getStartTime().minusDays(1).isBefore(LocalDateTime.now())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Vé đã quá hạn hủy", "")
                );
            }
            ticket.get().setStatus(2);
            if (ticketService.updateTicket(ticket.get())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Gửi yêu cầu thành công", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Gửi yêu cầu thất bại", "")
        );
    }

    //Coach garage
    @PostMapping("/coachgarage/request")
    ResponseEntity<ResponseObject> requestAddCoachGarage(@RequestBody CoachGarage coachGarage){
        if (userService.existsById(coachGarage.getUserId())){
            if (districtService.getDistrictById(coachGarage.getDistrictId()).isPresent()){
                coachGarage.setStatus(2);
                if (coachGarageService.addCoachGarage(coachGarage)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Gửi yêu cầu xác nhận nhà xe thành công", "")
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Gửi yêu cầu xác nhận nhà xe thất bại", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy quận/huyện id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy người dùng id", "")
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
