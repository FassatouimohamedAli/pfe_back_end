package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirementRepo extends JpaRepository<Virement,Long> {

}
