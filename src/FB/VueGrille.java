package FB;

import java.awt.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class VueGrille extends JPanel implements Observer {
	private CModele modele;
	private final static int TAILLE = 60;

	public VueGrille(CModele modele) {
		this.modele = modele;
		modele.addObserver(this);
		Dimension dim = new Dimension(TAILLE * this.modele.getIle().getWidth(), TAILLE * this.modele.getIle().height());
		this.setPreferredSize(dim);
	}

	// Initialisation des images
	private BufferedImage AIR = null;
	private BufferedImage FEU = null;
	private BufferedImage EAU = null;
	private BufferedImage TERRE = null;
	private BufferedImage He = null;

	public void update() {
		repaint();
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public void paintComponent(Graphics g) {
		super.repaint();

		// Pour recupere les images pour les artifacts
		try {
			FEU = ImageIO.read(new File("feu.jpg"));
		} catch (IOException exc) {
			System.out.println("no file found");
			// TODO: Handle exception.
		}
		try {
			EAU = ImageIO.read(new File("eau.jpg"));
		} catch (IOException exc) {
			System.out.println("no file found");
			// TODO: Handle exception.
		}
		try {
			TERRE = ImageIO.read(new File("terre.jpg"));
		} catch (IOException exc) {
			System.out.println("no file found");
			// TODO: Handle exception.
		}
		try {
			AIR = ImageIO.read(new File("air.jpg"));
		} catch (IOException exc) {
			System.out.println("no file found");
			// TODO: Handle exception.
		}
		try {
			He = ImageIO.read(new File("H.jpg"));
		} catch (IOException exc) {
			System.out.println("no file found");
			// TODO: Handle exception.
		}

		BufferedImage f = resize(FEU, TAILLE, TAILLE);
		BufferedImage e = resize(EAU, TAILLE, TAILLE);
		BufferedImage t = resize(TERRE, TAILLE, TAILLE);
		BufferedImage a = resize(AIR, TAILLE, TAILLE);
		BufferedImage h = resize(He, TAILLE, TAILLE);

		for (int i = 0; i < this.modele.getIle().getWidth(); i++) {
			for (int j = 0; j < this.modele.getIle().height(); j++) {
				paint(g, modele.getZone(i, j), (i) * TAILLE, (j) * TAILLE);
				if (modele.getIle().id(i, j) == this.modele.getIle().indiceDeH()) {
					g.drawImage(h, i * TAILLE, j * TAILLE, this);
				}
				for (Joueur jou : this.modele.getJoueurs()) {
					if (jou.getZone() == this.modele.getZone(i, j)) {
						g.setColor(Color.RED);
						paintJoueur(g, modele.getJoueur(modele.getZone(i, j)), i * TAILLE, j * TAILLE);
					}
				}
				for (Artifact art : this.modele.getJeu().getArtifacts()) {
					if (!art.isGot()) {
						if (art.getZone() == this.modele.getZone(i, j)) {
							if (art.getElement() == Elements.Air) {
								g.drawImage(a, i * TAILLE, j * TAILLE, this);
							} else if (art.getElement() == Elements.Eau) {
								g.drawImage(e, i * TAILLE, j * TAILLE, this);
							} else if (art.getElement() == Elements.Feu) {
								g.drawImage(f, i * TAILLE, j * TAILLE, this);
							} else if (art.getElement() == Elements.Terre) {
								g.drawImage(t, i * TAILLE, j * TAILLE, this);
							}
						}
					}
				}
			}
		}
	}

	private void paint(Graphics g, Zone z, int x, int y) {
		if (z.getState() == State.NORMAL) {
			g.setColor(Color.WHITE);
		} else if (z.getState() == State.DROWNING) {
			g.setColor(Color.BLUE);
		} else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(x, y, TAILLE, TAILLE);
	}

	//Dessin du joueur
	private void paintJoueur(Graphics g, Joueur j, int x, int y) {
		// g.setColor(Color.RED);
		g.fillRect(x, y, TAILLE, TAILLE);
	}
	

	//DEssin de l'artifact
	private void paintArtifact(Graphics g, Artifact a, int x, int y) {
		// g.setColor(Color.RED);
		g.fillRect(x, y, TAILLE, TAILLE);
	}

}
