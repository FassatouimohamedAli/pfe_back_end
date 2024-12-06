package com.pfe.jwt.repository;

import com.pfe.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

   // List<User> findByUserFirstName(String firstName);
}
