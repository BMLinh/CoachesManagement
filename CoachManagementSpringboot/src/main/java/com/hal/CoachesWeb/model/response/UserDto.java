package com.hal.CoachesWeb.model.response;

import com.hal.CoachesWeb.entity.User;

import java.util.Date;
import java.util.Objects;


public class UserDto {
    private int id;
    private String fullname;
    private String email;
    private String phone;
    private Boolean gender;
    private String avatar;
    private Date createdDate;
    private String role;
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, email, phone, gender, avatar, createdDate, role, status);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", createdDate=" + createdDate +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public UserDto() {

    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.fullname = user.getFullname();
        userDto.email = user.getEmail();
        userDto.phone = user.getPhone();
        userDto.gender = user.getGender();
        userDto.avatar = user.getAvatar();
        userDto.createdDate = user.getCreatedDate();
        userDto.status = user.getStatus();
        return userDto;
    }
}
