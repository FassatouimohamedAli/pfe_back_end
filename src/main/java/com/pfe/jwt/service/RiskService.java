package com.pfe.jwt.service;

import com.pfe.jwt.repository.*;
import com.pfe.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RiskService {

    @Autowired
    private RiskRepo riskRepo;
    @Autowired
    private DebiteurRepo debiteurRepo ;
    @Autowired
    private ArrangementRepo arrangementRepo ;

    @Autowired
    private EchancierService echancierService ;

    @Autowired
    private EchancierRepo echancierRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AffectationRecouvrementRepo affectationRecouvrementDao ;

    public List<Risk> findDebiteurById(Long debiteurId) {
        Optional<Debiteur> existingDebiteur = debiteurRepo.findById(debiteurId);
        if (!existingDebiteur.isPresent()) {
            throw new IllegalArgumentException("Debiteur Not Found");
        }

        return riskRepo.findByDebiteurNumero(debiteurId);
    }


    public Risk payerRisk(Long idRisk , float montantPayer,String username) {
        Optional<Risk> existingRisk = riskRepo.findById(idRisk);
        if (!existingRisk.isPresent()) {
            throw new IllegalArgumentException("Risk Not Found");
        }

        Optional<User> existingUser = userRepo.findById(username);
        if (!existingUser.isPresent()) {
            throw new IllegalArgumentException("User Not Found");
        }

        if((montantPayer == 0 ) || (montantPayer <= 0)) {
            throw new IllegalArgumentException("le montant payer est egale 0 ou doit être supérieur à zéro ");
        }

        Risk risk = existingRisk.get();
        Optional<Debiteur> existingRiskDebiteur = debiteurRepo.findById(risk.getDebiteur().getNumero());
        if (!existingRiskDebiteur.isPresent()) {
            throw new IllegalArgumentException("Ce risque n'a pas de débiteur ");
        }

        if (!"Ouvert".equals(risk.getStade())) {
            throw new IllegalStateException("Le risque est déjà cloturé ou dans un autre état");
        }

        AffectationRecouvrement af = new AffectationRecouvrement() ;
            Debiteur debiteur = existingRiskDebiteur.get();
            if (risk.getSoldePrincipal() > 0 && risk.getSoldeIc() > 0 && risk.getMontantIr() == 0 && risk.getSoldeIr() == 0) {
                risk.payerSoldePrincipale(montantPayer);
                af.setTypEffectation("Principale");

            }else if(risk.getSoldePrincipal() == 0 && risk.getSoldeIc() > 0 && risk.getMontantIr() == 0 && risk.getSoldeIr() == 0) {
            risk.payerSoldeIC(montantPayer);
                af.setTypEffectation("IC");

            }else if (risk.getSoldePrincipal() == 0 && risk.getSoldeIc() == 0 && risk.getMontantIr() > 0 && risk.getSoldeIr() > 0) {
                // Si le solde principal et le solde IC sont nuls et le montant IR et le solde IR sont supérieurs à zéro
                risk.payerSoldeIR(montantPayer);
                af.setTypEffectation("IR");

            } else {
                throw new IllegalStateException("Le paiement ne peut pas être effectué pour le solde spécifié");
            }
            debiteur.NouvellemotantSodleRecouv(montantPayer);
            debiteurRepo.save(debiteur);
        af.setMontant(montantPayer);
        af.setDateEffectation(new Date());

    // a un arr ou non
        Arrangement arrangement = arrangementRepo.findByDebiteurNumero(debiteur.getNumero());
        if (arrangement != null) {
            appliquerPaiementEcheanciers(arrangement, montantPayer);
        }
        af.setRisk(risk);
        af.setUser(existingUser.get());
        affectationRecouvrementDao.save(af);
        return riskRepo.save(risk);
    }

    private void appliquerPaiementEcheanciers(Arrangement arrangement, double montantPayer) {
        List<Echancier> echanciers = echancierService.getEchanciersByArrangementId(arrangement.getId());
        for (Echancier echancier : echanciers) {
            if (montantPayer <= 0) {
                break;
            }
            if (!"payer".equalsIgnoreCase(echancier.getEtatEchancier())) {
                double montantRestant = echancier.getMontantPrevu() - echancier.getMontantPayer();
                if (montantPayer >= montantRestant) {
                    echancier.setMontantPayer(echancier.getMontantPrevu());
                    echancier.setEtatEchancier("payer");
                    montantPayer -= montantRestant;
                } else {
                    echancier.setMontantPayer((float) (echancier.getMontantPayer() + montantPayer));
                    echancier.setEtatEchancier("partiellement payér");
                    montantPayer = 0;
                }
                echancierRepo.save(echancier);
            }
        }
    }


    public float calculateTotalMontantForDebiteur(Long debiteurId) {

        Optional<Debiteur> existingDebiteur = debiteurRepo.findById(debiteurId);
        if (!existingDebiteur.isPresent()) {
            throw new IllegalArgumentException("Debiteur Not Found");
        }
        List<Risk> risks = riskRepo.findByDebiteurNumero(debiteurId);
        return Risk.calculateTotalMontant(risks);
    }

    public Optional<Risk> findByRisk(Long id){
        return riskRepo.findById(id) ;
    }


}
