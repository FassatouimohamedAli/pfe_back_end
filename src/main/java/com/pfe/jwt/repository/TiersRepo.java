package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Tiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TiersRepo extends JpaRepository<Tiers,Long> {

    List<Tiers> findByTypeTier(String typeTier);

}
