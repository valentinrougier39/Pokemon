package tp13;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JoueurTest {
	private Joueur joueur;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.joueur=new Joueur("Rougier","Valentin",17);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.joueur=null;
	}

	@Test
	void testGetNom() {
		this.joueur.setNom("Rougier");
		assertEquals("Rougier",this.joueur.getNom());
	}

	@Test
	void testGetPrenom() {
		this.joueur.setPrenom("Valentin");
		assertEquals("Valentin",this.joueur.getPrenom());
	}
	
	@Test
	void testCapturer() {
		 Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[] {new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
		 joueur.capturerPokemon(piplup);
		 piplup.setNomDonne("mon pokemon préféré");
		 
		 assertEquals("mon pokemon préféré",piplup.getNomDonne()); // Créativité Q6
		 assertEquals(joueur,piplup.getMonJoueur());
		 assertEquals(piplup,this.joueur.getPokemons()[0]);
		 assertEquals(5,this.joueur.getPokemons().length);
		 assertEquals(null,joueur.getPokemons()[2]);
		 // Les 4 tests passent 
	}
	
	@Test
	void testLiberer() {
		
		Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[] {new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
		joueur.capturerPokemon(piplup);
		
		joueur.libererPokemon(piplup);
		
		assertEquals(5,this.joueur.getPokemons().length);
		assertEquals(null,this.joueur.getPokemons()[0]);
		assertEquals(null, this.joueur.getPokemons()[2]);
		assertEquals(null, piplup.getMonJoueur()); // null car il n'a plus de maître car il a été libéré
		assertEquals(null,piplup.getNomDonne()); // Créativité Q6
		
		
	}
	/*
	@Test
	void testCapturerAvecMaitre() {
		Joueur joueur2 = new Joueur("DROUAL", "Antoine", 18);
		Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[] {new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
		joueur2.capturerPokemon(piplup);
		joueur.capturerPokemon(piplup);
		
		assertEquals(joueur2,piplup.getMonJoueur());
		assertEquals(piplup,joueur2.getPokemons()[0]);
		assertEquals(5,joueur2.getPokemons().length);
		assertEquals(null,joueur2.getPokemons()[2]);
		
		// joueur n'influe plus sur les tests car piplup appartient à joueur2 et non à joueur.
	}
	*/
	/*
	@Test 
	void testLibererDUnAutreMaitre() {
		Joueur joueur2 = new Joueur("DROUAL", "Antoine", 18);
		Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[] {new AttaqueTackle(), new AttaqueMorsure(), new AttaquePistoleEau(), new AttaqueEnfer()});
		joueur2.capturerPokemon(piplup);
		joueur.libererPokemon(piplup);
		
		assertEquals(5,joueur2.getPokemons().length);
		assertEquals(piplup,joueur2.getPokemons()[0]);
		assertEquals(null, joueur2.getPokemons()[2]);
		assertEquals(joueur2, piplup.getMonJoueur());
	}
	*/
	@Test
	void testCapturerPlusDePlace() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			for (int compteur = 0; compteur<=6;compteur++) {
				Pokemon piplup =  new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[4] );
				this.joueur.capturerPokemon(piplup);
			}
		});
			
	}
	// Il n'y a plus de place quand 5 pokémons sont présents dans le pokedex d'un joueur.
	
	@Test
	void testCapturerDejaUnMaitre() throws Exception {
		Assertions.assertThrows(Exception.class, () -> {
			Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[4] );
			Joueur joueur2 = new Joueur("DROUAL", "Antoine", 18);
			joueur2.capturerPokemon(piplup);
			this.joueur.capturerPokemon(piplup);
		});
	}
	
	@Test
	void testLibererDUnAutreMaitreException() throws Exception {
		
		Assertions.assertThrows(Exception.class, () -> {
			Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[4] );
			Joueur joueur2 = new Joueur("DROUAL", "Antoine", 18);
			this.joueur.capturerPokemon(piplup);
			joueur2.libererPokemon(piplup);
		});
		
	}
	
	@Test
	void testLibererLibre() throws Exception{
		Assertions.assertThrows(Exception.class, () -> {
			Pokemon piplup = new Pokemon(393, "Piplup", "EAU",5 , true, 51, 53,61,56,new Attaque[4] );
			Joueur joueur2 = new Joueur("DROUAL", "Antoine", 18);
			this.joueur.libererPokemon(piplup);
		});
	}
	
	// EXO2 Q2 : 2 erreurs sont retournés : testGetNom() et testGetPrenom().
	
}
