public class Tri {

	/*Tri le tableau t via la méthode "Tri par insertion"
	 * cf TD1 exo 2
	 */
	public static void triInsertion(int[] t){
		for(int j=1; j<=t.length-1; j++){
            int clé = t[j];
            int i =j-1;
            while(i>=0 && t[i]>clé){
                t[i+1]=t[i];
                i=i-1;
            }
            t[i+1]=clé;
        }
	}
	
	/*Tri le tableau t via la méthode "Tri fusion"
	 * cf TD2 exo 5
	 */
	public static void triFusion(int[] t){
		if (t.length>0)
			triFusion(t, 0, t.length-1);
	}

	/* Sous-fonction (récursive) pour le tri fusion
	 * Trie le sous-tableau t[debut]..t[fin]
	 */
	private static void triFusion(int[] t, int debut, int fin){
		if(debut<fin) {
            int milieu = (debut+fin)/2;
            triFusion(t,debut,milieu);
            triFusion(t,milieu+1,fin);
            fusionner(t,debut,milieu,fin);
        }
	}

	/* Sous-fonction pour le tri fusion
	 * Suppose que, dans le tableau t, 
	 *       les 2 sous-tableaux t[deb1]..t[fin1] et t[fin1+1]..[t[fin2] sont déjà triés
	 * Fusionne ces 2 sous-tableaux pour que le sous-tableau t[deb1]..t[fin2] soit trié
	 */
	private static void fusionner(int[] t, int deb1, int fin1, int fin2){
		int deb2 = fin1+1;
		//on recopie les éléments du début du tableau
		int[] t1 = new int[fin1-deb1+1];
		for(int i=deb1;i<=fin1;i++){
			t1[i-deb1]=t[i];
        }
		int compt1=deb1;
	    int compt2=deb2;
	    for(int i=deb1;i<=fin2;i++){        
	        if (compt1==deb2) //c'est que tous les éléments du premier tableau ont été utilisés
	            break; //tous les éléments ont donc été classés
	        else if (compt2==(fin2+1)){ //c'est que tous les éléments du second tableau ont été utilisés
	            t[i]=t1[compt1-deb1]; //on ajoute les éléments restants du premier tableau
	            compt1++;
	        }
	        else if (t1[compt1-deb1]<t[compt2]){
	            t[i]=t1[compt1-deb1]; //on ajoute un élément du premier tableau
	            compt1++;
	        }
	        else{
	            t[i]=t[compt2]; //on ajoute un élément du second tableau
	            compt2++;
	        }
        }
    }
}