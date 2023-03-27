package V2;

/**
 * La commande qui modifie la sélection du buffer.
 */
public class Selectionner implements Command {

	Editor buffer;

	public Selectionner(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.selectionner();
	}

	//	public class selectCommand {
	//		
	//		public selectCommand(Editor editeur,) {
	//			
	//		}
	//		
	//	}
}
