package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
//@Table(name = "operation_frais")
public class Operation {
    //operation Frais


    /// fns valider pour modifier etat

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation  ;
    private String nomBeneficier ;
    private String rib ;
    private String typeOperation ;
    private String etatOperation ;
    private String detailOperation ;
    private String objectOperation ;
    private Float Montant ;
    private String typePayment ;
    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "debiteur_id")
//    private Debiteur debiteur;
    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tiers tiers;

    @ManyToOne
    @JoinColumn(name = "risk_id")
    private Risk risk ;

    public Tiers getTiers() {
        return tiers;
    }

    public void setTiers(Tiers tiers) {
        this.tiers = tiers;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getNomBeneficier() {
        return nomBeneficier;
    }

    public void setNomBeneficier(String nomBeneficier) {
        this.nomBeneficier = nomBeneficier;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getEtatOperation() {
        return etatOperation;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public String getDetailOperation() {
        return detailOperation;
    }

    public void setDetailOperation(String detailOperation) {
        this.detailOperation = detailOperation;
    }

    public String getObjectOperation() {
        return objectOperation;
    }

    public void setObjectOperation(String objectOperation) {
        this.objectOperation = objectOperation;
    }

    public Float getMontant() {
        return Montant;
    }

    public void setMontant(Float montant) {
        Montant = montant;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }




}

