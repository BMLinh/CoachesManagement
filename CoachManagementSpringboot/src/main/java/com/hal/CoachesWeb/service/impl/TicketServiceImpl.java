package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.entity.Ticket;
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
    public List<Ticket> getTicketByCoaches(int id) {
        return ticketRepository.findAllByCoachesId(id);
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
    public boolean addTicket(Ticket ticket){
        String text = "Các mã vé của bạn là:";
        try {
            Coaches coaches = coachesRepository.getById(ticket.getCoachesId());
            coaches.setEmptySeat(coaches.getEmptySeat()-ticket.getAmount());
            coachesRepository.save(coaches);
            ticketRepository.save(new Ticket(ticket.getPrice(), ticket.getEmail()
                    , ticket.getPhone(), ticket.getAmount(), ticket.getCoachesId(), ticket.getUserId()
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
            ticket.setCoachesId(oldTicket.getCoachesId());
            ticket.setCreateDate(oldTicket.getCreateDate());
            ticket.setUserId(oldTicket.getUserId());
            ticket.setPrice(oldTicket.getPrice());
            ticketRepository.save(ticket);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteTicket(int id){
        try {
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
}
