package com.pfe.jwt.repository;

import com.pfe.jwt.entity.ChequeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChequeClientRepo extends JpaRepository<ChequeClient,Long> {
    //List<ChequeClient> findByDebiteurNumero(Long numero);

}
