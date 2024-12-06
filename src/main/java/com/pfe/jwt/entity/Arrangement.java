package com.pfe.jwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Arrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  float montant ;
    private String echancier ;
    private int nbrEchancierParMois ;
    @Temporal(TemporalType.DATE)
    private Date datedebut ;
    @Temporal(TemporalType.DATE)
    private Date dateFin ;
    @ManyToOne
    @JoinColumn(name = "User_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "arrangement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Echancier> echeanciers;


    public List<Echancier> getEcheanciers() {
        return echeanciers;
    }

    public void setEcheanciers(List<Echancier> echeanciers) {
        this.echeanciers = echeanciers;
    }

    @OneToOne
    @JoinColumn(name = "debiteur_id",nullable = false)
    private Debiteur debiteur;

    public void setNbrEchancierParMois(int nbrEchancierParMois) {
        this.nbrEchancierParMois = nbrEchancierParMois;
    }

    public void updateDateFin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.datedebut);

        int totalMonths = this.getNbrEchancierParMois();

        // Vérifier que le nombre de mois total est compatible avec la périodicité
        switch (this.echancier.toLowerCase()) {
            case "mensuel":
                // Mensuel signifie chaque mois, donc juste additionner les mois
                calendar.add(Calendar.MONTH, totalMonths);
                break;
            case "annuel":
                // Annuel, on ajoute le nombre d'années (totalMonths/12)
                if (totalMonths % 12 != 0) {
                    throw new IllegalArgumentException("Le nombre total de mois pour un échéancier annuel doit être un multiple de 12.");
                }
                calendar.add(Calendar.YEAR, totalMonths / 12);
                break;
            case "trimestriel":
                // Trimestriel, on ajoute des trimestres (totalMonths/3)
                if (totalMonths % 3 != 0) {
                    throw new IllegalArgumentException("Le nombre total de mois pour un échéancier trimestriel doit être un multiple de 3.");
                }
                calendar.add(Calendar.MONTH, totalMonths);
                break;
            case "semestriel":
                // Semestriel, on ajoute des semestres (totalMonths/6)
                if (totalMonths % 6 != 0) {
                    throw new IllegalArgumentException("Le nombre total de mois pour un échéancier semestriel doit être un multiple de 6.");
                }
                calendar.add(Calendar.MONTH, totalMonths);
                break;
            default:
                throw new IllegalArgumentException("Échéancier non valide");
        }

        // Mise à jour de la date de fin
        this.dateFin = calendar.getTime();
    }


    public int getNbrEchancierParMois() {
        return nbrEchancierParMois;
    }





    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }







    public Debiteur getDebiteur() {
        return debiteur;
    }


    public void setDebiteur(Debiteur debiteur) {
        this.debiteur = debiteur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public String getEchancier() {
        return echancier;
    }

    public void setEchancier(String echancier) {
        this.echancier = echancier;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
