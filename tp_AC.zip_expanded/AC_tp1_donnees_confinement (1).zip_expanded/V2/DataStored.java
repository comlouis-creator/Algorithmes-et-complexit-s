package V2;

public class DataStored {

	// last key that has been typed
	public static String keyTyped = "";

	// current direction for moving the cursor
	public static String direction = "droite";

	// bool to store if shift is currently pressed
	public static boolean isShiftPressed = false;

	//bool to store if ctrl is currently pressed
	public static boolean isCtrlPressed = false;


	public static void setKeyTyped(String key) {
		keyTyped = key;
	}

	public static void setDirection(String dir) {
		direction = dir;
	}

	public static void setIsShiftPressed(boolean bool) {
		isShiftPressed = bool;
	}

	public static void setIsCtrlPressed(boolean bool) {
		isCtrlPressed = bool;
	}

	public static String getKeyTyped() {
		return keyTyped;
	}

	public static String getDirection() {
		return direction;
	}

	public static boolean getIsShiftPressed() {
		return isShiftPressed;
	}

	public static boolean getIsCtrlPressed() {
		return isCtrlPressed;
	}

}
