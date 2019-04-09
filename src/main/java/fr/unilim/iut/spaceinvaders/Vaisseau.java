package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	int x;
	int y;
	
	public Vaisseau(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int abscisse() {
		return this.x;
	}


	public boolean occupeLaPosition(int x, int y) {
		return this.x == x && this.y == y;
	}
	
	public void seDeplacerVersLaDroite() {
	      this.x += 1 ;
 }

	public void seDeplacerVersLaGauche() {
		this.x -= 1;
	}
	
	
}