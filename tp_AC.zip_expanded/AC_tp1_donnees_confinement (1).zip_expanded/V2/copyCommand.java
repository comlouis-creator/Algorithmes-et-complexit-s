package V2;

public class copyCommand implements Command {

	Editor buffer;

	public copyCommand(Editor buff) {
		buffer = buff;
	}

	public void execute() {
		buffer.copier();
	}

}
