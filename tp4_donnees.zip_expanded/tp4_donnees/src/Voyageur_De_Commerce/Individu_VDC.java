package Voyageur_De_Commerce;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Algo_Genetiques.Individu;

/**
 * Class representing an individual in an optimization problem for the Traveling Salesman Problem (TSP).
 * Implements the Individu interface, which requires the implementation of an adaptation method and a croisement (crossover) method.
 * */
public class Individu_VDC implements Individu {

	/**
	 * Map containing the x and y coordinates of a set of cities.
	 */
	Map <Double, Double> coordonnees;

	private int[] parcours;

	//Constructeur
	public Individu_VDC(double[] coord_x, double[] coord_y) {
		// TODO 
		coordonnees = new HashMap<Double, Double>();
		for (int i = 0; i < coord_x.length; i++) {
			coordonnees.put(coord_x[i], coord_y[i]);
		}
	}

	/* Classes de l'interface Individu
	 */
	@Override
	public double adaptation() {
		// TODO 
		int res = 0;
		for(int i=0; i<coordonnees.size(); i++) {
			res += Math.sqrt(Math.pow(coordonnees.get(i), 2) + Math.pow(coordonnees.get(i+1), 2));
		}
		return res;
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

	@Override
	public Individu[] croisement(Individu conjoint) {
		// Generate a random crossover point
		int crossoverPoint = new Random().nextInt(1, parcours.length - 1);

		// Create the two offspring
		Individu[] offspring = new Individu[2];
		/*offspring[0] = new Individu_VDC(this.coordonnees);
		offspring[1] = new Individu_VDC(this.coordonnees);

		// Copy the first part of the tour from the first parent
		for (int i = 0; i < crossoverPoint; i++) {
			offspring[0].parcours[i] = this.parcours[i];
			offspring[1].parcours[i] = conjoint.parcours[i];
		}

		// Copy the remaining part of the tour from the second parent
		for (int i = crossoverPoint; i < this.parcours.length; i++) {
			offspring[0].parcours[i] = conjoint.parcours[i];
			offspring[1].parcours[i] = this.parcours[i];
		}

		// Optimize the tours using the 2-opt algorithm
		offspring[0].optim_2opt();
		offspring[1].optim_2opt();*/

		// Return the two offspring
		return offspring;
	}


	/**
	 * Mutates the route of this individual by swapping two cities at random.
	 * @param prob Probability of mutation.
	 */
	@Override
	public void mutation(double prob) {
		// TODO 
		Random r = new Random();
		int taille = coordonnees.size();
		int point_mutation = (int) (Math.random()*taille); // Generate a random mutation point
		int point_mutation2 = (int) (Math.random()*taille); // Generate a second random mutation point
		double[] tab1 = new double[taille];
		double[] tab2 = new double[taille]; // Create temporary arrays to hold the coordinates of the cities
		for(int i=0; i<taille; i++) {
			tab1[i] = coordonnees.get(i); // Copy the coordinates of the cities into the temporary arrays
			tab2[i] = coordonnees.get(i);
		}
		double temp = tab1[point_mutation];
		tab1[point_mutation] = tab1[point_mutation2]; // Swap the coordinates at the mutation points
		tab1[point_mutation2] = temp;
		Individu_VDC res = new Individu_VDC(tab1, tab2); // Create a new Individu_VDC object with the mutated coordinates
		coordonnees = res.coordonnees; // Update the coordonnees map with the mutated coordinates
	}

	/* Accesseurs (pour Display_VDC)
	 */
	public int[] get_parcours(){
		int [] res = new int[coordonnees.size()];
		for(int i=0; i<coordonnees.size(); i++) {
			res[i] = i;
		}
		return res;
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

}
