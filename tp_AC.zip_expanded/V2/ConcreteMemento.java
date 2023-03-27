package V2;

public class ConcreteMemento implements Memento<Editor> {
	Editor state;

	public ConcreteMemento() {}

	@Override
	public Editor getState() {
		return state;
	}

	@Override
	public void setState(Editor s) {
		state = s;
	}

}
