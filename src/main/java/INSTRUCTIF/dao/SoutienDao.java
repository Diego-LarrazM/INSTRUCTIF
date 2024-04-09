/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.dao;

import INSTRUCTIF.metier.modele.Soutien;
import java.util.List;

/**
 *
 * @author dlarrazmar
 */
public class SoutienDao {

    public void create(Soutien soutien){
        /**
        * Persiste <soutien> dans la base de données selon un contexte de persistance.
        * @param(Soutien) <soutien> : Un Soutien à persister dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().persist(soutien);
    }

    public Soutien findById(Long id){
        /**
        * Cherche dans la base de données l'entitée Soutien à l'id <id>. Selon un contexte de persistance.
        * @param(Long) <id> : L'id du Soutien cherché.
        * @return(Soutien) Le Soutien idéntifié par son id. Null s'il n'existe pas.
        */
        return JpaUtil.obtenirContextePersistance().find(Soutien.class,id);
    }
    
    public List<Soutien> findAll(){
        /**
        * Obtient de la base de données toutes les entitées Soutien persistées. Selon un contexte de persistance.
        * @return(List<Soutien>) une ArrayList avec tous les soutiens de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT s from Soutien s", Soutien.class).getResultList();
    }
    
    public Long getTotalNumber(){
        /**
        * Compte le nombre d'entitées Soutien persistées. Selon un contexte de persistance.
        * @return(Long) Le nombre total de soutiens dans la base de données.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT COUNT(s) from Soutien s", Long.class).getResultList().get(0);
    }
    
    public Long getMeanDuration(){
        /**
        * Compte la durée moyenne des soutiens réalisés. Selon un contexte de persistance.
        * @return(Long) La durée moyenne des soutiens dans la base de données.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT sum(s.duree)/ count(s.duree) FROM Soutien s WHERE s.duree > 0", Long.class).getResultList().get(0);
    }
    
    public Soutien.Evaluation getMeanEvaluation(){
        /**
        * Compte l'évaluation moyenne des soutiens réalisés. Selon un contexte de persistance.
        * @return(Long) L'évaluation moyenne des soutiens dans la base de données.
        */
        return Soutien.Evaluation.FromInt(JpaUtil.obtenirContextePersistance().createQuery("SELECT sum(s.evalEleve)/count(s.evalEleve) FROM Soutien s", Integer.class).getResultList().get(0));
    }

    public void update(Soutien soutien){
        /**
        * Met à jour les données associées à l'entitée <soutien> dans la base de données selon un contexte de persistance.
        * @param(Soutien) <soutien> : Un Soutien dont ses informations sont à mettre dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().merge(soutien);
    }
}
