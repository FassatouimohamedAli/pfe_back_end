package com.pfe.jwt.repository;

import com.pfe.jwt.entity.VirementClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VirementClientRepo extends JpaRepository<VirementClient,Long> {

    //List<VirementClient> findByDebiteurNumero(Long numero);

}
