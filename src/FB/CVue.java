package FB;
import java.awt.*;

import javax.swing.*;

class CVue {
	private JFrame frame;
	public static JTextArea area;
	private VueGrille grille;
	private VueCommandes commandes;

	public CVue(CModele modele) {
		frame = new JFrame();
		frame.setTitle("Jeu de l'ile interdite");
		frame.setLayout(new BorderLayout());

		grille = new VueGrille(modele);
		frame.add(grille, BorderLayout.WEST);
		commandes = new VueCommandes(modele);
		frame.add(commandes, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//String text = "";
		//text += modele.getJoueurs()[modele.getJeu().getCurrentPlayer()].toString() + "\n";
		//modele.getJoueurs()[modele.getJeu().getCurrentPlayer()].toString();
	
		area = new JTextArea(modele.getJoueurs()[modele.getJeu().getCurrentPlayer()].toString() + "\n",30, 30);
		//area.setLineWrap(true);
		area.setEditable(false);

		JScrollPane scroller = new JScrollPane(area);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroller, BorderLayout.EAST);
		frame.pack();
	}
	public static void updateArea(String s) {
		area.append(s + "\n");
	}
}

