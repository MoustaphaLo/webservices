package dev.project.servicesweb.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    private  long id;
    private String prenom;
    private  String nom;
    private Date date_naissance;

    public Client() {

    }

    public Client(String prenom, String nom, Date date_naissance){
        this.prenom = prenom;
        this.nom = nom;
        this.date_naissance = date_naissance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "prenom", nullable = false)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(name = "nom", nullable = false)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(name = "date_naissance", nullable = false)
    public Date getDate_naissance() {
        return date_naissance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", date_naissance=" + date_naissance +
                '}';
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
}
