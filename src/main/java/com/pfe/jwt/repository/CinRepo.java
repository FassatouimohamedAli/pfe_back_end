package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Cin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinRepo extends JpaRepository<Cin, Long> {
}
