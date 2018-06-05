package FB;

public class Keys {
	private Elements elements;
	private Zone zone;
	
	//Constructeur au cas on voulait des cles sur l'ile NON UTILISEE
	public Keys(Elements el, Zone z) {
		this.elements = el;
		this.zone = z;
	}
	
	//Constructeur pour l'echange de cle.
	public Keys(Elements el) {
		this.elements = el;
		this.zone = null;
	}
	
	/**
	 * Constructeur alternatif pour une cle aleatoire en fin de tour.
	 * @param el
	 */
	public Keys() {
		this.elements = randomElements();
		this.zone = null;
	}
	
	/**
	 * Cherche l'element de la cle
	 * @return this.elements
	 */
	//Test fait
	public Elements getElement() {
		return this.elements;
	}
	
	/**
	 *  Cherche la zone de la cle
	 * @return this.zone
	 */
	//Test fait
	public Zone getZone() {
		return this.zone;
	}

	/**
	 * Choix de l'element aleatoire pour la cle aleatoire de fin de tour

	 */
	public Elements randomElements() {
		int el = (int)(Math.random()*(4));
		if(el == 0)return Elements.Air;
		if(el == 1)return Elements.Eau;
		if(el == 2)return Elements.Feu;
		if(el == 3)return Elements.Terre;
		return elements;
	}

}
