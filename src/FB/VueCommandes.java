package FB;

import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;

class VueCommandes extends JPanel {
	  
    private CModele modele;
    private JPanel moveButtons;
    private JPanel dryButtons;

    public VueCommandes(CModele modele) {
	this.modele = modele;
	
	Icon Up = new ImageIcon("up.jpg");
	Icon Down = new ImageIcon("down.jpg");
	Icon Left = new ImageIcon("left.jpg");
	Icon Right = new ImageIcon("right.jpg");
	Icon dryUp = new ImageIcon("DryUp.jpg");
	Icon dryDown = new ImageIcon("DryDown.jpg");
	Icon dryLeft = new ImageIcon("DryLeft.jpg");
	Icon dryRight = new ImageIcon("DryRight.jpg");
	Icon dryHere = new ImageIcon("DryHere.jpg");

	 
	JButton finDeTour = new JButton("END");
	JButton dry = new JButton("DRY");
	JButton up = new JButton(Up);
	JButton down = new JButton(Down);
	JButton left = new JButton(Left);
	JButton right = new JButton(Right);
//	JButton key = new JButton("Key"); //NON UTILISEE

	JButton artifact = new JButton("Artifact");
	JButton here = new JButton(dryHere);
	JButton H = new JButton(dryUp);
	JButton B = new JButton(dryDown);
	JButton D = new JButton(dryRight);
	JButton G = new JButton(dryLeft);
	JButton GG = new JButton("GG");
	
	JButton UN = new JButton("1");
	JButton DEUX = new JButton("2");
	JButton TROIS = new JButton("3");
	JButton QUATRE = new JButton("4");
	JButton CINQ = new JButton("5");
	JButton SIX = new JButton("6");
	
	JButton AIR = new JButton("AIR");
	JButton EAU = new JButton("EAU");
	JButton FEU = new JButton("FEU");
	JButton TERRE = new JButton("TERRE");
	
	//Mouvements
	moveButtons = new JPanel(new BorderLayout());
	dryButtons = new JPanel(new BorderLayout());

	moveButtons.add(up, BorderLayout.NORTH);
	moveButtons.add(down, BorderLayout.CENTER);
	moveButtons.add(left, BorderLayout.WEST);
	moveButtons.add(right, BorderLayout.EAST);

	this.add(moveButtons);

	dryButtons.add(H, BorderLayout.NORTH);
	dryButtons.add(B, BorderLayout.SOUTH);
	dryButtons.add(here, BorderLayout.CENTER);
	dryButtons.add(D, BorderLayout.EAST);
	dryButtons.add(G, BorderLayout.WEST);

	
	this.add(dryButtons);
	
	//Actions
	//this.add(dry);
	this.add(finDeTour);
	//this.add(key);
	this.add(artifact);
	this.add(GG);
	
	//Players
	this.add(UN);
	this.add(DEUX);
	this.add(TROIS);
	this.add(QUATRE);
	this.add(CINQ);
	this.add(SIX);
	
	//Elements
	this.add(AIR);
	this.add(EAU);
	this.add(FEU);
	this.add(TERRE);
	
	Controleur ctrl = new Controleur(modele);
	UpListener haut = new UpListener(modele);
	up haut1 = new up(modele);
	LeftListener gauche = new LeftListener(modele);
	left l = new left(modele);
	RightListener droite = new RightListener(modele);
	right r = new right(modele);
	DownListener bas = new DownListener(modele);
	down d = new down(modele);
	//DryListener assecher = new DryListener(modele);
	ArtifactListener recupArtefact = new ArtifactListener(modele);
	gg gg = new gg(modele);
	
	UN un = new UN(modele);
	DEUX deux = new DEUX(modele);
	TROIS trois = new TROIS(modele);
	QUATRE quatre = new QUATRE(modele);
	CINQ cinq = new CINQ(modele);
	SIX six = new SIX(modele);
	
	AIR air = new AIR(modele);
	EAU eau = new EAU(modele);
	FEU feu = new FEU(modele);
	TERRE terre = new TERRE(modele);
	
	finDeTour.addActionListener(ctrl);
	up.addActionListener(haut);
	left.addActionListener(gauche);
	right.addActionListener(droite);
    down.addActionListener(bas);
    here.addActionListener(new HereListener(modele));
    //dry.addActionListener(assecher);
    H.addActionListener(haut1);
    B.addActionListener(d);
    D.addActionListener(r);
    G.addActionListener(l);
    artifact.addActionListener(recupArtefact);
    GG.addActionListener(gg);
    
    UN.addActionListener(un);
    DEUX.addActionListener(deux);
    TROIS.addActionListener(trois);
    QUATRE.addActionListener(quatre);
    CINQ.addActionListener(cinq);
    SIX.addActionListener(six);
    
    AIR.addActionListener(air);
    EAU.addActionListener(eau);
    FEU.addActionListener(feu);
    TERRE.addActionListener(terre);
    }
}
