package com.pfe.jwt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "traitement_recouvrement")
public class OperationRecouvrement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation  ;
    private String typeOperation ;
    private String etatOperation ;

    @OneToOne
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "User_id",nullable = false)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getEtatOperation() {
        return etatOperation;
    }

    public void setEtatOperation(String etatOperation) {
        this.etatOperation = etatOperation;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
