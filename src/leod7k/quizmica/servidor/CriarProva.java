package leod7k.quizmica.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.CriarProvaGUI;

public class CriarProva {

	JTextArea enunciado, a, b, c, d;
	ArrayList<ArrayList<String>> prova;
	CriarProvaGUI cpg;
	String nome;
	int qs = 1;

	public CriarProva(String nome) {
		this.nome = nome;
		cpg = new CriarProvaGUI();
		cpg.setTitle("Avaliação " + nome + " - Questão 1");
		enunciado = cpg.getEnunciado();
		a = cpg.getA();
		b = cpg.getB();
		c = cpg.getC();
		d = cpg.getD();
		prova = new ArrayList<>();

		cpg.getBtnNovaQ().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> questoes = new ArrayList<>();
				questoes.add(enunciado.getText().replace("\n", "<br>"));
				questoes.add(a.getText());
				questoes.add(b.getText());
				questoes.add(c.getText());
				questoes.add(d.getText());

				prova.add(questoes);

				enunciado.setText("");
				a.setText("");
				b.setText("");
				c.setText("");
				d.setText("");
				
				cpg.setTitle("Avaliação " + nome + " - Questão " + ++qs);
			}
		});

		cpg.getBtnSalvar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> questoes = new ArrayList<>();
				questoes.add(enunciado.getText().replace("\n", "<br>"));
				questoes.add(a.getText());
				questoes.add(b.getText());
				questoes.add(c.getText());
				questoes.add(d.getText());

				prova.add(questoes);
				
				BufferedWriter out = null;

				try {
					out = new BufferedWriter(new FileWriter(nome + ".txt"));

					for (ArrayList<String> q : prova) {
						for (int i = 0; i < 5; i++) {
							out.write(q.get(i));
							out.newLine();
						}
					}

					out.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Avaliação salva em " + nome + ".txt", nome, JOptionPane.INFORMATION_MESSAGE);
				cpg.dispatchEvent(new WindowEvent(cpg, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

}
