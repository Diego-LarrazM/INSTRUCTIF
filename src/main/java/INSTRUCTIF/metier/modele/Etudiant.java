/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author Diego Larraz M et Corentin J
 */
@Entity
public class Etudiant extends Intervenant {
    private String universite;
    private String specialite;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String universite, String specialite, String email, String tel, String motDePasse, Integer niveauMin, Integer niveauMax) {
        super(nom, prenom, email, tel, motDePasse, niveauMin, niveauMax);
        this.universite = universite;
        this.specialite = specialite;
    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Etudiant{id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", disponible=" + isDisponible() + ", niveauMin=" + getNiveauMin() + ", niveauMax=" + getNiveauMax() + ", universite=" + universite + ", specialite=" + specialite + '}';
    }
    
}
