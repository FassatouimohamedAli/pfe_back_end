package com.pfe.jwt.repository;

import com.pfe.jwt.entity.TypeIdentite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TypeidentiteRepo extends JpaRepository<TypeIdentite,Long> {
    TypeIdentite findByLibelle(String libelle);

    List<TypeIdentite> findBynumPiece(String numPiece);
}
