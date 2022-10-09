package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "CoachManagement/coach/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @GetMapping("")
    List<Picture> getAllPicture(){
        return pictureService.getAllPicture();
    }

    @GetMapping("/coachId")
    ResponseEntity<ResponseObject> getPictureByCoachId(@RequestBody int id){
        List<Picture> pictures = pictureService.getPictureByCoachId(id);
        if (!pictures.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Query picture successfully", pictures)
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject(400, "Can not found this coach id ="+id+" picture", "")
            );
        }
    }
}
