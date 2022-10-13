package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByCoachId (String id);
    Page<Comment> findAllByCoachIdAndRating(int id, int rating, Pageable pageable);
}
