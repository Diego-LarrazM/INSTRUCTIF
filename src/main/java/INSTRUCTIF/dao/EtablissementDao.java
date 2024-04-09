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
 * @author dlarrazmar
 */
public class EtablissementDao {
    public void create(Etablissement etablissement){
        JpaUtil.obtenirContextePersistance().persist(etablissement);
    }
    public Etablissement findByUai(String uai){
        TypedQuery<Etablissement> query = JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Etablissement e WHERE e.uai = :uai", Etablissement.class);
        query.setParameter("uai",uai);
        List<Etablissement> cls = query.getResultList();
        Etablissement res = null;
        if(cls.size() == 1) {
            res = cls.get(0);
        }
        return res;
    }
    
    public Etablissement findById(Long id){
        return JpaUtil.obtenirContextePersistance().find(Etablissement.class,id);
    }
    public List<Etablissement> findAll(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT e from Etablissement e", Etablissement.class).getResultList();
    }
    
    public List<EtabHitInstance> getHitList(){
        return JpaUtil.obtenirContextePersistance().createQuery("SELECT new INSTRUCTIF.util.EtabHitInstance(e.id, e.lat, e.lng, count(elv)) from Etablissement e JOIN Eleve elv ON (elv.etablissement = e) GROUP BY e.id, e.lat, e.lng ORDER BY count(elv) DESC ",EtabHitInstance.class).getResultList();
    }
    
}
