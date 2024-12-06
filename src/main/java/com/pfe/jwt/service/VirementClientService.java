package com.pfe.jwt.service;

import com.pfe.jwt.repository.VirementClientRepo;

import com.pfe.jwt.entity.VirementClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VirementClientService {

    @Autowired
    private VirementClientRepo virementClientRepo;


    public List<VirementClient> allVirementClient(){
        return virementClientRepo.findAll();
    }





}
