package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Debiteur;
import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.service.DebiteurService;
import com.pfe.jwt.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RiskController {

    @Autowired
    private RiskService riskService;


@GetMapping("/searchDebiteur")
public ResponseEntity<Object> searchDebiteur(@RequestParam Long id) {
    List<Risk> Risks = riskService.findDebiteurById(id);
    if (!Risks.isEmpty()) {
        return ResponseEntity.ok(Risks);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("risk debiteur  not found");
    }
}

    @GetMapping("/findRisk")
    @PreAuthorize("hasAnyRole('Admin','ValiderFrais','Frais','Recouvrement','ValiderRecouvrement')")
    public ResponseEntity<Object> FindRisk(@RequestParam Long id) {
        Optional<Risk> Risks = riskService.findByRisk(id);
        if (Risks.isPresent()) {
            return ResponseEntity.ok(Risks);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Risk with ID " + id + " not found");
        }
    }




    @GetMapping("/calculateTotalMontant")
    @PreAuthorize("hasAnyRole('ValiderRecouvrement')")
    public ResponseEntity<Float> calculateTotalMontantForDebiteur(@RequestParam Long id) {
        Float totalMontant = riskService.calculateTotalMontantForDebiteur(id);
        return new ResponseEntity<>(totalMontant, HttpStatus.OK);
    }

    @PostMapping("/PayerRisk")
    @PreAuthorize("hasAnyRole('Recouvrement')")
    public ResponseEntity<?> PayerRisk( @RequestParam Long idRisk , @RequestParam float montant,String username ) {
     Risk   risk = riskService.payerRisk(idRisk,montant,username);
        return ResponseEntity.ok(risk);
    }



}
