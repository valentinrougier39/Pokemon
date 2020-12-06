package tp13;

public class Outil extends Item implements ChangerItems {

	public Outil(String nom, int frequence, int nombreUtilisations) {
		super(nom, frequence, nombreUtilisations);
	}
	
	
	@Override
	public void changer(Modifiable item) {
		if (null != item && this.utilisationsRestantes > 0) {
			item.modifier();
			this.baisserUtilisationsRestantes(1);
		}

	}

	@Override
	public Item genererMemeItem(boolean generer) {
		if (generer) {
			return new Outil(this.nom, this.frequence, this.nombreUtilisations);
		}
		return null;
	}

}
