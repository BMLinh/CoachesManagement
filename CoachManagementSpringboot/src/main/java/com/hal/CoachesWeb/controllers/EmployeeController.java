package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CoachesService;
import com.hal.CoachesWeb.service.CoachesStopByService;
import com.hal.CoachesWeb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/employee")
public class EmployeeController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private CoachesStopByService coachesStopByService;

    @GetMapping("/coaches/getall")
    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "startTime") String startTime){
        try {
            LocalDate start = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    //Ticket
    @GetMapping("/ticket/coaches/{id}")
    ResponseEntity<ResponseObject> getTicketByCoaches(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getTicketByCoachesAndStatus(id, 1))
        );
    }
    @GetMapping("/ticket/{id}")
    ResponseEntity<ResponseObject> getTicketById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getTicketById(id))
        );
    }

    @PutMapping("/ticket/update")
    ResponseEntity<ResponseObject> updateTicket(@RequestBody Ticket ticket){
        Optional<Ticket> old = ticketService.getTicketById(ticket.getId());
        if (old.isPresent()){
            if(coachesStopByService.existsByCoachesAndStopBy(old.get().getCoachesId(), ticket.getPickUpId())
                    && coachesStopByService.existsByCoachesAndStopBy(old.get().getCoachesId(), ticket.getDropOffId())){
                if (!ticketService.updateTicket(ticket)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(400, "Cập nhật vé xe thất bại", "")
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy điểm dừng/trả thuộc chuyến xe", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe id", "")
        );
    }

    @DeleteMapping("/ticket/delete/{id}")
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
