package com.hal.CoachesWeb.service.impl;

import com.hal.CoachesWeb.entity.Role;
import com.hal.CoachesWeb.repositories.RoleResponsitory;
import com.hal.CoachesWeb.service.RoleService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleResponsitory roleResponsitory;

    @Override
    public Optional<Role> getRoleById (int id){
        return roleResponsitory.findById(id);
    }
    @Override
    public List<Role> getAllRole(){
        return roleResponsitory.findAll();
    }
    @Override
    public Role getRoleByName(String name){
        return roleResponsitory.findByName(name).get();
    }
    @Override
    public boolean addRole(Role role){
        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setStatus(role.getStatus());
        try{
            roleResponsitory.save(newRole);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean updateRole(Role role){
        Role newRole = roleResponsitory.getById(role.getId());
        newRole.setName(role.getName());
        newRole.setStatus(role.getStatus());
        try {
            roleResponsitory.save(newRole);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean deleteRole(int id){
        try {
            roleResponsitory.deleteById(id);
            return true;
        } catch (HibernateException ex){
            System.out.println(ex);
        }
        return false;
    }
}
