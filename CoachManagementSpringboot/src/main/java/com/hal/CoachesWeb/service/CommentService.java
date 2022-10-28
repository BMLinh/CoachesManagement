package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    Page<Comment> getCommentByCoachAndRating(int coachId, int rating, Pageable pageable);
    Page<Comment> getCommentByCoach(int coachId, Pageable pageable);
    List<Comment> getAllByCoachesId(int id);
    List<Comment> getAllByCoachesIdAndRating(int id, int rating);
    boolean addComment(Comment comment);
    boolean deleteCommentById(int id);
}
