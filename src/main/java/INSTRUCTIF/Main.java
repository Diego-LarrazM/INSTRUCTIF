/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF;

import INSTRUCTIF.dao.JpaUtil;
import INSTRUCTIF.metier.service.Service;
import INSTRUCTIF.tests.Scenarios;


/**
 *
 * @author dlarrazmar
 */
public class Main {

    
    private static Service eServ;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JpaUtil.desactiverLog();
        
        JpaUtil.creerFabriquePersistance();
        
        
        Scenarios.AjoutHardCode();
        Scenarios.PopulationDBScenario();
        
        //Scenarios.Alice();
        Scenarios.DebordementDemandes();
        //Scenarios.AttentionAuxIntrus();
        //Scenarios.ChronologicalEventsAlice();
        
        JpaUtil.fermerFabriquePersistance();
    }
    
}
    
    
    
    
    /*
    static void inputs() {
        boolean exit = false;
        while(!exit) {
            String input = Saisie.lireChaine("Action (i | aA | x): ");
            switch (input.charAt(0)) {
                case 'i': {
                    inscrire();
                    break;
                }
                case 'a': {
                    authentifier(true);
                    break;
                }
                case 'A': {
                    authentifier(false);
                    break;
                }
                //case 'r': {
                //    System.out.println("Recherche par ID");
                //    Long id = new Long(Saisie.lireInteger("ID: "));
                //    rechercher(id);
                //    break;
                //}
                case 'x':
                    exit = true;
                    break;
                default:
                    System.out.println("Action inconnue: " + input);
                    break;
            }
        }        
    }
    static void msgInsc(boolean insc, String prenom){
        if(insc){
            System.out.println("Inscription réussie pour " + prenom );
        }else{
            System.out.println("Inscription échouée pour " + prenom );
        }
    }
    
    static void inscrire() {
        System.out.println("Inscription");
        String prenom = Saisie.lireChaine("prenom: ");
        String nom = Saisie.lireChaine("nom: ");
        String pattern = "dd/MM/yyyy";
        String date = Saisie.lireChaine("Date de naissance (" + pattern+"): ");
        String mail = Saisie.lireChaine("mail: ");
        String mdp = Saisie.lireChaine("mot de passe: ");
        String uai = Saisie.lireChaine("UAI: ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date dateAniv;
        boolean insc = false;
        try {
            dateAniv = simpleDateFormat.parse(date);
            insc = eServ.inscrireEleve(new Eleve(nom, prenom, dateAniv, mail, mdp), uai); 
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(insc){
            System.out.println("Inscription réussie pour " + nom + " " + prenom + " <" + mail + ">");
        }else{
            System.out.println("Inscription échouée pour " + nom + " " + prenom + " <" + mail + ">");
        }
    }

    static void authentifier(boolean eleve) {
        System.out.println("Authentification");
        String mail = Saisie.lireChaine("mail: ");
        String motDePasse = Saisie.lireChaine("mot de passe: ");
        if(eleve){
            Eleve c = eServ.authentifierEleve(mail, motDePasse);
            if(c != null){
                System.out.println("Authentification réussie pour " + c.getNom() + " " + c.getPrenom() + " <" + c.getEmail() + ">");
            }else{
                System.out.println("Authentification échouée pour <" + mail+ "> : " + motDePasse);
            }
        } else {
            Intervenant c = eServ.authentifierIntervenant(mail, motDePasse);
            if(c != null){
                System.out.println("Authentification réussie pour " + c.getNom() + " " + c.getPrenom() + " <" + c.getEmail() + ">");
            }else{
                System.out.println("Authentification échouée pour <" + mail+ "> : " + motDePasse);
            }
        }

    }

*/