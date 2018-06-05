package FB;

public class Run {
	public static void main(String[] args) {
		Ile ile = new Ile(10, 10);
		Jeu jeu = new Jeu(ile);
		//System.out.println(jeu.toString());
		 CModele modele = new CModele(jeu);
	     CVue vue = new CVue(modele);
	     //CVue.updateArea(jeu.getJoueurs()[0].toString());
	}
}
