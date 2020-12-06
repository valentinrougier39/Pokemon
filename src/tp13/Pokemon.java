package tp13;

import java.util.List;
import java.util.ArrayList;


public class Pokemon {

	//ajoute en TP 10
	
	private int numeroPokedex;
	private String nom;
	private String type;
	private int niveau;
	private boolean diurne;
	private String nomDonne;
	private Joueur monJoueur;
	private int appetit;
	private int loyaute;
	
	// ajoutes en TP 6
	
	private int attaque;
	private int defense;
	private int attaqueSpeciale;
	private int defenseSpeciale;
	private int hp;

	private List<Attaque> attaques = new ArrayList<Attaque>();

	
	public Pokemon(int numeroPokedex, String nom, String type, int niveau, 
			boolean diurne, String nomDonne, Joueur monJoueur,
			int attaque, int defense, int attaqueSpeciale, 
			int defenseSpeciale, Attaque[] attaques) {
		this.numeroPokedex = numeroPokedex;
		this.nom = nom;
		this.type = type;
		this.niveau = niveau;
		this.diurne = diurne;
		this.nomDonne = nomDonne;
		this.monJoueur = monJoueur;
		this.appetit = 50;
		this.loyaute = 0;
		
		// ajoutees en TP 6
		this.attaque = attaque;
		this.defense = defense;
		this.attaqueSpeciale = attaqueSpeciale;
		this.defenseSpeciale = defenseSpeciale;
		this.hp = 30; 
		
		for(int i = 0; i < attaques.length; i++) {
			this.ajouterAttaque(attaques[i]);;
		}
		
		if (attaques.length > 4) {
			System.err.println("Liste trop longue, j'ai seulement pris en compte les 4 premieres attaques.");
		}
	}
	
	public Pokemon(int numeroPokedex, String nom, String type, int age, 
			boolean diurne,int attaque, int defense, 
			int attaqueSpeciale, int defenseSpeciale,
			Attaque[] attaques) {
		this(numeroPokedex, nom, type, age, diurne, null, null, attaque, defense, attaqueSpeciale,defenseSpeciale, attaques);
	}
	
	
	private int trouverAttaque(Attaque attaque) {
		
		return this.attaques.indexOf(attaque);
	}
	
	public void ajouterAttaque(Attaque attaque) {
		if (attaque.isCompatible(this)) {
			if (this.attaques.size() < 4) {
				this.attaques.add(attaque);
			}
			else {
				System.err.println("Trop d'attaques déjà dans la liste d'attaques !");
			}
			
		}
	}
	
	public void ajouterAttaque(Attaque attaque, int i) {
		if(i >= 0 && i < 4) {
			if (attaque.isCompatible(this)) {
				this.attaques.add(i, attaque);
			}
		}
	}
	
	public void rechargerAttaques() {
		for (int i = 0; i<this.attaques.size(); i++) {
			if(null != this.attaques.get(i)) {
				this.attaques.get(i).resetNombreRepetitions();
			}
		}
	}
	
	public void blessure(int dommage) {
		this.hp -= dommage;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
	
	public boolean sEstEvanoui() {
		return (this.hp == 0);
	}
	
	public void utiliserAttaque(int index, Pokemon victime) {
		if(! this.sEstEvanoui() && index >= 0 && index < this.attaques.size() && null != this.attaques.get(index)) {
			this.attaques.get(index).utiliserAttaque(this, victime);
		
		}
	}
	
	public void afficherEtatAttaques() {
		String attaques = "";
		for(int i = 0; i < this.attaques.size(); i++) {
			if (null != this.attaques.get(i)) {
				attaques += i + " : " + this.attaques.get(i).getNom() + ", " + this.attaques.get(i).getRepetitionsRestantes() + "/" + this.attaques.get(i).getNombreRepetitions() + "\n"; 
			}
		}
		System.out.println(attaques);
	}
	
	public void utiliser(Utilisable item) {
		if(null != item) {
			if (null != this.monJoueur) {
				int index = this.monJoueur.trouverPokemon(this);
				if (index != -1) {
					item.utiliser(this.monJoueur, index);
					System.out.println("Ce pokemon a utilise cet item !");
				}
				else {
					System.out.println("Vous n'etes pas le maitre de ce pokemon pour lui donner des objets !");
				}
				
			}
			else {
				System.out.println("Ce pokemon n'a pas de maitre. On ne peut pas donner des objets utilisables a un pokemon dont on n'est pas le maitre !");
			}
		}
	}

	public void miseANiveau() {
		this.niveau += 1;
	}

	public String sePresente() {
		if (null != this.monJoueur) {
			if (null != this.nomDonne) {
				return (this.nomDonne + "est un pokemon de genre " + this.nom + ", du type" + this.type + ", qui a le niveau  " + this.niveau);
			}
			else {
				return ("Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon appartient a " + this.monJoueur.getNom() + this.monJoueur.getPrenom());
			}
		}
		return ("Voici un pokemon du genre " + this.nom + ", du type " + this.type + ", qui a le niveau " + this.niveau + ". Ce pokemon n'a pas encore de maitre.");
	}
	
	public void direBonjour(String periode) {
		if (periode.equals("jour")) {
			if (this.diurne) {
				System.out.println(this.nom +" dit bonjour !");
			}
			else {
				System.out.println(this.nom + " dit zzzzzzzzzzzzz !");
			}
		}
		else {
			if (this.diurne) {
				System.out.println(this.nom + " dit zzzzzzzzzzzzz !");
			}
			else {
				System.out.println(this.nom + " dit bonsoir !");
			}
		}
		
	}
	
	public void monterAppetit(int difference) {
		this.appetit+= difference;
		if (this.appetit > 100) {
			this.appetit = 100;
		}
	}
	
	public void baisserAppetit(int difference) {
		this.appetit -= difference;
		if (this.appetit < 0) {
			this.appetit = 0;
		}
	}
	
	public void monterLoyaute(int difference) {
		this.loyaute += difference;
		if (this.loyaute > 100) {
			this.loyaute = 100;
		}
	}
	
	public void baisserLoyaute(int difference) {
		this.loyaute -= difference;
		if (this.loyaute < 0) {
			this.loyaute = 0;
		}
	}
	
	public Pokemon genererMemePokemon(boolean generer) {
		if (generer) {
			Attaque[] attaques = new Attaque[this.attaques.size()];
			for(int i = 0; i<this.attaques.size(); i++) {
				attaques[i] = this.attaques.get(i);
			}
			
			return new Pokemon(this.numeroPokedex, this.nom, this.type, this.niveau, 
			this.diurne, this.attaque, this.defense, this.attaqueSpeciale, this.defenseSpeciale,
			attaques);
		}
		else {
			return null;
		}
	}

	
	public int getNumeroPokedex() {
		return this.numeroPokedex;
	}
	
	public String getNomDonne() {
		return this.nomDonne;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getNiveau() {
		return this.niveau;
	}
	
	public Joueur getMonJoueur() {
		return this.monJoueur;
	}
	
	public void setNomDonne(String nomDonne) {
		this.nomDonne = nomDonne;
	}
	
	public boolean isDiurne() {
		return this.diurne;
	}
	
	public void setMonJoueur(Joueur monJoueur) {
		this.monJoueur = monJoueur;
	}
	
	public int getAppetit() {
		return this.appetit;
	}
	
	public int getLoyaute() {
		return this.loyaute;
	}
	
	public int getAttaque() {
		return this.attaque;
	}
	
	public int getDefense() {
		return this.defense;
	}
	
	public int getAttaqueSpeciale() {
		return this.attaqueSpeciale;
	}
	
	public int getDefenseSpeciale() {
		return this.defenseSpeciale;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public List<Attaque> getAttaques() {
		return this.attaques;
	}
	
	public String toString() {
		String attaques = "{";
		for (int i = 0; i < this.attaques.size()-1; i++) {
			if (this.attaques.get(i) != null){
				attaques += this.attaques.get(i) + ", ";
			}
			
		}
		
		if (this.attaques.get(this.attaques.size()-1) != null){
			attaques += this.attaques.get(this.attaques.size()-1);
		}
		
		attaques += "}";
		
		return("["+ this.numeroPokedex + " : " + "Nom : " + this.nom + "; Type : " + this.type + "; Niveau : " 
				+this.niveau + "; Diurne : " + this.diurne + "; nomDonne : " 
				+this.nomDonne + "; monJoueur : " + this.monJoueur + "; Appetit :" 
				+this.appetit + "; Loyaute :" + this.loyaute + "; Attaque" + this.attaque 
				+ "; Defense : " + this.defense + "; AttaqueSpeciale : " 
				+ "; DefenseSpeciale : " + this.defenseSpeciale + "; HP : " + this.hp
				+ "; Attaques : " + attaques + "]");
	}
	
}
