package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.District;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CountryService;
import com.hal.CoachesWeb.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllDistrict(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả quận/huyện thành công", districtService.getAllDistrict())
        );
    }
    @GetMapping("/country/{id}")
    ResponseEntity<ResponseObject> getDistrictByCountryId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy quận/huyện thành công", districtService.getDistrictByCountryId(id))
        );
    }
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addDistrict(@RequestBody District district){
        if (countryService.getCountryById(district.getCountryId()).isPresent()){
            if (districtService.addDistrict(district)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Thêm quận/huyện thành công", district)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thêm quận/huyện thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy thành phố id", "")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateDistrict(@RequestBody District district){
        if (districtService.getDistrictById(district.getId()).isPresent()){
            if (countryService.getCountryById(district.getCountryId()).isPresent()){
                if (districtService.updateDistrict(district)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Cập nhật quận/huyện thành công", district)
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Cập nhật quận/huyện thất bại", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy thành phố id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy quận/huyện id", "")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteDistrict(@PathVariable int id){
        if (districtService.getDistrictById(id).isPresent()){
            if (districtService.deleteDistrict(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa quận/huyện thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa quận/huyện thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không thể tìm thấy quận/huyện id", "")
        );
    }
}