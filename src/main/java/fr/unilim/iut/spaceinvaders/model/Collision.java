package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	
	public static boolean detecterCollision(Sprite missile, Sprite envahisseur) {
		if (envahisseur.ordonneeLaPlusBasse() < missile.ordonneeLaPlusHaute()) {
			return true;
		}
		return false;
	}
	
}
