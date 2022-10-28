package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.MonthStat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TicketService {
    Page<Ticket> getAllTicket(Pageable pageable);
    List<Ticket> getAllTicketByCoaches(int id);
    List<Ticket> getAllActiveTicketByUserId(int id);
    List<Ticket> getTicketByCoachesAndStatus(int id, int status);
    List<Ticket> getRequestTicket();
    Optional<Ticket> getTicketById(int id);
    boolean acceptRefundTicket (Ticket ticket);
    boolean rejectRefundTicket (Ticket ticket);
    boolean addTicket(Ticket ticket);
    boolean updateTicket(Ticket ticket);
    boolean deleteTicket(int id);
    boolean existsById(int id);
//    List<MonthStat> getMonthStat(int month, int year);
}
