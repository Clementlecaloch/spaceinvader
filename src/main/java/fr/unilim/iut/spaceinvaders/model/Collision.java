package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	
	public static boolean detecterCollision(Sprite sprite1, Sprite sprite2) {
		if (sprite1DansSprite2(sprite1, sprite2)) {
			return true;
		}
		return false;
	}

	public static boolean sprite1DansSprite2(Sprite sprite1, Sprite sprite2) {
		return (leHautDuSprite1DansSprite2(sprite1,sprite2) || BasDuSprite1DansSprite2(sprite1, sprite2)) && (droiteDuSprite1dansSprite2(sprite1, sprite2)
				|| (leHautDuSprite1DansSprite2(sprite1,sprite2) || BasDuSprite1DansSprite2(sprite1, sprite2)) && gaucheDuSprite1DansSprite2(sprite1, sprite2));
	}

	public static boolean gaucheDuSprite1DansSprite2(Sprite sprite1, Sprite sprite2) {
		return (sprite2.abscisseLaPlusADroite() >= sprite1.abscisseLaPlusAGauche()) && (sprite2.abscisseLaPlusAGauche() < sprite1.abscisseLaPlusAGauche());
	}

	public static boolean droiteDuSprite1dansSprite2(Sprite sprite1, Sprite sprite2) {
		return (sprite2.abscisseLaPlusADroite() >= sprite1.abscisseLaPlusADroite()) && (sprite2.abscisseLaPlusAGauche() < sprite1.abscisseLaPlusADroite());
	}

	public static boolean leHautDuSprite1DansSprite2(Sprite sprite1, Sprite sprite2) {
		if (sprite1.ordonneeLaPlusBasse() <= sprite2.ordonneeLaPlusHaute() && sprite1.ordonneeLaPlusBasse() >= sprite2.ordonneeLaPlusBasse()) 
			return true;
		return false;
	}

	public static boolean BasDuSprite1DansSprite2(Sprite sprite1, Sprite sprite2) {
		if (sprite1.ordonneeLaPlusHaute() <= sprite2.ordonneeLaPlusHaute() && sprite1.ordonneeLaPlusHaute() >= sprite2.ordonneeLaPlusBasse()) 
			return true;
		return false;
	}
	
}

