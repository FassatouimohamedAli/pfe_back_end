package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Cheque;
import com.pfe.jwt.entity.ChequeClient;
import com.pfe.jwt.entity.VirementClient;
import com.pfe.jwt.service.ChequeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ChequeClientController {

    @Autowired
    private ChequeClientService chequeClientService ;


    @GetMapping("/allchequeclients")
    @PreAuthorize("hasRole('Recouvrement')")
    public List<ChequeClient> findAllCheques( ) {
        return chequeClientService.allChequeClient();
    }



}
