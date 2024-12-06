package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Debiteur {
    @Id
    @Column(name = "numero")
    private Long numero;
    @Column(name = "transfer_date")
    @Temporal(TemporalType.DATE)
    private Date transferDate;

    private String etat ;
    @Column(name = "solde_recouvrement")
    private Float soldeRecouvrement;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public void calculeSoldeRecouvrmeent(OperationRecouvrement opr) {
        float solderecouv = this.getSoldeRecouvrement() + opr.getPayment().getMontant();
        this.setSoldeRecouvrement(solderecouv);
    }
    public void AnnullerSoldeRecouvrmeent(OperationRecouvrement opr) {
        float solderecouv = this.getSoldeRecouvrement() - opr.getPayment().getMontant();
        this.setSoldeRecouvrement(solderecouv);
    }

    public void NouvellemotantSodleRecouv(float montantPayer) {
        float newSolde = this.soldeRecouvrement - montantPayer;
        if (newSolde < 0) {
            throw new IllegalStateException("Le nouveau solde du débiteur est négatif");
        }
        this.setSoldeRecouvrement(newSolde);
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }


    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Float getSoldeRecouvrement() {
        return soldeRecouvrement;
    }

    public void setSoldeRecouvrement(Float soldeRecouvrement) {
        this.soldeRecouvrement = soldeRecouvrement;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
