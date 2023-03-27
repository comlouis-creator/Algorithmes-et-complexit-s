package V2;

public class pasteCommand implements Command {

	Editor buffer;

	public pasteCommand(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.coller();
	}

}
