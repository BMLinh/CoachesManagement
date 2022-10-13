package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.repositories.CommentRepository;
import com.hal.CoachesWeb.service.CommentService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getCommentByCoachAndRating(int coachId, int rating, Pageable pageable){
        return commentRepository.findAllByCoachIdAndRating(coachId, rating, pageable);
    }
    @Override
    public boolean addComment(Comment comment){
        try {
            commentRepository.save(new Comment(comment.getContent(), comment.getRating(), comment.getCoachId(), comment.getUserId(), 1));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean deleteCommentById(int id){
        try {
            commentRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
