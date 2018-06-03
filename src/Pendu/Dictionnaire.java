/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pendu;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author KREATURE
 */
public class Dictionnaire {

    private ArrayList<String> dico;

    /**
     *Remplie le dictionnaire avec la lite de mot "liste_francais.csv"
     * @throws FileNotFoundException
     */
    public Dictionnaire() throws FileNotFoundException {
        dico = new ArrayList<>();

        Scanner sc;
        try {
            File f = new File("liste_francais.csv");
            sc = new Scanner(f);
            String ligne;
            while (sc.hasNextLine()) {
                ligne = sc.nextLine();
                dico.add(ligne);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public String toString() {
        if (dico.isEmpty()) {
            return "le dictionnaire est vide\n";
        }
        return dico.toString();
    }

    /**
     *Teste si le mot contient les caracteres speciaux espace et tiret et si le mot contient plus de 4 lettres 
     * @param mot Mot a tester pour l'utiliser comme mot a deviner dans le pendu
     * @return true si le mot est conforme
     */
    public boolean motEstValide(String mot) {
        mot = mot.toUpperCase();
       if(mot.contains(" ") || mot.contains("-")){
           return false;
       }
        if (mot.length() > 4) {
            return true;
        }
        return false;
    }
    
    /**
     *Remplace les caracteres speciaux d'un mot avec les caracteres [A,z]
     * @param mot
     * @return le mot sans accent
     */
    public String removeAccent(String mot) {
        return Normalizer.normalize(mot, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
        //Normalizer.normalize(source, Normalizer.Form.NFD) renvoi une chaine unicode décomposé. Les caractères accentués sont décomposés en deux caractères (par exemple "à" se transforme en "a`").
        //replaceAll("[\u0300-\u036F]", "") supprime tous les caractères unicode allant de u0300 à u036F.
    }
    
    /**
     *Cherche un mot dans le dictionnaire aleatoirement
     * @return un mot conforme, en majuscule et sans accent
     */
    public String motAleatoire() {
        Random r = new Random();
        String motAleatoire = dico.get(r.nextInt(dico.size()));
        motAleatoire =removeAccent(motAleatoire);
        motAleatoire = motAleatoire.toUpperCase();
        if (motEstValide(motAleatoire)){
            return motAleatoire;
        }
        return motAleatoire();
    }
}
