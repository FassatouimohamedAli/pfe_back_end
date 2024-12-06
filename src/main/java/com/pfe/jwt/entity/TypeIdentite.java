package com.pfe.jwt.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;
import javax.persistence.*;
import javax.persistence.Id;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cin.class, name = "cin"),
        @JsonSubTypes.Type(value = Passeport.class, name = "passeport")
})
public class TypeIdentite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String numPiece ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Client_id", unique = true)
    private Client client;

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
