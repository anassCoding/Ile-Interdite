package FB;

class CModele extends Observable {
    private int Largeur;
    private int Hauteur;
    private Zone[][] zones;
    private Joueur[] joueurs;
    private Ile ile;
    private Jeu jeu;

    public CModele(Jeu jeu) {
    this.ile = new Ile(10, 10);
    this.jeu = new Jeu(this.ile);
    this.joueurs = this.jeu.getJoueurs();
	this.Largeur = this.ile.getWidth();
	this.Hauteur = this.ile.height();
	this.zones = new Zone[this.Largeur][this.Hauteur];
	for(int i = 0; i<this.Largeur; i++) {
		for(int j = 0; j<this.Hauteur;j++) {
			zones[i][j] = new Zone(this.ile, i, j);
			this.zones[i][j] = this.ile.getZone(j*this.Largeur+i);
		}
	}
    }

    public void flood3Zones() {
    	this.ile.flood3RandomZone();
    	notifyObservers();
    }
    
    public Joueur[] getJoueurs() {
    	return this.joueurs;
    }
 
    public Zone getZone(int x, int y) {
    	return zones[x][y];
    }
    
    public Joueur getJoueur(Zone z) {
    	for(Joueur j : this.joueurs) {
    		if(j.getZone() == z) {
    			return j;
    		}
    	}
    	return null;
    }
    
    public Artifact getArtifact(Zone z) {
    	for(Artifact art : this.jeu.getArtifacts()) {
    		if(art.getZone() == z) {
    			return art;
    		}
    	}
    	return null;
    }
    
    public Ile getIle() {
    	return this.ile;
    }
    
    public void move(Zone z) {
   // 	this.j.move(z);
    	notifyObservers();
    }
    
    public Jeu getJeu() {
    	return this.jeu;
    }

}
