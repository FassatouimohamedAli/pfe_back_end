package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Arrangement;
import com.pfe.jwt.entity.Echancier;
import com.pfe.jwt.service.EchancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EchancierController {


    @Autowired
    private EchancierService echancierService ;

    @GetMapping("/getEchanciersByArrangement")
    @PreAuthorize("hasRole('ValiderRecouvrement')")
    public ResponseEntity<Object> getEchanciersByArrangement(@RequestParam Long arrangementId) {
        List<Echancier> echanciers = echancierService.getEchanciersByArrangementId(arrangementId);
        if (!echanciers.isEmpty()) {
            return ResponseEntity.ok(echanciers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Echéanciers not found for the given arrangement ID");
        }
    }

}
