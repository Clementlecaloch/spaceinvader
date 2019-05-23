package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

import fr.unilim.iut.spaceinvaders.model.Dimensions;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Collision;

public class CollisionTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
	public void test_detecterCollisionEntreDeuxSprites_hautDuMissileAuDessusDuBasDeLEnvahisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		 assertTrue(Collision.leHautDuSprite1DansSprite2(spaceinvaders.recupererMissile().get(0), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_detecterCollisionEntreDeuxSprites_BasDuMissileDansLEnvahisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 1);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,3), 1);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		 assertTrue(Collision.BasDuSprite1DansSprite2(spaceinvaders.recupererMissile().get(0),spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_detecterCollisionEntreDeuxSprites_droiteDuMissileEnDehorsDeLevanhisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(3,1), 1);
		
		
		 assertTrue(Collision.gaucheDuSprite1DansSprite2(spaceinvaders.recupererMissile().get(0), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_detecterCollisionEntreDeuxSprites_gaucheDuMissileEnDehorsDeLevanhisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(8,1), 1);
		
		 assertTrue(Collision.droiteDuSprite1dansSprite2(spaceinvaders.recupererMissile().get(0), spaceinvaders.recupererEnvahisseur()));
	}
	
	@Test
	public void test_detecterCollisionEntreDeuxSprites_casNormal() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		 assertTrue(Collision.detecterCollision(spaceinvaders.recupererMissile().get(0), spaceinvaders.recupererEnvahisseur()));
	}
	
	
}
