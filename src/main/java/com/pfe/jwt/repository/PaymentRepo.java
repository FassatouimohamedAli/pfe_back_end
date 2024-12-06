package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    List<Payment> findAllByDebiteurNumero(long numero);
}
