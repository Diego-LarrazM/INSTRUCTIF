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
 * @author dlarrazmar
 */
public class MatiereDao {
    public void create(Matiere matiere){
        JpaUtil.obtenirContextePersistance().persist(matiere);
    }
    public Matiere findByLabel(String label){
        TypedQuery<Matiere> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT m from Matiere m WHERE m.libelle = :label", Matiere.class);
        query.setParameter("label", label);
        List<Matiere> cls = query.getResultList();
        Matiere res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Matiere findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Matiere.class,id);
    }
    
    public List<Matiere> findAllAsc(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT m from Matiere m ORDER BY m.libelle ASC", Matiere.class).getResultList();
    }
}
