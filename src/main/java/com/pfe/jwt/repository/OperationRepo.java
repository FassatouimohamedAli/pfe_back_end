package com.pfe.jwt.repository;


import com.pfe.jwt.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {

    //List<Operation> findAllByDebiteurNumero(long numero);
List<Operation> findByTypeOperation(String etat);
}
