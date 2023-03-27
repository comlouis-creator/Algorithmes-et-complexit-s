package Voyageur_De_Commerce;

import java.io.*;
import Util.Lecture;
import Algo_Genetiques.Population;

public class Client_Voyageur_De_Commerce {

	/**
	 * lit une liste de poids dans un fichier
	 * @param nomFichier  nom du fichier texte contenant les coordonnées des villes
	 * @param nbr_villes nombre de villes
	 * @param coord_x et coord_y : les 2 tableaux que la fonction remplit et qui vont contenir les coordonnées des villes
	 */
	public static void charge_coords(String nomFichier, int nbr_villes, double[] coord_x, double[] coord_y){
		assert(coord_x.length==coord_y.length) : "charge_coords : coord_x et coord_y n'ont pas la même taille ?";
		InputStream IS = Lecture.ouvrir(nomFichier);
		if (IS==null){
			System.err.println("pb d'ouverture du fichier "+nomFichier);
		}
		int i=0;
		while(!Lecture.finFichier(IS) && i<coord_x.length){
			coord_x[i] = Lecture.lireDouble(IS);
			coord_y[i] = Lecture.lireDouble(IS);
			i++;
		}
		Lecture.fermer(IS);
	}

	public static void main(String[] args) throws InterruptedException{

		/* on initialise les coordonnées des villes en les lisant ds un fichier 
		 */

		int nbr_villes = 16;
		int nbr_villes0 = 4;
		int nbr_villes1 = 64;
		int nbr_villes2 = 250;
		int nbr_villes3 = 256;
		int nbr_villes4 = 200;
		double[] coord_x = new double[nbr_villes];
		double[] coord_y = new double[nbr_villes];
		double[] coord_x0 = new double[nbr_villes0];
		double[] coord_y0 = new double[nbr_villes0];
		double[] coord_x1 = new double[nbr_villes1];
		double[] coord_y1 = new double[nbr_villes1];
		double[] coord_x2 = new double[nbr_villes2];
		double[] coord_y2 = new double[nbr_villes2];
		double[] coord_x3 = new double[nbr_villes3];
		double[] coord_y3 = new double[nbr_villes3];
		double[] coord_x4 = new double[nbr_villes4];
		double[] coord_y4 = new double[nbr_villes4];
		charge_coords("./tp4_donnees/data_vdc/"+nbr_villes+"coords.txt",nbr_villes, coord_x, coord_y);
		charge_coords("./tp4_donnees/data_vdc/"+nbr_villes0+"coords.txt",nbr_villes0, coord_x0, coord_y0);
		charge_coords("./tp4_donnees/data_vdc/"+nbr_villes1+"coords.txt",nbr_villes1, coord_x1, coord_y1);
		charge_coords("./tp4_donnees/data_vdc/"+nbr_villes2+"coords.txt",nbr_villes2, coord_x2, coord_y2);
		charge_coords("./tp4_donnees/data_vdc/spirale_"+nbr_villes3+".txt",nbr_villes3, coord_x3, coord_y3);
		charge_coords("./tp4_donnees/data_vdc/quadraturecercle_"+nbr_villes4+".txt",nbr_villes4, coord_x4, coord_y4);
		charge_coords("./tp4_donnees/data_vdc/doublespirale_"+nbr_villes4+".txt",nbr_villes4, coord_x4, coord_y4);
		
		/* Exemple d'utilisation de Display_VDCC (il faut d'abord faire le constructeur pour ce test fonctionne, ainsi que compléter les accesseurs)
		 */
		Individu_VDC ind1 = new Individu_VDC(coord_x, coord_y); //on crée un individu aléatoire
		Individu_VDC ind3 = new Individu_VDC(coord_x0, coord_y0);
		Individu_VDC ind5 = new Individu_VDC(coord_x1, coord_y1);
		Individu_VDC ind7 = new Individu_VDC(coord_x2, coord_y2);
		Individu_VDC ind9 = new Individu_VDC(coord_x3, coord_y3);
		Individu_VDC ind11 = new Individu_VDC(coord_x4, coord_y4);
		Display_VDC disp = new Display_VDC(ind1); //on l'affiche
		//Display_VDC disp0 = new Display_VDC(ind3);
		//Display_VDC disp1 = new Display_VDC(ind5);
		//Display_VDC disp2 = new Display_VDC(ind7);
		//Display_VDC disp3 = new Display_VDC(ind9);
		//Display_VDC disp4 = new Display_VDC(ind11);
		Thread.sleep(1000); //pause de 1 seconde (pour avoir le temps de voir le premier affichage)
		Individu_VDC ind2 = new Individu_VDC(coord_x, coord_y); //on en crée un autre
		Individu_VDC ind4 = new Individu_VDC(coord_x0, coord_y0);
		Individu_VDC ind6 = new Individu_VDC(coord_x1, coord_y1);
		Individu_VDC ind8 = new Individu_VDC(coord_x2, coord_y2);
		Individu_VDC ind10 = new Individu_VDC(coord_x3, coord_y3);
		Individu_VDC ind12 = new Individu_VDC(coord_x4, coord_y4);
		disp.refresh(ind2); //on met à jour l'affichage avec le nouveau
		//disp0.refresh(ind4);
		//disp1.refresh(ind6);
		//disp2.refresh(ind8);
		//disp3.refresh(ind10);
		//disp4.refresh(ind12);
	}
}