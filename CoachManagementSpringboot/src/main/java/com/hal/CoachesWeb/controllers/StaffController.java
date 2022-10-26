package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CoachesService;
import com.hal.CoachesWeb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/employee")
public class StaffController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachesService coachesService;

    @GetMapping("/coaches/getall")
    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "startTime") String startTime){
        try {
            LocalDateTime start = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công"
                        , coachesService.getAllCoachesInDay(start))
                );
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Error"
                        , ex.getMessage())
                );
        }
    }

}
