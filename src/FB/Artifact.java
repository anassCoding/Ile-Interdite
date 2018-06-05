package FB;

public class Artifact {
	private Zone location;
	private Elements element;
	private Boolean isGot;
	
	public Artifact(Elements el, Zone z) {
		this.element = el;
		this.location = z;
		this.isGot = false;
	}
	
	/**
	 * Cherche l'element de l artefact
	 * @return l'element de l'artefact
	 */
	//Test fait
	public Elements getElement() {
		return this.element;
	}
	
	/**
	 * Modifie this.isGot si un joueur recupere cet artifact
	 */
	
	//Test a faire
	public void getArtifact() {
		this.isGot = true;
	}
	
	/**
	 * Verifie si l'artifact a ete recupere par un joueur ou non
	 * @return this.isGot
	 */
	//Test fait
	public Boolean isGot() {
		return this.isGot;
	}
	
	/**
	 * Verifie que l'element de l'artifact correspond a celui de la cle
	 * @param Keys k
	 * @return true si l'element de la cle correspond a celui de l'artifact, false sinon
	 */
	//Test fait
	public Boolean checkElement(Keys k) {
		return this.element == k.getElement();
	}
	
	/**
	 * 
	 * @return La zone de cet artifact
	 */
	
	public Zone getZone() {
		return this.location;
	}
	
	public void newZone(Zone z) {
		this.location = z;
	}

}
