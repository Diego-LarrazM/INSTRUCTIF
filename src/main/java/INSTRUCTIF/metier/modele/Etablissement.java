/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.modele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diego Larraz M et Corentin J
 */
@Entity
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uai;
    @Column(nullable = false)
    private String nomEts;
    @Column(nullable = false)
    private String secteur;
    @Column(nullable = false)
    private String nomCommune;
    @Column(nullable = false)
    private String codeCommune;
    @Column(nullable = false)
    private String nomeDpt;
    @Column(nullable = false)
    private String codeDpt;
    @Column(nullable = false)
    private String academie;
    
    private Double ips;
    private Double lng;
    private Double lat;

    public Etablissement() {
    }

    public Etablissement(String uai, String nomEts, String secteur, String nomCommune, String codeCommune, String nomeDpt, String codeDpt, String academie, Double ips) {
        this.uai = uai;
        this.nomEts = nomEts;
        this.secteur = secteur;
        this.nomCommune = nomCommune;
        this.codeCommune = codeCommune;
        this.nomeDpt = nomeDpt;
        this.codeDpt = codeDpt;
        this.academie = academie;
        this.ips = ips;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + ", uai=" + uai + ", nomEts=" + nomEts + ", secteur=" + secteur + ", nomCommune=" + nomCommune + ", codeCommune=" + codeCommune + ", nomeDpt=" + nomeDpt + ", codeDpt=" + codeDpt + ", academie=" + academie + ", ips=" + ips + ", lng=" + lng + ", lat=" + lat + '}';
    }

    public Long getId() {
        return id;
    }

    public String getUai() {
        return uai;
    }

    public String getNomEts() {
        return nomEts;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public String getNomeDpt() {
        return nomeDpt;
    }

    public String getCodeDpt() {
        return codeDpt;
    }

    public String getAcademie() {
        return academie;
    }

    public Double getIps() {
        return ips;
    }

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setUai(String uai) {
        this.uai = uai;
    }

    public void setNomEts(String nomEts) {
        this.nomEts = nomEts;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public void setNomeDpt(String nomeDpt) {
        this.nomeDpt = nomeDpt;
    }

    public void setCodeDpt(String codeDpt) {
        this.codeDpt = codeDpt;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public void setIps(Double ips) {
        this.ips = ips;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    
}
