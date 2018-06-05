package FB;
import java.util.ArrayList;
import java.util.Random;

public class Ile {
	private int width;
	private int height;
	private int numberOfCases;
	private Zone[] zones;
	private int indiceH;

	/**
	 * Cree une ile de taille 
	 * largeur et hauteur passees en parametres.
	 *
	 * @param width The isle's grid width
	 * @param height The isle's grid height
	 */
	public Ile(int width, int height){
		this.width = width;
		this.height = height;
		this.numberOfCases = width * height;
		this.zones = new Zone[this.numberOfCases];
		initiateCases();
	}

	/**
	 * Initialise l'ile
	 */
	public void initiateCases(){
		for (int j = 0; j < this.height; j++){
			for (int i = 0; i < this.width; i++){
				this.zones[j*this.width + i] = new Zone(this, i, j);
			}
		}
		//UN seul et unique Heliport
		this.zones[(int)(Math.random()*this.numberOfCases)] = new Heliport(this, this.zones[(int)(Math.random()*this.numberOfCases)].getX(), this.zones[(int)(Math.random()*this.numberOfCases)].getY(), true);
		this.zones[(int)(Math.random()*this.numberOfCases)].setToSpecial();
		this.indiceH = (int)(Math.random()*this.numberOfCases);
	}


	@Override
	/**
	 * Retourne une chaine de caractere de l ile entiere.
	 */
	public String toString(){
		String res = "";
		for (int j = 0; j < this.height; j++){
			for (int i = 0; i < this.width; i++){
				res += "|" + this.zones[j * this.width + i].toString();	
			}
		}
		return res;
	}
	
	/* 
	 */
	public Zone randomZone() {
		return this.zones[(int)(Math.random()*this.numberOfCases)];
	}
	
	/**
	 * Traduit des coordonnes en une zone de l ile
	 */
	//Test fait
	public Zone Parse(int x, int y) {
		return this.zones[y*this.width+x];
	}
	
	/**
	 * Traduit des coordonnees en un indice.s
	 * @param x
	 * @param y
	 * @return l indice de la case correspondante
	 */
	
	//Test fait
	public int id(int x, int y) {
		int res = 0;
		if(x>9) {y--;}
		res = y*this.width + x;
		return res;
	}
	
	/*
	 * Innonde 3 zones aleatoire
	 */
	/**
	 * Innonde 3 zones aleatoires de l'ile
	 */

	
	//Test fait
	public void flood3RandomZone(){
		Random rand = new Random();
		ArrayList<Integer> List = new ArrayList<>();
		for(Zone z : this.zones) {
			if(z.getState() != State.DROWN) {
				List.add(this.id(z.getX(), z.getY()));
			}
		}
		
		int numberOfEl = 3;
		
		for(int i = 0; i<numberOfEl; i++) {
			int res = rand.nextInt(List.size());
			this.zones[List.get(res)].drowning();
			List.remove(res);
			}
		}
	
		

	
	/**
	 * Innonde toutes les zones DANS LE CADRE DES TEST UNIQUEMENT !
	 * 
	 */
	public void floodAllZones() {
		for(Zone z : this.zones) {
			z.drowning();
		}
	}
	
	/**
	 * Retourne une Zone d'indice i
	 */
	//Test fait
	public Zone getZone(int i) {
		return this.zones[i];
	}
	
	/**
	 * Retourne l'indice d'une ile
	 */
	public int getIndice(Zone z) {
		return this.id(z.getX(), z.getY());
	}
	
	/**
	 * Retourne toutes les zones de l'ile
	 * @return this.zones
	 */
	//Test
	public Zone[] zones(){
		return this.zones;	
	}
	
	/**
	 *  Retourne les cases voisines en haut, en bas, a gauche et a droite d une case passee en parametre
	 */
	//Test fait
	
	public Zone[] voisines(int i) {
		//Zone[] res = new Zone[4];
		if(10%i!=0 && i>9 && i<90 && i%9!=0) {
		Zone[] res = new Zone[4];
		res[0]=this.zones[i-this.width];
		res[1]=this.zones[i+this.width];
		res[2]=this.zones[i+1];
		res[3]=this.zones[i-1];
		return res;
		
		} else if(i<10 && i%10!=0 && i%9!=0) {
			Zone[] res = new Zone[3];
			res[0] = this.zones[i+this.width];
			res[1] = this.zones[i+1];
			res[2] = this.zones[i-1];
			return res;
			
		} else if(i==0) {
			Zone[] res = new Zone[2];
			res[0] = this.zones[i+this.width];
			res[1] = this.zones[i+1];
			return res;
		} else if(i == 9) {
			Zone[] res = new Zone[2];
			res[0] = this.zones[i+this.width];
			res[1] = this.zones[i-1];
			return res;
		} else if(i==99) {
			Zone[] res = new Zone[2];
			res[0] = this.zones[i-this.width];
			res[1] = this.zones[i-1];
			return res;
		} else if(i==90) {
			Zone[] res = new Zone[2];
			res[0] = this.zones[i-this.width];
			res[1] = this.zones[i+1];
			return res;
		} else if(i%10 == 0 && i!=0 && i!=90) {
			Zone[] res = new Zone[3];
			res[0] = this.zones[i+this.width];
			res[1] = this.zones[i+1];
			res[2] = this.zones[i-this.width];
			return res;
		} else if(i%9 == 0 && i!=9 && i!=99) {
			Zone[] res = new Zone[3];
			res[0] = this.zones[i+this.width];
			res[1] = this.zones[i-1];
			res[2] = this.zones[i-this.width];
			return res;
		}
		return null;
	}
	
	/**
	 * Verifie que l'on peut se deplacer sur les cases voisines de la zone passee en parametre
	 * @param z
	 * @return True si on peut se deplacer sur une zone, false sinon
	 */
	
	//Test Non fait FONCTION NON UTILISEE MAIS IMPLEMENTEE AU CAS OU
	public Boolean peutSeDeplacer(Zone z) {
		int c = id(z.getX(), z.getY());
		Zone[] zs = voisines(c);
		for(Zone zone : zs) {
			if(zone.getState() != State.DROWN) return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return la largeur de l'ile
	 */
	public int getWitdh() {
		return this.width;
	}
	
	/**
	 * 
	 * @return la hauteur de l'ile
	 */
	
	public int height() {
		return this.height;
	}
	
	/**
	 * 
	 * @return La largeur de l'ile comme get witdh 
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * 
	 * @return le nombre de cases de l'ile
	 */
	
	public int getNumberOfCases() {
		return this.numberOfCases;
	}
	
	/**
	 * 
	 * @return l'indice de l'heliport
	 */
	
	public int indiceDeH() {
		return this.indiceH;
	}
}


