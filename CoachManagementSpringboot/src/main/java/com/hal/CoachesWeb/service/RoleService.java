package com.hal.CoachesWeb.service;

import com.hal.CoachesWeb.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> getRoleById(int id);
    List<Role> getAllRole();
    Role getRoleByName(String name);
    boolean addRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(int id);
}
