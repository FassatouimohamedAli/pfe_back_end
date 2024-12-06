package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Debiteur;
import com.pfe.jwt.entity.DebiteurRequest;
import com.pfe.jwt.entity.Operation;
import com.pfe.jwt.service.DebiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DebiteurController {


    @Autowired
    private DebiteurService debiteurService;
    @PostMapping("/addNewDebiteur")
    public ResponseEntity<String> registerNewUser(@RequestBody DebiteurRequest request) {
        return debiteurService.addNewDebiteur(request.getDebiteur(),request.getClient(),request.getPiece());
    }

@GetMapping("/searchParDebiteur")
public ResponseEntity<Object> searchParDebiteur(@RequestParam Long id) {
Optional<Debiteur> debiteurOptional = debiteurService.findDebiteurById(id);
if (debiteurOptional.isPresent()) {
       return ResponseEntity.ok(debiteurOptional.get());
   } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Debiteur not found");
  }
}


}
