/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.metier.service;


import INSTRUCTIF.dao.EleveDao;
import INSTRUCTIF.dao.EtablissementDao;
import INSTRUCTIF.dao.IntervenantDao;
import INSTRUCTIF.dao.JpaUtil;
import INSTRUCTIF.dao.MatiereDao;
import INSTRUCTIF.dao.SoutienDao;
import INSTRUCTIF.metier.modele.Autre;
import INSTRUCTIF.metier.modele.Eleve;
import INSTRUCTIF.metier.modele.Enseignant;
import INSTRUCTIF.metier.modele.Etablissement;
import INSTRUCTIF.metier.modele.Etudiant;
import INSTRUCTIF.metier.modele.Intervenant;
import INSTRUCTIF.metier.modele.Matiere;
import INSTRUCTIF.metier.modele.Soutien;
import INSTRUCTIF.util.EducNetApi;
import INSTRUCTIF.util.GeoNetApi;
import INSTRUCTIF.util.Message;
import INSTRUCTIF.util.Statistiques;
import com.google.maps.model.LatLng;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author dlarrazmar
 */
public class Service {
    
    public Eleve obtenirEleveParId(Long id ) {
        Eleve res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            EleveDao eDao = new EleveDao();
            res = eDao.findById(id);
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Eleve> obtenirEleves(){
        List<Eleve> eleves = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            
            EleveDao mDao = new EleveDao();
            eleves = mDao.findAll();

        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return eleves;
    }
    
    public Etablissement obtenirEtablissementParId(Long id ){
        Etablissement res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            EtablissementDao eDao = new EtablissementDao();
            res = eDao.findById(id);
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Etablissement> obtenirEtablissements(){
        List<Etablissement> etablissements = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            
            EtablissementDao mDao = new EtablissementDao();
            etablissements = mDao.findAll();
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return etablissements;
    }
    
    public Intervenant obtenirIntervenantParId(Long id ) {
        Intervenant res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            IntervenantDao eDao = new IntervenantDao();
            res = eDao.findById(id);
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public List<Intervenant> obtenirIntervenants(){
        List<Intervenant> intervenants = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            
            IntervenantDao mDao = new IntervenantDao();
            intervenants = mDao.findAll();
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return intervenants;
    }
    
    public Matiere obtenirMatiereParId(Long id ) {
        Matiere res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            MatiereDao eDao = new MatiereDao();
            res = eDao.findById(id);
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public List<Matiere> obtenirMatieres(){
        List<Matiere> matieres = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            
            MatiereDao mDao = new MatiereDao();
            matieres = mDao.findAllAsc();

        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return matieres;
    }
    
    public Soutien obtenirSoutienParId(Long id ) {
        Soutien res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            SoutienDao eDao = new SoutienDao();
            res = eDao.findById(id);
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Soutien> obtenirSoutiens(){
        List<Soutien> soutiens = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            
            SoutienDao mDao = new SoutienDao();
            soutiens = mDao.findAll();
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return soutiens;
    }
    
    public Statistiques obtenirStatistiques(){
        Statistiques  stats = new Statistiques();
        try{
            JpaUtil.creerContextePersistance();
            
            SoutienDao sDao = new SoutienDao();
            IntervenantDao iDao = new IntervenantDao();
            EleveDao eDao = new EleveDao();
            EtablissementDao etabDao = new EtablissementDao();
            
            stats.setNbSoutiens(sDao.getTotalNumber());
            stats.setNbIntervenant(iDao.getTotalNumber());
            stats.setNbEleves(eDao.getTotalNumber());
            stats.setEvalMoyenne(sDao.getMeanEvaluation());
            stats.setMeillerInter(iDao.getMVI());
            stats.setRepartitionGeo(etabDao.getHitList());

        }catch(Exception e){
            stats = null;
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return stats;
    }
    
    private Intervenant trouverIntervenant(Long niveau) {
        List<Intervenant> choices = new IntervenantDao().findAvailableOrdered(niveau);
        return choices.isEmpty() ? null : choices.get(0);
    }

    public Boolean demanderSoutien(Eleve eleve, Soutien soutien) {
        boolean res = false;
        try{   
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            SoutienDao sDao = new SoutienDao();
            IntervenantDao iDao = new IntervenantDao();
            EleveDao eDao = new EleveDao();
            
            soutien.setDateDemande(new Date());
            eleve.addSoutien(soutien);
            
            sDao.create(soutien);
            eDao.update(eleve);
               
            Intervenant inter = trouverIntervenant(eleve.getNiveau());
            
            if (inter != null) {
                DateFormat dateFormatter = new SimpleDateFormat("HH:mm");
                inter.addSoutien(soutien);
                inter.setDisponible(false);
                
                sDao.update(soutien);
                iDao.update(inter);
                
                Message.envoyerNotification(inter.getTel(), "Bonjour "+ inter.getPrenom() 
                    + ". Merci de prendre en charge la demande de soutien en <<" + soutien.getMatiere().getLibelle()
                    +">> demandée à " + dateFormatter.format(soutien.getDateDemande()) + " par " 
                    + eleve.getPrenom() 
                    + " en calsse de " + eleve.getClasse() + ".");
                res = true;
            } else{
                soutien.setDuree(new Date(0)); // soutient ave duére nulle -> pas d'intervenant au moment de la demande
            }
            
            
            JpaUtil.validerTransaction();
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public boolean demarerVisio(Soutien soutien) {
        
        boolean res = false;
        try{  
            System.out.println("[LOG] Demarrage de la visio-conference");
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SoutienDao sDao = new SoutienDao();
            
            soutien.setDateDebut(new Date());
            sDao.update(soutien);
            res = true;
            
            JpaUtil.validerTransaction();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public boolean redigerBilan(Soutien soutien, String bilan){
        boolean res = false;
        try{  
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SoutienDao sDao = new SoutienDao();
            
            soutien.setBilanInter(bilan);
            sDao.update(soutien);
            res = true;
            
            JpaUtil.validerTransaction();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public boolean affecterUneEvaluation(Soutien soutien, Soutien.Evaluation eval){
        boolean res = false;
        try{  
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SoutienDao sDao = new SoutienDao();
            
            soutien.setEvalEleve(eval);
            sDao.update(soutien);
            res = true;
            
            JpaUtil.validerTransaction();
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
            
    }
    
    public boolean arreterVisio(Soutien soutien) {
        boolean res = false;
        try{   
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            IntervenantDao iDao = new IntervenantDao();
            SoutienDao sDao = new SoutienDao();
            
            soutien.setDuree(new Date(new Date().getTime() - soutien.getDateDebut().getTime()));
            soutien.getIntervenant().setDisponible(true);
            iDao.update(soutien.getIntervenant());
            sDao.update(soutien);
            res = true;
            
            JpaUtil.validerTransaction();
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    private Boolean inscrireEtablissement(Etablissement ets) {
        boolean res = false;
        String adresseEtablissement = ets.getNomEts() + ", " + ets.getNomCommune();
        LatLng coordsEtablissement = GeoNetApi.getLatLng(adresseEtablissement);
        ets.setLat(coordsEtablissement.lat);
        ets.setLng(coordsEtablissement.lng);

        EtablissementDao edao = new EtablissementDao();
        edao.create(ets);

        return res;
    }

    public boolean insererIntervenants() {
        boolean res = false;
        try{   
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            
            IntervenantDao iDao = new IntervenantDao();

            iDao.create(new Etudiant("Martin", "Camille", "Sorbonne", "Langues orientales", "camille.martin@sorbonne.fr", "0655447788", "lapinou", 6, 3));
            iDao.create(new Enseignant("Zola", "Anna", "Supérieur", "anna.zola@sup.fr", "6033221144", "monmignon", 6, 0));
            iDao.create(new Enseignant("Hugo", "Emile", "College", "emile.hugo@sup.fr", "0788559944", "motdepasse", 3, 3));
            iDao.create(new Autre("Youcenar", "Simone", "Retraité", "simone.youcenar@retraites.fr", "0722447744", "1234", 5, 0));
            JpaUtil.validerTransaction();
            res = true;
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;

    }
    
    public Intervenant authentifierIntervenant(String mail, String motDePasse) {
        Intervenant res = null;
        try{
            JpaUtil.creerContextePersistance();
            IntervenantDao idao = new IntervenantDao();
            res = idao.findByMail(mail);
            if(!res.getMotDePasse().equals(motDePasse)){
                res = null;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public Boolean inscrireEleve(Eleve eleve, String uai) {
        boolean res = false;
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            EtablissementDao etsDao = new EtablissementDao();
            Etablissement etab = etsDao.findByUai(uai);

            
            if(etab == null) {
                EducNetApi api = new EducNetApi();

                List<String> result = api.getInformationCollege(uai);
                if (result == null) {
                    result = api.getInformationLycee(uai);
                }
                
                if (result != null) {
                    etab = new Etablissement(uai,result.get(1),result.get(2),result.get(4),result.get(3),result.get(6),result.get(5),result.get(7),Double.parseDouble(result.get(8)));
                    inscrireEtablissement(etab);
                }
                else {
                    System.out.println("[ERROR] Etablissement inconnu");
                }
            }
            if(etab != null) {
                EleveDao edao = new EleveDao();
                eleve.setEtablissement(etab);
                edao.create(eleve);
                Message.envoyerMail("contact@instruct.if", eleve.getEmail(), "Bienvenu sur le réseau INSTRUCT'IF", "Bonjour" + eleve.getPrenom() + ", "
                    + "nous te confirmons ton insciiption au reaseau INSTRUCT'IF.\n"
                    + "Si tu as besoin d'un soutien pour tes leçons ou tes devoirs, rends toi sur notre site pour la mise en relation avec un intervenant.");

                JpaUtil.validerTransaction();
                res = true;
            }
            
        }
        catch(Exception e){
            System.err.println(e);
            Message.envoyerMail("contact@instruct.if", eleve.getEmail(),"Echec de l'inscription sur le réseau INSTRUCT'IF", "Merci de recommencer ultérieurement.");
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public Eleve authentifierEleve(String mail, String motDePasse) {
        Eleve res = null;
        try{
            JpaUtil.creerContextePersistance();
            EleveDao edao = new EleveDao();
            res = edao.findByMail(mail);
            if(!res.getMotDePasse().equals(motDePasse)){
                res = null;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Eleve> obtenirNbIntervenant() {
        List<Eleve> res = null;
        try{
            JpaUtil.creerContextePersistance();
            EleveDao cdao = new EleveDao();
            res = cdao.findAllAsc();
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
        
    }
    
    public boolean insererMatieres() {
        boolean res = false;
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            MatiereDao mDao = new MatiereDao();
            mDao.create(new Matiere("Histoire-Geographie"));
            mDao.create(new Matiere("Mathematiques"));
            mDao.create(new Matiere("Français"));
            mDao.create(new Matiere("Anglais"));
            mDao.create(new Matiere("Physique - Chimie"));
            mDao.create(new Matiere("Espagnol"));
            mDao.create(new Matiere("Chinois"));
            mDao.create(new Matiere("NSI"));
            mDao.create(new Matiere("Math Expertes"));
            mDao.create(new Matiere("Sciences de la Vie et de la Terre"));
            mDao.create(new Matiere("Technologie"));
            mDao.create(new Matiere("Musique"));
            mDao.create(new Matiere("Art Plastiques"));
            mDao.create(new Matiere("Latin"));
            JpaUtil.validerTransaction();
            res = true;
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;

    }

}
