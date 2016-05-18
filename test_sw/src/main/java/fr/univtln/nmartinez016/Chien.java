package fr.univtln.nmartinez016;

import java.io.Serializable;
import java.security.InvalidParameterException;

/**
 * Created by marti on 17/05/2016.
 */


public class Chien implements Serializable {
    private int id;
    private String nom;
    private Personne maitre;

    public Chien() {}

    public Chien(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMaitre(Personne maitre) {
        if (maitre == null) throw new InvalidParameterException();
        if (!maitre.getChiens().contains(this))
            maitre.addChien(this);
        this.maitre = maitre;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Personne getMaitre() {
        return maitre;
    }

    public void removeMaitre() {
        maitre = null;
    }

    @Override
    public String toString() {
        return "Chien{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", maitre=" + maitre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chien)) return false;

        Chien chien = (Chien) o;

        if (id != chien.id) return false;
        if (!nom.equals(chien.nom)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + nom.hashCode();
        return result;
    }
}

