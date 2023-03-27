package V2;

public class Cursor implements Command {

	//	int position;
	//	
	//	updatePosition(){
	//		
	//	}

	Editor buffer;

	public Cursor(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.deplacer();
	}

}
