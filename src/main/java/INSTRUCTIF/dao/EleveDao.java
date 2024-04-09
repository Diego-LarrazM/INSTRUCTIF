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
        * @param <eleve> :  Un Eleve à persister dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().persist(eleve);
    }
    public void update(Eleve eleve){
        /**
        * Met à jour les données associées à l'entité eleve dans la base de données selon un contexte de persistance.
        * @param <eleve> :  Un Eleve dont ses informations sont à mettre dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().merge(eleve);
    }
    public Eleve findByMail(String email){
        /**
        * Cherche dans la base de données l'entité Eleve dont son email est <email>. Selon un contexte de persistance.
        * @param <email> :  un String correspondant à un email.
        * @return <res> :  L'élève idéntifié par son email. Null s'il n'existe pas.
        */
        TypedQuery<Eleve> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e WHERE e.email = :email", Eleve.class);
        query.setParameter("email",email);
        List<Eleve> cls = query.getResultList();
        Eleve res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Eleve findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Eleve.class,id);
    }

    public List<Eleve> findAll(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e", Eleve.class).getResultList();
    }
    
    public List<Eleve> findAllAsc(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Eleve e ORDER BY e.nom, e.prenom ASC", Eleve.class).getResultList();
    }
    
    public Long getTotalNumber(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT COUNT(e) from Eleve e", Long.class).getResultList().get(0);
    }
}
