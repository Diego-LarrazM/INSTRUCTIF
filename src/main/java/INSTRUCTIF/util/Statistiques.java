/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.util;

import INSTRUCTIF.metier.modele.Intervenant;
import INSTRUCTIF.metier.modele.Soutien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlarrazmar
 */
public class Statistiques {
    private Long nbIntervenant;
    private Long nbSoutiens;
    private Long nbEleves;
    private Intervenant meillerInter;
    private Soutien.Evaluation evalMoyenne;
    private List<EtabHitInstance> repartitionGeo = new ArrayList();

    @Override
    public String toString() {
        return "Statistiques{" + "nbIntervenant=" + nbIntervenant + ", nbSoutiens=" + nbSoutiens + ", nbEleves=" + nbEleves + ", meillerInter=" + meillerInter + ", evalMoyenne=" + evalMoyenne +  '}';
    }

    public List<EtabHitInstance> getRepartitionGeo() {
        return repartitionGeo;
    }

    public void setRepartitionGeo(List<EtabHitInstance> repartitionGeo) {
        this.repartitionGeo = repartitionGeo;
    }

    public Statistiques(Long nbIntervenant, Long nbSoutiens, Long nbEleves, Intervenant meillerInter, Soutien.Evaluation evalMoyenne, List<EtabHitInstance> repartitionGeo) {
        this.nbIntervenant = nbIntervenant;
        this.nbSoutiens = nbSoutiens;
        this.nbEleves = nbEleves;
        this.meillerInter = meillerInter;
        this.evalMoyenne = evalMoyenne;
        this.repartitionGeo = repartitionGeo;
    }

    
    
    public Statistiques() {
    }

    public Soutien.Evaluation getEvalMoyenne() {
        return evalMoyenne;
    }

    public void setEvalMoyenne(Soutien.Evaluation evalMoyenne) {
        this.evalMoyenne = evalMoyenne;
    }

    public Long getNbIntervenant() {
        return nbIntervenant;
    }

    public void setNbIntervenant(Long nbIntervenant) {
        this.nbIntervenant = nbIntervenant;
    }

    public Long getNbSoutiens() {
        return nbSoutiens;
    }

    public void setNbSoutiens(Long nbSoutiens) {
        this.nbSoutiens = nbSoutiens;
    }

    public Long getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(Long nbEleves) {
        this.nbEleves = nbEleves;
    }

    public Intervenant getMeillerInter() {
        return meillerInter;
    }

    public void setMeillerInter(Intervenant meillerInter) {
        this.meillerInter = meillerInter;
    }
}
