package tp13;

public abstract class Item {
	protected String nom;
	protected int frequence;
	protected int nombreUtilisations;
	protected int utilisationsRestantes;
	
	public Item(String nom, int frequence, int nombreUtilisations) {
		this.nom = nom;
		this.frequence = frequence;
		this.nombreUtilisations = nombreUtilisations;
		this.utilisationsRestantes = nombreUtilisations;
	}
	
	public void monterUtilisationsRestantes(int difference) {
		this.utilisationsRestantes += difference;
		if(this.utilisationsRestantes > this.nombreUtilisations) {
			this.utilisationsRestantes = this.nombreUtilisations;
		}
	}
	
	public void baisserUtilisationsRestantes(int difference) {
		this.utilisationsRestantes -= difference;
		if(this.utilisationsRestantes < 0) {
			this.utilisationsRestantes = 0;
		}
	}
	
	public void resetUtilisationsRestantes() {
		this.utilisationsRestantes = this.nombreUtilisations;
	}
	
	public abstract Item genererMemeItem(boolean generer);
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getFrequence() {
		return this.frequence;
	}
	
	public int getNombreUtilisations () {
		return this.nombreUtilisations;
	}
	
	public int getUtilisationsRestantes() {
		return this.utilisationsRestantes;
	}
	
	public String toString() {
		return (this.nom + " : " + this.frequence + ", " + this.utilisationsRestantes + "/" + this.nombreUtilisations);
	}
	
	
}
