package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Passeport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasseportRepo extends JpaRepository<Passeport,Long> {

}
