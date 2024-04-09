/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

/**
 *
 * @author dlarrazmar
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Intervenant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String tel;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private Boolean disponible;
    @Column(nullable = false)
    private Integer niveauMin;
    @Column(nullable = false)
    private Integer niveauMax;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "intervenant")
    private List<Soutien> soutiens = new ArrayList();

    public List<Soutien> getSoutiens() {
        return soutiens;
    }
    
    public void addSoutien(Soutien soutien){
        this.soutiens.add(soutien);
        if(soutien.getIntervenant() != this){
            soutien.setIntervenant(this);
        }
    }

    public void removeSoutien(Soutien soutien){
        this.soutiens.remove(soutien);
        soutien.setIntervenant(null);
    }
    
    public Integer getNiveauMin() {
        return niveauMin;
    }

    public void setNiveauMin(Integer niveauMin) {
        this.niveauMin = niveauMin;
    }

    public Integer getNiveauMax() {
        return niveauMax;
    }

    public void setNiveauMax(Integer niveauMax) {
        this.niveauMax = niveauMax;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Boolean isDisponible() {
        return disponible;
    }
    
    
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public Soutien dernierSoutien(){
        return soutiens.get(soutiens.size() -1);
    }

    public Intervenant() {
    }

    public Intervenant(String nom, String prenom, String email, String tel, String motDePasse, Integer niveauMin, Integer niveauMax) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.motDePasse = motDePasse;
        this.disponible = true;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
    }

    @Override
    public String toString() {
        return "Intervenant{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", motDePasse=" + motDePasse + ", disponible=" + disponible + ", niveauMin=" + niveauMin + ", niveauMax=" + niveauMax + '}';
    }

}
