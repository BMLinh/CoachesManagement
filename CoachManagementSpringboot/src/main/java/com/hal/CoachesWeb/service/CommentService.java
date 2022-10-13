package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Page<Comment> getCommentByCoachAndRating(int coachId, int rating, Pageable pageable);
    boolean addComment(Comment comment);
    boolean deleteCommentById(int id);
}
