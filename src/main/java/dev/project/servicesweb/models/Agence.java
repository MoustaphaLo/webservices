package dev.project.servicesweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "agence")
public class Agence {
    @Id
    private String code;

    private  String nom_agence;

    private String address;
    private  String telephone;

    @JsonIgnore
    @OneToMany(mappedBy = "agence", cascade = {CascadeType.ALL})
    private List<Compte> comptes;

    public Agence() {
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public Agence(String code, String nom_agence, String address, String telephone) {
        this.code = code;
        this.nom_agence = nom_agence;
        this.address = address;
        this.telephone = telephone;
    }
    @Column(name = "code", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    @Column(name = "address", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "telephone", nullable = false)
    public String getTelephone() {
        return telephone;
    }

    @Column(name = "nom_agence", nullable = false)
    public String getNom_agence() {
        return nom_agence;
    }

    public void setNom_agence(String nom_agence) {
        this.nom_agence = nom_agence;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "code='" + code + '\'' +
                ", nom='" + nom_agence + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
