package tp13;

public class Joueur {
	private String nom;
	private String prenom;
	private int age;
	private Pokemon[] pokemons = new Pokemon[5];
	private Nourriture[] provisions;
	private Item[] sac;
	private Pokedex pokedex;
	
	public Joueur(String nom, String prenom, int age, Pokemon[] pokemons) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.pokedex = new Pokedex();

		int tailleMinimale = this.pokemons.length;
		if (pokemons.length < tailleMinimale) {
			tailleMinimale = pokemons.length;
		}
		
		for(int i = 0; i < tailleMinimale; i++) {
			this.pokemons[i] = pokemons[i];
		}
		
		this.provisions = new Nourriture[10];
		for (int i = 0; i < pokemons.length; i++) {
			if (null!=this.pokemons[i]) {
				this.pokemons[i].setMonJoueur(this);
			}
			
		}
		for (int i = 0; i < this.pokemons.length; i++) {
			this.rencontrer(this.pokemons[i]);
		}
		this.sac = new Item[15];
	}
	
	public Joueur(String nom, String prenom, int age) {
		this(nom, prenom, age, new Pokemon[] {null, null, null, null, null});
	}

	// TP 10
	public void rencontrer (Pokemon pokemon) {
		this.pokedex.rencontrer(pokemon);
	}


	public void donnerItem(int indexPokemon, int indexItem) {
		if(indexPokemon >= 0 && indexPokemon < this.pokemons.length && indexItem >= 0 && indexItem < this.sac.length) {
			if (null != this.pokemons[indexPokemon] && null != this.sac[indexItem]) {
				if (this.sac[indexItem] instanceof Utilisable) {
					Utilisable item = (Utilisable) this.sac[indexItem];
					item.utiliser(this, indexPokemon);
					if (this.sac[indexItem].getUtilisationsRestantes() == 0) {
						this.sac[indexItem] = null;
					}
				}
				else {
					System.out.println("Cet item n'est pas utilisable.");
				}
			}
			else {
				System.out.println("Variable null a cet emplacement !");
			}
		}
		else {
			System.out.println("Index non-valide");
		}
	}
	
	public void modifierItem(int indexChangeur, int indexAModifier) {
		if(indexChangeur >= 0 && indexChangeur < this.sac.length && indexAModifier >= 0 && indexAModifier < this.sac.length) {
			if (null != this.sac[indexChangeur] && null != this.sac[indexAModifier]) {
				if (this.sac[indexChangeur] instanceof ChangerItems && this.sac[indexAModifier] instanceof Modifiable) {
					ChangerItems itemChangeur = (ChangerItems) this.sac[indexChangeur];
					Modifiable itemAModifier = (Modifiable) this.sac[indexAModifier];
					itemChangeur.changer(itemAModifier);
					if (this.sac[indexChangeur].getNombreUtilisations() == 0) {
						this.sac[indexChangeur] = null;
					}
				}
			}
		}
	}
	
	public int trouverPokemon(Pokemon pokemon) {
		
		int iterateur = 0;
		
		while (iterateur<this.pokemons.length) {
			if(this.pokemons[iterateur] == pokemon) {
				return iterateur;
			}
			iterateur++;
		}
		return -1;
	}

	private int trouverItem(Item item) {
		int iterateur = 0;
		
		while (iterateur<this.sac.length) {
			if(this.sac[iterateur] == item) {
				return iterateur;
			}
			iterateur++;
		}
		return -1;
	}
	
	private int trouverProvision(Nourriture nourriture) {
		
		int iterateur = 0;
		
		while (iterateur<this.provisions.length) {
			if(this.provisions[iterateur] == nourriture) {
				return iterateur;
			}
			iterateur++;
		}
		return -1;
	}
	
	public void ajouterItem(Item item) {
		int positionLibre = this.trouverItem(null);
		if (positionLibre != -1) {
			this.sac[positionLibre] = item;
		}
		else {
			System.out.println("Vous n'avez plus de place pour cette nourriture.");
		}

	}
	
	public void lacherItem(Item item) {
		int positionLibre = this.trouverItem(item);
		if (positionLibre != -1) {
			this.sac[positionLibre] = null;
		}
		else {
			System.out.println("Vous n'avez plus de place pour cette nourriture.");
		}
	}
	
	public void ajouterProvision(Nourriture nourriture) {
		int positionLibre = this.trouverProvision(null);
		if (positionLibre != -1) {
			this.provisions[positionLibre] = nourriture;
		}
		else {
			System.out.println("Vous n'avez plus de place pour cette nourriture.");
		}
	}

	public void nourrirPokemon(Pokemon pokemon, Nourriture nourriture) {
		if (pokemon == null || nourriture == null || this.trouverPokemon(pokemon) == -1 || this.trouverProvision(nourriture) == -1) {
			System.out.println("Parametres non valides.");
		}
		else {
			pokemon.utiliser(nourriture);
			this.provisions[this.trouverProvision(nourriture)] = null;
		}
	}
	
	public void afficherProvisions() {
		for (int i = 0; i < this.provisions.length; i++) {
			if (null != this.provisions[i]) {
				System.out.println(i + " : " + this.provisions[i]);
			}
		}
	}
	
	public void afficherSac() {
		for (int i = 0; i < this.sac.length; i++) {
			if (null != this.sac[i]) {
				System.out.println(i + " : " + this.sac[i]);
			}
		}
	}
	
	public void caresserPokemon(Pokemon pokemon) {
		if (null != pokemon && this == pokemon.getMonJoueur()) {
			pokemon.monterLoyaute(1);
			if (pokemon.getLoyaute() == 100) {
				System.out.println("Oui, moi je t'aime aussi !");
			}
			else {
				System.out.println("Mmmm, ca sent bien ! Et sous mon oreille gauche ?");
			}
		}
		else {
			if (this != pokemon.getMonJoueur()) {
				this.rencontrer(pokemon);
			}
		}
	}
	
	public void capturerPokemon(Pokemon pokemon) {
		if (pokemon != null) {
			this.rencontrer(pokemon);
		}
		if (pokemon.getMonJoueur()!=null ) {
			System.out.println(new DejaUnMaitreException());
			//System.out.println("Vous ne pouvez pas capturer le pokemon d'un autre joueur.");
		}
		else {
			int premierePlaceDisponible = this.trouverPokemon(null);
			if (premierePlaceDisponible != -1) {
				this.pokemons[premierePlaceDisponible] = pokemon;
				System.out.println("Ce pokemon devient la propriete du joueur " + this.nom);
				pokemon.setMonJoueur(this);
				pokemon.baisserLoyaute(100); // car la methode en question remet la valeur a zero si le resultat est negatif
				pokemon.baisserAppetit(100);
				pokemon.monterAppetit(10);
			}
			else {
				//System.out.println("Vous n'avez pas la place pour accueillir ce pokemon ! Vous devrez renoncer a un autre pokemon pour capturer celui-ci.");
				
				System.out.println(new PlusDePlaceException());
			}
		}
		
	}

	public void libererPokemon(Pokemon pokemon) {
		int positionPokemon = this.trouverPokemon(pokemon);
		if(positionPokemon != -1) {
			this.pokemons[positionPokemon] = null;
			System.out.println("Ce pokemon n'est plus la propriete du joueur " + this.nom);
			pokemon.setMonJoueur(null);
			pokemon.setNomDonne(null);
			pokemon.baisserLoyaute(100); // car la methode en question remet la valeur a zero si le resultat est negative
			pokemon.baisserAppetit(100);
			pokemon.monterAppetit(10);
		}
		else {
			//System.out.println("Vous n'etes pas le maitre de ce pokemon.");
			System.out.println(new LibererDUnAutreMaitreException());
			this.rencontrer(pokemon);
		}
	}
	
	public void donnerNom(Pokemon pokemon, String nomDonne) {
		if (pokemon != null) {
			int positionPokemon = this.trouverPokemon(pokemon);
			if (positionPokemon != -1) {
				if (null == pokemon.getNomDonne()) {
					pokemon.monterLoyaute(10);
				}
				else {
					pokemon.baisserLoyaute(10);
				}
				pokemon.setNomDonne(nomDonne);
			}
			else {
				System.out.println("Vous ne pouvez pas nomme ce pokemon car vous n'etes pas son maitre !");
				this.rencontrer(pokemon);
			}
		}
	}
		
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public Pokemon[] getPokemons() {
		return this.pokemons;
	}
	
	public Nourriture[] getProvisions() {
		return this.provisions;
	}
	
	public Item[] getSac() {
		return this.sac;
	}
	
	public Pokedex getPokedex() {
		return this.pokedex;
	}
		
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String toString() {
		return ("[ Nom : " + this.nom + "; prenom : " + this.prenom + "; age" + this.age + " ]");
	}

}
