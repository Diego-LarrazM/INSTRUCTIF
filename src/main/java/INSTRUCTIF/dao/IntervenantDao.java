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
 * @author Diego Larraz M et Corentin J
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
        * @param(Intervenant) <intervenant> : Un Intervenant dont ses informations sont à mettre dans la base de données.
        */
        JpaUtil.obtenirContextePersistance().merge(intervenant);
    }
    
    public Long getTotalNumber() {
        /**
        * Compte le nombre d'entitées Intervenant persistées. Selon un contexte de persistance.
        * @return(Long) Le nombre total d'intervenants dans la base de données.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT count(i) from Intervenant i", Long.class).getResultList().get(0);
    }
    
    public Intervenant getMVI() {
        /**
        * Obtient de la base de données le meilleur intervenant (Most Valuable Intervenant).
        * La qualité d'un intervenant est évaluée par l'évaluation moyenne par les élèves de tous les soutiens où il est intervenu.
        * Ne prends en compte que les soutiens évalués, c'est à dire celles non égales à Soutien.Evaluation.PAS_EVALUE.
        * @return(Intervenant) L'intervenant dans la base de données avec la meilleur moyenne d'évaluation par les élèves de ses interventions. Null si aucun Intervenant ou soutien existant.
        */
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i FROM Intervenant i LEFT JOIN i.soutiens s WHERE s.evalEleve != :nonDef GROUP BY i ORDER BY sum(s.evalEleve)/count(s.evalEleve) DESC",Intervenant.class);
        query.setParameter("nonDef",Soutien.Evaluation.PAS_EVALUE);
        List<Intervenant> cls = query.getResultList(); 
        // S'il n'existe pas res est mis à null.
        Intervenant res = null;
        if(cls.size() > 0) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Intervenant findByMail(String email){
        /**
        * Cherche dans la base de données l'entitée Intervenant dont son email est <email>. Selon un contexte de persistance.
        * @param(String) <email> : l'email de l'Intervenant cherché.
        * @return(Intervenant) <res> : L'Intervenant idéntifié par son email. Null s'il n'existe pas.
        */
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i WHERE i.email = :email", Intervenant.class);
        query.setParameter("email",email);
        List<Intervenant> cls = query.getResultList();
        // S'il n'existe pas res est mis à null.
        Intervenant res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }

    public List<Intervenant> findAvailableOrdered(Long niveau){
        /**
        * Obtient de la base de données toutes les entitées Intervenant disponibles pour répondre à un soutien. Selon un contexte de persistance.
        * Un intervenant est disponible si il n'est pas déjà occupé d'un autre soutien (Intervenant.disponible est vrai).
        * De même il faut que son niveau d'enseignement convient au niveau de la demande de soutien <niveau>: 
        * Intervenant peut enseigner des classes de niveauMin à niveauMax, il faut que <niveau> ∈ [niveauMin, niveauMax] pour qu'il soit choisi.
        * @param(Long) <niveau> : Le niveau (classe) de l'élève qui démande le soutien.
        * @return(List<Intervenant>) une ArrayList avec tous les intervenants disponibles. Null si aucun existe.
        */
        String jpql = "SELECT i FROM Intervenant i LEFT JOIN i.soutiens s WHERE i.disponible = true AND i.niveauMax <= :n AND :n <= i.niveauMin GROUP BY i ORDER BY count(s) ASC";
        TypedQuery<Intervenant> query = JpaUtil.obtenirContextePersistance().createQuery(jpql, Intervenant.class);
        return query.setParameter("n",niveau).getResultList();
    }
    
    public Intervenant findById(Long id){
        /**
        * Cherche dans la base de données l'entitée Intervenant à l'id <id>. Selon un contexte de persistance.
        * @param(Long) <id> : L'id de l'Intervenant cherché.
        * @return(Intervenant) L'Intervenant idéntifié par son id. Null s'il n'existe pas.
        */
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class,id);
    }

    public List<Intervenant> findAll(){
        /**
        * Obtient de la base de données toutes les entitées Intervenant persistées. Selon un contexte de persistance.
        * @return(List<Intervenant>) une ArrayList avec tous les intervenants de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i", Intervenant.class).getResultList();
    }
    
    public List<Intervenant> findAllAsc(){
        /**
        * Obtient de la base de données, avec un ordre ascendant selon leur nom, toutes les entitées Intervenant persistées. Selon un contexte de persistance.
        * @return(List<Intervenant>) une ArrayList avec tous les intervenants de la base de données. Null si aucun existe.
        */
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT i from Intervenant i ORDER BY i.nom, i.prenom ASC", Intervenant.class).getResultList();
    }
}
