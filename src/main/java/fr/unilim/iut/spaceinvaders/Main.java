package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.moteurjeu.MoteurGraphique;

public class Main {
	public static void main(String Args[]) throws InterruptedException {
		SpaceInvaders jeu = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);
		jeu.initialiserJeu();
		DessinSpaceInvaders affichage = new DessinSpaceInvaders(jeu);

		MoteurGraphique moteur = new MoteurGraphique(jeu, affichage);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR, Constante.ESPACEJEU_HAUTEUR);

	}
}
