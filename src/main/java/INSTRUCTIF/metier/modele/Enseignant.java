/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import javax.persistence.Entity;

/**
 *
 * @author dlarrazmar
 */
@Entity
public class Enseignant extends Intervenant {
    private String typeEts;

    public Enseignant() {
    }

    public Enseignant(String nom, String prenom, String typeEts, String email, String tel, String motDePasse, Integer niveauMin, Integer niveauMax) {
        super(nom, prenom, email, tel, motDePasse, niveauMin, niveauMax);
        this.typeEts = typeEts;
    }


    public String getTypeEts() {
        return typeEts;
    }

    public void setTypeEts(String typeEts) {
        this.typeEts = typeEts;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "typeEts=" + typeEts + '}';
    }
}
