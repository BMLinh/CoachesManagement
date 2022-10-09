package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.service.CoachGarageService;
import com.hal.CoachesWeb.service.DistrictService;
import com.hal.CoachesWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/coachgarage")
public class CoachGarageController {
    @Autowired
    private CoachGarageService coachGarageService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private UserService userService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCoachGarage(){
        List<CoachGarage> coachGarages = coachGarageService.getAllCoachGarage();
        if (!coachGarages.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Lấy nhà xe thành công", coachGarages)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400,"Nhà xe trống","")
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCoachGarageById(@PathVariable int id){
        Optional<CoachGarage> coachGarage = coachGarageService.getCoachGarageById(id);
        if (coachGarage.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy nhà xe thành công", coachGarage)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id", "")
        );
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCoachGarage(@RequestBody CoachGarage coachGarage){
        if (userService.existsById(coachGarage.getUserId())){
            if (districtService.getDistrictById(coachGarage.getDistrictId()).isPresent()){
                if (coachGarageService.addCoachGarage(coachGarage)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Tạo nhà xe thành công", coachGarage)
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Thêm nhà xe thất bại", "")
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

    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCoachGarage(@RequestBody CoachGarage coachGarage){
        if (coachGarageService.getCoachGarageById(coachGarage.getId()).isPresent()){
            if (coachGarageService.updateCoachGarage(coachGarage)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật nhà xe thành công", coachGarage)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Cập nhật nhà xe thất bại","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id","")
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCoachGarage(@PathVariable int id){
        if (coachGarageService.getCoachGarageById(id).isPresent()){
            if (coachGarageService.deleteCoachGarage(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa nhà xe thành công", "")
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Xóa nhà xe thất bại", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id", "")
        );
    }
}
