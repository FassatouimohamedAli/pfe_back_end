package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class ChequeClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero ;

    //nom benificier
    private String nomDestinataire ;
    private String nomPropritaire ;
private String agenceReception ;



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

    public String getNomDestinataire() {
        return nomDestinataire;
    }

    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }

    public String getNomPropritaire() {
        return nomPropritaire;
    }

    public void setNomPropritaire(String nomPropritaire) {
        this.nomPropritaire = nomPropritaire;
    }

    public String getAgenceReception() {
        return agenceReception;
    }

    public void setAgenceReception(String agenceReception) {
        this.agenceReception = agenceReception;
    }




}
