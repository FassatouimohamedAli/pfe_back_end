package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Cheque;
import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.service.ChequeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChequeController {

    @Autowired
    private ChequeService chequeService ;

    @PostMapping("/addCheque")
    @PreAuthorize("hasRole('Frais')")
    public Cheque addCheque(@RequestBody Cheque cheque, @RequestParam long idoperation ) {
        return chequeService.ajouterCheque(idoperation,cheque);
    }

    @GetMapping("/findCheques")
    @PreAuthorize("hasRole('Frais')")
    public List<Cheque> findCheques( ) {
        return chequeService.findCheques();
    }

}
