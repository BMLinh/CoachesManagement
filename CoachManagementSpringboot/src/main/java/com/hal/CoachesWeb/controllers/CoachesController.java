package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CoachService;
import com.hal.CoachesWeb.service.CoachesService;
import com.hal.CoachesWeb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/coaches")
public class CoachesController {
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private CoachService coachService;
    @Autowired
    private CountryService countryService;

    @GetMapping("/")
    ResponseEntity<ResponseObject> getAllCoaches(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoaches(pageRequest).get())
        );
    }
    @GetMapping("/coach/")
    ResponseEntity<ResponseObject> getAllCoachesByCoachId(@PathParam(value = "page") int page, @PathParam(value = "size") int size, @PathParam(value = "id") int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByCoachId(id, PageRequest.of(page, size)).get())
        );
    }
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCoachesById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy chuyến xe thành công", coachesService.getCoachesById(id))
        );
    }
    @GetMapping("/date/")
    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "page") int page, @PathParam(value = "size") int size, @PathParam(value = "startTime")LocalDateTime startTime, @PathParam(value = "endTime")LocalDateTime endTime){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByStartDate(startTime, endTime, PageRequest.of(page, size)).get())
        );
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCoaches(@RequestBody Coaches coaches){
        ResponseEntity<ResponseObject> res = coachesChecking(coaches);
        if (res!=null){
            return res;
        }
        Coaches newCoaches = coachesService.addCoaches(coaches);
        if (newCoaches==null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thêm chuyến xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Thêm chuyến xe thành công", newCoaches)
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCoaches(@RequestBody Coaches coaches){
        if (!coachesService.existsById(coaches.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy chuyến xe id", "")
            );
        }
        ResponseEntity<ResponseObject> res = coachesChecking(coaches);
        if (res!=null){
            return res;
        }
        Coaches newCoaches = coachesService.updateCoaches(coaches);
        if (newCoaches==null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật chuyến xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Cập nhật chuyến xe thành công", newCoaches)
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteCoaches(@PathVariable int id){
        if (coachesService.deleteCoaches(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Xóa chuyến xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Xóa chuyến xe thất bại", "")
        );

    }

    private ResponseEntity<ResponseObject> coachesChecking(Coaches coaches){
        if (!coachService.existsById(coaches.getCoachId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy xe id", "")
            );
        }
        if (!countryService.existsById(coaches.getStartPoint()) || !countryService.existsById(coaches.getEndPoint())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy thành phố id", "")
            );
        }
        if (coaches.getStartTime().isAfter(coaches.getEndTime())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Ngày/giờ kết thúc phải lớn hơn xuất phát", "")
            );
        }
        if (coaches.getStartTime().isBefore(LocalDateTime.now()) || coaches.getEndTime().isBefore(LocalDateTime.now())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Ngày/giờ xuất phát phải lớn hơn hiện tại", "")
            );
        }
        if (coaches.getPrice()<0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Số tiền vé phải lớn hơn 0", "")
            );
        }
        return null;
    }

}
