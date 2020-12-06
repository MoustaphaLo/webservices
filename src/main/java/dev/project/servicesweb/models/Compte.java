package dev.project.servicesweb.models;

import javax.persistence.*;

@Entity
@Table(name = "compte")
public class Compte {

    private int id;
    private double solde;
    private double decouvert;

    private Agence agence;

    private Client client;

    public Compte() {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "solde")
    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Column(name = "decouvert")
    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agence_code")
    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
