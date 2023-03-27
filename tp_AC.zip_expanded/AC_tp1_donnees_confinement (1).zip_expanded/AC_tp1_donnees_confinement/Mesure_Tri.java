import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mesure_Tri {

	public static void permutation(String target, String original,boolean afficher){
	    int i;
	    String target1, original1;
	    if (original.length() == 0 && afficher){
	        System.out.println(target);
	    }
		else {
			i = 0;
			while (i < original.length()){
			    target1 = target + original.substring(i,i+1);
			    original1 = original.substring(0,i) + original.substring(i+1,original.length());
			    permutation(target1,original1,afficher);
			    i = i + 1;
			}
		} 
	}
	
	/* Affiche un tableau d'entiers
	 */
	public static void afficher(int [] t){
		System.out.print("[");
		for(int k=0;k<t.length;k++)
			System.out.print(" "+t[k]+" ");
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		//Vérification de l'implémentation des tris
		int [] t = new int[10];
		int [] t2 = new int[10];
		for(int k=0;k<t.length;k++){ //initialisation aléatoire
			t[k]=(int) (Math.random()*100);
			t2[k]=t[k];
		}
		System.out.print("Avant le tri : "); afficher(t);
		Tri.triInsertion(t);
		System.out.print("Apr�s le tri par insertion: "); afficher(t);
		Tri.triFusion(t2);
		System.out.print("Apr�s le tri fusion:        "); afficher(t2);
		
		//Analyse du temps d'exécution
//		long debut_test1 = System.currentTimeMillis();
//		Tri.triInsertion(t);
//		long fin_test1 = System.currentTimeMillis();
//		double ecart1 = fin_test1 - debut_test1;
//		System.out.println("Ecart tableau tri� : "+ ecart1 +" secondes");
		
		
		
		//A FAIRE : adapter les 3 valeurs suivantes pour avoir des mesures significatives
		// surtout la valeurs de la variable taille_fin
		int taille_init = 10000;
		int taille_fin  = 67000000;//rapport *1000 entre le debut et la fin 
		int nbrMesures = 10;
		
		int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);

		List<Integer> tab_tailles = new ArrayList<Integer>();
		List<Integer> tab_temps = new ArrayList<Integer>();
		for(int n = taille_init; n<taille_fin; n=n+taille_incr){			  
			tab_tailles.add(n); //on sauvegarde la taille
			t = new int[n];

			//A FAIRE : initialisation de t
			
			// CAS TABLEAU DEJA TRIE
//			for(int i=0; i<n; i++) {
//				t[i]=i;
//			}
			
//			// CAS TABLEAU TRIE INVERSE
//			for(int i=0; i<n; i++) {
//				t[i]=n-i;
//			}
//			
//			// CAS TABLEAU ALEATOIRE
			for(int i=0; i<n; i++) {
				t[i]=(int) (Math.random()*100);
			}
//			char c='a';
//			for (int i=0; i<n; i++) {
//				c++;
//				t[i]=c;
//			}

//			String x="";
//			String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//			String y=alphabet.substring(0, n);
			long date1 = System.currentTimeMillis(); //on lance le chrono
			//Tri.triInsertion(t); //on trie le tableau // utile pour la premi�re partie ( insertion)
			Tri.triFusion(t);// utile pour la deuxi�me partie ( fusion)
			//permutation(x,y,false);
			long date2 = System.currentTimeMillis(); //on arrête le chrono
			tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
			System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
		}
		
		// Affichage des mesures effectuées
		Graph g = new Graph(tab_tailles,tab_temps);
		g.display();
		
		
		// Régression manuelle 
		double a=0; 
		Scanner in = new Scanner(System.in);
		System.out.println("------------------------------------------");
		System.out.println("Attention ! les nombres décimaux doivent utiliser une virgule (et pas un point) !");
		System.out.println("------------------------------------------");
		do {
			System.out.print("Entrez le coefficient de la droite/parabole/nLogn : ");
			if (in.hasNextDouble()) {
				a = in.nextDouble();
				// g.addLine(a);
				// g.addQuadratic(a);
				 g.addnLogn(a);
			}
			else
				System.out.println(in.next()+" n'a pas été reconnu comme un double.");
		} while(a!=-1);
		in.close();
	}

}
