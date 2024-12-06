package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;
@Entity

public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typePayment ;

    private Float montant ;
    private Date dateReception;

    @OneToOne
    @JoinColumn(name = "virementClient_id")
    private VirementClient virementClient;

    @OneToOne
    @JoinColumn(name = "chequeClient_id")
    private ChequeClient chequeClient;

    @ManyToOne
    @JoinColumn(name = "debiteur_id",nullable = false)
    private Debiteur debiteur;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    public VirementClient getVirementClient() {
        return virementClient;
    }

    public void setVirementClient(VirementClient virementClient) {
        this.virementClient = virementClient;
    }

    public ChequeClient getChequeClient() {
        return chequeClient;
    }

    public void setChequeClient(ChequeClient chequeClient) {
        this.chequeClient = chequeClient;
    }

    public Debiteur getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }
}
