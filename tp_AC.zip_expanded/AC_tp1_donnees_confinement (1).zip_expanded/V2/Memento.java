package V2;

/**
 * L'interface Memento  
 */
public interface Memento <T> {

	public T getState();
	public void setState(T state);
}
