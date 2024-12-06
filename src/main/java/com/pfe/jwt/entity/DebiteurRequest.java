package com.pfe.jwt.entity;

public class DebiteurRequest {

    private Client client;
    private Debiteur debiteur  ;
    private TypeIdentite piece;

    public TypeIdentite getPiece() {
        return piece;
    }






    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient() {
        return client;
    }
    public Debiteur getDebiteur() {
        return debiteur;
    }

    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }


}
