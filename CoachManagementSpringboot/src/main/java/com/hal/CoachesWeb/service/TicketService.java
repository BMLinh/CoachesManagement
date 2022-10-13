package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TicketService {
    Page<Ticket> getAllTicket(Pageable pageable);
    Optional<Ticket> getTicketById(int id);
    boolean addTicket(Ticket ticket, int amount);
//    Ticket updateTicket(Ticket ticket);
    boolean deleteTicket(int id);
    boolean existsById(int id);
}
