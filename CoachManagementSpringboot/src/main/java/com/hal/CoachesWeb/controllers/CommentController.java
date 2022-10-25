package com.hal.CoachesWeb.controllers;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.model.response.ResponseObject;
import com.hal.CoachesWeb.repositories.CommentRepository;
import com.hal.CoachesWeb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/coach/")
    ResponseEntity<ResponseObject> getCommentByCoachAndRating(@PathParam(value = "page") int page
            , @PathParam(value = "size") int size, @PathParam(value = "coachId") int coachId, @PathParam(value = "rating") int rating){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(200, "Lấy tất cả bình luận thành công"
                        , commentService.getCommentByCoachAndRating(coachId, rating, PageRequest.of(page, size)).get())
        );
    }

    @PostMapping("/add")
    ResponseEntity<ResponseObject> addComment(@RequestBody Comment comment){
        if (commentService.addComment(comment)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Thêm bình luận thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Thêm bình luận thất bại", "")
        );
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObject> addComment(@PathVariable int id){
        if (commentService.deleteCommentById(id)){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(200, "Xóa bình luận thành công", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(400, "Xóa bình luận thất bại", "")
        );
    }
}
