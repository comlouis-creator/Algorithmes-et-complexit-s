package Algo_Genetiques;

import java.util.List;
import java.util.ArrayList;

public class Population<Indiv extends Individu> {
	
	// Liste contenant les différents individus d'une génération
	private List<Indiv> population;
	
	/**
	 * construit une population à partir d'un tableau d'individu
	 */
	public Population(Indiv[] popu){
		//TODO
		population = new ArrayList<Indiv>();
		for(int i = 0; i < popu.length; i++) {
			population.add(popu[i]);
		}
	}
	
	/**
	 * sélectionne un individu (sélection par roulette par exemple, cf TD)
	 * @param adapt_totale somme des adaptations de tous les individus (pour ne pas avoir à la recalculer)
	 * @return indice de l'individu sélectionné
	 */
	public int selection(double adapt_totale){
		//TODO
		double u = Math.random();
		int i = 0;
		double tmp = 0, somme = 0;
		for(int j = 0; j < population.size(); j++) {
			somme += population.get(j).adaptation();
		}
		while(tmp/somme < u) {
			tmp += population.get(i).adaptation();
			i++;
		}
		return i-1;
	}
	
	/**
	 * remplace la génération par la suivante
	 * (croisement + mutation)
	 * @param prob_mut probabilité de mutation
	 */
	@SuppressWarnings("unchecked")
	public void reproduction(double prob_mut) {
		
		/***** on construit la nouvelle génération ****/
		List<Indiv> new_generation = new ArrayList<Indiv>();
		
		/* élitisme */
		//TODO (dans un second temps)
		

		// tant qu'on n'a pas le bon nombre 
		while (new_generation.size()<population.size()){
			// on sélectionne les parents
			//TODO
			double adapt_totale = 0;
			for(int i = 0; i < population.size(); i++) {
				adapt_totale += population.get(i).adaptation();
			}
			Indiv parent1 = population.get(selection(adapt_totale));
			Indiv parent2 = population.get(selection(adapt_totale));
			
			// ils se reproduisent
			//TODO
			Individu [] children = parent1.croisement(parent2);
			
			// on les ajoute à la nouvelle génération
			//TODO
			new_generation.add((Indiv) children[0]);
		}
		
		// on applique une éventuelle mutation à toute la nouvelle génération
		//TODO
		for(int i = 0; i < new_generation.size(); i++ ) {
			new_generation.get(i).mutation(prob_mut);
		}

		//on remplace l'ancienne par la nouvelle
		population = new_generation;
	}
	
	/**
	 * renvoie l'individu de la population ayant l'adaptation maximale
	 */	
	public Indiv individu_maximal(){
		//TODO
		Indiv max = population.get(0);
		for(int i = 1; i < population.size(); i++) {
			if(population.get(i).adaptation() > max.adaptation()) {
				max = population.get(i);
			}
		}
		return max;
	}

	/**
	 * renvoie l'adaptation moyenne de la population
	 */
	public double adaptation_moyenne(){
		//TODO
		int somme = 0;
		for(int i = 0; i < population.size(); i++) {
			somme += population.get(i).adaptation();
		}
		return somme / population.size();
	}
	
	/**
	 * renvoie l'adaptation maximale de la population
	 */	
	public double adaptation_maximale(){
		//TODO
		double max = population.get(0).adaptation();
		for(int i = 1; i < population.size(); i++) {
			if(population.get(i).adaptation() > max) {
				max = population.get(i).adaptation();
			}
		}
		return max;
	}
	
}
