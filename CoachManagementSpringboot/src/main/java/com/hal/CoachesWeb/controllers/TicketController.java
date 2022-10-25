package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachesStopByService coachesStopByService;

    @GetMapping("/")
    ResponseEntity<ResponseObject> getAllTicket(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả vé xe thành công", ticketService.getAllTicket(PageRequest.of(page, size)).get())
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getTicketById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getTicketById(id))
        );
    }
    @PostMapping("/add")
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
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteTicket(@PathVariable int id){
        if (ticketService.existsById(id)){
            if (ticketService.deleteTicket(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa vé xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe id", "")
        );
    }
}
