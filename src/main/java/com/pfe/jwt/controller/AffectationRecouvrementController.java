package com.pfe.jwt.controller;

import com.pfe.jwt.entity.AffectationRecouvrement;
import com.pfe.jwt.entity.OperationRecouvrement;
import com.pfe.jwt.service.AffectationRecouvrementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AffectationRecouvrementController {

    @Autowired
    private AffectationRecouvrementService affectationRecouvrementService ;


    @GetMapping("/allAffectations")
    @PreAuthorize("hasAnyRole('Admin','ValiderFrais','Frais','Recouvrement','ValiderRecouvrement')")
    public ResponseEntity<Object> fetchAllAffectation() {
        List<AffectationRecouvrement> affectations = affectationRecouvrementService.listedesEffectation();
        if (!affectations.isEmpty()) {
            return ResponseEntity.ok(affectations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fetch affectations not found");
        }
    }
}
