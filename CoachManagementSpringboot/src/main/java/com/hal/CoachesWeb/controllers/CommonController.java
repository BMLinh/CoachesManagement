package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Category;
import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/common")
public class CommonController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachesStopByService coachesStopByService;

    //Category
    @GetMapping("/category/getall")
    ResponseEntity<ResponseObject> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy tất cả loại xe thành công", categoryService.getAllActiveCategory())
        );
    }
    @GetMapping("/category/{id}")
    ResponseEntity<ResponseObject> getCategoryById(@PathVariable int id){
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent() && category.get().getStatus() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy loại xe thành công", category.get())
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy loại xe id", "")
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
}
