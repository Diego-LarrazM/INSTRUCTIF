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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * @author Diego Larraz M et Corentin J
 */
public class Service {
    
    public Eleve obtenirEleveParId(Long id) {
        /**
        * Renvoie l'élève identifié par <id>.
        * @param(Long) <id> : l'id de l’élève cherché.
        * @return(Eleve) <res> : l’élève identifié par son id. Null s'il n'existe pas.
        */
        Eleve res = null;
        try{   
            JpaUtil.creerContextePersistance();
            res = eDao.findById(id); // recherche de l'élève par id
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Eleve> obtenirEleves(){
        /**
        * Renvoie la liste de tous les élèves inscrits sur INSTRUCT'IF.
        * @return(List<Eleve>) <eleves> : Une ArrayList d’élève inscrits sur la base de données. Null si aucun existe.
        */
        List<Eleve> eleves = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            eleves = eDao.findAll(); // obtention de tous les élèves
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return eleves;
    }
    
    public Etablissement obtenirEtablissementParId(Long id){
        /**
        * Renvoie l’établissement identifié par <id>.
        * @param(Long) <id> : l'id de l’établissement cherché.
        * @return(Etablissement) <res> : l’établissement identifié par son id. Null s'il n'existe pas.
        */
        Etablissement res = null;
        try{   
            JpaUtil.creerContextePersistance();
            res = etsDao.findById(id); // recherche de l'établissement par id
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Etablissement> obtenirEtablissements(){
        /**
        * Renvoie la liste de tous les établissements d'élèves inscrits sur INSTRUCT'IF.
        * @return(List<Etablissement>) <etablissements> : Une ArrayList d’établissement inscrits sur la base de données. Null si aucun existe.
        */
        List<Etablissement> etablissements = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            etablissements = etsDao.findAll(); // obtention de tous les établissements
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return etablissements;
    }
    
    public Intervenant obtenirIntervenantParId(Long id) {
        /**
        * Renvoie l'intervenant identifié par <id>.
        * @param(Long) <id> : l'id de l'Intervenant cherché.
        * @return(Intervenant) <res> : L'Intervenant identifié par son id. Null s'il n'existe pas.
        */
        Intervenant res = null;
        try{   
            JpaUtil.creerContextePersistance();
            
            res = iDao.findById(id); // recherche de l'intervenant par id
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public List<Intervenant> obtenirIntervenants(){
        /**
        * Renvoie la liste de tous les intervenants inscrits sur INSTRUCT'IF.
        * @return(List<Intervenant>) <intervenants> : Une ArrayList d'Intervenant inscrits sur la base de données. Null si aucun existe.
        */
        List<Intervenant> intervenants = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            intervenants = iDao.findAll(); // obtention de tous les intervenants
            
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return intervenants;
    }
    
    public Matiere obtenirMatiereParId(Long id) {
        /**
        * Renvoie la matière identifiée par <id>.
        * @param(Long) <id> : l'id de la Matière cherchée.
        * @return(Matiere) <res> : La Matière identifiée par son id. Null si elle n'existe pas.
        */
        Matiere res = null;
        try{   
            JpaUtil.creerContextePersistance();

            res = mDao.findById(id); // recherche de la matière par id
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }

    public List<Matiere> obtenirMatieres(){
        /**
        * Renvoie la liste de toutes les matières disponibles sur INSTRUCT'IF.
        * @return(List<Matiere>) <res> : Une ArrayList des Matiere inscrites sur la base de données. Null si aucune existe.
        */
        List<Matiere> matieres = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            matieres = mDao.findAllAsc(); // obtention de toutes les matières
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return matieres;
    }
    
    public Soutien obtenirSoutienParId(Long id) {
        /**
        * Renvoie le soutien identifié par <id>.
        * @param(Long) <id> : l'id du Soutien cherché.
        * @return(Soutien) <res> : Le Soutien identifié par son id. Null s'il n'existe pas.
        */
        Soutien res = null;
        try{   
            JpaUtil.creerContextePersistance();
            res = sDao.findById(id); // recherche du soutien par id
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public List<Soutien> obtenirSoutiens(){
        /**
        * Renvoie la liste de tous les soutiens réalisés sur INSTRUCT'IF.
        * @return(List<Soutien>) <soutiens> : Une ArrayList des Soutien sauvegardés sur la base de données. Null si aucun existe.
        */
        List<Soutien> soutiens = new ArrayList();
        try{
            JpaUtil.creerContextePersistance();
            soutiens = sDao.findAll(); // obtention de tous les soutiens
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return soutiens;
    }
    
    public Statistiques obtenirStatistiques(){
        /**
        * Renvoie les statistiques du tableau de bord. L'informations est stockée sous forme d'un objet 
        * Statistiques(nombreSoutiensRéalisés, nombreElevesSurINSTRUCTIF, nombreIntervenantsSurINSTRUCTIF, EvaluationMoyenneDesSoutiens, meilleurIntervenant, RepartitionGeographique).
        * Le meilleurIntervenant correspond à l'intervenant avec la meilleur évaluation moyenne de ses interventions.
        * La RepartitionGeographique correspond à l'id, coordonnées (lat,lng) et nombre d'élèves dans INSTRUCT'IF de chaque établissement de la base de données. 
        * Les informations de chaque établissement sont stockées sous forme d'un objet EtabHitInstance(idEtablissement, lat, lng, nbElevesInstructifAssociés).
        * Liste ordonnée par nombre d'élèves pour un établissement.
        * @return(Statistiques) <stats> : Les statistiques à afficher sur la fenêtre du tableau de bord. Null si un erreur s'est produit lors de l'obtention d'une statistique.
        */
        Statistiques  stats = new Statistiques();
        try{
            JpaUtil.creerContextePersistance();
            // Obtention des statistiques une par une.
            stats.setNbSoutiens(sDao.getTotalNumber());
            stats.setNbIntervenant(iDao.getTotalNumber());
            stats.setNbEleves(eDao.getTotalNumber());
            stats.setEvalMoyenne(sDao.getMeanEvaluation());
            stats.setMeillerInter(iDao.getMVI());
            stats.setRepartitionGeo(etsDao.getHitList());
        }catch(Exception e){
            stats = null; 
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return stats;
    }
    
    private Intervenant trouverIntervenant(Long niveau) {
        /**
        * Choisi un intervenant parmi les disponibles, pour répondre à un soutien réalisé par un élève en classe <niveau>.
        * Un intervenant est disponible si il n'est pas déjà occupé d'un autre soutien (Intervenant.disponible est vrai).
        * De même il faut que son niveau d'enseignement convient au niveau de la demande de soutien <niveau>: 
        * Intervenant peut enseigner des classes de niveauMin à niveauMax, il faut que <niveau> ∈ [niveauMin, niveauMax] pour qu'il soit choisi.
        * @param(Long) <niveau> : Le niveau (classe) de l'élève qui demande le soutien.
        * @precondition(niveau) <niveau> ∈ [0, 6] avec 6: classe de 6ème et 0: classe de Terminale.
        * @return(Intervenant) L'intervenant choisi pour répondre au soutien. Null si aucun disponible.
        */
        List<Intervenant> choices = iDao.findAvailableOrdered(niveau);
        return choices.isEmpty() ? null : choices.get(0);
    }

    public Boolean demanderSoutien(Eleve eleve, Soutien soutien){
        /**
        * Réalise la demande d'un soutien crée par un élève.
        * La méthode met à jour la date de demande du soutien, attribue le soutien à l'élève et tente de l'attribuer à un intervenant.
        * Dans le cas où aucun intervenant soit trouvé celle-ci est considérée comme refusée.
        * Le soutien, l'élève et l'intervenant éventuellement sont mis à jour dans la base de données.
        * |!|ATTENTION|!| : La demande est réalisée en mode sérialisé pour éviter des concurrences: même intervenant pour deux soutiens par exemple. 
        * Les demandes sont traitées par ordre d'arrivée : la première demande (celle qui arrive le plus tôt) sera traitée, puis la deuxième.
        * @param(Eleve) <eleve> : L'élève qui demande le soutien.
        * @param(Soutien) <soutien> : Le Soutien demande à traiter.
        * @return(Boolean) <res> : Faux si un erreur s'est produite ou si la demande a été refusée. Vrai sinon
        */
        boolean res = false;
        lockSoutien.tryLock(); // Blockage de la méthode pour le traitement en série des demandes
        try{   
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Affecter au soutien sa date de demande à l'actuelle
            soutien.setDateDemande(new Date());
            // Attribuer le soutien à l'élève <eleve>
            eleve.addSoutien(soutien);
            // Persistance du soutien et mise à jour des informations dans la base de données
            sDao.create(soutien);
            eDao.update(eleve);   
            // Trouver l'intervenant et envoyer le message correspondant ou refuser la demande
            res = traiterSoutien(soutien); 
            JpaUtil.validerTransaction();
        }
        catch(Exception e){
            System.err.println(e);
            res = false;
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        lockSoutien.unlock(); // Traitement fini, déblocage.
        return res;
    }
    
    private Boolean traiterSoutien(Soutien soutien) {
        /**
        * Trouve un intervenant pour <soutien> et le refuse en cas d'indisponibilité.
        * Un soutien est refusé si sa durée est égale à 0. En attente si sa durée est null (non définie).
        * @param(Soutien) <soutien> : Un Soutien demande à traiter.
        * @return(Boolean) <res> : Faux si un erreur s'est produite ou si la demande a été refusée. Vrai sinon
        */
        Boolean res = false;
        try{   
            // Recherche d'un intervenant disponible à qui affecter <soutien>.
            Eleve eleve = soutien.getEleve();
            Intervenant inter = trouverIntervenant(eleve.getNiveau());
            // Si un intervenant trouvé
            if (inter != null) {
                // Attribuer le soutien à l'intervenant
                inter.addSoutien(soutien);
                // L'intervenant a été pris, il est ainsi mis indisponible pour d'autres demandes.
                inter.setDisponible(false);
                // Mise à jour des informations dans la base de données
                sDao.update(soutien);
                iDao.update(inter);
                // Envoie du sms à l'intervenant choisi pour l'informer de son intervention en attente.
                DateFormat dateFormatter = new SimpleDateFormat("HH:mm");
                Message.envoyerNotification(inter.getTel(), "Bonjour "+ inter.getPrenom() 
                    + ". Merci de prendre en charge la demande de soutien en <<" + soutien.getMatiere().getLibelle()
                    +">> demandée à " + dateFormatter.format(soutien.getDateDemande()) + " par " 
                    + eleve.getPrenom() 
                    + " en calsse de " + eleve.getClasse() + ".");
                res = true;
            } else{
                soutien.setDuree(new Date(0)); // pas d'intervenant au moment de la demande -> refus du soutien
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return res;
    }
    
    public boolean demarrerVisio(Soutien soutien) {
        /**
        * Met à jour les informations nécessaires pour le lancement de la visio-conférence.
        * Plus précisément, il met à jour la date de début de la visio-conférence.
        * @param(Soutien) <soutien> : Un Soutien demande.
        * @precondition(Soutien) <soutien> n'est pas refusé et est attribué à un intervenant.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur. Sinon Faux.
        */
        boolean res = false;
        try{  
            System.out.println("[LOG] Démarrage de la visio-conference");
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Mise à jour de la date de début de la visio-conférence à l'actuelle.
            soutien.setDateDebut(new Date());
            sDao.update(soutien);
            JpaUtil.validerTransaction();
            res = true;
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public boolean redigerBilan(Soutien soutien, String bilan){
        /**
        * Affecte à soutien <soutien>  le bilan <bilan>  rédigé par l'intervenant qui est intervenu au soutien.
        * @param(Soutien) <soutien> : Le Soutien demande.
        * @param(String) <bilan> : Le bilan du soutien <soutien> pour l'élève, écrit par l'intervenant.
        * @precondition(<soutien>) <soutien> est fini (visio-conférence réalisée) <=> durée > 0.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur. Sinon Faux.
        */
        boolean res = false;
        try{  
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Écriture du bilan dans <soutien> et mise à jour dans la BD.
            soutien.setBilanInter(bilan);
            sDao.update(soutien);
            JpaUtil.validerTransaction();
            res = true;
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public boolean affecterUneEvaluation(Soutien soutien, Soutien.Evaluation eval){
        /**
        * Affecte à <soutien> l'évaluation <eval> de l'élève qui a demandé le soutien.
        * @param(Soutien) <soutien> : Le Soutien demande.
        * @param(Soutien.Evaluation) <eval> : L'évaluation du soutien par l'élève.
        * @precondition(<soutien>) <soutien> est fini (visio-conférence réalisée) <=> durée > 0.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur.
        */
        boolean res = false;
        try{  
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Affectation de l'évaluation et mise à jour de la BD
            soutien.setEvalEleve(eval);
            sDao.update(soutien);
            JpaUtil.validerTransaction();
            res = true;
        }catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
            
    }
    
    public boolean arreterVisio(Soutien soutien) {
        /**
        * Affecte la durée du soutien à <soutien> et remet l'intervenant qui l'a réalisé comme disponible.
        * @param(Soutien) <soutien> : Un Soutien demande.
        * @precondition(Soutien) <soutien> n'est pas refusé et est attribué à un intervenant.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur. Sinon Faux.
        */
        boolean res = false;
        try{   
            System.out.println("[LOG] Arrêt de la visio-conference");
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Affecte la durée du soutien ( date actuelle - date de debut de la visio)
            soutien.setDuree(new Date(new Date().getTime() - soutien.getDateDebut().getTime()));
            soutien.getIntervenant().setDisponible(true);
            // Mise à jour dans la base de données
            iDao.update(soutien.getIntervenant());
            sDao.update(soutien);
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
    
    private void inscrireEtablissement(Etablissement ets) {
        /**
        * Inscrit dans la base de données l'établissement <ets>
        * @param(Etablissement) <ets> : Un établissement à inscrire.
        * @precondition(<ets>) Au moins un élève d'INSTRUCT'IF appartient à cet établissement.
        */
        // Obtention des coordonnées avec GeoNetApi et affectation à <ets>
        String adresseEtablissement = ets.getNomEts() + ", " + ets.getNomCommune();
        LatLng coordsEtablissement = GeoNetApi.getLatLng(adresseEtablissement);
        ets.setLat(coordsEtablissement.lat);
        ets.setLng(coordsEtablissement.lng);
        // création de <ets> dans la base de données.
        etsDao.create(ets);
    }

    public boolean insererIntervenants() {
        /**
        * Inscrit les intervenants hardcodés d'INSTRUCT'IF.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur. Sinon Faux.
        */
        boolean res = false;
        try{   
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Inscription des intervenants.
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
        /**
         * Essaye d’authentifier un intervenant avec son <mail> et son <motDePasse>.
         * @param(String) <mail> : l'email d'un Intervenant.
         * @param(String) <motDePasse> : l mot de passe avec lequel on tente dde s'authentifier.
         * @return(Intervenant) <intervenant> : L'intervenant si ses identifiants sont corrects, null sinon.
         */
        Intervenant intervenant = null;
        try{
            JpaUtil.creerContextePersistance();
            // Recherche de l'entité Intervenant par son mail.
            intervenant = iDao.findByMail(mail);
            // Si un Intervenant identifié par <mail> existe (non null), tester que le mot de passe soit correcte.
            if(intervenant != null && !intervenant.getMotDePasse().equals(motDePasse)){
                intervenant = null;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return intervenant;
    }
    
    public Boolean inscrireEleve(Eleve eleve, String uai) {
        /**
         * Inscrit dans la base de données un un Eleve <eleve> appartenant à un
         * établissement identifié par son <uai>.
         * 
         * @param(Eleve) <eleve> : Un élève à inscrire.
         * @param(String) <uai> : Le code uai d'un établissement dans l'API EducNET (https://data.education.gouv.fr/explore/dataset/fr-en-annuaire-education/).
         * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu
         *                  d’erreur. Sinon Faux.
         */
        boolean res = false;
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            // Tente de récupérer son établissement dans notre base de donnée, sinon tente de l'ajouter.
            Etablissement etab = etsDao.findByUai(uai);
            if (etab == null) {
                // Recherche un college ou lycée avec cet uai.
                EducNetApi api = new EducNetApi();
                List<String> result = api.getInformationCollege(uai);
                if (result == null) {
                    result = api.getInformationLycee(uai);
                }
                // Si trouvé, l'ajouter à la base de données.
                if (result != null) {
                    etab = new Etablissement(uai, result.get(1), result.get(2), result.get(4), result.get(3),
                            result.get(6), result.get(5), result.get(7), Double.parseDouble(result.get(8)));
                    inscrireEtablissement(etab);
                } else {
                    System.out.println("[ERROR] Etablissement inconnu");
                }
            }
            
            // Si l'uai est valide (l'établissement existe).
            if(etab != null) {
                // L'affecter à l’élève et mettre à jour ses informations dans la base de données.
                eleve.setEtablissement(etab);
                eDao.create(eleve);
                JpaUtil.validerTransaction();
                // Envoie du mail de confirmation d'inscription.
                Message.envoyerMail("contact@instruct.if", eleve.getEmail(), "Bienvenu sur le réseau INSTRUCT'IF", "Bonjour" + eleve.getPrenom() + ", "
                    + "nous te confirmons ton inscription au reseau INSTRUCT'IF.\n"
                    + "Si tu as besoin d'un soutien pour tes leçons ou tes devoirs, rends toi sur notre site pour la mise en relation avec un intervenant.");
                res = true;
            }
            
        }
        catch(Exception e){
            System.err.println(e);
            // Envoie du mail de de refus d'inscription.
            Message.envoyerMail("contact@instruct.if", eleve.getEmail(), "Échec de l'inscription sur le réseau INSTRUCT'IF", "Merci de recommencer ultérieurement.");
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return res;
    }
    
    public Eleve authentifierEleve(String mail, String motDePasse) {
        /**
         * Essaye d’authentifier un élève avec son <mail> et son <motDePasse>.
         * @param(String) <mail> : l'email d'un Eleve.
         * @param(String) <motDePasse> : le mot de passe avec lequel on tente de s'authentifier.
         * @return(Eleve) <eleve> : L'élève authentifié si ses identifiants sont corrects, null sinon.
         */
        Eleve eleve = null;
        try{
            JpaUtil.creerContextePersistance();
            // Recherche de l'entité Eleve par son mail.
            eleve = eDao.findByMail(mail);
            // Si un Eleve identifié par <mail> existe, tester que le mot de passe soit correcte.
            if(eleve != null && !eleve.getMotDePasse().equals(motDePasse)){
                eleve = null;
            }
        }
        catch(Exception e){
            System.err.println(e);
        }finally{
            JpaUtil.fermerContextePersistance();
        }
        return eleve;
    }
    
    public boolean insererMatieres() {
        /**
        * Inscrit les matières hardcodés possibles pour demander un soutien sur INSTRUCT'IF.
        * @return(Boolean) <res> : Indicateur de succès. Vrai si il n’y a pas eu d’erreur. Sinon Faux.
        */
        boolean res = false;
        try{
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            // Creation des matières dans la base de données.            mDao.create(new Matiere("Histoire-Géographie"));
           mDao.create(new Matiere("Mathématiques"));
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
    
    private static final  SoutienDao sDao = new SoutienDao();
    private static final  IntervenantDao iDao = new IntervenantDao();
    private static final  EleveDao eDao = new EleveDao();
    private static final MatiereDao mDao = new MatiereDao();
    private static final EtablissementDao etsDao = new EtablissementDao();
    private static final Lock lockSoutien = new ReentrantLock();

}
