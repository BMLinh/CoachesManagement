package com.hal.CoachesWeb.repositories;

import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.FrequentlyMonthStatRes;
import com.hal.CoachesWeb.model.response.MonthStat;
import com.hal.CoachesWeb.model.response.YearStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query(value = "select SUM(price) as total, DATE(create_date) as date, YEAR(create_date) as year " +
            "from ticket " +
            "where month(create_date) = ?1 and year(create_date) =?2 " +
            "GROUP BY date(create_date) " +
            "order by Date(create_date)asc", nativeQuery = true)
    Collection<MonthStat> getMonthStat(int month, int year);

    @Query(value = "select  sum(price) as total, month(create_date) as month , YEAR(create_date) as year " +
            "from ticket " +
            "where month(create_date) between ?1 and ?2 and year(create_date)=?3  " +
            "GROUP BY month(create_date) " +
            "order by month(create_date)asc", nativeQuery = true)
    Collection<YearStat> getBetweenStat(int monthStart, int monthEnd, int year);
    @Query(value = "select  sum(price) as total, month(create_date) as month , YEAR(create_date) as year " +
            "from ticket " +
            "where year(create_date)=?1  " +
            "GROUP BY month(create_date) " +
            "order by month(create_date)asc", nativeQuery = true)
    Collection<YearStat> getYearStat(int year);

    @Query(value = "select sum(t.price) as total , Date(t.create_date) as date , YEAR(t.create_date) as year " +
            "from coach_garage cg, coach c, coaches cs, ticket t " +
            "where month(t.create_date)=?1 and year(t.create_date)=?2 and cg.user_id = ?3 " +
            "and c.coach_garage_id=cg.id and cs.coach_id = c.id and t.coaches_id=cs.id " +
            "group by Date(t.create_date) " +
            "order by Date(t.create_date)asc", nativeQuery = true)
    Collection<MonthStat> getMonthStatByCoachGarage(int month, int year, int id);

    @Query(value = "select sum(t.price) as total , Month(t.create_date) as month  , YEAR(t.create_date) as year " +
            "from coach_garage cg, coach c, coaches cs, ticket t " +
            "where month(t.create_date) between ?1 and ?2 and year(t.create_date)=?3 and cg.user_id = ?4 " +
            "and c.coach_garage_id=cg.id and cs.coach_id = c.id and t.coaches_id=cs.id " +
            "group by Month(t.create_date) " +
            "order by month(t.create_date)asc", nativeQuery = true)
    Collection<YearStat> getBetweenStatByCoachGarage(int monthStart, int monthEnd, int year, int id);

    @Query(value = "select sum(t.price) as total , month(t.create_date) as month , YEAR(t.create_date) as year " +
            "from coach_garage cg, coach c, coaches cs, ticket t " +
            "where year(t.create_date)=?1 and cg.user_id = ?2 and c.coach_garage_id=cg.id " +
            "and cs.coach_id = c.id and t.coaches_id=cs.id " +
            "group by Month(t.create_date) " +
            "order by month(t.create_date)asc", nativeQuery = true)
    Collection<YearStat> getYearStatByCoachGarage(int year, int id);

    @Query(value = "select month(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches c " +
            "join country c1 on c.start_point = c1.id " +
            "join country c2 on c.end_point = c2.id " +
            "join ticket t on t.coaches_id = c.id " +
            "where month(t.create_date) = ?1 and year(t.create_date)=?2 " +
            "group by c.start_point " +
            "order by c.end_point desc, month(t.create_date) asc", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyMonthStat(int month, int year);

    @Query(value = "select quarter(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches c " +
            "join country c1 on c.start_point = c1.id " +
            "join country c2 on c.end_point = c2.id " +
            "join ticket t on t.coaches_id = c.id " +
            "where month(t.create_date) between ?1 and ?2 and year(t.create_date)=?3 " +
            "group by c.start_point " +
            "order by c.end_point desc, month(t.create_date) asc", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyBewteenMonthStat(int start, int end, int year);

    @Query(value = "select year(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches c " +
            "join country c1 on c.start_point = c1.id " +
            "join country c2 on c.end_point = c2.id " +
            "join ticket t on t.coaches_id = c.id " +
            "where year(t.create_date)=?1 " +
            "group by c.start_point " +
            "order by c.end_point desc, month(t.create_date) asc", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyYearStat(int year);

    @Query(value = "select month(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches cs " +
            "join country c1 on cs.start_point = c1.id " +
            "join country c2 on cs.end_point = c2.id " +
            "join ticket t on t.coaches_id = cs.id " +
            "join coach c on c.id = cs.coach_id " +
            "join coach_garage cg on c.coach_garage_id=cg.id " +
            "join user u on u.id=cg.user_id " +
            "where month(t.create_date)=?1 and year(t.create_date)=?2 and u.id=?3 " +
            "group by cs.start_point " +
            "order by cs.end_point desc;", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyMonthStatByUser(int month, int year, int id);

    @Query(value = "select quarter(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches cs " +
            "join country c1 on cs.start_point = c1.id " +
            "join country c2 on cs.end_point = c2.id " +
            "join ticket t on t.coaches_id = cs.id " +
            "join coach c on c.id = cs.coach_id " +
            "join coach_garage cg on c.coach_garage_id=cg.id " +
            "join user u on u.id=cg.user_id " +
            "where month(t.create_date) between ?1 and ?2 and year(t.create_date)=?3 and u.id=?4 " +
            "group by cs.start_point " +
            "order by cs.end_point desc;", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyBetweenMonthStatByUser(int start, int end, int year, int id);

    @Query(value = "select year(t.create_date) as id, YEAR(t.create_date) as year, count(t.id) as amount, c1.name as startPoint, c2.name as endPoint " +
            "from coaches cs " +
            "join country c1 on cs.start_point = c1.id " +
            "join country c2 on cs.end_point = c2.id " +
            "join ticket t on t.coaches_id = cs.id " +
            "join coach c on c.id = cs.coach_id " +
            "join coach_garage cg on c.coach_garage_id=cg.id " +
            "join user u on u.id=cg.user_id " +
            "where year(t.create_date)=?1 and u.id=?2 " +
            "group by cs.start_point " +
            "order by cs.end_point desc;", nativeQuery = true)
    Collection<FrequentlyMonthStatRes> getFrequentlyYearStatByUSer(int year, int id);
}
