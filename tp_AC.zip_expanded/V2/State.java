package V2;

public class State {

	private int debSelec;
	private int longSelec;
	private String texteSelec;
	private String ppTexte;
	private char car;

	public State(State s2) {

	}

	public int getDebSelec() {
		return debSelec;
	}
	
	public void setDebSelec(int d) {
		debSelec = d;
	}

	public int getLongSelec() {
		return longSelec;
	}
	
	public void setLongSelec(int l) {
		longSelec = l;
	}

	public String getTexteSelec() {
		return texteSelec;
	}

	public void setTexteSelect(String t) {
		texteSelec = t;
	}

	public String getPPTexte() {
		return ppTexte;
	}

	public char getCar() {
		return car;
	}

	public void setCar(char c) {
		car = c;
	}

}
