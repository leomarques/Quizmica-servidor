package leod7k.quizmica.servidor.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ServerGUI extends JFrame {
	private Panel panel;

	public ServerGUI() {
		setTitle("Quizmica");
		setSize(640, 480);
		setLocationRelativeTo(null);

		panel = new Panel();
		add(panel);

		setVisible(true);
	}

	public JTextArea getTextArea() {
		return panel.textArea;
	}

	public JButton getBtnAbrir() {
		return panel.btnAbrir;
	}

	public JButton getBtnComecar() {
		return panel.btnComecar;
	}

	public JButton getBtnTerminar() {
		return panel.btnTerminar;
	}
}

@SuppressWarnings("serial")
class Panel extends JPanel {
	public JTextArea textArea;
	public JButton btnAbrir, btnComecar, btnTerminar;

	Panel() {
		textArea = new JTextArea();
		btnAbrir = new JButton("Abrir");
		btnComecar = new JButton("Começar");
		btnTerminar = new JButton("Terminar");

		textArea.setEditable(false);

		setLayout(new BorderLayout());
		add(textArea, BorderLayout.PAGE_START);

		JPanel p = new JPanel();

		p.add(btnAbrir, BorderLayout.PAGE_END);
		p.add(btnComecar, BorderLayout.PAGE_END);
		p.add(btnTerminar, BorderLayout.PAGE_END);

		add(p, BorderLayout.PAGE_END);
	}
}
