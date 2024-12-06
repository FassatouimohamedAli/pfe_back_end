package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.entity.Tiers;
import com.pfe.jwt.entity.User;
import com.pfe.jwt.service.TiersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class TiersController {

    @Autowired
    private TiersService tiersService ;

    @GetMapping("/tiers")
    @PreAuthorize("hasRole('Frais')")
    public List<Tiers> getAll() {
return tiersService.findAll() ;   }

    @GetMapping("/findtypeTiers")
    @PreAuthorize("hasRole('Frais')")
    public ResponseEntity<Object> searchTypeTiers(@RequestParam String Type) {
        List<Tiers> tiers = tiersService.findByType(Type);
        if (!tiers.isEmpty()) {
            return ResponseEntity.ok(tiers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tiers   not found");
        }
    }
}
