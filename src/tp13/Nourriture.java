package tp13;

public class Nourriture extends Item implements Utilisable {

	protected int apport;
	protected String[] compatibilites;
	public static String[] tousLesTypesDePokemons = {"PLANTE", "POISON", "FEU", "DRAGON", "VOL", "EAU", "INSECTE", "NORMAL", "FONCE", "ELECTRIQUE", "TELEPATIQUE", "GLACE", "ACIER", "TERRE", "FEE", "COMBAT", "ROCHE", "FANTOME"};
	
	public Nourriture(String nom, int frequence, int apport, String[] compatibilites) {
		super(nom, frequence, 1);
		this.apport = apport;
		this.compatibilites = compatibilites;
	}

	public boolean isCompatible(Pokemon pokemon) {
		
		boolean trouve = false;
		int i = 0;
		
		while(null != pokemon && i<this.compatibilites.length && !trouve) {
			if (this.compatibilites[i].equals(pokemon.getType())) {
				trouve = true;
			}
			i++;
		}
		
		if (null != pokemon && !trouve) {
			System.out.println("Un pokemon de type " + pokemon.getType() + " n'est pas compatible avec une nourriture de type " + this.getNom() + ".");
		}
		return trouve;
	}
	
	
	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (null != joueur && indexPokemon >= 0 && indexPokemon < joueur.getPokemons().length) {
			if (null != joueur.getPokemons()[indexPokemon] && this.utilisationsRestantes > 0) {
				joueur.getPokemons()[indexPokemon].baisserAppetit(this.apport);
				this.baisserUtilisationsRestantes(1);
			}
		}

	}

	@Override
	public Item genererMemeItem(boolean generer) {
		
		if(generer) {
			return new Nourriture(this.nom, this.frequence, this.apport, this.compatibilites);
		}
		return null;
	}
	
	public int getApport() {
		return this.apport;
	}
	
	public String[] getCompatibilites() {
		return this.compatibilites;
	}
	
	@Override
	public String toString() {
		String listeCompatibilites = "{";
		for (int i = 0; i < this.compatibilites.length-1; i++) {
			listeCompatibilites += this.compatibilites[i] + ",";
		}
		listeCompatibilites += this.compatibilites[this.compatibilites.length-1] + "}";
		
		return (super.toString() + ", " + this.apport + ", " + listeCompatibilites);
	}

}
