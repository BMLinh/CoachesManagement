package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUserIdAndStatus (int id, int status);
    List<Ticket> findAllByCoachesId(int id);
    List<Ticket> findAllByCoachesIdAndStatusIs(int id, int status);
    List<Ticket> findAllByStatus (int status);
    Ticket findTopByEmailOrderByIdDesc(String email);
    boolean existsByCoachesId (int id);
    boolean existsByUserId (int id);
}
