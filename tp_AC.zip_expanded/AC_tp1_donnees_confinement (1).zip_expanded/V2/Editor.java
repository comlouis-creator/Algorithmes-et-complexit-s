package V2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;

public class Editor {

	static JLabel textArea;
	public List<String> texte;
	static int positionCourante;
	static List<String> texteCopie;
	static int debSelect;
	static int finSelect;
	static int taille;

	public Editor(JLabel Area) {
		textArea = Area;
		texte = new LinkedList<String>();
		positionCourante = 0;
		taille = 0;
		texteCopie = new ArrayList<String>();
		debSelect = 0;
		finSelect = 0;
	}

	public Editor(Editor buff) {
		textArea = buff.textArea;
		texte = new LinkedList<String>(buff.texte);
		positionCourante = buff.positionCourante;
		taille = buff.taille;
		texteCopie = new ArrayList<String>(buff.texteCopie);
		debSelect = buff.debSelect;
		finSelect = buff.finSelect;
	}

	public void update() {
		textArea.setText(to_string(texte));
	}

	public String to_string(List<String> tab) {
		String res = "<html>";
		for (int i=0; i<tab.size(); i++) {
			if (i==positionCourante) res+=("|");
			if (finSelect!=debSelect && i==Math.min(debSelect,finSelect)) {
				res+="<font color='red'>";
				while (i<Math.max(debSelect, finSelect)) {
					res+=tab.get(i);
					i++;
					if (i==positionCourante) res+=("</font>|<font color='red'>");
				}
				res+="</font>";
			}
			if (i<tab.size()) res+=tab.get(i);
		}
		if (positionCourante==tab.size() && debSelect==finSelect) res+=("|");
		return res+"</html>";
	}


	///////////////////////////
	//////// Commandes ////////
	///////////////////////////

	public void ecrire() {
		String caractere = DataStored.getKeyTyped();

		if(debSelect != finSelect) this.effacer();

		if (positionCourante==taille) {
			texte.add(caractere);
			positionCourante++;
			taille++;
		} else {
			texte.add(positionCourante,caractere);
			positionCourante++;
			taille++;
		}
	}

	public void effacer() {
		if (positionCourante>0) {
			if(debSelect == finSelect) {
				texte.remove(positionCourante-1);
				positionCourante--;
				taille--;		
			} else {
				for(int i=Math.min(debSelect,finSelect); i<Math.max(debSelect,finSelect); i++) {
					texte.remove(Math.min(debSelect,finSelect));
					taille--;
				}
				positionCourante=Math.min(debSelect,finSelect);
				endSelection();
			}
		}
	}

	public void deplacer() {
		if (DataStored.getDirection() == "droite" && positionCourante < taille) {
			positionCourante++;
		} else if (DataStored.getDirection() == "gauche" && positionCourante > 0) {
			positionCourante--;
		}
	}

	public void selectionner() {
		if (DataStored.getDirection() == "droite" && finSelect<taille) {
			finSelect++;
		} else if(DataStored.getDirection() == "gauche" && finSelect>0) {
			finSelect--;
		}
	}

	public void startSelection() {
		debSelect=positionCourante;
		finSelect=positionCourante;
	}

	public void endSelection() {
		debSelect=0;
		finSelect=0;
	}

	public void copier() {
		if (debSelect!=finSelect) texteCopie.clear();;
		for(int i=Math.min(debSelect, finSelect); i<Math.max(debSelect, finSelect); i++) {
			texteCopie.add(texte.get(i));
		}
	}

	public void couper() {
		if (finSelect!=debSelect) {
			this.copier();
			this.effacer();
		}
	}

	public void coller() {
		endSelection();
		for(int i=0; i<texteCopie.size(); i++) {
			texte.add(positionCourante, texteCopie.get(i));
			//if (positionCourante==taille) taille++;
			taille++;
			positionCourante++;
		}
	}

	/**
	 * Fonctions de la classe pour les commandes enregistrables de la v2. Il s'agit des fonctions de
	 * l'Originator du patron Memento.
	 */

	public ConcreteMemento getMemento() {
		ConcreteMemento mem = new ConcreteMemento();
		mem.setState(new Editor(this));
		System.out.println("memento créé :"+mem.getState().texte);
		return mem;
	}

	public void setMemento(ConcreteMemento m) {
		Editor buff = m.getState();
		Editor.textArea = buff.textArea;
		texte = buff.texte;
		Editor.positionCourante = buff.positionCourante;
		Editor.taille = buff.taille;
		Editor.texteCopie = buff.texteCopie;
		Editor.debSelect = buff.debSelect;
		Editor.finSelect = buff.finSelect;
	}

}
