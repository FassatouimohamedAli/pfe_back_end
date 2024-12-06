package com.pfe.jwt.entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.*;
import java.util.Date;
@Entity
@JsonTypeName("cin")
public class Cin  extends TypeIdentite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateEmission;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }


}
