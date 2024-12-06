package com.pfe.jwt.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Tiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  private String typeTier ;
  private String nom ;
  private String prenom ;
  private String rib ;
  private String tel ;

    public Tiers() {}
    public Tiers(String idStr) {
        this.id = Long.parseLong(idStr);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeTier() {
        return typeTier;
    }

    public void setTypeTier(String typeTier) {
        this.typeTier = typeTier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
