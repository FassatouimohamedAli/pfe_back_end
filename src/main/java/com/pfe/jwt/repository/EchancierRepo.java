package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Echancier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchancierRepo extends JpaRepository<Echancier,Long> {
    List<Echancier> findByArrangementId(Long arrangementId);
}
