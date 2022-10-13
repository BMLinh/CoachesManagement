package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket findTopByEmailOrderByIdDesc(String email);
}
