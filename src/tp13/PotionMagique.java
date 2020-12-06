package tp13;

public class PotionMagique extends Nourriture {

	public PotionMagique(String nom, int frequence) {
		super(nom, frequence, 0, Nourriture.tousLesTypesDePokemons);
	}

	@Override
	public void utiliser(Joueur joueur, int indexPokemon) {
		if (null != joueur && indexPokemon > 0 && indexPokemon < joueur.getPokemons().length) {
			if (null != joueur.getPokemons()[indexPokemon] && this.utilisationsRestantes > 0) {
				joueur.getPokemons()[indexPokemon].miseANiveau();
				this.baisserUtilisationsRestantes(1);
			}
		}

	}
	
	@Override
	public Item genererMemeItem(boolean generer) {
		if(generer) {
			return new PotionMagique(this.nom, this.frequence);
		}
		return null;
	}
}
