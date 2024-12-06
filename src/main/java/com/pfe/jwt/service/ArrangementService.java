package com.pfe.jwt.service;

import com.pfe.jwt.repository.ArrangementRepo;
import com.pfe.jwt.repository.DebiteurRepo;
import com.pfe.jwt.repository.EchancierRepo;
import com.pfe.jwt.repository.UserRepo;
import com.pfe.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArrangementService {

    @Autowired
    private ArrangementRepo arrangementRepo ;
@Autowired
private DebiteurRepo debiteurRepo;
@Autowired
private UserRepo userRepo;
@Autowired
private EchancierRepo echancierRepo;

@Autowired
private EchancierService echancierService ;
    public Arrangement CreateArrangement(Arrangement arrangement, Long numero,String username) {
        Optional<Debiteur> existingDebiteur = debiteurRepo.findById(numero);
        if (!existingDebiteur.isPresent()) {
            throw new IllegalArgumentException("Débiteur non trouvé");
        }
       Arrangement arrangementExist = arrangementRepo.findByDebiteurNumero(numero);
        if (arrangementExist != null) {
            throw new IllegalArgumentException("arrangement Exist deja ");
        }


        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User non trouvé");
        }


            arrangement.setUser(existingUser.get());
            arrangement.setDebiteur(existingDebiteur.get());

            // Calcul de la date de fin
            arrangement.updateDateFin();


        return arrangementRepo.save(arrangement);

    }



    public Arrangement getArrangementsByDebiteurId(Long debiteurId) {
        return arrangementRepo.findByDebiteurNumero(debiteurId);
    }

}
