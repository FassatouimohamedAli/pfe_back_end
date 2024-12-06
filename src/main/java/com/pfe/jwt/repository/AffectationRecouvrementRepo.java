package com.pfe.jwt.repository;

import com.pfe.jwt.entity.AffectationRecouvrement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRecouvrementRepo extends JpaRepository<AffectationRecouvrement,Long> {


}
