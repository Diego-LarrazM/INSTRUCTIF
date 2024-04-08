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
        JpaUtil.obtenirContextePersistance().persist(soutien);
    }

    public Soutien findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Soutien.class,id);
    }
    
    public List<Soutien> findAll(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT s from Soutien s", Soutien.class).getResultList();
    }
    
    public Long getTotalNumber(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT COUNT(s) from Soutien s", Long.class).getResultList().get(0);
    }
    
    public Long getMeanDuration(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT sum(s.duree)/ count(s.duree) FROM Soutien s", Long.class).getResultList().get(0);
    }
    
    public Soutien.Evaluation getMeanEvaluation(){
        return Soutien.Evaluation.FromInt(JpaUtil.obtenirContextePersistance().createQuery("SELECT sum(s.evalEleve)/count(s.evalEleve) FROM Soutien s", Integer.class).getResultList().get(0));
    }

    public void update(Soutien soutien){
        JpaUtil.obtenirContextePersistance().merge(soutien);
    }
}
