package fr.unilim.iut.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.Commande;
import fr.unilim.iut.spaceinvaders.moteurjeu.Jeu;
import fr.unilim.iut.spaceinvaders.utils.*;

public class SpaceInvaders implements Jeu {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	List<Missile> missiles;
	Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		missiles = new ArrayList<Missile>();
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimensions dimensionVaisseau = new Dimensions(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Position positionEnvahisseur = new Position(this.longueur / 2, 300);
		Dimensions dimensionsEnvahisseur = new Dimensions(Constante.ENVAHISSEUR_LONGUEUR,
				Constante.ENVAHISSEUR_HAUTEUR);

		this.positionnerUnNouvelEnvahisseur(dimensionsEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnEnvahisseur() {
		return envahisseur != null;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		boolean res = false;
		for (int i = 0; i < missiles.size(); i++) {
			if (this.aUnMissile() && missiles.get(i).occupeLaPosition(x, y)) {
				res = true;
			}
		}
		return res;
	}

	public boolean aUnMissile() {
		return !missiles.isEmpty();
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void positionnerUnNouveauVaisseau(Dimensions dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		;
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public List<Missile> recupererMissile() {
		return this.missiles;
	}

	public void evoluer(Commande commandeUser) {

		if (commandeUser.gauche) {
			deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.droite) {
			deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.tir) {
			tirerUnMissile(new Dimensions(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
		}
		if (this.aUnMissile()) {
			this.deplacerMissile();
		}
		if (this.aUnEnvahisseur()) {
			this.deplacerEnvahisseur();
		}
		if (this.aUnMissile()) {
			this.etreFini();
		}
	}

	public boolean etreFini() {
		if (this.aUnMissile()) {
			boolean res = false;
			for (int i = 0; i < missiles.size(); i++) {
				if (Collision.detecterCollision(missiles.get(i), envahisseur))
					res = true;
			}
			return res;
		}
		return false;
	}

	public void tirerUnMissile(Dimensions dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		Missile missileTempo = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
		 boolean res = true;
		if (this.aUnMissile()) {
			for (Missile missile : missiles) {
				if (Collision.detecterCollision(missile, missileTempo)) {
					res = false;
				}
			}

		} 
		if (res) {
			this.missiles.add(missileTempo);
		}
	}

	public void deplacerMissile() {
		if (this.aUnMissile()) {
			this.missiles.forEach(i -> i.deplacerVerticalementVers(Direction.HAUT_ECRAN));
			this.missiles.forEach(i -> {
				if (i.ordonneeLaPlusBasse() < 0)
					i = null;
			});
		}
	}

	public void positionnerUnNouvelEnvahisseur(Dimensions dimensions, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

		int longueurVaisseau = dimensions.longueur();
		int hauteurVaisseau = dimensions.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

		envahisseur = new Envahisseur(dimensions, position, vitesse);

	}

	public void deplacerEnvahisseurVersLaDroite() {
		if (this.aUnEnvahisseur()) {
			this.envahisseur.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusADroite(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(longueur - envahisseur.longueur(), envahisseur.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerEnvahisseurVersLaGauche() {
		if (this.aUnEnvahisseur()) {
			this.envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
			if (!estDansEspaceJeu(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusHaute())) {
				envahisseur.positionner(0, envahisseur.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerEnvahisseur() {
		if (this.aUnEnvahisseur()) {
			switch (envahisseur.sensDeDeplacement()) {
			case Constante.DROITE:
				this.deplacerEnvahisseurVersLaDroite();
				changerDirectionAGauche();
				break;
			case Constante.GAUCHE:
				this.deplacerEnvahisseurVersLaGauche();
				changerDirectionADroite();
				break;
			default:
				break;
			}

		}
	}

	public void changerDirectionAGauche() {
		if (this.envahisseur.abscisseLaPlusADroite() == this.longueur - 1) {
			this.envahisseur.changerSensDeDirection(Constante.GAUCHE);
		}
	}

	public void changerDirectionADroite() {
		if (this.envahisseur.abscisseLaPlusAGauche() == 0) {
			this.envahisseur.changerSensDeDirection(Constante.DROITE);
		}
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

}