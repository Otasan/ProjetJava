/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BatailleNavale;
import java.util.TreeSet;
import java.util.HashSet;
/**
 *
 * @author aabdo
 */
public class GrilleBN {
    private TreeSet<CaseBN> lesCases;
    private HashSet<Bateau> lesBateaux;
    
    /**
     * crée une grille de 10*10 cases vierges
     */
    public GrilleBN(){
        lesBateaux=new HashSet();
        lesCases=new TreeSet();
        for(int i=0;i<100;i++){
            lesCases.add(new CaseBN(i%10,(int)i/10));
        }
    }
    
    /**
     * place un bateau du type typeBateau dont la case la plus en haut ou à gauche est cI et dont la direction est s
 lance des BNException si une des cases occupées par le nouveau bateau est déjà occupée ou en dehors de la grille
     * @param typeBateau string du nom de classe du bateau (ne respecte pas la case)
     * @param cI case la plus en haut ou à gauche du bateau
     * @param d direction du bateau
     * @throws BNException 
     */
    public void placerBateau(String typeBateau, CaseBN cI, Direction d)throws BNException{
        Bateau b;
        if(lesCases.ceiling(cI).getCase()==TypeCase.bateau){
            throw new BNException("Bateaux en collision : "+lesCases.ceiling(cI));
        }
        else{
            lesCases.ceiling(cI).setCase(TypeCase.bateau);
        }
        if(cI.getX()<10 && cI.getY()<10){
            typeBateau=typeBateau.toLowerCase();
            switch(typeBateau){
                case "contretorpilleur":
                    b=new ContreTorpilleur(cI,d);
                    break;
                case "croiseur":
                    b=new Croiseur(cI,d);
                    break;
                case "porteavion":
                    b=new PorteAvion(cI,d);
                    break;
                case "sousmarin":
                    b=new SousMarin(cI,d);
                    break;
                case "torpilleur":
                    b=new Torpilleur(cI,d);
                    break;
                default:
                    throw new BNException("Bateau type : "+typeBateau+" n'existe pas");
            }
            //System.out.println(b+"\n"+lesCases);
            if(lesBateaux.contains(b)){
                throw new BNException("Bateau déjà existant sur cete grille : "+typeBateau+" : "+lesBateaux.toString());
            }
            else{
                switch(d){
                    case horizontal:
                        if(cI.getX()+b.getTaille()>10){
                            throw new BNException("Bateau hors de la grille "+cI.toString()+" taille : "+b.getTaille()+" direction : "+d);
                        }
                        else{
                            for(int i=cI.getX()+1;i<cI.getX()+b.getTaille();i++){
                                if(lesCases.ceiling(new CaseBN(i,cI.getY())).getCase()==TypeCase.bateau){
                                    for(int o=i-1; o>=cI.getX();o--){//hey
                                        lesCases.ceiling(new CaseBN(o,cI.getY())).setCase(TypeCase.vierge);
                                    }
                                    throw new BNException("Bateaux en collision : "+lesCases.ceiling(new CaseBN(i,cI.getY())).toString());
                                }
                                else{
                                    lesCases.ceiling(new CaseBN(i,cI.getY())).setCase(TypeCase.bateau);
                                }
                            }
                        }
                        break;
                    case vertical:
                        if(cI.getY()+b.getTaille()>10){
                            throw new BNException("Bateau hors de la grille "+cI.toString()+" taille : "+b.getTaille()+" direction : "+d);
                        }
                        else{
                            for(int i=cI.getY()+1;i<cI.getY()+b.getTaille();i++){
                                if(lesCases.ceiling(new CaseBN(cI.getX(),i)).getCase()==TypeCase.bateau){
                                    for(int o=i-1; o>=cI.getY();o--){
                                        lesCases.ceiling(new CaseBN(cI.getX(),o)).setCase(TypeCase.vierge);
                                    }
                                    throw new BNException("Bateaux en collision : "+lesCases.ceiling(new CaseBN(cI.getX(),i)).toString());
                                }
                                else{
                                    lesCases.ceiling(new CaseBN(cI.getX(),i)).setCase(TypeCase.bateau);
                                }
                            }
                        }
                        break;
                }
                lesBateaux.add(b);
            }
        }
        else{
            throw new BNException("Case externe à la grille:"+cI.toString());
        }
        
    }
    
    /**
     * "touche" la case [x;y] et vérifie si un bateau à été coulé.
     * Lance une BNException si la case est déjà touchée
     * @param x
     * @param y
     * @throws BNException 
     */
    public void tire(int x, int y) throws BNException{
        if(lesCases.ceiling(new CaseBN(x, y)).touche()==TypeCase.touche){
            chkBateauCoule();
        }
    }
    
    /**
     * "touche" une case à la même position que c et vérifie si un bateau à été coulé.
     * Lance une BNException si la case est déjà touchée
     * @param c
     * @throws BNException 
     */
    public void tire(CaseBN c) throws BNException{
        if(lesCases.ceiling(c).touche()==TypeCase.touche){
            chkBateauCoule();
        }
    }
    
    /**
     * vérifie si UN bateau est coulé et le retire de la hashset lesBateaux
     */
    public void chkBateauCoule(){
        Bateau bat = null;
        for(Bateau b : lesBateaux){
            int nbTouche=0;
            switch(b.getSens()){
                case horizontal:
                    for(int i=b.getCaseInitiale().getX();i<b.getTaille()+b.getCaseInitiale().getX();i++){
                        if(lesCases.ceiling(new CaseBN(i, b.getCaseInitiale().getY())).getCase()==TypeCase.touche){
                            nbTouche++;
                        }
                    }
                    break;
                case vertical:
                    for(int i=b.getCaseInitiale().getY();i<b.getTaille()+b.getCaseInitiale().getY();i++){
                        if(lesCases.ceiling(new CaseBN(b.getCaseInitiale().getX(), i)).getCase()==TypeCase.touche){
                            nbTouche++;
                        }
                    }
                    break;
            }
            if(nbTouche == b.getTaille()){
                System.out.println(b);
                bat=b;
            }
        }
        if(bat != null){
            lesBateaux.remove(bat);//supprimer bateau après iteration sinon ConcurrentModificationException sera levée
        }
    }
    
    /**
     * 
     * @return le nombre de bateaux restant dans la hashset les bateaux
     */
    public int nbBateauRestant(){
        return lesBateaux.size();
    }
    
    /**
     * 
     * @return une HashSet contenant les bateaux non coulés
     */
    public HashSet<Bateau> getBateaux(){
        return lesBateaux;
    }
    
    /**
     * 
     * @return une TreeSet des cases
     */
    public TreeSet<CaseBN> getGrille(){
        return lesCases;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return la case à la position [x;y]
     */
    public CaseBN getCase(int x, int y){
        return lesCases.ceiling(new CaseBN(x,y));
    }
    
    /**
     * 
     * @param c
     * @return la case à la même position que c
     */
    public CaseBN getCase(CaseBN c){
        return lesCases.ceiling(c);
    }
}
