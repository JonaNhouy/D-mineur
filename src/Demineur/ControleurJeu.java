package Demineur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControleurJeu implements ActionListener {
	
	private BarreAction barreAction;
    private BarreEtat barreEtat; 
    private ChampMine champMine;
    
    
    
    ControleurJeu (BarreAction barreAction, BarreEtat barreEtat, ChampMine champMine) {
    	this.barreAction = barreAction;
    	this.barreEtat = barreEtat;
    	this.champMine = champMine;
    }//ControleurJeu()
    
    public void actionPerformed(ActionEvent e){
    	
    	int i = champMine.getLigneBouton(e.getActionCommand());
    	int j= champMine.getColonneBouton(e.getActionCommand());
    	
    	
    	if(barreAction.estActionMarcher()){
    		if(champMine.estUneMine(i, j)){
    			champMine.setExplosee(i, j);
    			champMine.decouvrirGrille();
    			barreEtat.setPartiePerdue();
    		}
    		else{
    			champMine.setDecouverte(i, j);
    	    	if(champMine.aGagne()){
    	    		champMine.decouvrirGrille();
    	    		barreEtat.setPartieGagnee();
    	    	}//Si tout découvert
    		}
    	}//Si bouton Marcher actif
    	
    	else if(barreAction.estActionMarquerDoute()){
	    	champMine.setMarqueeDoute(i, j);
    	}//Si bouton Marquer Doute actif
    	
    	else if(barreAction.estActionMarquerMine()){
			champMine.setMarqueeMine(i, j);
		}//Si bouton Marquer Mine actif
    	

    		
    }//actionPerformed()
}
