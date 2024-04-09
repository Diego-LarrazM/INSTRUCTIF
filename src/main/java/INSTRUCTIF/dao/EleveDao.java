/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.dao;

import INSTRUCTIF.metier.modele.Eleve;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Larraz M et Corentin J
 */
public class EleveDao {
    
    public void create(Eleve eleve){
        /**
        * Persiste <eleve> dans la base de données selon un contexte de persistance.
        * @param(Eleve) <eleve> : Un Eleve à persister dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    public void update(Eleve eleve){
        /**
        * Met à jour les données associées à l'entitée <eleve> dans la base de données selon un contexte de persistance.
        * @param(Eleve) <eleve> : Un Eleve dont ses informations sont à mettre dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().merge(eleve);
    }
    public Eleve findByMail(String email){
        /**
        * Cherche dans la base de données l'entitée Eleve dont son email est <email>. Selon un contexte de persistance.
        * @param(String) <email> : l'email de l'Eleve cherché.
        * @return(Elève) <res> : L'Elève idéntifié par son email. Null s'il n'existe pas.
        */
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e WHERE e.email = :email", Eleve.class);
        query.setParameter("email",email);
        List<Eleve> cls = query.getResultList();
        // S'il n'existe pas res est mis à null.
        Eleve res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Eleve findById(Long id){
        /**
        * Cherche dans la base de données l'entitée Eleve à l'id <id>. Selon un contexte de persistance.
        * @param(Long) <id> : L'id de l'Eleve cherché.
        * @return(Eleve) L'Elève idéntifié par son id. Null s'il n'existe pas.
        */
        return JpaUtil.obtenirContextePersistance().find(Eleve.class,id);
    }

    public List<Eleve> findAll(){
        /**
        * Obtient de la base de données toutes les entitées Eleve persistées. Selon un contexte de persistance.
        * @return(List<Eleve>) une ArrayList avec tous les élèves de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e", Eleve.class).getResultList();
    }
    
    public List<Eleve> findAllAsc(){
        /**
        * Obtient de la base de données, avec un ordre ascendant selon leur nom, toutes les entitées Eleve persistées. Selon un contexte de persistance.
        * @return(List<Eleve>) une ArrayList avec tous les élèves de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e ORDER BY e.nom, e.prenom ASC", Eleve.class).getResultList();
    }
    
    public Long getTotalNumber(){
        /**
        * Compte le nombre d'entitées Eleve persistées. Selon un contexte de persistance.
        * @return(Long) Le nombre total d'élèves dans la base de données.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT COUNT(e) from Eleve e", Long.class).getResultList().get(0);
    }
}
