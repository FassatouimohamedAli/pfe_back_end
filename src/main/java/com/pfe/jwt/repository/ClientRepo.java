package com.pfe.jwt.repository;

import com.pfe.jwt.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    //Personne findByUser(User user);

   // List<Client> findByLastName(String lastName);
}
