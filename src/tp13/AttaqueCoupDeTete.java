package tp13;

public class AttaqueCoupDeTete extends AttaquePhysique {
	public AttaqueCoupDeTete() {
		super("coup de tete", 70, 100, 15);
	}
	
	@Override
	public AttaqueCoupDeTete genererMemeAttaque(boolean generer) {
		if(generer) {
			return new AttaqueCoupDeTete();
		}
		else {
			return null;
		}
	}

}
