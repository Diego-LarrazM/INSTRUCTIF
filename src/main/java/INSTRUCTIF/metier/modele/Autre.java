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
public class Autre extends Intervenant {
    private String activite;

    public Autre() {
    }

    public Autre(String nom, String prenom, String activite, String email, String tel, String motDePasse, Integer niveauMin, Integer niveauMax) {
        super(nom, prenom, email, tel, motDePasse, niveauMin, niveauMax);
        this.activite = activite;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    @Override
    public String toString() {
        return "Autre{id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + ", disponible=" + isDisponible() + ", niveauMin=" + getNiveauMin() + ", niveauMax=" + getNiveauMax() + ", activite=" + activite + '}';
    }

}
