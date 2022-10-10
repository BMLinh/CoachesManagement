package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.service.CategoryService;
import com.hal.CoachesWeb.service.CoachGarageService;
import com.hal.CoachesWeb.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/coach")
public class CoachController {
    @Autowired
    private CoachService coachService;
    @Autowired
    private CoachGarageService coachGarageService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCoach(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả xe thành công", coachService.getAllCoach())
        );
    }@GetMapping("/coachgarage/{id}")
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

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCoachByLicensePlate(@PathVariable int id){
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
    @PostMapping("/add")
    ResponseEntity<ResponseObject> addCoach(@RequestBody Coach coach){
        if (coachGarageService.existsById(coach.getCoachGarageId())){
            if (categoryService.existsById(coach.getCategoryId())){
                coach.setStatus(1);
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
    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCoach(@RequestBody Coach coach){
        Optional<Coach> optCoach = coachService.getCoachById(coach.getId());
        if (optCoach.isPresent()){
            if (coachGarageService.existsById(coach.getCoachGarageId())){
                if (categoryService.existsById(coach.getCategoryId())){
                    coach.setCoachGarageId(optCoach.get().getCoachGarageId());
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
    @DeleteMapping("/{id}")
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
}
