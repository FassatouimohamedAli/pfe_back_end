package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity

public class AffectationRecouvrement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float montant ;
    private String typEffectation ;
    private Date dateEffectation ;

    @ManyToOne
    @JoinColumn(name = "Risk_id",nullable = false)
    private Risk risk;

    @ManyToOne
    @JoinColumn(name = "User_id",nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getTypEffectation() {
        return typEffectation;
    }

    public void setTypEffectation(String typEffectation) {
        this.typEffectation = typEffectation;
    }

    public Date getDateEffectation() {
        return dateEffectation;
    }

    public void setDateEffectation(Date dateEffectation) {
        this.dateEffectation = dateEffectation;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
