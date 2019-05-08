package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	private Position origine;
	private Dimensions dimension;
	int vitesse;

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}

	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this(new Dimensions(longueur, hauteur), new Position(x, y),1);
	}

	public Vaisseau(Dimensions dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
	}
	
	public Vaisseau(Dimensions dimension, Position positionOrigine) {
		this(dimension, positionOrigine, 1);
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public boolean occupeLaPosition(int x, int y) {
		return (estAbscisseCouverte(x)) && (estOrdonneeCouverte(y));
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	private boolean estOrdonneeCouverte(int y) {
		return (ordonneeLaPlusBasse() <= y) && (y <= ordonneeLaPlusHaute());
	}

	public int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute() - this.dimension.hauteur() + 1;
	}

	public int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}

	public int abscisseLaPlusADroite() {
		return origine.abscisse() + this.dimension.longueur() - 1;
	}

	public int abscisseLaPlusAGauche() {
		return origine.abscisse();
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() + this.vitesse);
	}
    
    public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() - this.vitesse);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}
	
	public int hauteur() {
		return this.dimension.hauteur();
	}

	public int longueur() {
		return this.dimension.longueur();
	}

}
