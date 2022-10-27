package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Category;
import com.hal.CoachesWeb.entity.Country;
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
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/common")
public class CommonController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CoachGarageService coachGarageService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StopByService stopByService;
    @Autowired
    private CoachesService coachesService;

    //Category
    @GetMapping("/category/getall")
    ResponseEntity<ResponseObject> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy tất cả loại xe thành công", categoryService.getAllActiveCategory())
        );
    }
    @GetMapping("/category/{id}")
    ResponseEntity<ResponseObject> getCategoryById(@PathVariable int id){
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent() && category.get().getStatus() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy loại xe thành công", category.get())
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy loại xe id", "")
        );
    }

    //coach garage
    @GetMapping("/coachgarage")
    ResponseEntity<ResponseObject> getCoachGarage(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy nhà xe thành công", coachGarageService.getCoachGarageByStatus(1))
        );
    }

    //Coaches
    @GetMapping("/date")
    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "page") int page, @PathParam(value = "size") int size, @PathParam(value = "startTime") String startTime, @PathParam(value = "endTime")String endTime){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByStartDate(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), PageRequest.of(page, size)).get())
        );
    }

    //Country
    @GetMapping("/country/getall")
    ResponseEntity<ResponseObject> getAllCountry(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy tất cả tỉnh/thành phố tỉnh/thành công", countryService.getAllCountry())
        );
    }
    @GetMapping("/country/{id}")
    ResponseEntity<ResponseObject> getCountryById(@PathVariable int id){
        Optional<Country> country = countryService.getCountryById(id);
        if (country.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy tỉnh/thành phố tỉnh/thành công", country)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy tỉnh/thành phố id", "")
        );
    }

    //District
    @GetMapping("/district/getall")
    ResponseEntity<ResponseObject> getAllDistrict(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả quận/huyện thành công", districtService.getAllDistrict())
        );
    }
    @GetMapping("/district/country/{id}")
    ResponseEntity<ResponseObject> getDistrictByCountryId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy quận/huyện thành công", districtService.getDistrictByCountryId(id))
        );
    }

    //Stop by
    @GetMapping("/stopby/getall")
    ResponseEntity<ResponseObject> getAllStopBy(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả điểm dừng thành công", stopByService.getAllStopBy())
        );
    }
    @GetMapping("/stopby/coachesid/{id}")
    ResponseEntity<ResponseObject> getStopByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả điểm dừng thành công", stopByService.getAllStopByCoachesId(id, 3))
        );
    }
    @GetMapping("/stopby/country/{id}")
    ResponseEntity<ResponseObject> getStopByByCountryId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy điểm dừng thành công", stopByService.getAllStopByCountryId(id))
        );
    }
    @GetMapping("/stopby/country-with-district/{id}")
    ResponseEntity<ResponseObject> getStopByResCountryId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy điểm dừng thành công", stopByService.getAllStopByResCountryId(id))
        );
    }
}
