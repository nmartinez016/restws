package fr.univtln.nmartinez016;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marti on 17/05/2016.
 */

public final class Personne implements Serializable {
    private int id;
    private String nom;
    private String prenom;
    private Team team;
    private List<A> mAs = new ArrayList<A>();

    @JsonIgnore
    private transient List<Chien> chiens = new ArrayList<Chien>();

    //Obligatoire pour JSON
    public Personne() {}

    public Personne(int id, String nom, String prenom, Team team, List<A> pAs) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.team = team;
        mAs = pAs;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Team getTeam(){
        return team;
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public List<A> getAs(){
        return mAs;
    }

    public void setAs(List<A> pAs){
        mAs = pAs;
    }

    @JsonIgnore
    public List<Chien> getChiens() {
        return chiens;
    }

    @JsonProperty
    public void setChiens(List<Chien> chiens) {
        this.chiens = chiens;
    }

    public boolean addChien(Chien chien) {
        boolean result = chiens.add(chien);
        chien.setMaitre(this);
        return result;
    }

    public boolean removeChien(Object o) {
        if (o instanceof Chien) ((Chien) o).removeMaitre();
        return chiens.remove(o);
    }

    @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", a='" + mAs + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (id != personne.id) return false;
        if (!nom.equals(personne.nom)) return false;
        if (!prenom.equals(personne.prenom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nom.hashCode();
        result = 31 * result + prenom.hashCode();
        return result;
    }
}