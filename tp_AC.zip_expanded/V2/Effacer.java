package V2;

public class Effacer implements Command {
	
	Editor buffer;
	
	public Effacer(Editor buff) {
		buffer = buff;
	}
	
	public void execute() {
		buffer.effacer();
	}
	
}

