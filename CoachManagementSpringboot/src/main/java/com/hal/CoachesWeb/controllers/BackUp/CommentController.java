package com.hal.CoachesWeb.controllers.BackUp;

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
