package leod7k.quizmica.servidor.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("serial")
public class ServerGUI extends JFrame {
	private Panel panel;

	public ServerGUI() {
		setTitle("Quizmica v2015.1");
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
	
	public JButton getBtnCriarProva() {
		return panel.btnCriarProva;
	}
}

@SuppressWarnings("serial")
class Panel extends JPanel {
	public JTextArea textArea;
	public JButton btnAbrir, btnComecar, btnTerminar, btnCriarProva;
	public JScrollPane scrollPane;
	
	Panel() {
		textArea = new JTextArea(25, 1);
		scrollPane = new JScrollPane(textArea);
		btnAbrir = new JButton("Abrir");
		btnComecar = new JButton("Come�ar");
		btnTerminar = new JButton("Terminar");
		btnCriarProva = new JButton("Criar avalia��o");

		textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.PAGE_START);

		JPanel p = new JPanel();

		p.add(btnAbrir, BorderLayout.PAGE_END);
		p.add(btnComecar, BorderLayout.PAGE_END);
		p.add(btnTerminar, BorderLayout.PAGE_END);
		p.add(btnCriarProva, BorderLayout.PAGE_END);

		add(p, BorderLayout.PAGE_END);
	}
}
