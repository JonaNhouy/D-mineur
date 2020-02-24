package Demineur;

import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class BarreAction extends JPanel implements ActionListener {
	
	private static final int MARCHER       = 1;
    private static final int MARQUER_DOUTE = 2;
    private static final int MARQUER_MINE  = 3;

    private int action;  // action choisie
 
    private JRadioButton boutonMarcher;
    private JRadioButton boutonDoute;
    private JRadioButton boutonMine;
 
    private static final String cheminBase = "images/";
    
	
    
	public BarreAction(){
		ButtonGroup Barre = new ButtonGroup();
		boutonMarcher = new JRadioButton(new ImageIcon(cheminBase + "marcher_off.png"));
		boutonDoute = new JRadioButton(new ImageIcon(cheminBase + "marquer_doute_off.png"));
		boutonMine = new JRadioButton(new ImageIcon(cheminBase + "marquer_mine_off.png"));
		
		boutonMarcher.setSelectedIcon(new ImageIcon(cheminBase + "marcher_on.png"));
		boutonDoute.setSelectedIcon(new ImageIcon(cheminBase + "marquer_doute_on.png"));
		boutonMine.setSelectedIcon(new ImageIcon(cheminBase + "marquer_mine_on.png"));
		
		boutonMarcher.setRolloverIcon(new ImageIcon(cheminBase + "marcher_roll.png"));
		boutonDoute.setRolloverIcon(new ImageIcon(cheminBase + "marquer_doute_roll.png"));
		boutonMine.setRolloverIcon(new ImageIcon(cheminBase + "marquer_mine_roll.png"));
		
		boutonMarcher.addActionListener(this);
		boutonDoute.addActionListener(this);
		boutonMine.addActionListener(this);
		
		Barre.add(boutonMarcher);
		Barre.add(boutonDoute);
		Barre.add(boutonMine);
		
		this.setLayout(new FlowLayout());
		this.add(boutonMarcher);
		this.add(boutonDoute);
		this.add(boutonMine);
		
		action = MARCHER;
		setVisible(true);
	}//BarreAction()
	
	public boolean estActionMarcher(){
		return (action == MARCHER);
	}//estActionMarcher()
	
	public boolean estActionMarquerDoute(){
			return (action == MARQUER_DOUTE);
	}//estActionMarquerDoute()
	
	public boolean estActionMarquerMine(){
			return (action == MARQUER_MINE);
	}//estActionMarquerMine()

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == boutonMarcher)
			action=MARCHER;
		else if(e.getSource() == boutonDoute)
			action=MARQUER_DOUTE;
		else if(e.getSource() == boutonMine)
				action=MARQUER_MINE;
		
	}//actionPerformed()

	public static void main(String[] args){
		
		JFrame Fenetre = new JFrame ("Démineur");
		BarreAction Outils = new BarreAction();
    	Fenetre.setLayout(new FlowLayout());
    	Fenetre.add(Outils);
    	Fenetre.setSize(300,400);
    	Fenetre.setVisible(true);
    	
    }//main()

}//BarreAction