package com.pfe.jwt.controller;

import com.pfe.jwt.entity.User;
import com.pfe.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }



    @GetMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/updateUser")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> updateUser(@RequestParam String userName, @RequestParam String oldRoleName, @RequestParam String newRoleName) {
        try {
            User updatedUser = userService.updateUser(userName, oldRoleName, newRoleName);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to update user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/AddUser")
    @PreAuthorize("hasRole('Admin')")
    //add  role
    public ResponseEntity<?> AddUser(@RequestParam String userName, @RequestParam String RoleAdd) {
        try {
            User updatedUser = userService.AddUser(userName, RoleAdd);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to Add Role To user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/removeUserRole")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<?> removeUserRole(@RequestParam String userName, @RequestParam String roleName) {
        try {
            User userDelete = userService.removeUserRole(userName, roleName);
            return ResponseEntity.ok(userDelete); // Retourne l'utilisateur mis à jour
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




    @GetMapping({"/forContentieux"})
    @PreAuthorize("hasRole('Contentieux')")
    //admin + User multipleRoles  @PreAuthorize("hasAnyRole('Admin','User')")
    public String forContentieux(){
        return "This URL is only accessible to the Contentieux ";
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    //admin + User multipleRoles  @PreAuthorize("hasAnyRole('Admin','User')")
    public String forAdmin(){
        return "This URL is only accessible to the admin ";
    }
    @GetMapping({"/forAdminandUser"})
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public String forAdminandUser(){
        return "This URL is only accessible to the admin and User";
    }
    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('Recouvrement')")
    public String forUser(){
        return "This URL is only accessible to the Recouvrement";
    }
}
