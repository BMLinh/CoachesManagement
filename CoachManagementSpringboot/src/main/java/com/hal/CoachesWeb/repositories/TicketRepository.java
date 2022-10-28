package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.MonthStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByUserIdAndStatus (int id, int status);
    List<Ticket> findAllByUserId (int id);
    List<Ticket> findAllByCoachesId(int id);
    List<Ticket> findAllByCoachesIdAndStatusIs(int id, int status);
    List<Ticket> findAllByStatus (int status);
    Ticket findTopByEmailOrderByIdDesc(String email);
    boolean existsByCoachesId (int id);
    boolean existsByUserId (int id);

    @Query(value = "select SUM(price) as total , DATE(create_date) as date from ticket where create_date between ?1 and ?2 GROUP BY date(create_date)", nativeQuery = true)
    Collection<MonthStat> getMonthStat(LocalDate start, LocalDate end);
}
