package com.pfe.jwt.entity;

import javax.persistence.*;
@Entity
public class Cheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero ;


    private String nomPropritaire  ; //tetenaha

    @OneToOne
    @JoinColumn(name = "operation_id")
    private Operation operation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }



    public String getNomPropritaire() {
        return nomPropritaire;
    }

    public void setNomPropritaire(String nomPropritaire) {
        this.nomPropritaire = nomPropritaire;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
