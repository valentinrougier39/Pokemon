package tp13;

import java.util.Random;
import java.util.Scanner;

public class Bataille {
	private Pokemon pokemon1;
	private Pokemon pokemon2;
	private Scanner lecteurBataille;
	private boolean batailleFinie;
	
	public Bataille(Pokemon pokemon1, Pokemon pokemon2) {
		this.pokemon1 = pokemon1;
		this.pokemon2 = pokemon2;
		this.lecteurBataille = new Scanner(System.in);
		this.batailleFinie = false;
	}
	
	
	private void choisirAction(Pokemon pokemonActif, Pokemon pokemonPassif) {
		if(null != pokemonActif.getMonJoueur()) {
			pokemonActif.afficherEtatAttaques();
			System.out.println("Pour pokemon " + pokemonActif.getNom() + ", choisissez l'index de votre attaque contre le pokemon " + pokemonPassif.getNom());
			int reponseIndex = this.lecteurBataille.nextInt();
			while (reponseIndex < 0 || reponseIndex > pokemonActif.getAttaques().size()) {
				System.out.println("Refaites votre choix.");
				reponseIndex = this.lecteurBataille.nextInt();
			}
			pokemonActif.utiliserAttaque(reponseIndex, pokemonPassif);
			if (pokemonPassif.sEstEvanoui()) {
				this.batailleFinie = true;
				System.out.println("Le vainqueur est " + pokemonActif.getNom());
			
				Joueur maitre = pokemonActif.getMonJoueur();
				if (null != maitre) {
					if (null == pokemonPassif.getMonJoueur()) {
						System.out.println("Voulez-vous capturer ce pokemon ? Tapez <<oui>> ou <<non>>");
						String reponseText = this.lecteurBataille.next();
						
						while(!reponseText.equals("oui") && !reponseText.equals("non")) {
							System.out.println("Re-faites votre choix. Il faut taper <<oui>> ou <<non>>.");
							reponseText = this.lecteurBataille.next();
						}
						
						if (reponseText.equals("oui")) {
							maitre.capturerPokemon(pokemonPassif);
							if (null == pokemonPassif.getMonJoueur()) {
								// on n'a pas la place pour le prendre
								System.out.println("Meme si vous n'avez pas la place pour ce pokemon, vous pourriez faire de la place en liberant un de vos pokemons actuels " + maitre.getPokemons());
								System.out.println("Voulez-vous liberer un de vos pokemons ? Tapez <<oui>> ou <<non>>.");
								reponseText = this.lecteurBataille.next();
								while(!reponseText.equals("oui") && !reponseText.equals("non")) {
									System.out.println("Re-faites votre choix. Il faut taper <<oui>> ou <<non>>.");
									reponseText = this.lecteurBataille.next();
								}
								if (reponseText.equals("oui")) {
									System.out.println("Lequel de vos pokemons voulez-vous eliberer pour capturer " +pokemonPassif.getNom() + " ? Tapez un index.");
									reponseIndex = this.lecteurBataille.nextInt();
									while (reponseIndex < 0 || reponseIndex > maitre.getPokemons().length) {
										System.out.println("Refaites votre choix.");
										reponseIndex = this.lecteurBataille.nextInt();
									}
									maitre.libererPokemon(maitre.getPokemons()[reponseIndex]);
									maitre.capturerPokemon(pokemonPassif);
								}
								
							}
						}
					}
				}
			}
		}
		else {
			Random alea = new Random();
			pokemonActif.utiliserAttaque(alea.nextInt(pokemonActif.getAttaques().size()), pokemonPassif);
			if (pokemonPassif.sEstEvanoui()) {
				this.batailleFinie = true;
				System.out.println("Le vainqueur est " + pokemonActif.getNom());
			}
		}
	}

	
	public void run() {
		while (!this.batailleFinie) {
			if (this.pokemon1.sEstEvanoui() || this.pokemon2.sEstEvanoui()) {
				this.batailleFinie = true;
			}
			if(!this.batailleFinie) {
				this.choisirAction(this.pokemon1, this.pokemon2);
			}
			
			if(!this.batailleFinie && !this.pokemon2.sEstEvanoui()) {
				this.choisirAction(this.pokemon2, this.pokemon1);
			}
		}

		
		this.pokemon1.rechargerAttaques();
		this.pokemon2.rechargerAttaques();
		

	}
	
	
	public Pokemon getPokemon1() {
		return this.pokemon1;
	}
	
	public Pokemon getPokemon2() {
		return this.pokemon2;
	}
	
	public String toString() {
		return ("Bataille : " +this.pokemon1.getNom() + " vs. " + this.pokemon2.getNom());
	}
}
