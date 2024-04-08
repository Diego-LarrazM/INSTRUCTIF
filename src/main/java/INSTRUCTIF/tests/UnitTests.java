/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INSTRUCTIF.tests;

import INSTRUCTIF.Main;
import INSTRUCTIF.metier.modele.Eleve;
import INSTRUCTIF.metier.modele.Matiere;
import INSTRUCTIF.metier.modele.Intervenant;
import INSTRUCTIF.metier.modele.Soutien;
import INSTRUCTIF.metier.service.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlarrazmar
 */



public class UnitTests {
    
    public static final Service Serv = new Service();
    
    private static void printTestName(String name){
        System.out.println("---------- TEST " + name + " ----------");
    }

    private static void printTestCase(String name, boolean res){
        System.out.println("[TEST] " + name + ": " + res);
    }
    
    public static void InscrireEleves(){
        printTestName("InscrireEleves");
        
        boolean res = false;
        try {
            Eleve alice = new Eleve("Pascal", "Alice", 5L, (new SimpleDateFormat("dd/MM/yyyy")).parse("01/01/2008"), "alice.pascal@free.fr", "lapin");
            Serv.inscrireEleve(alice, "0691664J");
            res = true;
            System.out.println("Etablissement: " + alice.getEtablissement().getNomEts());
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        printTestCase("Inscription de Alice: ", res);
        
        System.out.println();
    }

    public static void AutentifiactionEleves(){     
        printTestName("AutentifiactionEleves");
        
        TestAuthE("alice.pascal@free.fr", "mauvaisMdp");
        TestAuthE("alice.pascal@free.fr", "lapin");
        TestAuthE("diego.leFouineur@hax.fr", "FouinFouin");
        TestAuthE("missing@void.com", "lapin");
        TestAuthE("camille.martin@sorbonne.fr", "lapinou");
        
        System.out.println();
    }
    private static void TestAuthE(String mail, String mdp) {
        boolean ok = Serv.authentifierEleve(mail, mdp)!= null;
        printTestCase("Autentification de " + mail + " ("+mdp+ ")", ok);
    }

    public static void AutentifiactionIntervenants(){
        printTestName("AutentifiactionIntervenants");
        
        TestAuthA("camille.martin@sorbonne.fr", "lapinou");
        TestAuthA("camille.martin@sorbonne.fr", "mauvaisMdp");
        TestAuthA("diego.leFouineur@hax.fr", "FouinFouin");
        TestAuthA("simone.youcenar@retraites.fr", "1234");
        TestAuthA("emile.hugo@sup.fr", "motdepasse");
        TestAuthA("alice.pascal@free.fr", "lapin");
        TestAuthA("missing@void.com", "lapinou");
        
        System.out.println();
    }
    private static void TestAuthA(String mail, String mdp) {
        boolean ok = Serv.authentifierIntervenant(mail, mdp)!= null;
        printTestCase("Autentification de " + mail + " ("+mdp+ ")", ok);
    }
    
    public static void HardCodeIntervenants() {
        printTestName("HardCodeIntervenants");
        
        Serv.insererIntervenants();

        for(Intervenant mat : Serv.obtenirIntervenants()){
            System.out.println(mat + " ");
        }
        System.out.println("\n");

        System.out.println("ID 4: " + Serv.obtenirIntervenantParId(4L));

        System.out.println();
    }
    
    public static void HardCodeMatieres() {
        printTestName("HardCodeMatieres");
        
        Serv.insererMatieres();
        
        for(Matiere mat : Serv.obtenirMatieres()){
            System.out.println(mat + " ");
        }
        System.out.println("\n");

        System.out.println("ID 4: " + Serv.obtenirMatiereParId(4L));

        System.out.println();
    }
    
}
