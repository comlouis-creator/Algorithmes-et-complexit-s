package V2;

public class cutCommand implements Command {

	Editor buffer;

	public cutCommand(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.couper();
	}

}
