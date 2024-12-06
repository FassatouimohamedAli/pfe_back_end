package com.pfe.jwt.service;

import com.pfe.jwt.repository.ArrangementRepo;
import com.pfe.jwt.repository.EchancierRepo;
import com.pfe.jwt.entity.Arrangement;
import com.pfe.jwt.entity.Echancier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EchancierService {

    @Autowired
    private EchancierRepo echancierRepo;
    @Autowired
    private ArrangementRepo arrangementRepo;

    public List<Echancier> createEchanciersForArrangement(Long idArr) {
        Optional<Arrangement> existingArrangement = arrangementRepo.findById(idArr);
        if (!existingArrangement.isPresent()) {
            throw new IllegalArgumentException("Arrangement Not Found");
        }

        Arrangement arrangement = existingArrangement.get();
        float montantArrangement = arrangement.getMontant();
        Date dateDebut = arrangement.getDatedebut();
        int nbreEcheancierMois = arrangement.getNbrEchancierParMois();

        List<Echancier> echanciers = new ArrayList<>();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(dateDebut);

        String periodicite = arrangement.getEchancier().toLowerCase();
        int intervalleMois = getIntervalleMois(periodicite);

        if (intervalleMois == -1) {
            throw new IllegalArgumentException("Échéancier non valide");
        }

        // Calculer le nombre de tranches en fonction de l'intervalle et de la période totale
        int nombreDeTranches = nbreEcheancierMois / intervalleMois;
        float montantParTranche = montantArrangement / nombreDeTranches;

        for (int i = 0; i < nombreDeTranches; i++) {
            Echancier echancier = new Echancier();
            echancier.setMontantPrevu(montantParTranche);
            echancier.setMontantPayer(0f);
            echancier.setEtatEchancier("impayé");
            echancier.setDatePrevu(currentDate.getTime());
            echancier.setArrangement(arrangement);
            echanciers.add(echancier);

            currentDate.add(Calendar.MONTH, intervalleMois);
        }

        return echancierRepo.saveAll(echanciers);
    }

    private int getIntervalleMois(String periodicite) {
        switch (periodicite) {
            case "mensuel":
                return 1;
            case "trimestriel":
                return 3;
            case "semestriel":
                return 6;
            case "annuel":
                return 12;
            default:
                return -1; // Échéancier non valide
        }
    }


    public List<Echancier> getEchanciersByArrangementId(Long arrangementId) {
        return echancierRepo.findByArrangementId(arrangementId);
    }
}
