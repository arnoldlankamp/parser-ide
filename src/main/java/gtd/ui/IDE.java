package gtd.ui;

import gtd.internal.Interpreter;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class IDE {
	private final Interpreter interpreter = new Interpreter();

	private JPanel createEditor() {
		JPanel editor = new JPanel();
		editor.setLayout(new BorderLayout());

		final JTextPane inputArea = new JTextPane();
		Font courier = new Font("courier", 0, 14);
		inputArea.setFont(courier);
		inputArea.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			
			public void keyPressed(KeyEvent arg0) {}
			
			public void keyReleased(KeyEvent arg0) {
				if(Character.isDefined(arg0.getKeyChar())) {
					interpreter.execute(inputArea.getText());
				}
			}
		});
		editor.add(new JScrollPane(inputArea), BorderLayout.CENTER);

		return editor;
	}

	public void init() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		frame.setLayout(new BorderLayout());
		frame.add(createEditor(), BorderLayout.CENTER);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IDE ide = new IDE();
				ide.init();
			}
		});
	}
}
