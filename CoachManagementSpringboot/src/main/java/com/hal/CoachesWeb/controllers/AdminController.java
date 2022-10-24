package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Category;
import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CoachService coachService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private CoachGarageService coachGarageService;

    //Category
    @GetMapping("/category/getall")
    ResponseEntity<ResponseObject> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy tất cả loại xe thành công", categoryService.getAllCategory())
        );
    }
    @GetMapping("/category/{id}")
    ResponseEntity<ResponseObject> getCategoryById(@PathVariable int id){
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent() && category.get().getStatus() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy loại xe thành công", category)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy loại xe id", "")
        );
    }
    @PostMapping("/category/add")
    ResponseEntity<ResponseObject> addCategory(@RequestBody Category category){
        if (categoryService.addCategory(category)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm loại xe thành công", category)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm loại xe thất bại", category)
        );
    }
    @PutMapping("/category/update")
    ResponseEntity<ResponseObject> updateCategory(@RequestBody Category category){
        if (categoryService.existsById(category.getId())){
            if (categoryService.updateCategory(category)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật loại xe thành công", category)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật loại xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy loại xe id", "")
        );
    }
    @DeleteMapping("/category/delete/{id}")
    ResponseEntity<ResponseObject> deleteCategory(@PathVariable int id){
        if (categoryService.existsById(id)){
            if (categoryService.deleteCategory(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa loại xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa loại xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy loại xe id", "")
        );
    }

    //Coach
    @GetMapping("/coach/getall")
    ResponseEntity<ResponseObject> getAllCoach(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả xe thành công", coachService.getAllCoach())
        );
    }
    @GetMapping("/coach/coachgarage/{id}")
    ResponseEntity<ResponseObject> getCoachByGarageId(@PathVariable int id){
        if (coachGarageService.existsById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy danh sách xe thành công", coachService.getAllCoachByGarageId(id))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id", "")
        );
    }

    @GetMapping("/coach/{id}")
    ResponseEntity<ResponseObject> getCoachById(@PathVariable int id){
        Optional<Coach> coach = coachService.getCoachById(id);
        if (coach.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy xe thành công", coach)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy xe", "")
        );
    }
    @PostMapping("/coach/add")
    ResponseEntity<ResponseObject> addCoach(@RequestBody Coach coach){
        if (coachGarageService.existsById(coach.getCoachGarageId())){
            if (categoryService.existsById(coach.getCategoryId())){
                Coach newCoach = coachService.addCoach(coach);
                if (newCoach!=null){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Thêm xe thành công", newCoach)
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Thêm xe thất bại", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy loại xe id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id", "")
        );
    }
    @PutMapping("/coach/update")
    ResponseEntity<ResponseObject> updateCoach(@RequestBody Coach coach){
        Optional<Coach> optCoach = coachService.getCoachById(coach.getId());
        if (optCoach.isPresent()){
            if (coachGarageService.existsById(coach.getCoachGarageId())){
                if (categoryService.existsById(coach.getCategoryId())){
                    Coach newCoach = coachService.updateCoach(coach);
                    if (newCoach!=null){
                        return ResponseEntity.status(HttpStatus.OK).body(
                                new ResponseObject(200, "Cập nhật xe thành công", newCoach)
                        );
                    }
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(400, "Cập nhật xe thất bại", "")
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Không tìm thấy loại xe id", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy nhà xe id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy xe id", "")
        );
    }
    @DeleteMapping("/coach/delete/{id}")
    ResponseEntity<ResponseObject> deleteCoach(@PathVariable int id){
        if (coachService.existsById(id)){
            if (coachService.deleteCoach(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy xe id", "")
        );
    }

    //Coaches

}
