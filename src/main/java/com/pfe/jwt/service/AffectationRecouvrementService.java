package com.pfe.jwt.service;

import com.pfe.jwt.entity.AffectationRecouvrement;
import com.pfe.jwt.repository.AffectationRecouvrementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationRecouvrementService {

    @Autowired
    private AffectationRecouvrementRepo affectationRecouvrementRepo ;

    public List<AffectationRecouvrement> listedesEffectation (){

       return affectationRecouvrementRepo.findAll();
    }
}
