package V2;

import javax.swing.*;
import java.awt.event.*;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.plaf.metal.*;

import V2.Caretaker;

/**
 * Classe principale : instancie et relie les différents composants de
 * l'application.
 */
class InterfaceHommeMachine extends JFrame implements ActionListener, KeyListener {

	// Frame
	JFrame f;

	// textArea
	JLabel textArea;

	// Buffer
	Editor buffer;

	// Commands
	Map<String, Command> commands;

	// Constructor
	InterfaceHommeMachine() {
		// Create a frame
		f = new JFrame("Editeur");
		f.addKeyListener(this);

		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		} catch (Exception e) {
		}

		// Create JLabel
		textArea = new JLabel();
		// textArea.setSize(500,500);
		textArea.setVerticalAlignment(JLabel.TOP);
		textArea.setHorizontalAlignment(JLabel.LEFT);

		// Create buffer
		buffer = new Editor(textArea);

		// Initiate commands
		commands = new TreeMap<String, Command>();
		createCommands(buffer);

		// Create a menubar
		JMenuBar mb = new JMenuBar();

		// Create a menu for menu
		JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Edit");

		// Create menu items
		JMenuItem mi1 = new JMenuItem("Save");
		JMenuItem mi2 = new JMenuItem("Load");
		JMenuItem mi4 = new JMenuItem("cut");
		JMenuItem mi5 = new JMenuItem("copy");
		JMenuItem mi6 = new JMenuItem("paste");

		JButton b1 = new JButton("<");

		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
		b1.addActionListener(this);

		m1.add(mi1);
		m1.add(mi2);

		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);

		mb.add(m1);
		mb.add(m2);
		// mb.add(b1);

		f.setJMenuBar(mb);
		f.add(textArea);
		f.setSize(500, 500);
		f.setVisible(true);

		Caretaker.saveAction(buffer);
		textArea.setText("<html>bon<font color='red'>jo</font>ur</html>");
	}

	private void createCommands(Editor buffer) {
		commands.put("ecrire", new writeCommand(buffer));
		commands.put("effacer", new Effacer(buffer));
		commands.put("deplacer", new Cursor(buffer));
		commands.put("copier", new copyCommand(buffer));
		commands.put("couper", new cutCommand(buffer));
		commands.put("coller", new pasteCommand(buffer));
		commands.put("selectionner", new Selectionner(buffer));
	}

	// If a button is pressed
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "cut":
			commands.get("couper").execute();
			System.out.println("texte copié: " + buffer.texteCopie);
			Caretaker.saveAction(buffer);
			break;

		case "copy":
			commands.get("copier").execute();
			System.out.println("texte copié: " + buffer.texteCopie);
			break;

		case "paste":
			commands.get("coller").execute();
			Caretaker.saveAction(buffer);
			break;
		}
		buffer.update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		analyseKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
			DataStored.isShiftPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_CONTROL)
			DataStored.setIsCtrlPressed(false);
	}

	private void analyseKeyPressed(KeyEvent key) {
		DataStored.setKeyTyped("" + key.getKeyChar());

		switch (key.getKeyCode()) {
		case KeyEvent.VK_BACK_SPACE:
			commands.get("effacer").execute();
			Caretaker.saveAction(buffer);
			break;

		case KeyEvent.VK_LEFT:
			DataStored.setDirection("gauche");
			if (DataStored.isShiftPressed)
				commands.get("selectionner").execute();
			else
				commands.get("deplacer").execute();
			Caretaker.saveAction(buffer);
			break;

		case KeyEvent.VK_RIGHT:
			DataStored.setDirection("droite");
			if (DataStored.isShiftPressed)
				commands.get("selectionner").execute();
			else
				commands.get("deplacer").execute();
			Caretaker.saveAction(buffer);

			break;

		case KeyEvent.VK_SHIFT:
			buffer.startSelection();
			if (!DataStored.isShiftPressed)
				DataStored.setIsShiftPressed(true);
			break;

		case KeyEvent.VK_ENTER:
			DataStored.setKeyTyped("<br>");
			commands.get("ecrire").execute();
			Caretaker.saveAction(buffer);

			break;

		case KeyEvent.VK_CONTROL:
			if (!DataStored.isCtrlPressed)
				DataStored.setIsCtrlPressed(true);
			break;

		default:
			if ((int)key.getKeyChar() > 31 && (int)key.getKeyChar() < 177 || key.getKeyCode()==KeyEvent.VK_Z) {
				if (!DataStored.getIsCtrlPressed()) {
					commands.get("ecrire").execute();
					Caretaker.saveAction(buffer);
				} else {
					if (key.getKeyCode() == 90) {
						Caretaker.undo(buffer); // pas le bon buffer (celui la pas update)
					}
				}
			}
			break;
		}
		if (key.getKeyCode() != KeyEvent.VK_SHIFT && !DataStored.getIsShiftPressed())
			buffer.endSelection();
		buffer.update();
	}

	public static void main(String[] args) {
		Caretaker.newBufferMemory();
		new InterfaceHommeMachine();
	}

	// finalisation de l'ihm

	//	public void ActionPerformed(ActionEvent e) {
	//		Objet source = e.getSource();
	//		new writeCommand(editor,this).execute();
	//		if(source==this.insertButton) {
	//			new Insert(editor,this).execute();
	//		} else if(source == this.cutButton) {
	//
	//		} else if(source == this.copyButton) {
	//			new copyCommand(editor,this).execute();
	//		} else if(source == this.pasteButton) {
	//			new pasteCommand(editor,this).execute();
	//		} else if(source == this.writeButton) {
	//			new writeCommand(editor,this).execute();
	//		} else if(source == this.slectButton) {
	//			new selectCommand(editor,this).execute();
	//		}
	//		refreshText();
	//	}

	public void printStack() {
		System.out.println("---print---");
	}

	//	public History getMemento() {
	//		this.courant--;
	//	}

}
