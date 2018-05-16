/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendu;

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
    

    public String removeAccent(String source) {
        return Normalizer.normalize(source, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
    }
    

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
