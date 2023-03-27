package Sac_A_Dos;

import java.io.InputStream;
import Util.Lecture;
import Algo_Genetiques.Individu;
import Algo_Genetiques.Individu_SAD;
import Algo_Genetiques.Population;

public class Client_Sac_A_Dos {


	/**
	 * lit une liste de poids dans un fichier
	 * @param nomFichier  nom du fichier texte contenant les poids
	 * @param nbr_objets nombre d'objets possibles
	 * @return tableau de poids
	 */
	public static double[] charge_poids(String nomFichier, int nbr_objets){
		double[] poids = new double[nbr_objets];
    	InputStream IS = Lecture.ouvrir(nomFichier);
    	if (IS==null){
    		System.err.println("Impossible d'ouvrir le fichier \""+nomFichier+"\" (n'existe pas ? pas au bon endroit ?)");
    	}
    	int i=0;
    	int somme=0;
    	while(!Lecture.finFichier(IS) && i<nbr_objets){
    		poids[i] = Lecture.lireDouble(IS);
    		somme += poids[i];
    		i++;
    	}
    	System.out.println("charge_poids ("+nomFichier+") : poids total des objets = "+somme);
    	Lecture.fermer(IS);
    	return poids;
	}

	
	public static void main(String[] args){
		
		/* paramètres */ 
		int nbr_indiv=100;
		double prob_mut=0.1;
		
		double prob_mut0=0.5;
		
		/* On initialise les poids en lisant un fichier 
		 */
		
		int nbr_objets=28;
		int capacite=1581;
		
		int nbr_objets0=70;
		int capacite0=350;
		
//		int nbr_objets=70;
//		int capacite=350;		
		
		double[] poids = charge_poids("./tp4_donnees/data_sad/nbrobj"+nbr_objets+"_capacite"+capacite+".txt",nbr_objets);
		
		System.out.println();
		
		double[] poids0 = charge_poids("./tp4_donnees/data_sad/nbrobj"+nbr_objets0+"_capacite"+capacite0+".txt",nbr_objets0);
		
		System.out.println();
		
		/* on crée une population (aléatoire)
		 * de nbr_indiv individus associés au problème
		 * du sac à dos considéré 
		 */
		//TODO
		
		Individu_SAD [] tableau = new Individu_SAD[nbr_indiv];
		
		Individu_SAD [] tableau0 = new Individu_SAD[nbr_indiv];
		
		for(int i=0; i < nbr_indiv; i++) {
			tableau[i] = new Individu_SAD(capacite, poids) ;
			tableau0[i] = new Individu_SAD(capacite0, poids0) ;
		}
		
		Population pop = new Population(tableau);
		
		Population pop0 = new Population(tableau0);
		
		/* on génére les générations successives
		 * en faisant se reproduire la population
		 * et on affiche l'adaptation moyenne et maximale de chaque génération
		 * on s'arrête si on a atteint la capacité ou si on fait un nombre donné (paramètre) d'itérations
		 * le résultat est alors donné par l'individu maximal de la dernière génération
		 */
		//TODO
		for(int i = 0; i < 100 ; i++) {
			pop.reproduction(prob_mut);
			System.out.println("pop generation"+i+" : adaptation moyenne = "+pop.adaptation_moyenne()+" ; adaptation max = "+pop.adaptation_maximale());
			if(pop.adaptation_maximale() == capacite) break;
		}
		
		System.out.println();
		
		for(int j = 0; j < 1000 ; j++) {
			pop0.reproduction(prob_mut0);
			System.out.println("pop0 generation"+j+" : adaptation moyenne = "+pop0.adaptation_moyenne()+" ; adaptation max = "+pop0.adaptation_maximale());
			if(pop0.adaptation_maximale() == capacite0) break;
		}

	}
	
}

