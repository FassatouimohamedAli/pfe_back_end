package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Debiteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebiteurRepo extends JpaRepository<Debiteur,Long> {


}
