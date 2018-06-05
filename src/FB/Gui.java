package FB;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*L'objective de cette classe est de transformer la logique du jeu en une jeu jouable avec une interface graphique, pour cela
on a besoin d'une fenetre ou se deroule le jeu, des onglets representant les actions de chaque joueur,
des boutons definissants les actions de chaque joueur (boutons qui change d'onglet a la fin de chaque tour d'un joueur), des
petites images importees representants les joueurs, elements, case heliport etc ... des classes qui implements ActionListener
et qui permettront le changement visible de la fenetre lorsqu'on clique sur un bouton, implementer la fonction paint pour avoir un changement visuel */
public class Gui extends JFrame{
	JTabbedPane tab;
	Box buttonBox;
	JPanel p1;
	JPanel p2;
	JLabel label;
	public static void main(String[] args) {
		new Gui().gui();
	}
	
	public void gui () {
		JFrame frame = new JFrame("Forbidden Island");
		tab = new JTabbedPane();
		buttonBox = new Box(BoxLayout.X_AXIS);
		buttonBox.add(new JLabel("Actions: "));
		JPanel Move = new JPanel(new FlowLayout());

		label = new JLabel("Waiting for turn");

		p1 = new JPanel();
		p2 = new JPanel();
		//Start doit etre remplace une fois qu'on sait comment afficher le jeu, cest juste un filler en ce moment
		JButton start = new JButton("Start");
		
		JButton up = new JButton("Up");
		up.addActionListener(new UpListener());
		Move.add(up);

		JButton down = new JButton("Down");
		down.addActionListener(new DownListener());
		Move.add(down);

		JButton left = new JButton("Left");
		left.addActionListener(new LeftListener());
		Move.add(left);

		JButton right = new JButton("Right"); 
		right.addActionListener(new RightListener());
		Move.add(right);
		buttonBox.add(Move);

		JButton dry = new JButton("Dry Target");
		dry.addActionListener(new DryListener());
		buttonBox.add(dry);

		JButton treasure = new JButton("Get Artifact");
		treasure.addActionListener(new ArtifactListener());
		buttonBox.add(treasure);

		p1.add(buttonBox);
		p2.add(label);

		tab.addTab("Player 1",p1);
		tab.addTab("Player 2",p2);

		frame.getContentPane().add(start);
		frame.getContentPane().add(BorderLayout.SOUTH, tab);

		frame.setSize(600,600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
	}
	
	//classes qui implementent ce qu'on fait lorsqu'on clique un bouton, appelle la classe controlleur 
	//qui a son tour appelle la classe joueur (ou game?) pour effectuer l'action necessaire	
	
	class UpListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Up
		public void actionPerformed(ActionEvent e) {}
	}
	class DownListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Down
		public void actionPerformed(ActionEvent e) {}
	}
	class LeftListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Left
		public void actionPerformed(ActionEvent e) {}
	}
	class RightListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Right
		public void actionPerformed(ActionEvent e) {}
	}
	class DryListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Dry
		public void actionPerformed(ActionEvent e) {}
	}	
	class ArtifactListener implements ActionListener {
		//Changement a effectuer lorsqu'on clique le bouton Get Artifact
		public void actionPerformed(ActionEvent e) {}
	}	

	//fonction qui change l'onglet du joueur automatiquement a la fin d'un tour
	public void switchTabs (int i) {
		tab.setSelectedIndex(i);
	}	


}
