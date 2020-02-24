package Demineur;

import javax.swing.*;

import java.awt.*;
import  java.util.Random;

public class ChampMine extends JPanel{
	
	private int[][] grille;        // grille du jeu (partie non visible)
	private int taille;
	private int nbMines;
	private final int VIDE = -1;
	private final int MINE = -2;
	
	private int nbCasesDecouvertes;
	
	private ImageIcon iconeCaseNormale;
	private ImageIcon iconeMarqueDoute;   // icone d'une case marquee douteuse
	private ImageIcon iconeMarqueMine;    // icone d'une case marquee comme minee
	private ImageIcon iconeCaseMinee;     // icone d'une case contenant une mine
	private ImageIcon iconeCaseExplosee;  // icone d'une mine ayant explose
	
	private ImageIcon [] iconesChiffres;  // icones des cases découvertes
	
	private ControleurJeu controleur;   // le controleur du jeu: écouteur des boutons
	
	private JButton[][] boutons;   // boutons du jeu (partie affichée)
	
	private static final String cheminBase = "images/";
	
	
	
	private ChampMine(int taille, int nbMines){
		super();
		this.taille=taille;
		this.nbMines=nbMines;
		creerGrille();
	}//champMine()
	
	public ChampMine(int taille, int nbMines, BarreAction barreAction, BarreEtat barreEtat){
		super();
		this.taille = taille;
		this.nbMines = nbMines;
		nbCasesDecouvertes = 0;
		setLayout (new GridLayout(taille, taille));
		controleur = new ControleurJeu(barreAction, barreEtat, this);
		creerGrille();
		mettreAJourVoisinage();
		chargerIcones();
		creerBoutons();
	}//ChampMine2()
	
	private void creerGrille(){
		grille = new int [taille][taille];
		for(int i=0 ; i < taille ; ++i)
			for(int j=0 ; j < taille ; ++j)
				grille[i][j]=VIDE;
		placerMines();
	}//creerGrille()
	
	private void placerMines(){
		Random generateur = new Random(System.currentTimeMillis());
		int nbMinesRest = nbMines;
		while(nbMinesRest>0){
			int j = generateur.nextInt(taille);
			int i = generateur.nextInt(taille);
			if(grille[i][j] != MINE){
				grille[i][j]= MINE;
				--nbMinesRest;
			}
		}
	}//placerMines()
	
	private void ecrireGrille(){
		for(int i=0 ; i < taille ; ++i){
			for(int j=0 ; j < taille ; ++j)
				System.out.print(grille[i][j]);
			System.out.println();
		}
			
	}//ecrireGrille()
	
	private int nbMinesVoisines(int i, int j){
		 int NbMine = 0;
		 
		 for(int x = i-1; x<=i+1; ++x){
				for(int y = j-1; y<=j+1; ++y){
					if(y >= 0 && y < taille && x >= 0 && x < taille && grille[x][y] == MINE)
							++NbMine;
				}
			}
		 return NbMine;
	 } // nbMinesVoisines()

	private void mettreAJourVoisinage(){
		 for(int i = 0; i<taille; ++i){
				for(int j = 0; j<taille; ++j){
					if(grille[i][j] != MINE)
						grille[i][j] = nbMinesVoisines(i,j);
				}
			}
	 } // mettreAJourVoisinage()
	 
	private void ecrireGrilleVoisinage(){
		for (int i = 0 ; i < taille ; ++i)
		{
			for (int j = 0 ; j < taille ; ++j)
			{
				System.out.print( (grille[i][j] != -2) ? grille[i][j] : "X");
					
				
			}
			System.out.println();
		}
	 }//ecrireGrilleVoisinage()
	 
	public boolean aGagne(){
		 return (nbCasesDecouvertes + nbMines == taille*taille);
	 }//aGagne()
	 
	public boolean estUneMine(int i, int j){
		return (grille[i][j] == MINE);
	 }//estUneMine()
	
	private void chargerIcones(){
		
		iconeCaseNormale = new ImageIcon (cheminBase + "case_normale.png");
		iconeMarqueDoute = new ImageIcon (cheminBase + "case_marquee_douteuse.png");  
		iconeMarqueMine = new ImageIcon (cheminBase + "case_marquee_minee.png");
		iconeCaseMinee = new ImageIcon (cheminBase + "case_mine.png");
		iconeCaseExplosee = new ImageIcon (cheminBase + "case_mine_explosee.png");
		
		 iconesChiffres = new ImageIcon [9];
		
		for (int i = 0 ; i < 9 ; i++) {
			iconesChiffres[i] = new ImageIcon (cheminBase + "case_" + i + ".png");
        }
	}//chargerIncones()
	
	private void creerBoutons(){
		boutons = new JButton[taille][taille];
		for (int i = 0 ; i < taille-1 ; ++i)
			for (int j = 0 ; j < taille-1 ; ++j)
			{
				boutons[i][j] = new JButton (iconeCaseNormale);
				boutons[i][j].setBorderPainted(false);
				boutons[i][j].setPreferredSize(new Dimension (iconeCaseNormale.getIconWidth(), iconeCaseNormale.getIconHeight()));
				boutons[i][j].setActionCommand(i+" "+j);
				boutons[i][j].addActionListener(controleur);
				this.add(boutons[i][j]);
			}
	}//creerBoutons()
	
	public int getLigneBouton(String commande){
		return Integer.parseInt(commande.substring(0, commande.indexOf(" ")));
	}//getLigneBouton()
	
	public int getColonneBouton(String commande){
		return Integer.parseInt(commande.substring(commande.indexOf(" ")+1));
	}//getColonneBouton()

	public void setMarqueeDoute (int i, int j){
		boutons[i][j].setIcon(iconeMarqueDoute);
	}//setMarqueeDoute()
	
	public void setMarqueeMine(int i, int j){
		boutons[i][j].setIcon(iconeMarqueMine);
	}//setMarqueeMine()
	
	public void setExplosee(int i, int j){
		boutons[i][j].setEnabled(false);
		boutons[i][j].setDisabledIcon(iconeCaseExplosee);
	}//setExplosee()
	
	public void setDecouverte(int i, int j){
		boutons[i][j].setEnabled(false);
		boutons[i][j].setDisabledIcon(iconesChiffres[grille[i][j]]);
		if (grille[i][j]==0) {
			int haut=(j!=0)?1:0;
			int bas=(j!=taille-1)?1:0;
			int gauche=(i!=0)?1:0;
			int droite=(i!=taille-1)?1:0;
			for (int k=i-gauche ; k<=i+droite; k++)
				for (int l=j-haut ; l<=j+bas ; l++)
					if (boutons[k][l].isEnabled())
						setDecouverte(k,l);
		}
		++nbCasesDecouvertes;
		
	}//setDecouverte()
	
	public void decouvrirGrille(){
		for(int i = 0; i<taille-1; ++i)
			for(int j = 0; j<taille-1; ++j)
				if(boutons[i][j].isEnabled()){
					boutons[i][j].setEnabled(false);
					if (grille[i][j] != MINE)
						boutons[i][j].setDisabledIcon(iconeCaseNormale);
					else
						boutons[i][j].setDisabledIcon(iconeCaseMinee);
				}
	}//decouvrirGrille()

	
	
	public static void main(String[] args){
		
		ChampMine champ = new ChampMine(10, 15);
		champ.mettreAJourVoisinage();
		champ.ecrireGrilleVoisinage();
		
    	
    }//main()
	
}
