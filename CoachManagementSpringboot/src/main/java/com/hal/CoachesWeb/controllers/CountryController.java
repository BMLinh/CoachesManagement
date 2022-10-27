package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Country;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCountry(@RequestBody Country country){
        if (countryService.addCountry(country)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm tỉnh/thành phố tỉnh/thành công", country)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm tỉnh/thành phố thất bại", "")
        );
    }
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCountry(@RequestBody Country country){
        if (countryService.getCountryById(country.getId()).isPresent()){
            if (countryService.updateCountry(country)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật tỉnh/thành phố tỉnh/thành công", country)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật tỉnh/thành phố thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy tỉnh/thành phố id", "")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> deleteCountry(@PathVariable int id){
        if (countryService.getCountryById(id).isPresent()){
            if (countryService.deleteCountry(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa tỉnh/thành phố tỉnh/thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa tỉnh/thành phố thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy tỉnh/thành phố id", "")
        );
    }
}