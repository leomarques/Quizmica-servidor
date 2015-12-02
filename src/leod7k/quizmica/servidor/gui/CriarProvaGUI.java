package leod7k.quizmica.servidor.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class CriarProvaGUI extends JFrame {
	private Panel4 panel;
	
	public CriarProvaGUI() {
		setTitle("Criar avaliação");
		setSize(600, 420);
		setLocationRelativeTo(null);

		panel = new Panel4();
		add(panel);

		setVisible(true);
	}
	
	public JTextArea getEnunciado() {
		return panel.enunciado;
	}
	
	public JTextArea getA() {
		return panel.a;
	}
	
	public JTextArea getB() {
		return panel.b;
	}
	
	public JTextArea getC() {
		return panel.c;
	}
	
	public JTextArea getD() {
		return panel.d;
	}
	
	public JButton getBtnNovaQ() {
		return panel.btnNovaQ;
	}
	
	public JButton getBtnSalvar() {
		return panel.btnSalvar;
	}
}

@SuppressWarnings("serial")
class Panel4 extends JPanel {
	public JTextArea enunciado, a, b, c, d;
	public JButton btnNovaQ, btnSalvar;

	Panel4() {
		enunciado = new JTextArea("Enunciado da questão", 4, 1);
		a = new JTextArea("Alternativa A", 4, 1);
		b = new JTextArea("Alternativa B", 4, 1);
		c = new JTextArea("Alternativa C", 4, 1);
		d = new JTextArea("Alternativa D", 4, 1);

		a.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		b.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		c.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		d.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//setLayout(new BorderLayout());
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(enunciado);
		add(a);
		add(b);
		add(c);
		add(d);
		
		JPanel p = new JPanel();

		btnNovaQ = new JButton("Nova questão");
		btnSalvar = new JButton("Salvar");
		p.add(btnNovaQ, BorderLayout.PAGE_END);
		p.add(btnSalvar, BorderLayout.PAGE_END);

		add(p);
	}
}