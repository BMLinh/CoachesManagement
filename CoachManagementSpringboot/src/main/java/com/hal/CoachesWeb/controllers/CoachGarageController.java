package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.service.CoachGarageService;
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

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllCoachGarage(){
        List<CoachGarage> coachGarages = coachGarageService.getAllCoachGarage();
        if (!coachGarages.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200,"Get coach garage success", coachGarages)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400,"Coach garage is empty","")
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getCoachGarageById(@PathVariable int id){
        Optional<CoachGarage> coachGarage = coachGarageService.getCoachGarageById(id);
        if (coachGarage.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Get coach garage success", coachGarage)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(400, "Can not found coach garage with id= "+id, "")
        );
    }

    @PostMapping("/addcoachgarage")
    ResponseEntity<ResponseObject> addCoachGarage(@RequestBody CoachGarage coachGarage){
        if (coachGarageService.addCoachGarage(coachGarage)){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject(200, "Create coach garage success", coachGarage)
            );
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ResponseObject(400, "Can not create coach garage", "")
        );
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObject> updateCoachGarage(@RequestBody CoachGarage coachGarage){
        if (coachGarageService.getCoachGarageById(coachGarage.getId()).isPresent()){
            if (coachGarageService.updateCoachGarage(coachGarage)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Update coach garage success", coachGarage)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        new ResponseObject(400, "Can not update coach garage","")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(400, "Can not found coach garage id= "+coachGarage.getId(),"")
        );
    }
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCoachGarage(@PathVariable int id){
        if (coachGarageService.getCoachGarageById(id).isPresent()){
            if (coachGarageService.deleteCoachGarage(id)){
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(200, "Delete coach garage success", "")
                );
            }
            else{
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                        new ResponseObject(400, "Can not delete coach garage", "")
                );
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject(400, "Can not found coach garage", "")
        );
    }
}
