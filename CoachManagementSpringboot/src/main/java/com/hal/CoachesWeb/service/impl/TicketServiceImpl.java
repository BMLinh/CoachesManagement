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
    public Optional<Ticket> getTicketById(int id){
        return ticketRepository.findById(id);
    }
    @Override
    public boolean addTicket(Ticket ticket, int amount){
        String text = "Các mã vé của bạn là:";
        try {
            Coaches coaches = coachesRepository.getById(ticket.getCoachesId());
            coaches.setEmptySeat(coaches.getEmptySeat()-amount);
            coachesRepository.save(coaches);
            for (int i = 1; i <= amount; i++) {
                ticketRepository.save(new Ticket(ticket.getPrice(), ticket.getEmail()
                        , ticket.getPhone(), ticket.getCoachesId(), ticket.getUserId()
                        , ticket.getPickUpId(), ticket.getDropOffId(), 1));
                if (i<amount){
                    text = text.concat(" "+ticketRepository.findTopByEmailOrderByIdDesc(ticket.getEmail()).getId()+",");
                }else {
                    text = text.concat(" "+ticketRepository.findTopByEmailOrderByIdDesc(ticket.getEmail()).getId()+".");
                }
            }
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
//    @Override
//    public Ticket updateTicket(Ticket ticket){
//        try {
//            ticketRepository.save(ticket);
//            return ticketRepository.getById(ticket.getId());
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
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
