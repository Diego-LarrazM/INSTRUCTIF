/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dlarrazmar
 */
@Entity
public class Eleve implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private Long niveau; // la classe de l'élève 6ème : 6, ... Terminale : 0 
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String motDePasse;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Etablissement etablissement;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eleve")
    private List<Soutien> soutiens = new ArrayList();

    
    public Eleve() {
    }

    public Eleve(String nom, String prenom, Long niveau, Date dateDeNaissance, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
        this.dateDeNaissance = dateDeNaissance;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Eleve{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", niveau=" + niveau + ", dateDeNaissance=" + dateDeNaissance + ", email=" + email + ", motDePasse=" + motDePasse + '}';
    }
    
    public List<Soutien> getSoutiens() {
        return soutiens;
    }
    
    public void addSoutien(Soutien soutien){
        this.soutiens.add(soutien);
        if(soutien.getEleve() != this){
            soutien.setEleve(this);
        }
    }

    public void removeSoutien(Soutien soutien){
        this.soutiens.remove(soutien);
        soutien.setEleve(null);
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setNiveau(Long niveau) {
        this.niveau = niveau;
    }
    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
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
    
    public Long getNiveau() {
        return niveau;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    
    public Etablissement getEtablissement() {
        return etablissement;
    }
    
    public String getClasse(){
        /**
        * Renvoie la classe de l'élève sous forme textuelle.
        * @return(String) La classe de l'élève de 6ème à Terminale.
        */
        String clss = null;
        switch(niveau.intValue()){
            case 6: clss = "6ème"; break;
            case 5: clss = "5ème"; break;
            case 4: clss = "4ème"; break;
            case 3: clss = "3ème"; break;
            case 2: clss = "Seconde"; break;
            case 1: clss = "Première"; break;
            case 0: clss = "Terminale"; break;
        }
        return clss;
    }
    
    public Soutien dernierSoutien(){
        /**
        * Renvoie le dernier soutien de l'elève, que ce soit fini ou en cours.
        * @return(Soutien) le dernier soutien de l'Eleve.
        */
        return soutiens.get(soutiens.size() -1);
    }
    
    public String getAge(){
        DateFormat dateFormatter = new SimpleDateFormat("yyyy");
        return dateFormatter.format(new Date(new Date().getTime() - dateDeNaissance.getTime()));
    }

   
}
