package Algo_Genetiques;

import java.util.Map;

public class Individu_SAD implements Individu {

	boolean[] tab_binaire;

	double[] poids;
	int poids_max;
	
	private int[] parcours;
	
	/**
	 * Map containing the x and y coordinates of a set of cities.
	 */
	Map <Double, Double> coordonnees;
	
	public Individu_SAD(int poids_max, double poids[]){
		tab_binaire = new boolean[poids.length];
		for(int i = 0; i < tab_binaire.length; i++) {
			tab_binaire[i] = (Math.random()>0.5);
		}
		this.poids = poids;
		this.poids_max = poids_max;
	}

	public Individu_SAD(int poids_max, double poids[], boolean[] tab_binaire_arg){
		tab_binaire = new boolean[poids.length];
		for(int i = 0; i < tab_binaire.length; i++) {
			tab_binaire[i] = tab_binaire_arg[i];
		}
		this.poids = poids;
		this.poids_max = poids_max;
	}

	/**
	 * renvoie l'adaptation de cet individu
	 */
	public double adaptation() {
		// Calculer la valeur d'adaptation de cet individu
		double adaptation = 0;

		// Parcourir les choix binaires de l'individu
		for (int i = 0; i < tab_binaire.length; i++) {
			// Si l'objet est choisi, ajouter son poids à l'adaptation
			if (tab_binaire[i]) {
				adaptation += poids[i];
			}
		}

		// Si le poids total dépasse la limite, mettre l'adaptation à 0
		if (adaptation > poids_max) {
			adaptation = 0;
		}

		// Renvoyer la valeur d'adaptation calculée
		return adaptation;
	}

	/**
	 * renvoie un tableau de 2 individus constituant les
	 * enfants de la reproduction entre this et conjoint
	 * @param conjoint à accoupler avec l'objet courant
	 * @return tableau des 2 enfants
	 */
	public Individu[] croisement(Individu conjoint) {
		// Créer les deux enfants
		int taille = tab_binaire.length;
		boolean[] enfant0 = new boolean[taille];
		boolean[] enfant1 = new boolean[taille];
		Individu_SAD[] enfants = new Individu_SAD[2];

		// Choisir un point de croisement aléatoire
		int pointCroisement = (int) (Math.random() * tab_binaire.length);

		// Copier les choix binaires avant le point de croisement
		for (int i = 0; i < pointCroisement; i++) {
			enfant0[i] = this.tab_binaire[i];
			enfant1[i] = ((Individu_SAD) conjoint).tab_binaire[i];
		}

		// Copier les choix binaires après le point de croisement
		for (int i = pointCroisement; i < tab_binaire.length; i++) {
			enfant0[i] = ((Individu_SAD) conjoint).tab_binaire[i];
			enfant1[i] = this.tab_binaire[i];
		}

		// Renvoyer les enfants
		enfants[0] = new Individu_SAD(poids_max, poids, enfant0);
		enfants[1] = new Individu_SAD(poids_max, poids, enfant1);
		return enfants;
		
	}

	/**
	 * applique l'opérateur de mutation
	 * associé à la probabilité prob
	 */
	public void mutation(double prob) {
		for(int i = 0; i < tab_binaire.length; i++) {
			if(Math.random() < prob) {
				tab_binaire[i] = !tab_binaire[i];
			}
		}
	}
	
	/**
	 * Returns an array of the x coordinates of the cities in the route.
	 * @return Array of x coordinates.
	 */
	public double[] get_coord_x(){
		double[] coord_x = new double[coordonnees.size()];
		int i = 0;
		for (double key : coordonnees.keySet()) {
			coord_x[i] = key;
			i++;
		}
		return coord_x;
	}

	/**
	 * Returns an array of the y coordinates of the cities in the route.
	 * @return Array of y coordinates.
	 */
	public double[] get_coord_y(){
		double[] coord_x = get_coord_x();
		double[] coord_y = new double[coord_x.length];
		for(int i = 0; i < coord_x.length; i++) {
			coord_y[i] = coordonnees.get(coord_x[i]);
		}
		return coord_y;
	}	
	
	private double distance(int i, int j) {
		// Get the x and y coordinates of the two cities
		double[] xCoords = get_coord_x();
		double[] yCoords = get_coord_y();
		double x1 = xCoords[i];
		double y1 = yCoords[i];
		double x2 = xCoords[j];
		double y2 = yCoords[j];

		// Calculate the distance between the two cities using the Pythagorean theorem
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

	private double gain(int i, int j){
		int nb_villes = parcours.length;
		double gain = distance(parcours[i], parcours[(j+1)%nb_villes])
				+ distance(parcours[(i+nb_villes-1)%nb_villes], parcours[j])
				- distance(parcours[(i+nb_villes-1)%nb_villes], parcours[i])
				- distance(parcours[j], parcours[(j+1)%nb_villes]);
		return gain;
	}
	
	public void optim_2opt(){
		for(int i = 0; i < parcours.length; i++){
			for(int j =i+1 ; j < parcours.length; j++){
				if (gain(i,j) < 0){ 
					for(int k = 0 ; k < (j-i+1)/2; k++){
						int tmp = parcours[i+k];
						parcours[i+k] = parcours[j-k];
						parcours[j-k] = tmp;
					}
				}
			}
		}		    
	}

}
