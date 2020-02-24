package Demineur;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FenetreDemineur extends JFrame {
	public FenetreDemineur()
	{
		super();
		BarreAction BA = new BarreAction();
		BarreEtat BE = new BarreEtat();
		ChampMine CM = new ChampMine(10,15,BA,BE);
		this.setLayout(new BorderLayout());
		this.add(BA, BorderLayout.NORTH);
		this.add(BE, BorderLayout.SOUTH);
		this.add(CM, BorderLayout.CENTER);
		setVisible(true);
		
	}
	
	public static void main (String[] args)
	{
		FenetreDemineur FD = new FenetreDemineur();
		FD.pack();
	}
}
