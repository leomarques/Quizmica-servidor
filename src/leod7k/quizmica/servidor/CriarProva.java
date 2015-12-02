package leod7k.quizmica.servidor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.CriarProvaGUI;

public class CriarProva {

	JTextArea enunciado, a, b, c, d;
	ArrayList<ArrayList<String>> prova;
	CriarProvaGUI cpg;

	public CriarProva() {
		cpg = new CriarProvaGUI();
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
				questoes.add(enunciado.getText());
				questoes.add(a.getText());
				questoes.add(b.getText());
				questoes.add(c.getText());
				questoes.add(d.getText());

				prova.add(questoes);

				enunciado.setText("Enunciado");
				a.setText("Alternativa A");
				b.setText("Alternativa B");
				c.setText("Alternativa C");
				d.setText("Alternativa D");
			}
		});

		cpg.getBtnSalvar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedWriter out = null;

				try {
					String fileName = "prova.txt";
					out = new BufferedWriter(new FileWriter(fileName));

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
				
				cpg.dispatchEvent(new WindowEvent(cpg, WindowEvent.WINDOW_CLOSING));
			}
		});
	}

}
