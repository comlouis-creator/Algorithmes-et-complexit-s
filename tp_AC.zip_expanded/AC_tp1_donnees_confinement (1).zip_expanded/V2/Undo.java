package V2;
import V2.Editor;

public class Undo implements Command {

	Editor buffer;

	public Undo(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.effacer();
	}


}
