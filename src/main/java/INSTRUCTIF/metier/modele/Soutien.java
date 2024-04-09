/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dlarrazmar
 */




@Entity
public class Soutien implements Serializable {
    
    public enum Evaluation {
        PAS_EVALUE, PAS_COMPRIS, ENCORE_DU_MAL, MOYEN_COMPRIS, ACQUIS_ENSSENTIEL, TOUT_COMPRIS;
    
        public static Evaluation FromInt(int eval){
            return Evaluation.values()[eval];
        }
    };
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String descriptif;
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.TIME)
    private Date duree;
    @Enumerated(EnumType.ORDINAL)
    private Evaluation evalEleve = Evaluation.PAS_EVALUE;
    private String BilanInter;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Eleve eleve;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Intervenant intervenant;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Matiere matiere;

    public Soutien() {
    }

    
    public Soutien(String descriptif, Matiere matiere) {
        this.descriptif = descriptif;
        this.matiere = matiere;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getDescriptif() {
        return descriptif;
    }

    public void setDescriptif(String descriptif) {
        this.descriptif = descriptif;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDebut) {
        this.dateDemande = dateDebut;
    }

    public Long getDuree() {
        Long res = null;
        if(duree != null){
            res = duree.getTime();
        }
        return res;
    }

    public void setDuree(Date duree) {
        this.duree = duree;
    }

    public Evaluation getEvalEleve() {
        return evalEleve;
    }

    public void setEvalEleve(Evaluation evalEleve) {
        this.evalEleve = evalEleve;
    }

    public String getBilanInter() {
        return BilanInter;
    }

    public void setBilanInter(String BilanInter) {
        this.BilanInter = BilanInter;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Intervenant getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }        

    @Override
    public String toString() {
        return "Soutien{" + "id=" + id + ", descriptif=" + descriptif + ", dateDemande=" + dateDemande + ", dateDebut=" + dateDebut + ", evalEleve=" + evalEleve + ", BilanInter=" + BilanInter + ", eleve=" + eleve + ", intervenant=" + intervenant + ", matiere=" + matiere + '}';
    }
    
    
    
    
}