package com.hal.CoachesWeb.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hal.CoachesWeb.entity.CoachGarage;
import com.hal.CoachesWeb.entity.Picture;
import com.hal.CoachesWeb.entity.User;
import com.hal.CoachesWeb.model.response.UserDto;
import com.hal.CoachesWeb.repositories.*;
import com.hal.CoachesWeb.service.CoachGarageService;
import com.hal.CoachesWeb.service.UserService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceimpl implements UserService, UserDetailsService {
    private Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dd3tfkb7f",
            "api_key", "267679115459675",
            "api_secret", "oa4v8FNxe1LCovHI1wVLlhH4t9s"));
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleResponsitory roleResponsitory;
    @Autowired
    private CoachGarageServiceImpl coachGarageService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ShippingRepository shippingRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CoachGarageRepository coachGarageRepository;
    @Autowired
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByPhone(phone);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("Không tìm thấy số điện thoại người dùng");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRoleByRoleId().getName()));
        return new org.springframework.security.core.userdetails.User(user.get().getPhone(), user.get().getPassword(), authorities);
    }

    @Override
    public List<UserDto> getAllUser(){
        List<UserDto> userDto = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            UserDto newUserDto = new UserDto().userToDto(user);
            newUserDto.setRole(roleResponsitory.getById(user.getRoleId()).getName());
            userDto.add(newUserDto);
        });
        return userDto;
    }
    @Override
    public UserDto getUserById(int id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            UserDto userDto = new UserDto().userToDto(user.get());
            userDto.setRole(roleResponsitory.getById(user.get().getRoleId()).getName());
            return userDto;
        }
        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDto getUserDtoByPhone(String phone){
        Optional<User> user = userRepository.getUserByPhone(phone);
        if (user.isPresent()){
            UserDto userDto = new UserDto().userToDto(user.get());
            userDto.setRole(roleResponsitory.getById(user.get().getRoleId()).getName());
            return userDto;
        }
        return null;
    }
    @Override
    public User getUserByPhone(String phone){
        return userRepository.getUserByPhone(phone).get();
    }
    @Override
    @Transactional
    public boolean addUser(User user){
        if (user.getAvatarPic()!=null && !user.getAvatarPic().isEmpty()){
            try {
                user.setAvatar(cloudinary.uploader().upload(user.getAvatarPic().getBytes()
                        , ObjectUtils.emptyMap()).get("secure_url").toString());
            } catch (Exception ex){
                System.out.println(ex);
                return false;
            }
        }
        try {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(new User(user.getPassword(),user.getFullname(), user.getEmail(), user.getPhone()
                    , user.getGender(), user.getAvatar(), user.getRoleId(), user.getStatus()));
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateUser(User user){
        if (user.getAvatarPic()!=null && !user.getAvatarPic().isEmpty()){
            try {
                user.setAvatar(cloudinary.uploader().upload(user.getAvatarPic().getBytes()
                        , ObjectUtils.emptyMap()).get("secure_url").toString());
            } catch (Exception ex){
                System.out.println(ex);
                return false;
            }
        }
        try {
            user.setCreatedDate(userRepository.getById(user.getId()).getCreatedDate());
            userRepository.save(user);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }

//    @Override
//    public boolean updateStatus(User user) {
//        try {
//            user.getCoachGaragesById().forEach(coachGarage -> {
//                if (coachGarage.getStatus()!=0){
//                    coachGarageService.updateStatus(coachGarage);
//                }
//            });
//            if (user.getStatus()!=0){
//                user.setStatus(0);
//                userRepository.save(user);
//            }
//            return true;
//        } catch (HibernateException ex){
//            System.out.println(ex.getMessage());
//            return false;
//        }
//    }

    @Override
    public boolean deleteUser(int id){
        User user = userRepository.getById(id);
        user.setCommentsById(commentRepository.findAllByUserId(id));
        user.setCoachGaragesById(coachGarageRepository.findAllByUserId(id));
        user.setTicketsById(ticketRepository.findAllByUserId(id));
        user.setShippingsById(shippingRepository.findAllByUserId(id));
        if (user.getCoachGaragesById().isEmpty() && user.getCommentsById().isEmpty()
                && user.getShippingsById().isEmpty() && user.getTicketsById().isEmpty()){
            try {
                userRepository.deleteById(id);
                return true;
            } catch (HibernateException ex){
                System.out.println(ex);
                return false;
            }
        }
        user.setStatus(0);
        return updateUser(user);
    }
    @Override
    public boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }
    @Override
    public boolean existsById(int id){
        return userRepository.existsById(id);
    }
    @Override
    public boolean isCorrectPassword(String phone, String password){
        return passwordEncoder().matches(password, userRepository.getUserByPhone(phone).get().getPassword());
    }

    @Override
    public boolean isActiveByPhone(String phone) {
        Optional<User> user = userRepository.getUserByPhone(phone);
        if (user.isPresent() && user.get().getStatus()==1)
            return true;
        return false;
    }

    @Override
    public boolean isActiveById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().getStatus()==1)
            return true;
        return false;
    }
}
