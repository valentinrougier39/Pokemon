package tp13;

public class AttaqueFeinte extends AttaquePhysique {
	public AttaqueFeinte() {
		super("feinte", 30, 100, 10);
	}

	@Override
	public AttaqueFeinte genererMemeAttaque(boolean generer) {
		if(generer) {
			return new AttaqueFeinte();
		}
		else {
			return null;
		}
	}

}
