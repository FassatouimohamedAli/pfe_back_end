package com.pfe.jwt.service;

import com.pfe.jwt.repository.ChequeClientRepo;
import com.pfe.jwt.entity.ChequeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChequeClientService {
    @Autowired
    private ChequeClientRepo chequeClientRepo;


    public List<ChequeClient> allChequeClient(){
        return chequeClientRepo.findAll();
    }



}
