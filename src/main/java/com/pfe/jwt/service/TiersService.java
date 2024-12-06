package com.pfe.jwt.service;

import com.pfe.jwt.repository.TiersRepo;
import com.pfe.jwt.entity.Tiers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiersService {

    @Autowired
    private TiersRepo tiersRepo;


    public List<Tiers> findAll(){
        return (List<Tiers>) tiersRepo.findAll();
    }

    public List<Tiers> findByType(String type) {
        return tiersRepo.findByTypeTier(type);
    }
}
