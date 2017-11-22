/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import motus.Dictionnaire;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Pc
 */
public class Motus {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Date t1 = new Date();
        GestionFichiers gf = new GestionFichiers();
        String NOM_FICHIER = "C:\\Users\\Pc\\Documents\\NetBeansProjects\\Motus\\src\\motus\\motus.txt";
        String NOM_SAUVEGARDE = "C:\\Users\\Pc\\Documents\\NetBeansProjects\\Motus\\src\\motus\\save.ser";
        
        //System.out.println("Dictionnaire :\n" + dictionnaire);
        //System.out.println("finds: " + dictionnaire.rechercher("ADON"));
        
        File sauvegarde =  new File(NOM_SAUVEGARDE);
        
        Dictionnaire dictionnaire;
        
        if(!sauvegarde.exists()){
            dictionnaire = new Dictionnaire(NOM_FICHIER, gf);
            ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(sauvegarde));
            oos.writeObject(dictionnaire);
        }
        else{
            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(sauvegarde)) ;
            dictionnaire = (Dictionnaire)ois.readObject() ;
        }
        
        Date t2 = new Date();
        double constructionDictionnaire = t2.getTime() - t1.getTime();
        
        System.out.println("Temps de construction : " + constructionDictionnaire);
        ArrayList<Character> sur = new ArrayList<>();
        sur.add('B');
        sur.add(' ');
        sur.add(' ');
        sur.add('R');
        sur.add(' ');
        sur.add(' ');
        sur.add(' ');
        sur.add('E');
        ArrayList<String> finds = dictionnaire.rechercher(sur);
        //System.out.println("trouvé : " + finds);

        String pasSurString = "UR";
        ArrayList<Character> pasSur = motus.Dictionnaire.separate(pasSurString);

        String pasString = "ABS";
        ArrayList<Character> pas = motus.Dictionnaire.separate(pasString);

        finds = dictionnaire.rechercheAvance(finds,sur,pasSur,pas);
        System.out.println("trouvé : " + finds);
        
        Date t3 = new Date();
        double rechercheTemps = t3.getTime()-t2.getTime();
        System.out.println("Temps de trouvage : " + rechercheTemps);
        
        
    }
    
}
