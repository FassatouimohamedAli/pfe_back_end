package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Role;
import com.pfe.jwt.entity.User;
import com.pfe.jwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createNewRole"})
    @PreAuthorize("hasRole('Admin')")
    // add new role in table role
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        try {
            if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Role createdRole = roleService.createNewRole(role);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
        } catch (IllegalArgumentException e) {
            // If roleName is null or empty
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getAllRoles")
    @PreAuthorize("hasRole('Admin')")
    // get all roles from table role
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}
