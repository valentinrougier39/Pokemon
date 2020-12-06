package tp13;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class Pokedex {
	
	private Set<Integer> setPokemons;
	
	public Pokedex() {
		this.setPokemons = new TreeSet<>();
	}
	
	public void rencontrer(Pokemon pokemon) {
		if(null != pokemon) {
			this.setPokemons.add(pokemon.getNumeroPokedex());
		}
		else {
			System.out.println("Vous n'avez rencontre aucun pokemon la.");
		}
		//System.out.println(this.setPokemons);
	}
	
	public void rencontrer(Pokemon[] pokemons) {
		for (int i = 0; i < pokemons.length; i++) {
			this.rencontrer(pokemons[i]);
		}
	}
	
	public void charger(String chemin) throws IOException {
		try(FileReader lecteur = new FileReader(chemin)){
			Scanner s = new Scanner(lecteur);
			this.setPokemons.add(s.nextInt());
		}
		catch(IOException e) {
			//exo 5
			//e.printStackTrace();
			
			//exo 6
			throw e;
		}
				
	}
	
	public void sauvegarder(String chemin) throws IOException {
		try(FileWriter scribe = new FileWriter(chemin)) {
			PrintWriter afficheur = new PrintWriter(scribe);
			Iterator<Integer> monIterateur = this.setPokemons.iterator();
			while(monIterateur.hasNext()) {
				afficheur.println(monIterateur.next());
			}
		}
		catch(IOException e) {
			//exo 5
			//e.printStackTrace();
			
			//exo 6
			throw e;
		}
	}
	
	public Set<Integer> getSetPokemons() { 
		return this.setPokemons; 
	}
}
