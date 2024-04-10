/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.dao;

import INSTRUCTIF.metier.modele.Matiere;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego Larraz M et Corentin J
 */
public class MatiereDao {
    public void create(Matiere matiere){
        /**
        * Persiste <matiere> dans la base de données selon un contexte de persistance.
        * @param(Matiere) <matiere> : Une Matiere à persister dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    public Matiere findByLabel(String label){
        /**
        * Cherche dans la base de données l'entitée Matiere dont son label est <label>. Selon un contexte de persistance.
        * @param(String) <label> : le libellé/nom de la matière cherchée.
        * @return(Matiere) <res> : La matière idéntifiée par son label. Null si elle n'existe pas.
        */
        TypedQuery<Matiere> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT m from Matiere m WHERE m.libelle = :label", Matiere.class);
        query.setParameter("label", label);
        List<Matiere> cls = query.getResultList();
        // S'il n'existe pas res est mis à null.
        Matiere res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Matiere findById(Long id){
        /**
        * Cherche dans la base de données l'entitée Matiere à l'id <id>. Selon un contexte de persistance.
        * @param(Long) <id> : L'id de la matière cherchée.
        * @return(Matiere) La matière idéntifié par son id. Null s'il n'existe pas.
        */
        return JpaUtil.obtenirContextePersistance().find(Matiere.class,id);
    }
    
    public List<Matiere> findAllAsc(){
        /**
        * Obtient de la base de données, avec un ordre ascendant selon leur label, toutes les entitées Matiere persistées. Selon un contexte de persistance.
        * @return(List<Matiere>) une ArrayList avec tous les matières de la base de données. Null si aucune existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT m from Matiere m ORDER BY m.libelle ASC", Matiere.class).getResultList();
    }
}
