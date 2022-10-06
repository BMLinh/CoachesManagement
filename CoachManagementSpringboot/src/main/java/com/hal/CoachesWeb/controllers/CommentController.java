package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.repositories.CommentRepository;
import com.hal.CoachesWeb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/comment")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    private CommentService commentService;

    @GetMapping("/coach/{id}")
    ResponseEntity<ResponseObject> getCommentByCoach(@PathVariable String id){
        List<Comment> comments = commentRepository.findAllByCoachId(id);
        if (!comments.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Query comment success",comments)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject(400, "Can not found comment in coach id= "+id,"")
        );
    }

//    @PostMapping("/add")
//    ResponseEntity<ResponseObject> addComment(@RequestBody Comment comment){
//
//    }
}
