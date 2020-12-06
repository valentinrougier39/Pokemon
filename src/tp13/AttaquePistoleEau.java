package tp13;

public class AttaquePistoleEau extends AttaqueSpeciale {
	public AttaquePistoleEau() {
		super("pistole d'eau", new String[] {"EAU"}, 40, 100, 25);
	}

	@Override
	public AttaquePistoleEau genererMemeAttaque(boolean generer) {
		if(generer) {
			return new AttaquePistoleEau();
		}
		else {
			return null;
		}
	}

}
