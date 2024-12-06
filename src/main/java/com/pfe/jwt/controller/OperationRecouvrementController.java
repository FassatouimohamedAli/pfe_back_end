package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.entity.OperationRecouvrement;
import com.pfe.jwt.service.OperationRecouvrementService;
import com.pfe.jwt.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationRecouvrementController {




    @Autowired
    private OperationRecouvrementService operationRecouvrementService;
    @PostMapping("/addoperationtcheque")
    @PreAuthorize("hasRole('Recouvrement')")
    public ResponseEntity<?> createOperationForCheque(@RequestParam Long paymentId, @RequestParam String username) {
        try {
            OperationRecouvrement operation = operationRecouvrementService.createOperationRecouvrement(paymentId, username);
            return new ResponseEntity<>(operation, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @PostMapping("/validerOperationRecouvrement")
    @PreAuthorize("hasRole('ValiderRecouvrement')")
    public void validerOperationRecouvrement(@RequestParam Long operationId, @RequestParam String etat,String username) {
        operationRecouvrementService.validerOperationRecouvrement(operationId, etat,username);
    }

    @GetMapping("/fetchAllOperationsR")
    @PreAuthorize("hasAnyRole('Admin','ValiderFrais','Frais','Recouvrement','ValiderRecouvrement')")
    public ResponseEntity<Object> fetchAll() {
        List<OperationRecouvrement> operations = operationRecouvrementService.fetchAll();
        if (!operations.isEmpty()) {
            return ResponseEntity.ok(operations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fetch Operations not found");
        }
    }
/*
    @GetMapping("checkOperationRecouvrement")
    @PreAuthorize("hasRole('Recouvrement')")
    public ResponseEntity<Boolean> checkOperationRecouvrement(@RequestParam  Long idPayment) {
        boolean operationExists = operationRecouvrementService.existsByPaymentId(idPayment);
        return ResponseEntity.ok(operationExists);
    }

 */

    }

