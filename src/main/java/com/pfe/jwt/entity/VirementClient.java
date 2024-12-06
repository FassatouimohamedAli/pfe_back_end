package com.pfe.jwt.entity;

import javax.persistence.*;

@Entity
public class VirementClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rib ;
    private String nomPropritaireVirement ;
    private String nomDestinataire ;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }


    public String getNomPropritaireVirement() {
        return nomPropritaireVirement;
    }

    public void setNomPropritaireVirement(String nomPropritaireVirement) {
        this.nomPropritaireVirement = nomPropritaireVirement;
    }

    public String getNomDestinataire() {
        return nomDestinataire;
    }

    public void setNomDestinataire(String nomDestinataire) {
        this.nomDestinataire = nomDestinataire;
    }


}
