package leod7k.quizmica.servidor.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class CriarProvaGUI extends JFrame {
	private Panel4 panel;
	
	public CriarProvaGUI() {
		setTitle("Criar avaliação");
		setSize(600, 520);
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
	public JScrollPane scrollPaneE, scrollPaneA, scrollPaneB, scrollPaneC, scrollPaneD;

	Panel4() {
		enunciado = new JTextArea("", 4, 1);
		scrollPaneE = new JScrollPane(enunciado);
		a = new JTextArea("", 3, 1);
		scrollPaneA = new JScrollPane(a);
		b = new JTextArea("", 3, 1);
		scrollPaneB = new JScrollPane(b);
		c = new JTextArea("", 3, 1);
		scrollPaneC = new JScrollPane(c);
		d = new JTextArea("", 3, 1);
		scrollPaneD = new JScrollPane(d);

		a.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		b.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		c.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		d.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		//setLayout(new BorderLayout());
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel p1 = new JPanel();
		p1.add(new JLabel("Enunciado da questão"));
		add(p1);
		add(scrollPaneE);
		JPanel p2 = new JPanel();
		p2.add(new JLabel("Alternativa A"));
		add(p2);
		add(scrollPaneA);
		JPanel p3 = new JPanel();
		p3.add(new JLabel("Alternativa B"));
		add(p3);
		add(scrollPaneB);
		JPanel p4 = new JPanel();
		p4.add(new JLabel("Alternativa C"));
		add(p4);
		add(scrollPaneC);
		JPanel p5 = new JPanel();
		p5.add(new JLabel("Alternativa D"));
		add(p5);
		add(scrollPaneD);
		
		JPanel p = new JPanel();

		btnNovaQ = new JButton("Nova questão");
		btnSalvar = new JButton("Salvar");
		p.add(btnNovaQ, BorderLayout.PAGE_END);
		p.add(btnSalvar, BorderLayout.PAGE_END);

		add(p);
	}
}