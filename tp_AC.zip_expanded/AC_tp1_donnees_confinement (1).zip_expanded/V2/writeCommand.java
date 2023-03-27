package V2;

public class writeCommand implements Command {

	Editor buffer;

	public writeCommand(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.ecrire();
	}


	//writeCommand(Editor editor, ) 
}
