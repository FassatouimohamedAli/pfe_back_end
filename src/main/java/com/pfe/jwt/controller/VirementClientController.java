package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.entity.Virement;
import com.pfe.jwt.entity.VirementClient;
import com.pfe.jwt.service.VirementClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VirementClientController {

    @Autowired
    private VirementClientService virementClientService ;

    @GetMapping("/allvirementclients")
    @PreAuthorize("hasRole('Recouvrement')")
    public List<VirementClient> findAllVirements( ) {
        return virementClientService.allVirementClient();
    }


}
