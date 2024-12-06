package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Risk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskRepo extends JpaRepository<Risk,Long> {
    List<Risk> findByDebiteurNumero(Long numero);
}
