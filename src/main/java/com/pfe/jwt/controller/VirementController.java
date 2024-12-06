package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Cheque;
import com.pfe.jwt.entity.Virement;
import com.pfe.jwt.service.ChequeService;
import com.pfe.jwt.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VirementController {

    @Autowired
    private VirementService virementService ;

    @PostMapping("/AddVirement")
    @PreAuthorize("hasRole('Frais')")
    public Virement addVirement(@RequestBody Virement virement, @RequestParam long idoperation ) {
        return virementService.ajouterVirement(idoperation,virement);
    }

    @GetMapping("/findVirements")
    @PreAuthorize("hasRole('Frais')")
    public List<Virement> findVirement( ) {
        return virementService.findVirement();
    }
}
