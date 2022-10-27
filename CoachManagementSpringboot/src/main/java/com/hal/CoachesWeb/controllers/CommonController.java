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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private CommentService commentService;

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
                new ResponseObject(200,"Lấy nhà xe thành công"
                        , coachGarageService.getCoachGarageByStatus(1))
        );
    }

    //Coaches
    @GetMapping("coaches/")
    ResponseEntity<ResponseObject> getAllCoachesByStartDate(@PathParam(value = "startTime") String startTime
            , @PathParam(value = "endTime")String endTime, @PathParam(value = "startDate") String startDate
            , @PathParam(value = "endPoint") int endPoint, @PathParam(value = "startPoint") int startPoint
            , @PathParam(value = "minPrice") int minPrice, @PathParam(value = "maxPrice") int maxPrice
            , @PathParam(value = "pickUp") Integer pickUp, @PathParam(value = "dropOff") Integer dropOff
            , @PathParam(value = "emptySeat") Integer emptySeat, @PathParam(value = "coachGarage") Integer coachGarage){
        System.out.println(pickUp+" "+dropOff+" "+emptySeat+" "+coachGarage);
        try {
            LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Lấy tất cả chuyến xe thành công"
                            , coachesService.getAllCoachesByStartDate(startTime
                            , endTime, start, startPoint, endPoint, minPrice, maxPrice
                            , pickUp, dropOff, emptySeat, coachGarage, 1))
            );
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(400, "Error", ex.getMessage())
            );
        }

    }

    @GetMapping("/coaches/{id}")
    ResponseEntity<ResponseObject> getCoachesById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy chuyến xe thành công", coachesService.getCoachesById(id))
        );
    }

    //Comment
    @GetMapping("comment/coach/")
    ResponseEntity<ResponseObject> getCommentByCoachAndRating(@PathParam(value = "page") int page
            , @PathParam(value = "size") int size, @PathParam(value = "coachId") int coachId, @PathParam(value = "rating") int rating){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả bình luận thành công"
                        , commentService.getCommentByCoachAndRating(coachId, rating, PageRequest.of(page, size)).get())
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
    @GetMapping("/stopby/coaches/{id}")
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
    //StopBy
    @GetMapping("/coaches/pickup/{id}")
    ResponseEntity<ResponseObject> getPickUpByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả điểm đón thành công", stopByService.getAllStopByCoachesId(id, 3))
        );
    }
    @GetMapping("/coaches/dropoff/{id}")
    ResponseEntity<ResponseObject> getDropOffByCoachesId(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả điểm trả thành công", stopByService.getAllStopByCoachesId(id, 4))
        );
    }
}
