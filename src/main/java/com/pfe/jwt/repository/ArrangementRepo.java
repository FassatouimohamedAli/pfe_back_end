package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrangementRepo extends JpaRepository<Arrangement,Long> {

    Arrangement findByDebiteurNumero(Long numero);
}
