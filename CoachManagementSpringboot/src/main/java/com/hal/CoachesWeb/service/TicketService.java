package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.FrequentlyMonthStatRes;
import com.hal.CoachesWeb.model.response.MonthStat;
import com.hal.CoachesWeb.model.response.YearStat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface TicketService {
    Page<Ticket> getAllTicket(Pageable pageable);
    List<Ticket> getAllTicketByCoaches(int id);
    List<Ticket> getAllTicketByUserId(int id);
    List<Ticket> getTicketByCoachesAndStatus(int id, int status);
    List<Ticket> getRequestTicket();
    Optional<Ticket> getTicketById(int id);
    boolean acceptRefundTicket (Ticket ticket);
    boolean rejectRefundTicket (Ticket ticket);
    boolean addTicket(Ticket ticket);
    boolean updateTicket(Ticket ticket);
    boolean deleteTicket(int id);
    boolean existsById(int id);
    Collection<MonthStat> getMonthStat(int month, int year);
    Collection<YearStat> getQuarterStat(int quarter, int year);
    Collection<YearStat> getYearStat(int year);
    Collection<MonthStat> getMonthStatByCoachGarage(int month, int year, int id);
    Collection<YearStat> getQuarterStatByCoachGarage(int quarter, int year, int id);
    Collection<YearStat> getYearStatByCoachGarage(int year, int id);
    Collection<FrequentlyMonthStatRes> getMonthFrequentlyStat(int month, int year);
    Collection<FrequentlyMonthStatRes> getQuarterFrequentlyStat(int quarter, int year);
    Collection<FrequentlyMonthStatRes> getYearFrequentlyStat(int year);

}
