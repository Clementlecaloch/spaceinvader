package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	
	public String direction;

	public Envahisseur(Dimensions dimensions, Position position, int vitesse) {
		super(dimensions,position,vitesse);
		this.direction = Constante.DROITE;
	}

	public String sensDeDeplacement() {
		return direction;
	}

	public void changerSensDeDirection(String direction) {
		this.direction = direction;
	}
	
	

	
}
