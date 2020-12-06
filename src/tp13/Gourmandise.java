package tp13;


public class Gourmandise extends Nourriture {
	private int apportLoyaute;
	
	public Gourmandise(String nom, int frequence, int apport, String[] compatibilites, int apportLoyaute) {
		super(nom, frequence, apport, compatibilites);
		this.apportLoyaute = apportLoyaute;
	}
	
	
	@Override
	public Item genererMemeItem(boolean generer) {
		if(generer) {
			return new Gourmandise(this.nom, this.frequence, this.apport, this.compatibilites, this.apportLoyaute);
		}
		return null;
	}
	
	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (null != joueur && indexPokemon > 0 && indexPokemon < joueur.getPokemons().length) {
			if (null != joueur.getPokemons()[indexPokemon] && this.getUtilisationsRestantes() > 0) {
				joueur.getPokemons()[indexPokemon].baisserAppetit(this.getApport());
				joueur.getPokemons()[indexPokemon].monterLoyaute(this.apportLoyaute);
				this.baisserUtilisationsRestantes(1);
				
			}
		}
			}

	
	public int getApportLoyaute() {
		return this.apportLoyaute;
	}
	
	
	
	@Override
	public String toString() {
		return (super.toString() + ", " + this.apportLoyaute); 
	}
}
