package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.model.request.CoachesReq;
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

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCoaches(@RequestBody CoachesReq coachesReq){
        ResponseEntity<ResponseObject> res = coachesChecking(coachesReq);
        if (res!=null){
            return res;
        }
        if (!coachesService.addCoaches(coachesReq)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thêm chuyến xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Thêm chuyến xe thành công", "")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCoaches(@RequestBody CoachesReq coachesReq){
        if (!coachesService.existsById(coachesReq.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy chuyến xe id", "")
            );
        }
        ResponseEntity<ResponseObject> res = coachesChecking(coachesReq);
        if (res!=null){
            return res;
        }
        if (!coachesService.updateCoaches(coachesReq)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật chuyến xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Cập nhật chuyến xe thành công", "")
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

    private ResponseEntity<ResponseObject> coachesChecking(CoachesReq coachesReq){
        if (!coachService.existsById(coachesReq.getCoachId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy xe id", "")
            );
        }
        if (!countryService.existsById(coachesReq.getStartPoint()) || !countryService.existsById(coachesReq.getEndPoint())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy thành phố id", "")
            );
        }
        try {
            if (LocalDateTime.parse(coachesReq.getStartTime()).isAfter(LocalDateTime.parse(coachesReq.getEndTime()))){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Ngày/giờ kết thúc phải lớn hơn xuất phát", "")
                );
            }
            if (LocalDateTime.parse(coachesReq.getStartTime()).isBefore(LocalDateTime.now())
                    || LocalDateTime.parse(coachesReq.getEndTime()).isBefore(LocalDateTime.now())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Ngày/giờ xuất phát phải lớn hơn hiện tại", "")
                );
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Error", ex.getMessage())
            );
        }
        if (coachesReq.getPrice()<0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Số tiền vé phải lớn hơn 0", "")
            );
        }
        return null;
    }
}
