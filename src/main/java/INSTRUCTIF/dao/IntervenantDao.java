/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.dao;

import INSTRUCTIF.metier.modele.Intervenant;
import INSTRUCTIF.metier.modele.Soutien;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author dlarrazmar
 */
public class IntervenantDao {

    public void create(Intervenant intervenant){
        /**
        * Persiste <intervenant> dans la base de données selon un contexte de persistance.
        * @param(Intervenant) <intervenant> : Un Intervenant à persister dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().persist(intervenant);
    }
    
    
    public void update(Intervenant intervenant){
        /**
        * Met à jour les données associées à l'entitée <intervenant> dans la base de données selon un contexte de persistance.
        * @param(Intervenant) <intervenant> : Un Eleve dont ses informations sont à mettre dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().merge(intervenant);
    }
    
    public Long getTotalNumber() {
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT count(i) from Intervenant i", Long.class).getResultList().get(0);
    }
    
    public Intervenant getMVI() {
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i FROM Intervenant i LEFT JOIN i.soutiens s WHERE s.evalEleve != :nonDef GROUP BY i ORDER BY sum(s.evalEleve)/count(s.evalEleve) DESC",Intervenant.class);
        query.setParameter("nonDef",Soutien.Evaluation.PAS_EVALUE);
        return query.getResultList().get(0);
    }
    
    public Intervenant findByMail(String email){
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i WHERE i.email = :email", Intervenant.class);
        query.setParameter("email",email);
        List<Intervenant> cls = query.getResultList();
        Intervenant res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }

    public List<Intervenant> findAvailableOrdered(Long niveau){
        String jpql = "SELECT i FROM Intervenant i LEFT JOIN i.soutiens s WHERE i.disponible = true AND i.niveauMax <= :n AND :n <= i.niveauMin GROUP BY i ORDER BY count(s) ASC";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Intervenant.class);
        return query.setParameter("n",niveau).getResultList();
    }
    
    public Intervenant findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class,id);
    }

    public List<Intervenant> findAll(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i", Intervenant.class).getResultList();
    }
    
    public List<Intervenant> findAllAsc(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i ORDER BY i.nom, i.prenom ASC", Intervenant.class).getResultList();
    }
}
