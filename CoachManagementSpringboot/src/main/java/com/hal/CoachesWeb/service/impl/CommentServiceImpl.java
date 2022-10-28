package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Comment;
import com.hal.CoachesWeb.repositories.CommentRepository;
import com.hal.CoachesWeb.service.CommentService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Page<Comment> getCommentByCoachAndRating(int coachId, int rating, Pageable pageable){
        Page<Comment> comments = commentRepository.findAllByCoachIdAndRating(coachId, rating, pageable);
        comments.get().forEach(comment -> {
            comment.setFullName(comment.getUserByUserId().getFullname());
            comment.setAvatar(comment.getUserByUserId().getAvatar());
        });
        return comments;
    }

    @Override
    public Page<Comment> getCommentByCoach(int coachId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByCoachId(coachId, pageable);
        comments.get().forEach(comment -> {
            comment.setFullName(comment.getUserByUserId().getFullname());
            comment.setAvatar(comment.getUserByUserId().getAvatar());
        });
        return comments;
    }

    @Override
    public List<Comment> getAllByCoachesId(int id) {
        List<Comment> comments = commentRepository.findAllByCoachId(id);
        comments.forEach(comment -> {
            comment.setFullName(comment.getUserByUserId().getFullname());
            comment.setAvatar(comment.getUserByUserId().getAvatar());
        });
        return comments;
    }

    @Override
    public List<Comment> getAllByCoachesIdAndRating(int id, int rating) {
        List<Comment> comments = commentRepository.findAllByCoachIdAndRating(id, rating);
        comments.forEach(comment -> {
            comment.setFullName(comment.getUserByUserId().getFullname());
            comment.setAvatar(comment.getUserByUserId().getAvatar());
        });
        return comments;
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
