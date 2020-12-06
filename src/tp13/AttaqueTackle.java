package tp13;

public class AttaqueTackle extends AttaquePhysique {
	public AttaqueTackle() {
		super("tackle", 40, 100, 35);
	}
	
	@Override
	public AttaqueTackle genererMemeAttaque(boolean generer) {
		if(generer) {
			return new AttaqueTackle();
		}
		else {
			return null;
		}
	}
	
}
