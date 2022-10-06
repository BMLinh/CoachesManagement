package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.model.ResponseObject;
import com.hal.CoachesWeb.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

//    public ResponseEntity<ResponseObject> addComment (Comment comment){
//
//    }
}
