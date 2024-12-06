package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomPropritaireVirement ; //tetnaha
    private Date dateVirement ;
    @OneToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPropritaireVirement() {
        return nomPropritaireVirement;
    }

    public void setNomPropritaireVirement(String nomPropritaire) {
        this.nomPropritaireVirement = nomPropritaire;
    }

    public Date getDateVirement() {
        return dateVirement;
    }

    public void setDateVirement(Date dateVirment) {
        this.dateVirement = dateVirment;
    }
}
