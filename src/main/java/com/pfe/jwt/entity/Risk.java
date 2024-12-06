package com.pfe.jwt.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Risk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
private String nomCredit ;

    private Float montantEntrePrincipale ;
    private Float soldePrincipal ;
    private Float tauxIc ;
    private Float tauxIr  ;
    private String stade ;
    private float soldeIc ;
    private float soldeIr ;

    //float badel

private Long montantIc ;
    private Long montantIr ;
    private Float montantDesFrais ;

private Date dateTransfer ;

private Date  dateDeblocage ;

    public static Float calculateTotalMontant(List<Risk> risks) {
        Float totalMontant = 0.0f;
        Float soldeInteretRetard = 0.0f;

        for (Risk risk : risks) {
            totalMontant += risk.getSoldePrincipal() + risk.getMontantIc() + risk.getSoldeIr();
        }
        // avec solde ir ( fns qui return ir )


        // Assuming soldeInteretRetard should be added to the total
        totalMontant += soldeInteretRetard;

        return totalMontant;
    }


    public void payerSoldePrincipale(float montantPayer) {

            if ((this.soldePrincipal > 0 && this.soldeIc > 0) && (this.montantIr == 0 && this.soldeIr == 0)) {
                float montantMiseAjour = this.soldePrincipal - montantPayer;
                if(montantMiseAjour < 0) {
                    throw new IllegalArgumentException("Le montant est negative");
                }
                this.setSoldePrincipal(montantMiseAjour);
            } else {
                throw new IllegalStateException("Le paiement ne peut pas être effectué pour le solde spécifié payerSoldePrincipale");
            }

    }

    public void payerSoldeIC(float montantPayer){

   if (((this.soldePrincipal == 0 && this.soldeIc > 0) && (this.montantIr == 0 && this.soldeIr == 0))){
        float montantMiseAjour = this.soldeIc - montantPayer;
        if(montantMiseAjour < 0) {
            throw new IllegalArgumentException("Le montant est negative");
        }
        this.setSoldeIc(montantMiseAjour);
    } else {
        throw new IllegalStateException("Le paiement ne peut pas être effectué pour le solde spécifié payerSoldeIC");
    }
    }



    public void payerSoldeIR(float montantPayer) {
        if ((this.soldePrincipal == 0 && this.soldeIc == 0) && (this.montantIr > 0 && this.soldeIr > 0)) {
            float montantMiseAjour = this.soldeIr - montantPayer;
            if (montantMiseAjour < 0) {
                throw new IllegalArgumentException("Le montant est négatif");
            }
            this.setSoldeIr(montantMiseAjour);
        } else {
            throw new IllegalStateException("Le paiement ne peut pas être effectué pour le solde spécifié payerSoldeIR");
        }
    }






    public void calculeRisk(Operation o ){
        float montantOpR = this.getSoldePrincipal() +  o.getMontant()  ;
        this.setSoldePrincipal((long) montantOpR);
        float MontDF = this.getMontantDesFrais() + o.getMontant();
        this.setMontantDesFrais((long) MontDF);
    }

    public Float getMontantEntrePrincipale() {
        return montantEntrePrincipale;
    }

    public void setMontantEntrePrincipale(Float montantEntrePrincipale) {
        this.montantEntrePrincipale = montantEntrePrincipale;
    }

    public void setSoldePrincipal(Float soldePrincipal) {
        this.soldePrincipal = soldePrincipal;
    }

    public void setMontantDesFrais(Float montantDesFrais) {
        this.montantDesFrais = montantDesFrais;
    }

    public float getSoldeIc() {
        return soldeIc;
    }

    public void setSoldeIc(float soldeIc) {
        this.soldeIc = soldeIc;
    }

    public float getSoldeIr() {
        return soldeIr;
    }

    public void setSoldeIr(float soldeIr) {
        this.soldeIr = soldeIr;
    }




    @ManyToOne
    @JoinColumn(name = "debiteur_id", nullable = false)
    private Debiteur debiteur;

    public String getNomCredit() {
        return nomCredit;
    }

    public void setNomCredit(String nomCredit) {
        this.nomCredit = nomCredit;
    }

    public Debiteur getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }

    public Long getMontantIc() {
        return montantIc;
    }

    public void setMontantIc(Long montantIc) {
        this.montantIc = montantIc;
    }

    public Long getMontantIr() {
        return montantIr;
    }

    public void setMontantIr(Long montantIr) {
        this.montantIr = montantIr;
    }

    public float getMontantDesFrais() {
        return montantDesFrais;
    }

    public void setMontantDesFrais(float montantDesFrais) {
        this.montantDesFrais = montantDesFrais;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }

    public Date getDateDeblocage() {
        return dateDeblocage;
    }

    public void setDateDeblocage(Date dateDeblocage) {
        this.dateDeblocage = dateDeblocage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public float getSoldePrincipal() {
        return soldePrincipal;
    }

    public void setSoldePrincipal(float soldePrincipal) {
        this.soldePrincipal = soldePrincipal;
    }

    public Float getTauxIc() {
        return tauxIc;
    }

    public void setTauxIc(Float tauxIc) {
        this.tauxIc = tauxIc;
    }

    public Float getTauxIr() {
        return tauxIr;
    }

    public void setTauxIr(Float tauxIr) {
        this.tauxIr = tauxIr;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }
}
