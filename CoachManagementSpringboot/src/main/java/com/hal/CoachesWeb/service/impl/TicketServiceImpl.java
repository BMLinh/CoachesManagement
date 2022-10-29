package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.model.response.FrequentlyMonthStatRes;
import com.hal.CoachesWeb.model.response.MonthStat;
import com.hal.CoachesWeb.model.response.YearStat;
import com.hal.CoachesWeb.repositories.CoachesRepository;
import com.hal.CoachesWeb.repositories.TicketRepository;
import com.hal.CoachesWeb.service.TicketService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CoachesRepository coachesRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Ticket> getAllTicket(Pageable pageable){
        return ticketRepository.findAll(pageable);
    }

    @Override
    public List<Ticket> getTicketByCoachesAndStatus(int id, int status) {
        return ticketRepository.findAllByCoachesIdAndStatusIs(id, status);
    }

    @Override
    public List<Ticket> getAllTicketByCoaches(int id) {
        return ticketRepository.findAllByCoachesId(id);
    }

    @Override
    public List<Ticket> getAllTicketByUserId(int id) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(id);
        tickets.forEach(ticket -> {
            ticket.setStartDate(ticket.getCoachesByCoachesId().getStartTime());
        });
        return tickets;
    }

    @Override
    public List<Ticket> getRequestTicket() {
        return ticketRepository.findAllByStatus(2);
    }

    @Override
    public Optional<Ticket> getTicketById(int id){
        return ticketRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean acceptRefundTicket(Ticket ticket) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1951052099Linh@ou.edu.vn");
        message.setTo(ticket.getEmail());
        message.setSubject("Thông tin hủy vé từ H&L");
        message.setText("Vé xe "+ticket.getId()+" đã được chấp nhận hủy vé" +
                ". Mọi thắc mắc vui lòng liên hệ nhà xe để hiểu rõ hơn" +
                ". Cảm ơn bạn đã tin dùng nhà xe chúng tôi");
        try {
            Coaches coaches = coachesRepository.getById(ticket.getId());
            coaches.setEmptySeat(coaches.getEmptySeat()+1);
            coachesRepository.save(coaches);
            ticketRepository.delete(ticket);
            mailSender.send(message);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @Override
    @Transactional
    public boolean rejectRefundTicket(Ticket ticket) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1951052099Linh@ou.edu.vn");
        message.setTo(ticket.getEmail());
        message.setSubject("Thông tin hủy vé từ H&L");
        message.setText("Vé xe "+ticket.getId()+" không được chấp nhận hủy vé" +
                ". Mọi thắc mắc vui lòng liên hệ nhà xe để hiểu rõ hơn" +
                ". Cảm ơn bạn đã tin dùng nhà xe chúng tôi");
        ticket.setStatus(1);
        try {
            ticketRepository.save(ticket);
            mailSender.send(message);
            return true;
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean addTicket(Ticket ticket){
        String text = "Các mã vé của bạn là:";
        try {
            Coaches coaches = coachesRepository.getById(ticket.getCoachesId());
            coaches.setEmptySeat(coaches.getEmptySeat()-ticket.getAmount());
            coachesRepository.save(coaches);
            ticketRepository.save(new Ticket(coaches.getPrice()*ticket.getAmount(), ticket.getEmail()
                    , ticket.getPhone(), ticket.getName(), ticket.getAmount(), ticket.getCoachesId(), ticket.getUserId()
                    , ticket.getPickUpId(), ticket.getDropOffId(), 1));
                    text = text.concat(" "+ticketRepository.findTopByEmailOrderByIdDesc(ticket.getEmail()).getId());
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1951052099Linh@ou.edu.vn");
            message.setTo(ticket.getEmail());
            message.setSubject("Thông tin vé xe của H&L");
            message.setText(text);
            try {
                mailSender.send(message);
            } catch (MailException ex){
                System.out.println(ex.getMessage());
            }
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean updateTicket(Ticket ticket){
        try {
            Ticket oldTicket = ticketRepository.getById(ticket.getId());
            oldTicket.setPhone(ticket.getPhone());
            oldTicket.setName(ticket.getName());
            oldTicket.setEmail(ticket.getEmail());
            oldTicket.setDropOffId(ticket.getDropOffId());
            oldTicket.setPickUpId(ticket.getPickUpId());
            oldTicket.setStatus(ticket.getStatus());
            ticketRepository.save(oldTicket);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteTicket(int id){
        Ticket ticket = ticketRepository.getById(id);
        try {
            Coaches coaches = coachesRepository.getById(ticket.getCoachesId());
            coaches.setEmptySeat(coaches.getEmptySeat()+ticket.getAmount());
            coachesRepository.save(coaches);
            ticketRepository.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
    @Override
    public boolean existsById(int id){
        return ticketRepository.existsById(id);
    }

    @Override
    public Collection<MonthStat> getMonthStat(int month, int year) {
        return ticketRepository.getMonthStat(month, year);
    }

    @Override
    public Collection<YearStat> getQuarterStat(int quarter, int year) {
        return ticketRepository.getBetweenStat( quarter*3-2, quarter*3, year);
    }
    @Override
    public Collection<YearStat> getYearStat(int year) {
        return ticketRepository.getYearStat(year);
    }

    @Override
    public Collection<MonthStat> getMonthStatByCoachGarage(int month, int year, int id) {
        return ticketRepository.getMonthStatByCoachGarage(month, year, id);
    }

    @Override
    public Collection<YearStat> getQuarterStatByCoachGarage(int quarter, int year, int id) {
        return ticketRepository.getBetweenStatByCoachGarage(quarter*3-2, quarter*3, year, id);
    }

    @Override
    public Collection<YearStat> getYearStatByCoachGarage(int year, int id) {
        return ticketRepository.getYearStatByCoachGarage(year, id);
    }
    @Override
    public Collection<FrequentlyMonthStatRes> getMonthFrequentlyStat(int month, int year) {
        return ticketRepository.getFrequentlyMonthStat(month,year);
    }

    @Override
    public Collection<FrequentlyMonthStatRes> getQuarterFrequentlyStat(int quarter, int year) {
        return ticketRepository.getFrequentlyBewteenMonthStat(quarter*3-2, quarter*3, year);
    }

    @Override
    public Collection<FrequentlyMonthStatRes> getYearFrequentlyStat(int year) {
        return ticketRepository.getFrequentlyYearStat(year);
    }

    @Override
    public Collection<FrequentlyMonthStatRes> getMonthFrequentlyStatByCoachGarage(int month, int year, int id) {
        return ticketRepository.getFrequentlyMonthStatByUser(month, year, id);
    }

    @Override
    public Collection<FrequentlyMonthStatRes> getQuarterFrequentlyStatByCoachGarage(int quarter, int year, int id) {
        return ticketRepository.getFrequentlyBetweenMonthStatByUser(quarter*3-2, quarter*3, year, id);
    }

    @Override
    public Collection<FrequentlyMonthStatRes> getYearFrequentlyStatByCoachGarage(int year, int id) {
        return ticketRepository.getFrequentlyYearStatByUSer(year, id);
    }

}
