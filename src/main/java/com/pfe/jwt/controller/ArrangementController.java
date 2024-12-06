package com.pfe.jwt.controller;

import com.pfe.jwt.entity.Arrangement;
import com.pfe.jwt.entity.Echancier;
import com.pfe.jwt.entity.Risk;
import com.pfe.jwt.service.ArrangementService;
import com.pfe.jwt.service.EchancierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArrangementController {


    @Autowired
    private ArrangementService arrangementService ;

@Autowired
private EchancierService echancierService ;


    @PostMapping("/addArrangement")
    @PreAuthorize("hasRole('ValiderRecouvrement')")
    // insert amenagement dans la base de donnes
    public List<Echancier> addOperationArrg(@RequestBody Arrangement arrangement, @RequestParam Long numero, String username) {
        Arrangement createdArrangement = arrangementService.CreateArrangement(arrangement, numero, username);

        long id =createdArrangement.getId();
        echancierService.createEchanciersForArrangement(id);
        return echancierService.getEchanciersByArrangementId(createdArrangement.getId());

    }

    @GetMapping("/searchArrangementByDebiteur")
    @PreAuthorize("hasRole('ValiderRecouvrement')")
    public ResponseEntity<Object> searchDebiteurbyarrrrangement(@RequestParam Long id) {
        Arrangement arrangement = arrangementService.getArrangementsByDebiteurId(id);
        if (arrangement != null) {
            return ResponseEntity.ok(arrangement);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arrangement du débiteur non trouvé");
        }
    }
}
