import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class gameTestFunctions {
		
		@Test
		public void getStateTest() {
			Zone z1 = new Zone();
			assertEquals(State.NORMAL, z1.getState());
		}
		
		@Test
		public void drowningTest() {
			Zone z1 = new Zone();
			z1.drowning();
			assertEquals(State.DROWNING, z1.getState());
			Zone z2 = new Zone();
			z2.drowning();
			z2.drowning();
			assertEquals(State.DROWN, z2.getState());
		}
		
		@Test
		public void dryTest() {
			Zone z1 = new Zone();
			z1.drowning();
			z1.dry();
			assertEquals(State.NORMAL, z1.getState());
			Zone z2 = new Zone();
			z2.drowning();
			z2.drowning();
			z2.dry();
			assertEquals(State.DROWN, z2.getState());
		}
		
		@Test
		public void joueurIsAliveTest() {
			Joueur j1 = new Joueur();
			assertEquals(j1.isAlive(), true);
		}
		
		@Test
		public void joueurDieTest() {
			Joueur j1 = new  Joueur();
			j1.die();
			assertEquals(j1.isAlive(), false);
		}
		
		@Test
		public void joueurGetZoneTest() {
			Zone z1 = new Zone();
			Joueur j1 = new Joueur(z1);
			assertEquals(z1, j1.getZone());
		}
		
		@Test
		public void joueurSearchKeys() {
			Zone z1 = new Zone();
			Joueur j1 = new Joueur(z1);
			Keys k1 = new Keys(Elements.Air, z1);
			j1.searchKey(k1);
			Keys res[] = j1.getKeys();
			Keys[] t = new Keys[4];
			t[0] = k1;
			assertArrayEquals(t, res);
		}
		
		@Test
		public void joueurDryUpTest() {
			Ile ile = new Ile(10, 10);
			Jeu jeu = new Jeu(ile);
			ile.floodAllZones();
			Joueur j = new Joueur(jeu);
			j.dryUp(j.getZone());
			assertEquals(j.getZone().getState(), State.NORMAL);
			Zone z1 = jeu.getIle().getZone(jeu.getIle().id(j.getZone().getX()-1, j.getZone().getY()));
			j.dryUp(z1);
			assertEquals(z1.getState(), State.NORMAL);
			Zone z2 = jeu.getIle().getZone(jeu.getIle().id(0, 0));
			j.dryUp(z2);
			assertEquals(z2.getState(), State.DROWNING);
		}
		
		@Test
		public void joueurMoveTest() {
			Ile ile = new Ile(10, 10);
			Jeu jeu = new Jeu(ile);
			Joueur j = new Joueur(jeu);
		}
		
		@Test
		public void ileFloodRandomZoneTest() {
			Ile ile = new Ile(10, 10);
			ile.flood3RandomZone();
			int cont = 0;
			for(Zone z : ile.zones()) {
				if(z.getState() == State.DROWNING)cont++;
			}
			assertEquals(3, cont);
		}
		
		@Test
		public void ileParseTest() {
			Ile ile1 = new Ile(10, 10);
			Zone z1 = ile1.Parse(5, 5);
			assertEquals(z1, ile1.getZone(ile1.id(z1.getX(), z1.getY())));
		}
		
		@Test
		public void ileIdTest() {
			int id = 25;
			Ile ile1 = new Ile(10, 10);
			assertEquals(ile1.id(5, 2), 25);
		}
		
		@Test
		public void ileGetZoneTest() {
			Ile ile1 = new Ile(10, 10);
			Zone z1 = new Zone(ile1, 5, 5);
			assertEquals(z1.toString(), ile1.getZone(55).toString());
		}
		
		@Test
		public void ileVoisinesTest() {
			Ile ile1 = new Ile(10, 10);
			int id = 25;
			Zone[] t = ile1.voisines(id);
			Zone[] res = new Zone[4];
			res[0] = ile1.getZone(15);
			res[1] = ile1.getZone(35);
			res[2] = ile1.getZone(26);
			res[3] = ile1.getZone(24);
			assertArrayEquals(t, res);
		}
		
		@Test
		public void artifactGetElementTest() {
			Zone z1 = new Zone();
			Artifact a1 = new Artifact(Elements.Air, z1);
			assertEquals(a1.getElement(), Elements.Air);
		}
		
		@Test
		public void artifactCheckElements() {
			Zone z1 = new Zone();
			Artifact a1 = new Artifact(Elements.Air, z1);
			Keys k1 = new Keys(Elements.Air, z1);
			assertEquals(a1.checkElement(k1), true);
			Artifact a2 = new Artifact(Elements.Eau, z1);
			assertEquals(a2.checkElement(k1), false);
		}
	
		@Test
		public void artifactIsGotTest() {
			Zone z1 = new Zone();
			Artifact a1 = new Artifact(Elements.Air, z1);
			assertEquals(a1.isGot(), false);
		}
		
		@Test
		public void keysGetElement() {
			Zone z1 = new Zone();
			Keys k1 = new Keys(Elements.Air, z1);
			assertEquals(Elements.Air, k1.getElement());
		}
		
		@Test
		public void keysGetZone() {
			Zone z1 = new Zone();
			Keys k1 = new Keys(Elements.Air, z1);
			assertEquals(z1, k1.getZone());
		}
		
		/*
		@Test
		public void artifactGetArtifactTest() {
			Zone z1 = new Zone();
			Joueur j1 = new Joueur();
			Artifact a1 = new Artifact(Elements.Air, z1);
			Keys k1 = new Keys(Elements.Air, z1);
			j1.searchKey(k1);
			j1.recupereArtefact(a1);
			assertEquals(a1.isGot(), true);
		}*/
	
}
