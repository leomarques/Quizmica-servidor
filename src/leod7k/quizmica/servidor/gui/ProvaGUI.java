package leod7k.quizmica.servidor.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProvaGUI extends JFrame {
	private Panel2 panel;

	public ProvaGUI() {
		setTitle("Prova");
		setSize(500, 350);
		setLocationRelativeTo(null);

		panel = new Panel2();
		add(panel);

		setVisible(true);
	}

	public JButton getBtnPassar() {
		return panel.btnPassar;
	}

	public JButton getBtnResultado() {
		return panel.btnResultado;
	}

	public JLabel getLabel() {
		return panel.label;
	}
}

@SuppressWarnings("serial")
class Panel2 extends JPanel {
	public JLabel label;
	public JButton btnPassar;
	public JButton btnResultado;

	Panel2() {
		label = new JLabel();
		btnPassar = new JButton("Próximo");
		btnResultado = new JButton("Ver resultado");

		setLayout(new BorderLayout());
		add(label, BorderLayout.PAGE_START);

		JPanel p = new JPanel();

		p.add(btnPassar, BorderLayout.PAGE_END);
		p.add(btnResultado, BorderLayout.PAGE_END);

		add(p, BorderLayout.PAGE_END);
	}
}
