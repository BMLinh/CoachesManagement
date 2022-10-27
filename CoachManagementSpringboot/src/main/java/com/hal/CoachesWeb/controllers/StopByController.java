package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.StopBy;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.DistrictService;
import com.hal.CoachesWeb.service.StopByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/stopby")
public class StopByController {
    @Autowired
    private StopByService stopByService;
    @Autowired
    private DistrictService districtService;

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addStopBy(@RequestBody StopBy stopBy){
        if (districtService.getDistrictById(stopBy.getDistrictId()).isPresent()){
            if (stopByService.addStopBy(stopBy)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Thêm điểm dừng thành công", stopBy)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thêm điểm dừng thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy quận/huyện id", "")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateStopBy(@RequestBody StopBy stopBy){
        if (stopByService.getStopByById(stopBy.getId()).isPresent()){
            if (districtService.getDistrictById(stopBy.getDistrictId()).isPresent()){
                if (stopByService.updateStopBy(stopBy)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Cập nhật điểm dừng thành công", stopBy)
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Cập nhật điểm dừng thất bại", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy quận/huyện id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy điểm dừng id", "")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteStopBy(@PathVariable int id){
        if (stopByService.getStopByById(id).isPresent()){
            if (stopByService.deleteStopBy(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa điểm dừng thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa điểm dừng thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không thể tìm thấy điểm dừng id", "")
        );
    }
}
