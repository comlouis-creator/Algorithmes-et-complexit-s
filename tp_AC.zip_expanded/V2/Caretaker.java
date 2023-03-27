package V2;

import java.util.LinkedList;

/**
 * La classe Caretaker permet d'enregistrer les commandes pour les annuler
 * (défaire), et éventuellement les restaurer (refaire). Il s'agit du Caretaker
 * du patron Memento (v3).
 */

public class Caretaker {

	static LinkedList<ConcreteMemento> bufferMemory;

	public static void newBufferMemory() {
		bufferMemory = new LinkedList<ConcreteMemento>();
	}

	public static void undo(Editor state) {
		if (Caretaker.bufferMemory.size()>1) {
			Caretaker.bufferMemory.pop();
			state.setMemento(Caretaker.bufferMemory.getFirst());
			System.out.println("memento set"+state.texte);
		}
	}

	public static void saveAction(Editor state) {
		bufferMemory.push(state.getMemento());
	}
}
