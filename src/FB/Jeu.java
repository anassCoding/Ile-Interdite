package FB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Random;

public class Jeu extends Observable{
	
	private Ile ile;
	private Joueur[] Joueurs;
	private Artifact[] artifacts; 
	private int currentPlayer;
	private int numberOfPlayers;
	public Jeu(Ile ile) {
		this.ile = ile;
		this.Joueurs = new Joueur[4];
		this.artifacts = new Artifact[4];
		init();
	}
	
	/**
	 * Initialise les joueurs et les artefacts de l'ile
	 */
	public void init() {
		for(int i = 0; i<this.Joueurs.length; i++) {
			this.Joueurs[i] = new Joueur(this, Integer.toString(i));
		}

		this.artifacts[0] = new Artifact(Elements.Air, this.ile.randomZone());
		this.artifacts[1] = new Artifact(Elements.Eau, this.ile.randomZone());
		this.artifacts[2] = new Artifact(Elements.Feu, this.ile.randomZone());
		this.artifacts[3] = new Artifact(Elements.Terre, this.ile.randomZone());
		//Force les 4 artifacts a  sur des zones differentes
		for(int i = 0; i<4; i++) {
			for(int j = 0; j<4; j++) {
				if(this.artifacts[i].getZone() == this.artifacts[j].getZone()) {
					this.artifacts[j].newZone(this.ile.randomZone());
				}
			}
		}
		
		this.currentPlayer = 0;
	}
	
	public Jeu(Ile ile, int number) {
		this.ile = ile;
		this.Joueurs = new Joueur[4];
		this.artifacts = new Artifact[4];
		this.numberOfPlayers = number;
		this.Joueurs = new Joueur[this.numberOfPlayers];
		init();
	}
	
	/**
	 *  POUR LE CONTROLEUR ET LA VUE
	 * @return L'ile 
	 */
	public Ile getIle() {
		return this.ile;
	}
	
	/**
	 * 
	 * @return Le tableau de joueurs (POUR LE CONTROLLEUR)
	 */
	public Joueur[] getJoueurs() {
		return this.Joueurs;
	}
	
	/**
	 *  Determine si la partie est gagnee
	 * @return False si tous les artefacts n ont pas ete pris
	 * @return True si les 4 ont ete recuperes
	 */
	public Boolean isWon() {
		for(Artifact A : this.artifacts) {
			if(A.isGot()) {
				return true;
			}
		}
		for(Joueur j : this.Joueurs) {
			if(j.getZone() == this.ile.zones()[this.ile.indiceDeH()]) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  Determine si la partie est perdue
	 * @return true si l'une des conditions pour la defaite est respectee
	 */
	
	public Boolean isLost() {
		for(Joueur j : this.Joueurs) {
			if(!j.isAlive()) {
				CVue.updateArea("Un joueur est mort");
				return true;
			}
		}if(this.ile.zones()[this.ile.indiceDeH()].getState() == State.DROWN) {
			CVue.updateArea("Heliport innonde");
			return true;
		}
		for(Artifact art : this.artifacts) {
			if(art.getZone().getState() == State.DROWN) {
				CVue.updateArea("Un artefact a sombre!");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Tue un joueur s'il est situe sur une zone innondee
	 */
	public void kill() {
		for(Joueur j : this.Joueurs) {
			for(Zone z : this.ile.zones()) {
				if(j.getZone() == z && z.getState() == State.DROWN) {
					j.die();
				}
			}
		}
	}
	
	/**
	 * 
	 * @return le joueur courrant en numero
	 */
	public int getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	/**
	 * Affiche l'ile et les joueurs NON UTILISEE
	 */
	public String toString() {
		String res = "";
		for(Joueur j : this.Joueurs) {
			res+= j.toString();
		}
	//	res+= " " + "Ile : " + this.ile.toString();
		return res;
	}
	
	/**
	 * Action enregistree du joueur courrant
	 */
	
	public void actionPerformed() {
		this.Joueurs[this.currentPlayer].actionPerformed();
	}
	
	/**
	 * Effectue toutes les actions de fin de tour : innondation des 3 zones, don de cle aleatoire au joueur courrant, etc...
	 */
	
	public void finDeTour() {
		this.Joueurs[this.currentPlayer].reinit();
		CVue.area.setText("");
		//Moins de 10% de chances que le joueur Obtienne une et s'il ne possede pas une cle du meme element
		if((Math.random()<1.0)) {
			//if((Math.random()<0.1)) {
			Keys k = new Keys();
		
			if(this.Joueurs[this.currentPlayer].numberOfKeys()<100 ) {
					this.Joueurs[this.currentPlayer].obtainKey(k);
					CVue.updateArea(this.currentPlayer + " : " +"Le destin vous offre une cle de type :" + k.getElement());

					
				}
		}
		if(this.isLost()) {	CVue.updateArea("PARTIE PERDUE");}
		if(this.isWon()) {CVue.updateArea("PARTIE GAGNEE");}
		this.kill();
		this.currentPlayer++;
		if(this.currentPlayer >= this.Joueurs.length) {
			this.currentPlayer = 0;
		}
	}
	
	/**
	 * 
	 * @return tous les artifacts de l'ile
	 */
	public Artifact[] getArtifacts() {
		return this.artifacts;
	}
	
}





