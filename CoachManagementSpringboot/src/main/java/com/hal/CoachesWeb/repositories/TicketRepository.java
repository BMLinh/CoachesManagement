package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.MonthStat;
import com.hal.CoachesWeb.model.response.Quarter;
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

    @Query(value = "select SUM(price) as total, DATE(create_date) as date from ticket where month(create_date) = ?1 and year(create_date) =?2 GROUP BY date(create_date)", nativeQuery = true)
    Collection<MonthStat> getMonthStat(int month, int year);

    @Query(value = "select  sum(price) as total, month(create_date) as month from ticket where month(create_date) between ?1 and ?2 and year(create_date)=?3  GROUP BY month(create_date)", nativeQuery = true)
    Collection<Quarter> getBetweenStat(int monthStart, int monthEnd, int year);
    @Query(value = "select  sum(price) as total, month(create_date) as month from ticket where year(create_date)=?1  GROUP BY month(create_date)", nativeQuery = true)
    Collection<Quarter> getYearStat(int year);
}
