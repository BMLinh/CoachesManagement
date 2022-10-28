package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.entity.Coaches;
import com.hal.CoachesWeb.entity.Ticket;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.repositories.CoachGarageRepository;
import com.hal.CoachesWeb.repositories.UserRepository;
import com.hal.CoachesWeb.service.CoachGarageService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CoachGarageServiceImpl implements CoachGarageService {
    @Autowired
    private CoachGarageRepository coachGarageRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CoachServiceImpl coachService;

    @Override
    public List<CoachGarage> getCoachGarage(){
        return coachGarageRepository.findAllByStatusIsNot(2);
    }

    @Override
    public List<CoachGarage> getCoachGarageByStatus(int status) {
        return coachGarageRepository.findAllByStatus(status);
    }

    @Override
    public List<CoachGarage> getCoachGarageByUserId(int id) {
        return coachGarageRepository.findAllByUserIdAndStatus(id, 1);
    }

    @Override
    public Optional<CoachGarage> getCoachGarageById(int id){
        return coachGarageRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean acceptCoachGarage(CoachGarage coachGarage) {
        coachGarage.setStatus(1);
        if (updateCoachGarage(coachGarage)) {
            User user = coachGarage.getUserByUserId();
            user.setRoleId(3);
            userRepository.save(user);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1951052099Linh@ou.edu.vn");
            message.setTo(coachGarage.getUserByUserId().getEmail());
            message.setSubject("Cảm ơn bạn đã đăng ký là đối tác với chung tôi");
            message.setText("Đơn đăng ký thêm mới nhà xe của bạn đã được chấp thuận" +
                    ". Với tất cả sự tôn trọng, chúng tôi rất mong hợp tác thuận lợi " +
                    "và bền vững cho sự phát triển của chúng ta");
            try {
                mailSender.send(message);
                return true;
            } catch (MailException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean rejectCoachGarage(CoachGarage coachGarage) {
        if (deleteCoachGarage(coachGarage.getId())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1951052099Linh@ou.edu.vn");
            message.setTo(coachGarage.getUserByUserId().getEmail());
            message.setSubject("Cảm ơn bạn đã đăng ký là đối tác với chung tôi");
            message.setText("Đơn đăng ký thêm mới nhà xe của bạn đã bị từ chối" +
                    ". Đây là một sự đáng tiếc khi không thể hợp tác với bạn" +
                    ". Nếu bạn cảm thấy đây là một sự nhầm lẫn hay thử liên hệ quản lý");
            try {
                mailSender.send(message);
                return true;
            } catch (MailException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addCoachGarage(CoachGarage coachGarage){
        try {
            coachGarageRepository.save(new CoachGarage
                    (coachGarage.getName(), coachGarage.getOwner(), coachGarage.getPhone(), coachGarage.getEmail()
                            , coachGarage.getAddress(), coachGarage.getDistrictId()
                            , coachGarage.getUserId(), coachGarage.getContract(), coachGarage.getStatus()));
            User user = userRepository.getById(coachGarage.getUserId());
            if (user.getRoleId()!=3){
                user.setRoleId(3);
                userRepository.save(user);
            }
            return true;
        } catch (HibernateException ex) {
            System.out.println(ex);
            return false;
        }
    }

    @Override
    public boolean updateCoachGarage(CoachGarage coachGarage){
        try {
            coachGarageRepository.save(coachGarage);
            return true;
        } catch (HibernateException ex) {
            System.out.println(ex);
            return false;
        }
    }

    //    @Override
//    public boolean updateStatus(CoachGarage coachGarage) {
//        try {
//            coachGarage.setCoachesById(coachService.getAllCoachByGarageId(coachGarage.getId()));
//            System.out.println("coachGarage coach");
//            coachGarage.getCoachesById().forEach(coach -> {
//                if (coach.getStatus()!=0){
//                    coachService.updateStatus(coach);
//                }
//            });
//            if (coachGarage.getStatus()!=0){
//                coachGarage.setStatus(0);
//                coachGarageRepository.save(coachGarage);
//            }
//            return true;
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }

    @Override
    public boolean deleteCoachGarage (int id){
        CoachGarage coachGarage = coachGarageRepository.getById(id);
        if (coachService.getAllCoachByGarageId(id).isEmpty()){
            try {
                coachGarageRepository.deleteById(id);
                return true;
            } catch (HibernateException ex){
                System.out.println(ex);
                return false;
            }
        }
        coachGarage.setStatus(0);
        return updateCoachGarage(coachGarage);
    }
    @Override
    public boolean existsById(int id){
        return coachGarageRepository.existsById(id);
    }

    @Override
    public boolean isActive(int id) {
        return coachGarageRepository.existsByIdAndStatus(id, 1);
    }
}
