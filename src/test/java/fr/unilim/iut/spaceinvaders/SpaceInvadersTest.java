package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Before;

import fr.unilim.iut.spaceinvaders.model.Dimensions;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.*;

public class SpaceInvadersTest {
	private SpaceInvaders spaceinvaders;
	
	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception { 
	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7,2),new Position(5,9), 1);
	   spaceinvaders.tirerUnMissile(new Dimensions(7,9),1);
	}
	
	public void test_laPartieEstTermine() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		 assertEquals(true, spaceinvaders.etreFini());
	}
	
	@Test 
	public void test_jeuEstFini() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7, 2), new Position(5, 9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3, 2), 2);
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		spaceinvaders.deplacerMissile();
		
		 assertEquals(true, spaceinvaders.etreFini());
	}
	
	 @Test
	   public void test_PositonnerUnEnvahisseur_CorrectementPositionner() {

		   spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		   
	       assertEquals("" +
	       ".....EEEEEEE...\n" + 
	       ".....EEEEEEE...\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaDroite_AvecVitesse() {

		 	spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		   
	       spaceinvaders.deplacerEnvahisseurVersLaDroite();
	       assertEquals("" +
	    	       "......EEEEEEE..\n" + 
	    	       "......EEEEEEE..\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" + 
	    	       "...............\n" ,spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 
	 public void test_EnvahisseurAvance_DeplacerEnvahisseurVersLaGauche_AvecVitesse() {

		 	spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 1);
		   
	       spaceinvaders.deplacerEnvahisseurVersLaGauche();
	       assertEquals("" +
	    	       "....EEEEEEE....\n" + 
	    	       "....EEEEEEE....\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" + 
	    	       "...............\n" ,spaceinvaders.recupererEspaceJeuDansChaineASCII());
	 }
	 
	 @Test
	    public void test_EnvahisseurAvanceAutomatiquement() {

		 spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 3);

		  spaceinvaders.deplacerEnvahisseur();
		   
		  assertEquals("" +
	    	       "........EEEEEEE\n" + 
	    	       "........EEEEEEE\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" + 
	    	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 @Test
	    public void test_EnvahisseurAvanceAutomatiquement_RebonditContreLeMur_ADroite() {

		 spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(2,2),new Position(12,1), 1);

		  spaceinvaders.deplacerEnvahisseur();
		  spaceinvaders.deplacerEnvahisseur();
		  spaceinvaders.deplacerEnvahisseur();
		   
		  assertEquals("" +
	    	       "...........EE..\n" + 
	    	       "...........EE..\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" + 
	    	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 @Test
	    public void test_EnvahisseurAvanceAutomatiquement_RebonditContreLeMur_AGauche() {

		 spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(7,2),new Position(5,1), 3);
		 
		 for (int i = 0; i < 6; i++) {
			 spaceinvaders.deplacerEnvahisseur();
		 }
		 
		  assertEquals("" +
	    	       "......EEEEEEE..\n" + 
	    	       "......EEEEEEE..\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" +
	    	       "...............\n" + 
	    	       "...............\n" + 
	    	       "...............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 @Test
		public void test_UnNouvelEnvahisseurPositionneHorsEspaceJeu_DoitLeverUneException() {

			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(1, 1), new Position(15, 9), 1);
				fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(1, 1), new Position(-1, 9), 1);
				fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(1, 1), new Position(14, 10), 1);
				fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimensions(1, 1), new Position(14, -1), 1);
				fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
			} catch (final HorsEspaceJeuException e) {
			}

		}
	 
	 @Test
		public void test_UnNouvelEnvahisseurPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(9, 2), new Position(7, 9), 1);
				fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}

			try {
				spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3, 4), new Position(7, 1), 1);
				fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
			} catch (final DebordementEspaceJeuException e) {
			}

		}
	 
	 @Test
	   public void test_MissileDisparait_QuandIlCommenceASortirDeEspaceJeu() {

		   spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7,2),new Position(5,9), 1);
		   spaceinvaders.tirerUnMissile(new Dimensions(3,2),1);
		   for (int i = 1; i <= 8 ; i++) {
			   spaceinvaders.deplacerMissile();
		   }
		   
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".....VVVVVVV...\n" + 
	       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }

	@Test
    public void test_MissileAvanceAutomatiquement_ApresTirDepuisLeVaisseau() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7,2),new Position(5,9), 2);
	   spaceinvaders.tirerUnMissile(new Dimensions(3,2),2);

	   spaceinvaders.deplacerMissile();
	   
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       ".......MMM.....\n" + 
       ".......MMM.....\n" + 
       "...............\n" + 
       "...............\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }
	 @Test
     public void test_MissileBienTireDepuisVaisseau_VaisseauLongueurImpaireMissileLongueurImpaire() {

	   spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7,2),new Position(5,9), 2);
	   spaceinvaders.tirerUnMissile(new Dimensions(3,2),2);

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       ".......MMM.....\n" + 
       ".......MMM.....\n" + 
       ".....VVVVVVV...\n" + 
       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }
	
	public void test_VaisseauAvance_DeplacerVaisseauVersLaDroite_AvecVitesse() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3,2),new Position(7,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "..........VVV..\n" + 
	       "..........VVV..\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	   }
	 @Test
	    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaDroite() {

	       spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3,2),new Position(9,9),3);
	       spaceinvaders.deplacerVaisseauVersLaDroite();
	       assertEquals("" + 
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "...............\n" + 
	       "............VVV\n" + 
	       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	    }
	@Test
    public void test_VaisseauAvancePartiellement_DeplacerVaisseauVersLaGauche() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3,2),new Position(1,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "VVV............\n" + 
       "VVV............\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
     }

	@Test
	public void test_UnNouveauVaisseauPositionneDansEspaceJeuMaisAvecDimensionTropGrande_DoitLeverUneExceptionDeDebordement() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(9, 2), new Position(7, 9), 1);
			fail("Dépassement du vaisseau à droite en raison de sa longueur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3, 4), new Position(7, 1), 1);
			fail("Dépassement du vaisseau vers le haut en raison de sa hauteur trop importante : devrait déclencher une exception DebordementEspaceJeuException");
		} catch (final DebordementEspaceJeuException e) {
		}

	}

	@Test
    public void test_VaisseauAvance_DeplacerVaisseauVersLaGauche() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3,2),new Position(7,9), 3);
       spaceinvaders.deplacerVaisseauVersLaGauche();

       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "....VVV........\n" + 
       "....VVV........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
   }

	@Test
	public void test_VaisseauImmobile_DeplacerVaisseauVersLaGauche() {

		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3, 2), new Position(0, 9), 1);
		spaceinvaders.deplacerVaisseauVersLaGauche();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "VVV............\n" + "VVV............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
    public void test_VaisseauImmobile_DeplacerVaisseauVersLaDroite() {

       spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3,2),new Position(12,9), 3);
       spaceinvaders.deplacerVaisseauVersLaDroite();
       assertEquals("" + 
       "...............\n" + 
       "...............\n" +
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "...............\n" + 
       "............VVV\n" + 
       "............VVV\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
    }

	@Test
	public void test_unNouveauVaisseauAvecDimensionEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(3, 2), new Position(7, 9), 1);
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ ".......VVV.....\n" + ".......VVV.....\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_AuDebut_JeuSpaceInvaderEstVide() {
		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_unNouveauVaisseauEstCorrectementPositionneDansEspaceJeu() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(1, 1), new Position(7, 9), 1);

		spaceinvaders.deplacerVaisseauVersLaDroite();

		assertEquals("" + "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "...............\n" + "...............\n" + "...............\n"
				+ "...............\n" + "........V......\n", spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}

	@Test
	public void test_UnNouveauVaisseauPositionneHorsEspaceJeu_DoitLeverUneException() {

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(1, 1), new Position(15, 9), 1);
			fail("Position trop à droite : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(1, 1), new Position(-1, 9), 1);
			fail("Position trop à gauche : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(1, 1), new Position(14, 10), 1);
			fail("Position trop en bas : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(1, 1), new Position(14, -1), 1);
			fail("Position trop à haut : devrait déclencher une exception HorsEspaceJeuException");
		} catch (final HorsEspaceJeuException e) {
		}
	}	
	
	@Test
	public void test_tirerPlusieursMissiles_SansCollistion() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimensions(7,2),new Position(5,9), 2);
		spaceinvaders.tirerUnMissile(new Dimensions(3,2),1);
		
		spaceinvaders.deplacerMissile();
		
		spaceinvaders.tirerUnMissile(new Dimensions(3,2),1);
		
		  assertEquals("" + 
			       "...............\n" + 
			       "...............\n" +
			       "...............\n" + 
			       "...............\n" + 
			       "...............\n" + 
			       ".......MMM.....\n" + 
			       ".......MMM.....\n" + 
			       "...............\n" + 
			       ".....VVVVVVV...\n" + 
			       ".....VVVVVVV...\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	
	
	
}