package com.pfe.jwt.repository;

import com.pfe.jwt.entity.OperationRecouvrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRecouvrementRepo extends JpaRepository<OperationRecouvrement,Long> {

    boolean existsByPaymentId(Long idPayment);
}
