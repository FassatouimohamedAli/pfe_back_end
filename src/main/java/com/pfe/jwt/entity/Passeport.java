package com.pfe.jwt.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.Date;
@Entity
@JsonTypeName("passeport")
public class Passeport  extends TypeIdentite{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateFinValidite;
    private String nationalite;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(Date dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }


}
