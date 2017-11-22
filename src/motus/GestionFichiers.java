/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class GestionFichiers {
 
    FileReader fr;
    BufferedReader br;
 
    public void ouvrir(String nom){
        try{
            File f = new File (nom);
            fr = new FileReader (f);
            br = new BufferedReader (fr);
        }
        catch (FileNotFoundException e){
            System.out.println ("Le fichier n'a pas été trouvé : " + e.getMessage());
        }
    }
    
    
    public String lire() throws IOException{
        String text = "";
        try{
            String line = br.readLine();
            while (line != null){
                //System.out.println(line);
                text = text.concat(line.concat("\n"));
                line = br.readLine(); 
            }   
        }
        catch (IOException exception){
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
        return text;
    }
    
    public String lire(int i) throws IOException{
        String text = "";
        try{
            String line = null;
            int pointer = 0;
            while (line != null && pointer < i){
                //System.out.println(line);
                line = br.readLine(); 
                pointer ++;
            }  
            text = line;
        }
        catch (IOException exception){
            System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
        }
        return text;
    }
    
    public void fermer() throws IOException{
        try{
            br.close();
            fr.close();
        }catch (IOException exception){
            System.out.println ("Erreur lors de la fermeture : " + exception.getMessage());
        }
    }
    
}