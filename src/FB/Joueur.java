package FB;

import java.util.ArrayList;
import java.util.Arrays;

public class Joueur {
	
	private Zone currentZone;
	private String name;
	public static int counter = 0;
	private ArrayList<Keys> cle;
	private Artifact[] artifacts;
	private int numberOfArtifacts;
	private int numberOfKeys;
	private Boolean isAlive;
	private Jeu game;
	private int numberOfActions;
	
	public Joueur() {
		this.currentZone = new Zone();
		this.name = "";
		this.cle = new ArrayList<>();
		this.artifacts = new Artifact[4];
		this.numberOfArtifacts = 0;
		this.numberOfKeys = 0;
		this.isAlive = true;
		counter++;
	}
	//Apparition au milieu de l'ile
	public Joueur(Jeu jeu) {
		this.currentZone = jeu.getIle().getZone(jeu.getIle().id((jeu.getIle().getWitdh())/2, (jeu.getIle().height())/2));
		this.name = Integer.toString(this.counter);
		this.cle = new ArrayList<>();
		this.artifacts = new Artifact[4];
		this.numberOfArtifacts = 0;
		this.numberOfKeys = 0;
		this.isAlive = true;
		this.game = jeu;
		this.numberOfActions = 3;
		counter++;
	}
	
	public Joueur(Jeu jeu, String nom) {
		//APPARTION DE TOUS LES JOUEURS AU MILIEU
		this.currentZone = jeu.getIle().getZone(jeu.getIle().id((jeu.getIle().getWitdh())/2, (jeu.getIle().height())/2));
		this.name = nom;
		this.cle = new ArrayList<>();
		this.artifacts = new Artifact[4];
		this.numberOfArtifacts = 0;
		this.numberOfKeys = 0;
		this.isAlive = true;
		this.game = jeu;
		this.numberOfActions = 3;
		counter++;
	}
	
	public Joueur(Zone z) {
		this.currentZone = z;
		this.name = "";
		this.artifacts = new Artifact[4];
		this.numberOfArtifacts = 0;
		this.numberOfKeys = 0;
		this.isAlive = true;
		this.game = new Jeu(new Ile(10, 10));
		counter++;
	}
	
	/**
	 * Recupere un artefact passe en parametre. Verifie que le joueur possede la cle de l element correspond a l artefact
	 * @param A
	 */
	//Test a faire
	
	public void recupereArtefact(Artifact A) {
		if(this.numberOfKeys == 0) {
			CVue.updateArea("Vous n'avez pas de cle. Il vous faut une cle du type correspondant !");
		} else {
			Boolean contains = false;
			int count = 0;
			Elements[] el = this.hasElements();
			for(int i = 0; i<el.length; i++) {
				if(el[i] == A.getElement()) {
					count++;
				} 
				if(count >= 4) {
					contains = true;
				}
			} if(contains) {
					CVue.updateArea("Vous avez une cle correspondante, vous recuperez donc l artefact");
					this.artifacts[this.numberOfArtifacts] = A;
					this.numberOfArtifacts++;
					A.getArtifact();		
					} else {
						CVue.updateArea("Il vous manque" + Integer.toString(4-count) +" cle de cet element : " + A.getElement());
					}
				}
			}
		
	/**
	 * Effectue le mouvement du joueur si la case est voisine au joueur et si elle n'est pas innondee.
	 * @param z
	 */
	
	public void move(Zone z) {
		if(z.getState() != State.DROWN) {
			this.currentZone = z;
		} 
	}
	
	/**
	 * Verifie que le joueur est en vie
	 * @return
	 */
	
	//Test fait
	public Boolean isAlive() {
		return this.isAlive;
	}
	
	/**
	 * Asseche une zone, si le joueur est dessus ou si le joueur est adjacent i.e si z est contenue dans l'arrays voisines de la casse courrante du joueur
	 * @param z
	 */
	
	//Test fait
	public void dryUp(Zone z) {
		Zone[] res = this.game.getIle().voisines(this.game.getIle().id(this.currentZone.getX(), this.currentZone.getY()));
		if(z==this.currentZone) {z.dry();}
		else if(Arrays.asList(res).contains(z)) {z.dry();}
		else {CVue.updateArea("Impossible d'assecher une zone eloignee");}
	}
	
	/**
	 * Cherche une cle
	 * @param Keys k
	 */
	
	//Test fait
	public void searchKey(Keys k) {
		if(this.currentZone == k.getZone()) {
			this.cle.add(k);
			this.numberOfKeys++;
		}
	}
	
	/**
	 * Tue le joueur
	 */
	
	//Test fait
	public void die() {
		this.isAlive = false;
	}
	
	/**
	 * Cherche la zone courrante sur laquelle se situe le joueur
	 * @return this.currentZone
	 */
	//Test fait
	public Zone getZone() {
		return this.currentZone;
	}
	
	/**
	 * 
	 * @return les cles que possede le joueur
	 */
	
	public ArrayList<Keys> keys(){
		return this.cle;
	}
	
	/**
	 * 
	 * @return l'inventaire du joueur sous forme de chaine de caracteres
	 */
	public String inventaire() {
		int countA = 0;
		int countT = 0;
		int countF = 0;
		int countE = 0;
		for(int i = 0; i<this.numberOfKeys; i++) {
			if(this.cle.get(i).getElement() == Elements.Air) {
				countA++;
			} else if(this.cle.get(i).getElement() == Elements.Eau){
				countE++;
			} else if(this.cle.get(i).getElement()  == Elements.Feu) {
				countF++;
			} else if(this.cle.get(i).getElement()  == Elements.Terre) {
				countT++;
			}
		}
		return "Nombre de cles Air : " + countA + " " + "Nombre cles Eau : " + countE + " " + "Nombre cles Feu : " + countF + " " + "Nombre cles Terre :" + countT; 
	}
	
	/**
	 * Affiche l'inventaire du joueur et sa case courrante
	 */
	public String toString() {
		return "Nom : " + this.name + "  " + "Nombre Actions : " + this.numberOfActions + "    " + " Nombre artefacts : " + Integer.toString(this.numberOfArtifacts) + "  " + this.inventaire() + " Zone :" +this.currentZone.toString();
	}
	
	/**
	 * 
	 * @return Le nombre d'actions du joueur restantes
	 */
	
	public int getNumberOfActions() {
		return this.numberOfActions;
	}
	
	/**
	 * A chaque action effectuee, le nombre d'actions du joueur diminue
	 */
	public void actionPerformed() {
		this.numberOfActions--;
		//System.out.println(this.toString());
	}
	
	/**
	 * Reinitialise le nombre d'actions du joueur
	 */
	
	public void reinit() {
		this.numberOfActions = 3;
	}
	
	/**
	 *  Le joueur obtient la cle k
	 * @param k une cle obtenue
	 */
	public void obtainKey(Keys k) {
		this.cle.add(k);
		this.numberOfKeys++;
	}
	
	/**
	 * 
	 * @return Le nombre de cles du joueur, independant des elements
	 */
	public int numberOfKeys() {
		return this.numberOfKeys;
	}
	
	/**
	 * 
	 * @return le nombre d'artifacts du joueur
	 */
	public int getNumberOfArtifact() {
		return this.numberOfArtifacts;
	}
	
	/**
	 * 
	 * @return un tableau d'elements des cles possedees par le joueur
	 */
	public Elements[] hasElements(){
		Elements[] res = new Elements[this.cle.size()];
		for(int i =0; i<this.cle.size(); i++) {
			res[i] = this.cle.get(i).getElement();
		}
		return res;
	}
	
	/**
	 *  Permet d echanger des cles
	 * @param J Le joueur a qui on donne la cle
	 * @param K La cle donnee
	 */
	public void echangerKey(Joueur J, Keys K) {
		Elements[] res = this.hasElements();
		for(int i =0; i<this.numberOfKeys; i++) {
			if(K.getElement() == res[i]) {
				CVue.updateArea("Vous pouvez echanger une cle de" + K.getElement());
				J.obtainKey(K);
				this.cle.remove(K);
			}
		}
	}
	
}
