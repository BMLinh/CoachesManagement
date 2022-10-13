package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.CoachesStopBy;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CoachesStopByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/coachesstopby")
public class CoachesStopByController {
    @Autowired
    private CoachesStopByService coachesStopByService;

    @GetMapping("/coaches/{id}")
    ResponseEntity<ResponseObject> getCoachesStopByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả điểm dừng trên chuyến xe thành công", coachesStopByService.getAllByCoachesId(id))
        );
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCoachesStopBy(@RequestBody List<CoachesStopBy> coachesStopByList){
        if (coachesStopByService.upsertCoachesStopBy(coachesStopByList)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm điểm dừng chuyến xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm điểm dừng chuyến xe thất bại", "")
        );
    }
    @DeleteMapping("/delete")
    ResponseEntity<ResponseObject> deleteCoachesStopBy(@RequestBody List<CoachesStopBy> coachesStopByList){
        if (coachesStopByService.deleteCoachesStopBy(coachesStopByList)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Xóa điểm dừng chuyến xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Xóa điểm dừng chuyến xe thất bại", "")
        );
    }
}
