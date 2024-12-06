package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Echancier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
private Date datePrevu ;
    private Float montantPrevu ;
    private Float montantPayer  ;
    private String etatEchancier ;

    @ManyToOne
    @JoinColumn(name = "arrangement_id")
    private Arrangement arrangement;



    public Date getDatePrevu() {
        return datePrevu;
    }

    public void setDatePrevu(Date datePrevu) {
        this.datePrevu = datePrevu;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Float getMontantPrevu() {
        return montantPrevu;
    }

    public void setMontantPrevu(Float montantPrevu) {
        this.montantPrevu = montantPrevu;
    }

    public Float getMontantPayer() {
        return this.montantPayer;
    }

    public void setMontantPayer(Float montantPayer) {
        this.montantPayer = montantPayer;
    }

    public String getEtatEchancier() {
        return etatEchancier;
    }

    public void setEtatEchancier(String etatEchancier) {
        this.etatEchancier = etatEchancier;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }
}
