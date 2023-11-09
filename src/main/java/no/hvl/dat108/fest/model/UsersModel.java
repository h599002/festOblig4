package no.hvl.dat108.fest.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name ="deltager")
public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String fornavn;
    String etternavn;
    String mobil;
    String kjonn;
    String hash;
    String salt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getKjonn() {
        return kjonn;
    }

    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }

    public void setSalt(String salt){ this.salt = salt;}

    public String getSalt() { return this.salt;}

    public void setHash(String hash) { this.hash = hash;}

    public String getHash() {return hash;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersModel that = (UsersModel) o;
        return Objects.equals(id, that.id) && Objects.equals(fornavn, that.fornavn) && Objects.equals(etternavn, that.etternavn) && Objects.equals(mobil, that.mobil) && Objects.equals(kjonn, that.kjonn);
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", mobil='" + mobil + '\'' +
                ", kjonn='" + kjonn + '\'' +
                '}';
    }
}


