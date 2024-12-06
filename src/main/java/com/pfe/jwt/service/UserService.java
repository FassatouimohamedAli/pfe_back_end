package com.pfe.jwt.service;

import com.pfe.jwt.repository.ClientRepo;
import com.pfe.jwt.repository.RoleRepo;
import com.pfe.jwt.repository.TypeidentiteRepo;
import com.pfe.jwt.repository.UserRepo;
import com.pfe.jwt.entity.User;
import com.pfe.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService  {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private TypeidentiteRepo typeidentiteRepo;


    @Autowired
    public UserService(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }



    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

    public User registerNewUser(User user) {


try{            // Encode the user password
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));

            // Verify and set the role description
            Set<Role> roles = user.getRole();
            Set<Role> updatedRoles = new HashSet<>();
            for (Role role : roles) {
                Role existingRole = roleRepo.findById(role.getRoleName()).orElse(null);
                if (existingRole != null) {
                    role.setRoleDescription(existingRole.getRoleDescription());
                    updatedRoles.add(role);
                } else {
                    // Handle the case when the role doesn't exist
                    throw new RuntimeException("Role not found: " + role.getRoleName());
                }
            }
            user.setRole(updatedRoles);

            // Save the user
            return userRepo.save(user);
        } catch (Exception e) {
            // Handle any exceptions, maybe log the error
            throw new RuntimeException("Failed to register new user", e);
        }
    }





    public User updateUser(String userName, String oldRoleName, String newRoleName) {
        User user = userRepo.findById(userName).orElse(null);

        if (user != null) {
            Role oldRole = roleRepo.findById(oldRoleName).orElse(null);
            Role newRole = roleRepo.findById(newRoleName).orElse(null);

            if (oldRole != null && newRole != null) {
                Set<Role> userRoles = user.getRole();
                if (userRoles.contains(oldRole)) {
                    userRoles.remove(oldRole);
                    userRoles.add(newRole);
                    user.setRole(userRoles);
                    return userRepo.save(user);

                } else {
                    throw new RuntimeException("User does not have the old role");
                }
            } else {
                throw new RuntimeException("Old or new role not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User removeUserRole(String userName, String roleNameToRemove) {
        User user = userRepo.findById(userName).orElse(null);

        if (user != null) {
            Role roleToRemove = roleRepo.findById(roleNameToRemove).orElse(null);

            if (roleToRemove != null) {
                Set<Role> userRoles = user.getRole();
                if (userRoles.contains(roleToRemove)) {
                    userRoles.remove(roleToRemove);
                    user.setRole(userRoles);
                    return userRepo.save(user);
                } else {
                    throw new RuntimeException("User does not have the role to remove");
                }
            } else {
                throw new RuntimeException("Role to remove not found");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }


    public User AddUser(String userName , String RoleAdd){
        User user = userRepo.findById(userName).orElse(null) ;
        if(user != null){
            Role  roleAdd = roleRepo.findById(RoleAdd).orElse(null);
            if (roleAdd != null) {
                Set<Role> ur;
                ur = user.getRole();
                ur.add(roleAdd);
                user.setRole(ur);
                return userRepo.save(user);
            }else {

                throw new RuntimeException("New role not found");
            }
        } else {

            throw new RuntimeException("User not found");
        }
    }







    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
