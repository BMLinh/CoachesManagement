package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Coach;
import com.hal.CoachesWeb.model.request.CoachesReq;
import com.hal.CoachesWeb.model.response.CoachRes;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/garageowner")
public class CoachGarageController {
    @Autowired
    private CoachGarageService coachGarageService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CoachesService coachesService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ShippingService shippingService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CoachService coachService;

    //Coach Garage
    @GetMapping("/coachgarage/user/{id}")
    ResponseEntity<ResponseObject> getCoachGarageByUserId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy danh sách xe thành công"
                        , coachGarageService.getCoachGarageByUserId(id))
        );
    }
//    @GetMapping("/coachgarage/getall/")
//    ResponseEntity<ResponseObject> getAllCoachGarage(@PathParam(value = "id") int id){
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject(200,"Lấy tất cả nhà xe thành công"
//                        , coachGarageService.getCoachGarageByUserId(id))
//        );
//    }
//
//    @GetMapping("/coachgarage/{id}/detail/")
//    ResponseEntity<ResponseObject> getCoachGarageById(@PathVariable int id){
//        Optional<CoachGarage> coachGarage = coachGarageService.getCoachGarageById(id);
//        if (coachGarage.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject(200, "Lấy nhà xe thành công", coachGarage)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject(400, "Không tìm thấy nhà xe id", "")
//        );
//    }

    //Coach
    @GetMapping("/coachgarage/{id}")
    ResponseEntity<ResponseObject> getCoachByGarageId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy danh sách xe thành công"
                        , coachService.getAllCoachByGarageId(id))
        );
    }

    @GetMapping("/coachgarage/coach/{id}")
    ResponseEntity<ResponseObject> getCoachById(@PathVariable int id){
        Optional<Coach> coach = coachService.getCoachById(id);
        if (coach.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy xe thành công", new CoachRes(coach.get()))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy xe thành công", "")
        );
    }
    @PostMapping("/coachgarage/coach/add")
    ResponseEntity<ResponseObject> addCoach(@ModelAttribute Coach coach){
        if (coachGarageService.existsById(coach.getCoachGarageId())){
            if (categoryService.existsById(coach.getCategoryId())){
                if (coachService.addCoach(coach)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Thêm xe thành công", "")
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
    @PutMapping("/coachgarage/coach/update")
    ResponseEntity<ResponseObject> updateCoach(@ModelAttribute Coach coach){
        Optional<Coach> optCoach = coachService.getCoachById(coach.getId());
        if (optCoach.isPresent()){
            if (coachGarageService.existsById(coach.getCoachGarageId())){
                if (categoryService.existsById(coach.getCategoryId())){
                    coach.setCoachGarageId(optCoach.get().getCoachGarageId());
                    if (coachService.updateCoach(coach)){
                        return ResponseEntity.status(HttpStatus.OK).body(
                                new ResponseObject(200, "Cập nhật xe thành công", "")
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
    @DeleteMapping("/coachgarage/coach/delete/{id}")
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
    @GetMapping("/coachgarage/coaches/getall/{id}")
    ResponseEntity<ResponseObject> getAllCoachesByCoachGarageId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByCoachId(id))
        );
    }
    @GetMapping("/coachgarage/coaches/{id}")
    ResponseEntity<ResponseObject> getCoachesById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy chuyến xe thành công", coachesService.getCoachesById(id))
        );
    }
    @PostMapping("/coachgarage/coaches/add")
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
    @PutMapping("/coachgarage/coaches/update")
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
    @DeleteMapping("/coachgarage/coaches/delete/{id}")
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

    //Ticket
    @GetMapping("/coachgarage/coaches/ticket/{id}")
    ResponseEntity<ResponseObject> getTicketByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getTicketByCoaches(id))
        );
    }

    //Shipping
    @GetMapping("/coachgarage/coaches/shipping/{id}")
    ResponseEntity<ResponseObject> getShippingByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy giao hàng thành công", shippingService.getShippingByCoachesAndStatus(id, 1))
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
        if (LocalDateTime.parse(coachesReq.getStartTime()).isAfter(LocalDateTime.parse(coachesReq.getEndTime()))){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Ngày/giờ kết thúc phải lớn hơn xuất phát", "")
            );
        }
        if (LocalDateTime.parse(coachesReq.getStartTime()).isBefore(LocalDateTime.now()) || LocalDateTime.parse(coachesReq.getEndTime()).isBefore(LocalDateTime.now())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Ngày/giờ xuất phát phải lớn hơn hiện tại", "")
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
