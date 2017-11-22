/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Dictionnaire implements Serializable{
    protected ArrayList<String> dico;
    
    public Dictionnaire(String nom, GestionFichiers gf) throws IOException{
        this.dico = new ArrayList<>();
        //On ouvre et on lit le fichier contenant les mots
        gf.ouvrir(nom);
        String contenu = gf.lire();
        //System.out.println("Contenu : \n" + contenu);
        int pointer = 0;
        StringBuilder sb = new StringBuilder();
        
        //On met chaque mot dans une node d'une liste 
        //le caractere } marque la fin de la mise en liste
        while(contenu.charAt(pointer)!= '}'){
            
            while(contenu.charAt(pointer)!='\n'){
                sb.append(contenu.charAt(pointer));
                pointer ++;
            }
            this.dico.add(sb.toString());
            sb.delete(0, sb.length());
            pointer++ ;
        }
    }
    
    public String toString(){
        String str = "";
        for(String s : dico){
            str = str.concat(s.concat("\n"));
        }
        return str;
    }
    
    public ArrayList<String> rechercher(String search){
        ArrayList<String> matches = new ArrayList<>();
        for(String s : dico){
            if(s.contains(search))
                matches.add(s);
        }
        return matches;
    }
    
    public ArrayList<String> rechercher(ArrayList<Character> search ){
        ArrayList<String> matches = new ArrayList<>();
        int compteur;
        int i;
        for(String s : dico){
            
            compteur = 0;
            i=0;
            for(char t : search){
                
                if(t == ' ')
                    compteur++;
                else if(s.charAt(i) == t)
                    compteur++;
                i++;
            }
            if (compteur == search.size())
                matches.add(s);
        }
        return matches;
    }
    

    
    public ArrayList<String> rechercheAvance(ArrayList<String> finds,ArrayList<Character> sur, ArrayList<Character> pasSur, ArrayList<Character> pas){
        ArrayList<String> matches  = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int compteur;
        int efface;
        boolean dedans;
        for(String s : finds){
            sb.append(s);
            compteur = 0;
            efface = 0;
            int taille = sb.length();
            for(int i = 0;i<taille;i++){
                if(sur.get(i)!= ' '){
                    sb.deleteCharAt(i-efface);
                    efface ++;
                } 
            }
            efface = 0;

            //==================================================================
            
            for(char psc : pasSur){
                for(int i = 0;i<sb.length();i++){
                    efface ++;
                    if(sb.toString().charAt(i) == psc){
                        sb.deleteCharAt(i);
                        i--;
                        compteur ++;
                        break;
                    }
                }
            }
            
            //==================================================================
            
            dedans = true;
            for(char c : pas){
                //System.out.println("char : " + c);
                for(int i = 0; i < sb.length();i++){
                    //System.out.println("s_char : " + s.charAt(i) + " / " + c);
                    if(sb.toString().charAt(i)== c){
                        dedans = false;
                    }
                }   
            }
            
            //==================================================================
            
            if(dedans && compteur == pasSur.size())
                matches.add(s);
            sb.delete(0, sb.length());
        }
        return matches;
    }
   
    
    public static ArrayList<Character> separate(String s){
        ArrayList<Character> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            list.add(s.charAt(i));
        }
        return list;
    }
    
    
}
