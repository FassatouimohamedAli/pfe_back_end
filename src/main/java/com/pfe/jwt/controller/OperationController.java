package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperationController {


    @Autowired
    private OperationService operationService;
//saisie operation just  Role = Frais  ( agent Saisie frais )
    @PostMapping("/addOperation")
    @PreAuthorize("hasRole('Frais')")
    //ilfaut supprimer le id deb pour un cicle dans la base de donnes
    public Operation addOperation(@RequestBody Operation operation, @RequestParam String username , @RequestParam Long numero,@RequestParam long riskNumero) {
        return operationService.addOperation(operation, username,numero,riskNumero);
    }

//consultation tout les utilisateur ( Frais + admin + valdierFrais )
//    @GetMapping("/findByIdOperation")
//    public ResponseEntity<Object> findByIdDebeteurOperation(@RequestParam Long id) {
//        List<Operation> operations = operationService.findAllByDebiteur(id);
//        if (!operations.isEmpty()) {
//            return ResponseEntity.ok(operations);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Operations not found");
//        }
//    }

    @GetMapping("/AllOperations")
    @PreAuthorize("hasRole('ValiderFrais')")
    public ResponseEntity<Object> AllOperations() {
        List<Operation> operations = operationService.fetchOperations();
        if (!operations.isEmpty()) {
            return ResponseEntity.ok(operations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fetch Operations not found");
        }
    }

    //saisie operation just  Role = ValiderFrais  ( agent Dossier frais )
    @PostMapping("/validerOperation")
    @PreAuthorize("hasRole('ValiderFrais')")
    public void validerOperation(@RequestParam Long operationId, @RequestParam String etat,String username) {
        operationService.validerOperation(operationId, etat,username);
    }


    @GetMapping("/fetchAllOperations")
    @PreAuthorize("hasAnyRole('Admin','ValiderFrais','Frais','Recouvrement','ValiderRecouvrement')")
    public ResponseEntity<Object> fetchAll() {
        List<Operation> operations = operationService.fetchAll();
        if (!operations.isEmpty()) {
            return ResponseEntity.ok(operations);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fetch Operations not found");
        }
    }

}
