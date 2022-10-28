package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.*;
import com.hal.CoachesWeb.model.request.CoachesReq;
import com.hal.CoachesWeb.model.response.CoachRes;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.model.response.UserDto;
import com.hal.CoachesWeb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
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
    @Autowired
    private CountryService countryService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private CoachesStopByService coachesStopByService;
    @Autowired
    private StopByService stopByService;
    @Autowired
    private ShippingService shippingService;

    //Category
    @GetMapping("/category/getall")
    ResponseEntity<ResponseObject> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy tất cả loại xe thành công", categoryService.getAllCategory())
        );
    }
    @GetMapping("/category/{id}")
    ResponseEntity<ResponseObject> getCategoryById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy loại xe thành công", categoryService.getCategoryById(id).get())
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
                    new ResponseObject(200, "Lấy xe thành công", new CoachRes(coach.get()))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy xe thành công", "")
        );
    }
    @PostMapping("/coach/add")
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
    @PutMapping("/coach/update")
    ResponseEntity<ResponseObject> updateCoach(@ModelAttribute Coach coach){
        Optional<Coach> optCoach = coachService.getCoachById(coach.getId());
        if (optCoach.isPresent()){
            if (coachGarageService.existsById(coach.getCoachGarageId())){
                if (categoryService.existsById(coach.getCategoryId())){
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
    @GetMapping("/coaches/getall")
    ResponseEntity<ResponseObject> getAllCoaches(@PathParam(value = "page") int page, @PathParam(value = "size") int size){
        if (page==0 || size==0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoaches())
            );
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoaches(pageRequest))
        );
    }
    @GetMapping("/coaches/coach/")
    ResponseEntity<ResponseObject> getAllCoachesByCoachId(@PathParam(value = "page") int page, @PathParam(value = "size") int size, @PathParam(value = "id") int id){
        if (page==0 || size==0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByCoachId(id))
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByCoachId(id, PageRequest.of(page, size)))
        );
    }
    @GetMapping("/coaches/{id}")
    ResponseEntity<ResponseObject> getCoachesById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy chuyến xe thành công", coachesService.getCoachesById(id))
        );
    }
//    @GetMapping("/coaches/date/")
//    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "page") int page, @PathParam(value = "size") int size, @PathParam(value = "startTime") String startTime, @PathParam(value = "endTime")String endTime){
//
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject(200, "Lấy tất cả chuyến xe thành công", coachesService.getAllCoachesByStartDate(LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), PageRequest.of(page, size)).get())
//        );
//    }
    @PostMapping("/coaches/add")
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
    @PutMapping("/coaches/update")
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
        if (coachesService.updateCoaches(coachesReq)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Cập nhật chuyến xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Cập nhật chuyến xe thất bại", "")
        );
    }
    @DeleteMapping("/coaches/delete/{id}")
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

    //CoachGarage
    @GetMapping("/coachgarage/getall")
    ResponseEntity<ResponseObject> getAllCoachGarage(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy nhà xe thành công", coachGarageService.getCoachGarage())
        );
    }

    @GetMapping("/coachgarage/request")
    ResponseEntity<ResponseObject> getRequestCoachGarage(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200,"Lấy nhà xe yêu cầu thành công", coachGarageService.getCoachGarageByStatus(2))
        );
    }

    @GetMapping("/coachgarage/{id}")
    ResponseEntity<ResponseObject> getCoachGarageById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy nhà xe thành công", coachGarageService.getCoachGarageById(id))
        );
    }

    @PutMapping("/coachgarage/accept/{id}")
    ResponseEntity<ResponseObject> acceptCoachGarage(@PathVariable int id){
        Optional<CoachGarage> coachGarage = coachGarageService.getCoachGarageById(id);
        if (!coachGarage.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy nhà xe", "")
            );
        }
        if (coachGarage.get().getStatus()!=2){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Nhà xe không trong trạng thái cần phê duyệt", "")
            );
        }
        if (coachGarageService.acceptCoachGarage(coachGarage.get())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Xác nhận nhà xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Xác nhận nhà xe thất bại", "")
        );
    }

    @PutMapping("/coachgarage/reject/{id}")
    ResponseEntity<ResponseObject> rejectCoachGarage(@PathVariable int id){
        Optional<CoachGarage> coachGarage = coachGarageService.getCoachGarageById(id);
        if (!coachGarage.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy nhà xe", "")
            );
        }
        if (coachGarage.get().getStatus()!=2){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Nhà xe không trong trạng thái cần phê duyệt", "")
            );
        }
        if (coachGarageService.rejectCoachGarage(coachGarage.get())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Từ chối nhà xe thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Từ chối nhà xe thất bại", "")
        );
    }

    @PostMapping("/coachgarage/add")
    ResponseEntity<ResponseObject> addCoachGarage(@RequestBody CoachGarage coachGarage){
        if (userService.existsById(coachGarage.getUserId())){
            if (districtService.getDistrictById(coachGarage.getDistrictId()).isPresent()){
                if (coachGarageService.addCoachGarage(coachGarage)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(200, "Thêm nhà xe thành công", "")
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

    @PutMapping("/coachgarage/update")
    ResponseEntity<ResponseObject> updateCoachGarage(@RequestBody CoachGarage coachGarage){
        if (coachGarageService.existsById(coachGarage.getId())){
            if (userService.existsById(coachGarage.getUserId())) {
                if (districtService.getDistrictById(coachGarage.getDistrictId()).isPresent()) {
                    if (coachGarageService.updateCoachGarage(coachGarage)) {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                new ResponseObject(200, "Cập nhật nhà xe thành công", coachGarage)
                        );
                    } else {
                        return ResponseEntity.status(HttpStatus.OK).body(
                                new ResponseObject(400, "Cập nhật nhà xe thất bại", "")
                        );
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Không tìm thấy quận/huyện id", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy người dùng id", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy nhà xe id","")
        );
    }
    @DeleteMapping("/coachgarage/delete/{id}")
    ResponseEntity<ResponseObject> deleteCoachGarage(@PathVariable int id){
        if (coachGarageService.existsById(id)){
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
    //Ticket
    @GetMapping("/ticket/request")
    ResponseEntity<ResponseObject> getTicketRequest(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getRequestTicket())
        );
    }
    @GetMapping("/ticket/coaches/{id}")
    ResponseEntity<ResponseObject> getTicketByCoaches(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getAllTicketByCoaches(id))
        );
    }
    @GetMapping("/ticket/{id}")
    ResponseEntity<ResponseObject> getTicketById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy vé xe thành công", ticketService.getTicketById(id))
        );
    }
    @PutMapping("/ticket/refund/accept/{id}")
    ResponseEntity<ResponseObject> acceptRefundTicket(@PathVariable int id){
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()){
            if (ticketService.acceptRefundTicket(ticket.get())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Chấp thuận hủy vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Chấp thuận hủy vé xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe", "")
        );
    }

    @PutMapping("/ticket/refund/reject/{id}")
    ResponseEntity<ResponseObject> rejectRefundTicket(@PathVariable int id){
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()){
            ticket.get().setStatus(1);
            if (ticketService.rejectRefundTicket(ticket.get())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Từ chối hủy vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Từ chối hủy vé xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe", "")
        );
    }

    @PostMapping("/ticket/update")
    ResponseEntity<ResponseObject> updateTicket(@PathVariable Ticket ticket){
        Optional<Ticket> old = ticketService.getTicketById(ticket.getId());
        if (old.isPresent()){
            if(coachesStopByService.existsByCoachesAndStopBy(old.get().getCoachesId(), ticket.getPickUpId())
                    && coachesStopByService.existsByCoachesAndStopBy(old.get().getCoachesId(), ticket.getDropOffId())){
                if (!ticketService.updateTicket(ticket)){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(400, "Cập nhật vé xe thất bại", "")
                    );
                }
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy điểm dừng/trả thuộc chuyến xe", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe id", "")
        );
    }

    @DeleteMapping("/ticket/delete/{id}")
    ResponseEntity<ResponseObject> deleteTicket(@PathVariable int id){
        if (ticketService.existsById(id)){
            if (ticketService.deleteTicket(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa vé xe thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa vé xe thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy vé xe id", "")
        );
    }
    //User
    @GetMapping("/user/getall")
    ResponseEntity<ResponseObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả người dùng thành công", userService.getAllUser())
        );
    }

    @GetMapping("/user/{id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable int id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy người dùng thành công", userDto)
            );
        } else
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy người dùng", "")
            );
    }
    @PostMapping("/user/add")
    ResponseEntity<ResponseObject> addUser(@ModelAttribute User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thiếu trường dữ liệu", "")
            );
        }
        if (newUser.getPassword()==null || newUser.getPassword().isBlank()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thiếu trường password", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        if (!userService.existsByPhone(newUser.getPhone())){
            if (userService.addUser(newUser)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200,"Đăng ký thành công","")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Đăng ký thất bại, không thể lưu mới người dùng", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Số điện thoại đã có tài khoản", "")
        );
    }
    @PutMapping("/user/update")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser) {
        if (isFieldMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Thiếu trường dữ liệu", "")
            );
        }
        if (isDataMissing(newUser)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        User user = userService.getUserByPhone(newUser.getPhone());
        if (user!=null){
            if (!user.getPhone().equals(newUser.getPhone())){
                if (userService.existsByPhone(newUser.getPhone())){
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject(400, "Số điện thoại đã có tài khoản", "")
                    );
                }
            }
            newUser.setPassword(user.getPassword());
            if (userService.updateUser(newUser)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Cập nhật thành công", "")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Cập nhật thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy user", "")
        );
    }

    @DeleteMapping("/user/delete/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable int id){
        if (userService.getUserById(id)!=null){
            if (userService.deleteUser(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Xóa người dùng thành công","")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa người dùng thất bại", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy người dùng", "")
        );
    }

    //Shipping
    @GetMapping("/shipping/coaches/{id}")
    ResponseEntity<ResponseObject> getShippingByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy thông tin kiện hàng thành công", shippingService.getShippingByCoaches(id))
        );
    }
    @GetMapping("/shipping/{id}")
    ResponseEntity<ResponseObject> getShippingById(@PathVariable int id){
        Optional<Shipping> shipping = shippingService.getShippingById(id);
        if (shipping.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy thông tin kiện hàng thành công", shipping)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Không tìm thấy thông tin kiện hàng", "")
        );
    }
    @PostMapping("/shipping/add")
    ResponseEntity<ResponseObject> addShipping(@RequestBody Shipping shipping){
        if (!coachesService.existsById(shipping.getCoachesId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy chuyến xe", "")
            );
        }
        if (!userService.existsById(shipping.getUserId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy người dùng", "")
            );
        }
        if (shippingService.addShipping(shipping)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm kiện hàng thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm kiện hàng thất bại", "")
        );
    }
    @PutMapping("/shipping/update")
    ResponseEntity<ResponseObject> updateShipping(@RequestBody Shipping shipping){
        if (!shippingService.existsById(shipping.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy kiện hàng", "")
            );
        }
        if (!coachesService.existsById(shipping.getCoachesId())){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy chuyến xe", "")
            );
        }
        if (shippingService.updateShipping(shipping)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Cập nhật kiện hàng thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Cập nhật kiện hàng thất bại","")
        );
    }
    @DeleteMapping("/shipping/delete/{id}")
    ResponseEntity<ResponseObject> deleteShipping(@PathVariable int id){
        if (!shippingService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Không tìm thấy kiện hàng", "")
            );
        }
        if (shippingService.deleteShipping(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Xóa kiện hàng thành công", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Xóa kiện hàng thất bại", "")
            );
        }
    }

    //Stat
    @GetMapping("/stat/month")
    ResponseEntity<ResponseObject> getStatByMonth(@PathParam(value = "month") Integer month, @PathParam(value = "year") Integer year) {
        if (month == null || year == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        if (month < 0 || month > 12){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Tháng phải trong phạm vi 1-12", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy thống kê thành công", ticketService.getMonthStat(month,year))
        );
    }

    @GetMapping("/stat/quarter")
    ResponseEntity<ResponseObject> getStatByQuarter(@PathParam(value = "quarter") Integer quarter, @PathParam(value = "year") Integer year) {
        if (quarter == null || year == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Dữ liệu không được để trống", "")
            );
        }
        if (quarter < 0 || quarter > 4){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Quý phải trong phạm vi 1-4", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy thống kê thành công", ticketService.getQuarterStat(quarter,year))
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
        try {
            if (LocalDateTime.parse(coachesReq.getStartTime()).isAfter(LocalDateTime.parse(coachesReq.getEndTime()))){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Ngày/giờ kết thúc phải lớn hơn xuất phát", "")
                );
            }
            if (LocalDateTime.parse(coachesReq.getStartTime()).isBefore(LocalDateTime.now())
                    || LocalDateTime.parse(coachesReq.getEndTime()).isBefore(LocalDateTime.now())){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(400, "Ngày/giờ xuất phát phải lớn hơn hiện tại", "")
                );
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Error", ex.getMessage())
            );
        }
        if (coachesReq.getPrice()<0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Số tiền vé phải lớn hơn 0", "")
            );
        }
        return null;
    }

    private boolean isFieldMissing(User user) {
        if (user.getPhone() == null || user.getEmail() == null || user.getFullname() == null) {
            return true;
        }
        return false;
    }

    private boolean isDataMissing(User user){
        if (user.getPhone().isBlank() || user.getEmail().isBlank() || user.getFullname().isBlank()) {
            return true;
        }
        return false;
    }
}
