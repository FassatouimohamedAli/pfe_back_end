package com.pfe.jwt.service;

import com.pfe.jwt.repository.RoleRepo;
import com.pfe.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
    public List<Role> getAllRoles() {
        return (List<Role>) roleRepo.findAll();
    }

}
