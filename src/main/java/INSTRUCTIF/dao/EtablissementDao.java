/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.dao;

import INSTRUCTIF.metier.modele.Etablissement;
import INSTRUCTIF.util.EtabHitInstance;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Larraz M et Corentin J
 */
public class EtablissementDao {
    public void create(Etablissement etablissement){
        /**
        * Persiste <etablissement> dans la base de données selon un contexte de persistance.
        * @param(Etablissement) <etablissement> :  l'établissement persister dans la table.
        */
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    
    public Etablissement findByUai(String uai){
        /**
        * Cherche dans la base de données l'entitée Etablissement dont son uai est <email>. Selon un contexte de persistance.
        * @param(String) <uai> : l'uai de l'établissement cherché.
        * @return(Etablissement) <res> : L'Etablissement idéntifié par son uai. Null s'il n'existe pas.
        */
        TypedQuery<Etablissement> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Etablissement e WHERE e.uai = :uai", Etablissement.class);
        query.setParameter("uai",uai);
        List<Etablissement> cls = query.getResultList();
        // S'il n'existe pas res est mis à null.
        Etablissement res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Etablissement findById(Long id){
        /**
        * Cherche dans la base de données l'entitée Etablissement à l'id <id>. Selon un contexte de persistance.
        * @param(Long) <id> : L'id de l'établissement cherché.
        * @return(Etablissement) L'établissement idéntifié par son id. Null s'il n'existe pas.
        */
        return JpaUtil.obtenirContextePersistance().find(Etablissement.class,id);
    }
    public List<Etablissement> findAll(){
        /**
        * Obtient de la base de données toutes les entitées Etablissement persistées. Selon un contexte de persistance.
        * @return(List<Etablissement>) une ArrayList avec tous les établissements de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Etablissement e", Etablissement.class).getResultList();
    }
    
    public List<EtabHitInstance> getHitList(){
        /**
        * Obtient la répartition géographique des élèves d'INSTRUCT'IF par établissement. 
        * C'est à dire: l'id, coordonnées (lat,lng) et nombre d'élèves dans INSTRUCT'IF de chaque établissement de la base de données. 
        * Les informations de chaque établissement sont stockées sous forme d'un objet EtabHitInstance(idEtablissement, lat, lng, nbElevesInstructifAssociés).
        * Liste ordonnée par nombre d'élèves pour un établissement.
        * @return(List<EtabHitInstance>) une ArrayList de EtabHitInstance. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT new INSTRUCTIF.util.EtabHitInstance(e.id, e.lat, e.lng, count(elv)) from Etablissement e JOIN Eleve elv ON (elv.etablissement = e) GROUP BY e.id, e.lat, e.lng ORDER BY count(elv) DESC ",EtabHitInstance.class).getResultList();
    }
    
}
