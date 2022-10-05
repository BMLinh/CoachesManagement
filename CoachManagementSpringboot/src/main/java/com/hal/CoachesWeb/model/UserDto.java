package com.hal.CoachesWeb.model;

import com.hal.CoachesWeb.entity.User;

import java.util.Date;
import java.util.Objects;


public class UserDto {
    private int id;
    private String fullname;
    private String email;
    private String phone;
    private Date dob;
    private Boolean gender;
    private String avatar;
    private Date createdDate;
    private Integer roleId;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullname, email, phone, dob, gender, avatar, createdDate, roleId, status);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", avata='" + avatar + '\'' +
                ", createdDate=" + createdDate +
                ", roleId=" + roleId +
                ", status=" + status +
                '}';
    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.id = user.getId();
        userDto.fullname = user.getFullname();
        userDto.email = user.getEmail();
        userDto.phone = user.getPhone();
        userDto.dob = user.getDob();
        userDto.gender = user.getGender();
        userDto.avatar = user.getAvatar();
        userDto.createdDate = user.getCreatedDate();
        userDto.roleId = user.getRoleId();
        userDto.status = user.getStatus();
        return userDto;
    }
}
