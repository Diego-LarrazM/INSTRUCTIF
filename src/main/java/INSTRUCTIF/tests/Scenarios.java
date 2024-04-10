/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.tests;

import INSTRUCTIF.metier.modele.Eleve;
import INSTRUCTIF.metier.modele.Intervenant;
import INSTRUCTIF.metier.modele.Matiere;
import INSTRUCTIF.metier.modele.Soutien;
import INSTRUCTIF.metier.service.Service;
import INSTRUCTIF.util.EtabHitInstance;
import INSTRUCTIF.util.Statistiques;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author dlarrazmar
 */
public class Scenarios {
    
    public static void AjoutHardCode() {
        System.out.println("\n[PREPARATION] Ajout des intervenants et des matières");
        Serv.insererIntervenants();
        Serv.insererMatieres();
    }
    
    public static void PopulationDBScenario() {
        System.out.println("\n[PREPARATION] Ajout de soutiens et eleves fictifs pour le scenario");
        InscriptionEleve(new Eleve("Normand", "Norman", 5L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "norman.normand@free.fr", "lapinou"), "0691664J");
        InscriptionEleve(new Eleve("Normand", "Corman", 5L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "corman.normand@free.fr", "lapinou"), "0691664J");
        InscriptionEleve(new Eleve("Normand", "Sorman", 4L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "sorman.normand@free.fr", "lapinou"), "0691664J");
        InscriptionEleve(new Eleve("Halister", "Frank", 1L, new GregorianCalendar(2008, GregorianCalendar.FEBRUARY, 17).getTime(), "frank.halister@gmail.com", "emc2"), "0010001W");
        InscriptionEleve(new Eleve("Murph", "Murphy", 0L, new GregorianCalendar(2010, GregorianCalendar.JULY, 23).getTime(), "murphy@free.fr", "singularite"), "0020034B");
        InscriptionEleve(new Eleve("Sanderson", "Esperanza", 3L, new GregorianCalendar(2010, GregorianCalendar.JULY, 23).getTime(), "esperanza.sanderson@free.fr", "pasUneRefDuSHC"), "0020034B");
    }
    
    public static void AttentionAuxIntrus(){
        System.out.println("\n[SCENARIO] Tentatives D'intrusion");
        
        System.out.println("\n[SUB-SCENARIO] Création compte existant");
        InscriptionEleve(new Eleve("Pascal", "Alice", 5L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "alice.pascal@free.fr", "lapin"), "0691664J");
        System.out.println("\n[FIN SUB-SCENARIO] Création compte existant");
        
        System.out.println("\n[SUB-SCENARIO] Intrusion en tant qu'elève");
        System.out.println("\nLe bon:");
        AutentificationEleve("alice.pascal@free.fr", "lapin");
        System.out.println("\nLes intrus:");
        AutentificationEleve("alice.pascal@free.fr", "mauvaisMdp"); 
        AutentificationEleve("diego.leFouineur@hax.fr", "FouinFouin");
        AutentificationEleve("missing@void.com", "lapin");
        AutentificationEleve("camille.martin@sorbonne.fr", "lapinou");
        System.out.println("\n[FIN SUB-SCENARIO] Intrusion en tant qu'elève");
        
        System.out.println("\n[SUB-SCENARIO] Intrusion en tant qu'intervenant");
        System.out.println("\nLes bons:");
        AutentificationIntervenant("camille.martin@sorbonne.fr", "lapinou");
        AutentificationIntervenant("simone.youcenar@retraites.fr", "1234");
        AutentificationIntervenant("emile.hugo@sup.fr", "motdepasse");
        System.out.println("\nLes intrus:");
        AutentificationIntervenant("camille.martin@sorbonne.fr", "mauvaisMdp");
        AutentificationIntervenant("diego.leFouineur@hax.fr", "FouinFouin");
        AutentificationIntervenant("alice.pascal@free.fr", "lapin");
        AutentificationIntervenant("missing@void.com", "lapinou");
        System.out.println("\n[FIN SUB-SCENARIO] Intrusion en tant qu'intervenant");
        
        System.out.println("\n[Fin SCENARIO] Tentatives D'intrusion");
    }
    
    public static void DebordementDemandes(){
        System.out.println("\n[SCENARIO] Trop de demandes / plus d'intervenant");
        
        Eleve[] elvs = {AutentificationEleve("norman.normand@free.fr", "lapinou"),
                        AutentificationEleve("corman.normand@free.fr", "lapinou"),
                        AutentificationEleve("sorman.normand@free.fr", "lapinou"),
                        AutentificationEleve("frank.halister@gmail.com", "emc2"),
                        AutentificationEleve("murphy@free.fr", "singularite"),
                        AutentificationEleve("esperanza.sanderson@free.fr", "pasUneRefDuSHC")
        };
        
        Boolean allGood = true;
        int i = 0;
        while(allGood && i < 6){
            allGood = (elvs[i++] != null);
        }
        
        if(allGood){
            Soutien[] soutiens = new Soutien[6];
            for(i = 0; i < 6; i ++){
                soutiens[i] = CreationSoutien(elvs[i],"Descriptif",i);
                if(soutiens[i] != null){
                    DemandeSoutien(elvs[i],soutiens[i]);
                    System.out.println("Soutien n°" + i + ": "+ soutiens[i]);
                    
                    if(i == 0 || i == 3){
                        Serv.demarrerVisio(soutiens[i]);
                        Serv.arreterVisio(soutiens[i]);
                    }
                    afficherIntervs();
                }
            }
            ConsultationListeSoutiens(Serv.obtenirIntervenantParId(soutiens[0].getIntervenant().getId()));
        }
        
        System.out.println("\n[FIN SCENARIO] Trop de demandes / plus d'intervenant");
    }
    
    public static void Alice(){
        System.out.println("\n[SCENARIO] Alice (insc + auth + demande)");
        InscriptionEleve(new Eleve("Pascal", "Alice", 5L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "alice.pascal@free.fr", "lapin"), "0691664J");
        Soutien soutien = null;
        Eleve alice = AutentificationEleve("alice.pascal@free.fr", "lapin");
        if(alice != null){
            soutien = CreationSoutien(alice,"Ticher said That mon Englishe is-e very bad, I need-e help ?",0);
            if(soutien != null && DemandeSoutien(alice,soutien)){
                //Wait
                 if(VisioConference(soutien,true)){
                    CompteRenduEleve(soutien, Soutien.Evaluation.TOUT_COMPRIS);
                    Serv.redigerBilan(soutien, "Une progression aparente, je tiens à vous feliciter !\n"
                        + "Par la suite je vous reccomend de reviser la conjugaison du passé,"
                        + "voici pour vous aider lors des redactions : https://www.grammarly.com/ .\n"
                        + "N'hesitez pas à me recontacter si vous avez un nouveau doute."
                    );
                    ConsultationBilanSoutien(alice);
                }
            }
        }
        
        Simone(alice, soutien);
        System.out.println("\n[FIN SCENARIO] Alice (insc + auth + demande)");
    }
    
    private static void Simone(Eleve alice, Soutien soutien){
        System.out.println("\n[SUB-SCENARIO] Simone l'intervenant reponds à la demande de soutien d'Alice (insc + auth + bilan)");
        
        Intervenant intervenant = AutentificationIntervenant("simone.youcenar@retraites.fr","1234");
        
        if(intervenant != null){
            ConsultationSoutien(soutien);
            //wait
            if(VisioConference(soutien,false)){
                CompteRenduIntervenant(soutien, "Une progression aparente, je tiens à vous feliciter !\n"
                        + "Par la suite je vous reccomend de reviser la conjugaison du passé,"
                        + "voici pour vous aider lors des redactions : https://www.grammarly.com/ .\n"
                        + "N'hesitez pas à me recontacter si vous avez un nouveau doute."
                );                  
            }  
            ConsultationListeSoutiens(intervenant);
            ConsultationTableauBord();
        }
        System.out.println("\n[FIN SUB-SCENARIO] Simone l'intervenant repons à la demande de soutien (insc + auth + bilan)");
        
    }

    public static void ChronologicalEventsAlice() {
        System.out.println("\n[SCENARIO] Faits Chronologiques Alice (insc + auth + demande)");

        InscriptionEleve(new Eleve("Pascal", "Alice", 5L, new GregorianCalendar(2004, GregorianCalendar.JANUARY, 8).getTime(), "alice.pascal@free.fr", "lapin"), "0691664J");
        
        Eleve alice = AutentificationEleve("alice.pascal@free.fr", "lapin");
        if(alice != null){
            List<Matiere> matieres = Serv.obtenirMatieres();
            System.out.println(matieres.size() + " matières possibles");
            Soutien soutien =  new Soutien("Ticher said That mon Englishe is-e very bad, I need-e help ?", matieres.get(0));
            if(DemandeSoutien(alice,soutien)){
                if(AutentificationIntervenant("simone.youcenar@retraites.fr","1234") != null){
                    ConsultationSoutien(soutien);
                    if(VisioConference(soutien,true)){
                        CompteRenduEleve(soutien, Soutien.Evaluation.TOUT_COMPRIS);
                        CompteRenduIntervenant(soutien, "Une progression aparente, je tiens à vous feliciter !\n"
                                + "Par la suite je vous reccomend de reviser la conjugaison du passé,"
                                + "ce software pour vous aider aux redactions : https://www.grammarly.com/ .\n"
                                + "N'hesitez pas à me recontacter si vous avez un nouveau doute."
                        );                  
                    }
                    ConsultationListeSoutiens(soutien.getIntervenant());
                    ConsultationTableauBord();
                }
            }

        }
        System.out.println("\n[FIN SCENARIO] Faits Chronologiques Alice (insc + auth + demande)");
        
    }
    
    private static boolean InscriptionEleve(Eleve e, String uai) {
        System.out.println("\n[PHASE] Inscription de " + e.getPrenom() + " " + e.getNom());
        boolean res = Serv.inscrireEleve(e, uai);
        if(res) {
            System.out.println("[RESULT] Inscption réussie");
        } else {
            System.out.println("[RESULT] Inscption échouée");   
        }
        return  res;
    }
    
    private static Eleve AutentificationEleve(String mail, String mdp) {
        System.out.println("\n[PHASE] Autentification de <" + mail + ">:" + mdp);
        Eleve e = Serv.authentifierEleve(mail, mdp);
        if(e != null) {
            System.out.println("[RESULT] Autentification réussie de " + e.getPrenom() + " " + e.getNom());
        } else {
            System.out.println("[RESULT] Autentification échouée");   
        }
        return e;
    }
    
    private static Intervenant AutentificationIntervenant(String mail, String mdp) {
        System.out.println("\n[PHASE] Autentification de <" + mail + ">:" + mdp);
        Intervenant i = Serv.authentifierIntervenant(mail, mdp);
        if( i != null) {
            System.out.println("[RESULT] Autentification réussie de " +  i.getPrenom() + " " +  i.getNom());
        } else {
            System.out.println("[RESULT] Autentification échouée");   
        }
        return i;
    }
    
    private static Soutien CreationSoutien(Eleve e, String desc, int indexMat) {
        Soutien soutien = null;
        System.out.println("\n[PHASE] Creation d'un Soutien par " + e.getPrenom());
        List<Matiere> matieres = Serv.obtenirMatieres();
        if(matieres.size() > 0){
            soutien =  new Soutien(desc, matieres.get(indexMat));
            System.err.println("Soutien:\n"
                    + "   - Matière (" + soutien.getMatiere()+ ")\n"
                    + "   - Descriptif :" + soutien.getDescriptif()
    
            );
            System.out.println("[RESULT] Creation du soutien réussie");
        }
        else{
            System.out.println("[RESULT] Creation du soutien échouée");
        }
        return soutien;
    }
    
    private static boolean DemandeSoutien(Eleve e, Soutien s) {
        System.out.println("\n[PHASE] Demande de soutien de " + s.getMatiere().getLibelle() + " par " + e.getPrenom());
        Serv.demanderSoutien(e, s);
        
        boolean res = (s.getDuree() == null);
        if(res) {
           System.out.println("[RESULT] Demande de soutien réussie et attribuée à " + s.getIntervenant().getPrenom());
        } else {
            System.out.println("[RESULT] Demande de soutien échouée");
        }
        return res;
    }
    
    private static void ConsultationSoutien(Soutien s) {
        Intervenant i =  s.getIntervenant();
        Soutien dernierSoutien = i.dernierSoutien();
        
        System.out.println("\n[PHASE] Consultation des details du soutien par l'intervenant " + i.getPrenom());
        System.out.println("Eleve:" + dernierSoutien.getEleve().getPrenom() + dernierSoutien.getEleve().getNom());
        System.out.println("Details:\n"
                            + "   - descriptif(" + dernierSoutien.getDescriptif() + ")\n"
                            + "   - matière(" +  dernierSoutien.getMatiere().getLibelle() + ")\n"
                            + "   - age: (" + dernierSoutien.getEleve().getAge() + ")\n"
                            + "   - " + dernierSoutien.getEleve().getEtablissement()
        );
        System.out.println("[RESULT] Consultation réussie");
    }
    
    private static boolean VisioConference(Soutien s, boolean eleveView) {
        System.out.println("\n[PHASE] Demarrage du soutien de " + s.getMatiere().getLibelle() + " avec " + s.getEleve().getPrenom() + " et " + s.getIntervenant().getPrenom());
        boolean res = Serv.demarrerVisio(s);
        if(res) {
           System.out.println("[RESULT] Lancement de la visio-conférence réussie");
           if(eleveView){
               System.out.println("                        ;;\\\\/;;;;;;;;\n" +
                                        "                   ;;;;;;;;;;;;;;;;;\n" +
                                        "                ;;;;;;;;;;;;     ;;;;;\n" +
                                        "               ;;;;;    ;;;         \\;;\n" +
                                        "              ;;;;;      ;;          |;\n" +
                                        "             ;;;;         ;          |\n" +
                                        "             ;;;                     |\n" +
                                        "              ;;                     )\n" +
                                        "               \\    ~~~~ ~~~~~~~    /\n" +
                                        "                \\    ~~~~~~~  ~~   /\n" +
                                        "              |\\ \\                / /|\n" +
                                        "               \\\\| %%%%%    %%%%% |//\n" +
                                        "              [[====================]]\n" +
                                        "               | |  ^          ^  |\n" +
                                        "               | | :@: |/  \\| :@: | |\n" +
                                        "                \\______/\\  /\\______/\n" +
                                        "                 |     (@\\/@)     |\n" +
                                        "                /                  \\\n" +
                                        "               /  ;-----\\  ______;  \\\n" +
                                        "               \\         \\/         /\n" +
                                        "                )                  (\n" +
                                        "               /                    \\\n" +
                                        "               \\__                  /\n" +
                                        "                \\_                _/\n" +
                                        "                 \\______/\\/\\______/\n" +
                                        "                  _|    /--\\    |_\n" +
                                        "                 /%%\\  /\"'\"'\\  /%%\\\n" +
                                        "  ______________/%%%%\\/\\'\"'\"/\\/%%%%\\______________\n" +
                                        " / :  :  :  /  .\\%%%%%%%\\\"'/%%%%%%%/.  \\  :  :  : \\\n" +
                                        ")  :  :  :  \\.  .\\%%%%%%/'\"\\%%%%%%/.  ./  :  :  :  (");

                System.out.println("Art by Anthony Thyssen @ https://www.asciiart.eu/people/faces");
           }
           else{
               System.out.println("_\n" +
                    "            ,--,--.;  `__ ,-.\n" +
                    "           /.-'    \\  /  )   \\\n" +
                    "          (        .--.  ,-.  ))\n" +
                    "         (        (     Y   \\ ( )\n" +
                    "        (         /          `.)(\n" +
                    "       ()        /             ( )\n" +
                    "       ((       (   _           )(\n" +
                    "        )    (  ) .' `-.   .-'. ( ) \n" +
                    "        (( _  )(    __       _  /)( \n" +
                    "        ( /.`( (   <()>     /( ) \n" +
                    "         )||  ))                \\ ))\n" +
                    "        ( \\(- ((           \\     .((\n" +
                    "         ) \\./|)         (  )    .))\n" +
                    "        (     )(                 ;(\n" +
                    "        )     ()       .--.--.   /)\n" +
                    "        (      )).      `--.-'  /( \n" +
                    "        /      (  `.           /  )\n" +
                    "       (        )   `-.      .'   (\n" +
                    "   akg  \\              `-.--'");
               System.out.println("Art by akg @ https://ascii.co.uk/art/face");
           }
            
            System.out.println("\n[PHASE] Arrêt de la visio-conférence");
            if(res = Serv.arreterVisio(s)){
                System.out.println("[RESULT] Visio-conference Fermée et terminée correctement");
            }else{
                System.out.println("[RESULT] Arrêt de la visio-conférence échouée");
            }
        } else {
            System.out.println("[RESULT] Lancement de la visio-conférence échouée");
        }
        return res;
    }
    
    private static boolean CompteRenduIntervenant(Soutien s, String bilan){
        System.out.println("\n[PHASE] Redaction du bilan de l'intervention par " + s.getIntervenant().getPrenom());
        System.out.println("Bilan : " + bilan);
        boolean res = Serv.redigerBilan(s,bilan);
        if(res) {
            System.out.println("[RESULT] Ecriture du compte rendu reussie");
        }else{
            System.out.println("[RESULT] Ecriture du compte rendu échouée");
        }
        return res;
    }
    
    private static boolean CompteRenduEleve(Soutien s, Soutien.Evaluation eval){
        System.out.println("\n[PHASE] Evaluation de la visio-conference par " + s.getEleve().getPrenom());
        System.out.println("Evaluation : " + eval);
        boolean res = Serv.affecterUneEvaluation(s,eval);
        if(res) {
            System.out.println("[RESULT] Evaluation reussie");
        }else{
            System.out.println("[RESULT] Evaluation échouée");
        }
        return res;
    }
    
    private static void ConsultationBilanSoutien(Eleve e) {
        System.out.println("\n[PHASE] Consultation du bilan du dernier soutien de " + e.getPrenom());
        Soutien soutien = e.dernierSoutien();
        System.out.println("Details du Soutien:\n" 
                + "Date commencement: " +soutien.getDateDebut() + "\n"
                + "Durée: " +soutien.getDuree() + "ms\n"
                + "Bilan par l'intervenant " + soutien.getIntervenant().getPrenom() + ": " + soutien.getBilanInter()
        );
        System.out.println("[RESULT] Consultation réussie");     
    }
    
    private static void ConsultationListeSoutiens(Intervenant intervenant) {
        System.out.println("\n[PHASE] Consultation de la liste des dernieres interventions par l'intervenant " + intervenant.getPrenom());
        for(Soutien soutien: intervenant.getSoutiens()){
            System.out.println("   - " + soutien);
        }
        System.out.println("[RESULT] Consultation réussie");     
    }
    
    private static void ConsultationTableauBord() {      
        System.out.println("\n[PHASE] Consultation du tableau de bord des demandes de soutiens");
        Statistiques stats = Serv.obtenirStatistiques();
        if(stats != null){     
            System.out.println(stats);
            System.out.println("Repartition Geographique (nbEleves,établissement):"); 
            for(EtabHitInstance eH : stats.getRepartitionGeo() ){
                System.out.println(eH.getNbElevesInstructif() + " | " + Serv.obtenirEtablissementParId(eH.getEtabId()));
            }
            System.out.println("[RESULT] Consultation réussie");
            
        }else{
            System.out.println("[RESULT] Consultation échouée");     
        }
        
    }
    
    private static void afficherIntervs(){
        List<Intervenant> intervs = Serv.obtenirIntervenants();
        for(Intervenant i : intervs){
            System.out.println(i);
        }
    }
    
    public static final Service Serv = new Service();
}
