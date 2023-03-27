package V2;

/**
 * La classe sélection est un couple d'entiers représentant le début et la
 * longueur d'une sélection de texte dans le buffer.
 */
public class Selection {
	private int debut;
	private int longueur;

	/**
	 * Retourne le début de la sélection.
	 * 
	 * @return int debut
	 */
	public int getDebut() {
		return debut;
	}

	/**
	 * Retourne la longueur de la sélection.
	 * 
	 * @return int longueur
	 */
	public int getLongueur() {
		return longueur;
	}

	/**
	 * Modifie le début de la sélection.
	 * 
	 * @param debut
	 */
	public void setDebut(int debut) {
		this.debut = debut;
	}

	/**
	 * Modifie la longueur de la sélection, en s'assurant que cette longueur est
	 * positive.
	 * 
	 * @param longueur
	 */
	public void setLongueur(int l) {
		if (l > 0) {
			longueur = l;
		} else {
			longueur = 0;
		}
	}
}
