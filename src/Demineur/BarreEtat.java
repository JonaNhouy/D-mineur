package Demineur;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class BarreEtat extends JLabel{
	
	public BarreEtat(){
		setPartieEnCours();
		setHorizontalAlignment(CENTER);
		setIconTextGap(30);
    	setBorder(new EmptyBorder(10, 10, 10, 10));
	}
	
	private static final String cheminBase = "images/";

    private static final ImageIcon iconePartieEnCours = 
            new ImageIcon(cheminBase + "partie_en-cours.png");
   
    private static final ImageIcon iconePartieGagnee  = 
            new ImageIcon(cheminBase + "partie_gagnee.png");
   
    private static final ImageIcon iconePartiePerdue  = 
            new ImageIcon(cheminBase + "partie_perdue.png");
    
    public void setPartieEnCours(){
    	setIcon(iconePartieEnCours);
    	setText("Jouez...");
    }//setPartieEnCours()
    
    public void setPartieGagnee(){
    	setIcon(iconePartieGagnee);
    	setText("Gagné !");
    }//setPartieGagnee()
    
    public void setPartiePerdue(){
    	setIcon(iconePartiePerdue);
    	setText("Perdu !");
    }//setPartiePerdue()
    
    public static void main(String[] args){
    	
    	JFrame Fenetre = new JFrame ("Démineur");
    	Fenetre.setLayout(new GridLayout(0,1));
    	BarreEtat EC = new BarreEtat();
    	EC.setPartieEnCours();
    	Fenetre.add(EC);
    	BarreEtat PG = new BarreEtat();
    	PG.setPartieGagnee();
    	Fenetre.add(PG);
    	BarreEtat PP = new BarreEtat();
    	PP.setPartiePerdue();
    	Fenetre.add(PP);
    	Fenetre.pack();
    	Fenetre.setVisible(true);
    }
}
