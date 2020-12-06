package tp13;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class ChasseAuxPokemons {

	public static void main(String[] args) {
	
		// TP 10
		final Map<String, Integer> mapPokemons = new HashMap<>();
				
		try(FileReader lecteur = new FileReader("src/tp13/pokedexComplet.txt")){
			Scanner s = new Scanner(lecteur);
			while (s.hasNext()) {
				int numeroPokedex = s.nextInt();
				String nom = s.next();
				mapPokemons.put(nom, numeroPokedex);
			}
		}
			catch(IOException e) {
			e.printStackTrace();
		}
		
		// nos attaques
		final Map<String, Attaque> mappeAttaques = new HashMap<String, Attaque>();
		mappeAttaques.put("tackle", new AttaqueTackle());
		mappeAttaques.put("morsure", new AttaqueMorsure());
		mappeAttaques.put("pistoleEau", new AttaquePistoleEau());
		mappeAttaques.put("enfer", new AttaqueEnfer());
		mappeAttaques.put("feinte", new AttaqueFeinte());
		mappeAttaques.put("tornadeFeuilles", new AttaqueTornadeFeuilles());
		mappeAttaques.put("bulle", new AttaqueBulle());
		mappeAttaques.put("coupDeTete", new AttaqueCoupDeTete());
		mappeAttaques.put("croquer", new AttaqueCroquer());

		
		final ArrayList<Pokemon> pokemonList = new ArrayList<>();
		try(FileReader lecteur = new FileReader("src/tp13/InputFile.txt")){
			Scanner s = new Scanner(lecteur);
			while(s.hasNext()) {
				String name = s.next();
				String type = s.next();
				int niveau = s.nextInt();
				boolean diurne = s.nextBoolean();
				int attaque = s.nextInt();
				int defense = s.nextInt();
				int attaqueSpeciale = s.nextInt();
				int defenseSpeciale = s.nextInt();
				ArrayList<Attaque> sesAttaques = new ArrayList<>();
				String nomAttaque = s.next();
				while(! nomAttaque.equals("END")) {
					sesAttaques.add(mappeAttaques.get(nomAttaque).genererMemeAttaque(true));
					nomAttaque = s.next();
				}
				Attaque[] sesAttaquesTableau = new Attaque[sesAttaques.size()];
				for(int i = 0; i<sesAttaques.size(); i++) {
					sesAttaquesTableau[i] = sesAttaques.get(i);
				}
				pokemonList.add(new Pokemon(mapPokemons.get(name), name, type, niveau, diurne, attaque, defense, attaqueSpeciale, defenseSpeciale, sesAttaquesTableau));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// nos pokemons
		final Pokemon piplup = pokemonList.get(0);
		
		piplup.genererMemePokemon(true);
		
		final Pokemon rowlet = pokemonList.get(1);
		final Pokemon totodile = pokemonList.get(2);

		
		final Joueur moi = new Joueur("Onete", "Cristina", 20, new Pokemon[] {piplup, null, null, null, null});

		final Nourriture tartiflette = new Nourriture("tartiflette",20, 35, new String[]{"DRAGON", "FEU", "COMBAT", "NORMAL", "EAU", "ELECTRIQUE"});
		final Nourriture ratatouille = new Nourriture("ratatouille", 50, 10, new String[]{"PLANTE","EAU","VOL","FEU","NORMAL","ELECTRIQUE","COMBAT"});
						
		final Gourmandise barreChocolatee = new Gourmandise("Barre Chocolatee",10, 20, new String[] {"DRAGON", "FEU", "COMBAT", "EAU", "ELECTRIQUE"}, 7);
		final PotionMagique mojito = new PotionMagique("mojito", 2);

		final Nourriture[] tableauNourriture = new Nourriture[] {tartiflette, ratatouille, barreChocolatee, mojito};

		final Jouet balle = new Jouet("balle", 20, 10, 10, 5);
		final Outil marteau = new Outil("Le petit marteau des Merveilles", 10, 2);
		
		

		Scanner lecteurBataille = new Scanner(System.in);
		System.out.println();
		
		
		
		final Pokemon p1 = piplup;
		final Pokemon p2 = rowlet;
		
		//final Bataille bataille = new Bataille(piplup, rowlet);
		
		//bataille.run();
		
		final Scanner scanner = new Scanner(System.in);

		final Random seedAlea = new Random();
		int aleaPokemon;
		
		double alea;
		boolean generer = false;
		Item[] nourritureGeneree = new Nourriture[tableauNourriture.length];
		Pokemon pokemonGenere;
		
		System.out.println("Continuer ? Pour oui, appuyez sur n'importe quelle touche. Pour non, tapez stop.");
		String reponse = scanner.next();
		int reponseEntiere;
		while(!reponse.equals("stop")) {			
			alea = Math.random()*100;
			for (int j = 0; j<tableauNourriture.length; j++) {
				if (alea < tableauNourriture[j].getFrequence()) {
					generer = true;
				}
				else {
					generer = false;
				}
				nourritureGeneree[j] = tableauNourriture[j].genererMemeItem(generer);
				if (generer) {
					System.out.println("Vous avez trouvé un.e/du/de la " + tableauNourriture[j].getNom() + ". Voulez-vous la prendre ? Ecrivez oui si c'est le cas.");
					reponse = scanner.next();
					if (reponse.equals("oui")) {
						Nourriture nourriture = (Nourriture)nourritureGeneree[j];
						moi.ajouterProvision(nourriture);
					}
				}
					
			}
			
			// HERE FOR THE POKEMON TO BE GENERATED.
			
			aleaPokemon = seedAlea.nextInt(pokemonList.size());
			
			if(alea < 100/(pokemonList.get(aleaPokemon).getNiveau())) {
				generer = true;
			}
			else {
				generer = false;
			}
			
			pokemonGenere = pokemonList.get(aleaPokemon).genererMemePokemon(generer);
			if(generer) {
				System.out.println("Vous avez rencontre un pokemon ! " + " \n "  + pokemonGenere.getNom() + ", de niveau " + pokemonGenere.getNiveau() + "\n Voulez-vous le capturer (oui/non)?");
				reponse = scanner.next();
				if (reponse.equals("oui")) {
					for (int i = 0; i < moi.getPokemons().length; i++) {
						if (null != moi.getPokemons()[i]) {
							System.out.println("Index " + i + " : " +moi.getPokemons()[i]);
						}
					}
					System.out.println("Quel pokemon voulez-vous utiliser dans cette bataille ? Donnez son index !");
					reponseEntiere = scanner.nextInt();
					if (reponseEntiere < 0 || reponseEntiere >= moi.getPokemons().length || null == moi.getPokemons()[reponseEntiere]) {
						System.out.println("Mauvais index ! Vous perdez votre tour !");
					}
					else {
						Bataille nouvelleBataille = new Bataille(moi.getPokemons()[reponseEntiere], pokemonGenere);
						nouvelleBataille.run();
					}
					
				}
			}
			
			
			System.out.println("Tapez 1,2,3,4 ou stop pour : \n 1. regarder vos pokemons; \n 2. caresser vos pokemons; "
						+ "\n 3. regarder vos provisions; \n 4. nourrir vos pokemons; \n stop : s'arreter.");
			reponse = scanner.next();
			if (reponse.equals("1")) {
				for (int i = 0; i < moi.getPokemons().length; i++) {
					if (null != moi.getPokemons()[i]) {
						System.out.println("Index " + i + " : " +moi.getPokemons()[i]);
					}
				}
			}
			else {
				if (reponse.equals("2")) {
					System.out.println("Quel pokemon voulez-vous caresser ? Donnez son index entre 0 et " + (moi.getPokemons().length-1));
					reponseEntiere = scanner.nextInt();
					if(reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
						moi.caresserPokemon(moi.getPokemons()[reponseEntiere]);
					}
					else {
						System.out.println("Mauvaise valeur. Vous perdez votre tour.");
					}
				}
				else {
					if (reponse.equals("3")) {
						moi.afficherProvisions();
					}
					else {
						if (reponse.equals("4")) {
							System.out.println("Quel pokemon voulez-vous nourrir ? Donnez son index entre 0 et 4.");
							reponseEntiere = scanner.nextInt();
							if(reponseEntiere >= 0 && reponseEntiere < moi.getPokemons().length) {
								System.out.println("Quelle provision voulez-vous utiliser ? Donnez son index entre 0 et " + (moi.getProvisions().length-1));
								int deuxiemeReponse = scanner.nextInt();
								if(deuxiemeReponse >= 0 && deuxiemeReponse < moi.getProvisions().length) {
									moi.nourrirPokemon(moi.getPokemons()[reponseEntiere], moi.getProvisions()[deuxiemeReponse]);
								}
								else {
									System.out.println("Mauvaise valeur. Vous perdez votre tour.");
								}
							}
							else {
								System.out.println("Mauvaise valeur. Vous perdez votre tour.");
							}
						}
					}
				}
			}
		}
				
		scanner.close();

		// TP 10

		try {
			moi.getPokedex().charger("C:/Users/crist/eclipse-workspace/TP10/src/tp10/monPokedex.txt");
		}
		catch (FileNotFoundException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". Ce fichier n'existe encore pas, mais il sera genere lors de votre prochaine session.");
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch (InputMismatchException e) {
			System.err.println("Je ne peux pas charger le pokedex du joueur " +moi.getPrenom() + " " +moi.getNom() + ". Le fichier a un mauvais format. " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			moi.getPokedex().sauvegarder("C:/Users/crist/eclipse-workspace/TP10/src/tp10/monPokedex.txt");
		}
		catch (IOException e) {
			System.err.println("Je ne peux pas sauvegarder le pokedex du joueur " + moi.getPrenom() + " " +moi.getNom());
			e.printStackTrace();
		}

		
	}
	

}
