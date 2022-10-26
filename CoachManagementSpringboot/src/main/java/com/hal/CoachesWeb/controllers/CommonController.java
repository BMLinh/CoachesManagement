package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Category;
import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/common")
public class CommonController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CoachGarageService coachGarageService;

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

    //
    @GetMapping("/coachgarage/")
    ResponseEntity<ResponseObject> getCoachGarage(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy nhà xe thành công", coachGarageService.getCoachGarageByStatus(1))
        );
    }

}
