package tp13;
public class Jouet extends Item implements Utilisable, Modifiable {

	private int apportAppetit;
	private int apportLoyaute;
	
	public Jouet(String nom, int frequence, int nombreUtilisations, int apportAppetit, int apportLoyaute) {
		super(nom, frequence, nombreUtilisations);
		this.apportAppetit = apportAppetit;
		this.apportLoyaute = apportLoyaute;
	}
	
	
	@Override
	public void modifier() {
		this.setNom(this.nom + " magique");
		this.apportAppetit += 10;
		this.apportLoyaute += 5;
		this.resetUtilisationsRestantes();
		System.out.println("Quelle transformation !");
	}

	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (null != joueur && indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length) {
			if (null != joueur.getPokemons()[indexPokemon] && this.utilisationsRestantes > 0) {
				joueur.getPokemons()[indexPokemon].monterAppetit(this.apportAppetit);
				joueur.getPokemons()[indexPokemon].monterLoyaute(this.apportLoyaute);
				this.baisserUtilisationsRestantes(1);
				System.out.println("Wow, j'adore ce jouet ! On joue encore une fois ?");
			}
		}

	}

	@Override
	public Item genererMemeItem(boolean generer) {
		if(generer) {
			return new Jouet(this.nom, this.frequence, this.nombreUtilisations, this.apportAppetit, this.apportLoyaute);
		}
		return null;
	}
	
	public int getApportAppetit() {
		return this.apportAppetit;
	}
	
	public int getApportLoyaute() {
		return this.apportLoyaute;
	}

	public String toString() {
		return (super.toString() + ", " + this.apportAppetit + ", " + this.apportLoyaute);
	}
}
