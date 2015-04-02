package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.ProvaGUI;
import leod7k.quizmica.servidor.gui.listeners.BtnPassarListener;

public class Prova {
	
	private Connections con;
	private int q;
	private ProvaGUI gui;
	private ArrayList<String> qTexts;
	private JTextArea textArea;

	public Prova(Connections paramCon, JTextArea paramTextArea) {
		con = paramCon;
		q = -1;
		
		addQuestoes();
		
		textArea = paramTextArea;
		gui = new ProvaGUI();
		
		gui.getBtnPassar().addActionListener(new BtnPassarListener(this));
		proxima();
	}

	private void addQuestoes() {
		qTexts = new ArrayList<String>();
		qTexts.add("<html> <h3>1) Como resultado da experiência de Rutherford, foi concluído que</h3><p>"
				+ "A) o centro do átomo é vazio.<p>"
				+ "B) a massa atômica é distribuída por todo o átomo.<p>"
				+ "C) o centro do átomo tem carga negativa.<p>"
				+ "D) a maior parte to átomo é vazia. </html>");
		qTexts.add("<html> <h3>2) De acordo com Rutherford, o átomo consiste nas duas seguintes partes</h3><p>"
				+ "A) Elétrons e Prótons<p>"
				+ "B) Núcleo e a parte extra-nuclear<p>"
				+ "C) Nêutrons e Elétrons<p>"
				+ "D) Prótons e Nêutrons </html>");
		qTexts.add("<html> <h3>3) As três partículas fundamentais do átomo são</h3><p>"
				+ "A) núcleo, Anti-quarks e Elétrons<p>"
				+ "B) Mésons, Quarks e Anti-neutrino<p>"
				+ "C) Positron, Neutrino e Raios Gama<p>"
				+ "D) Próton, Nêutron e Elétron </html>");
		
	}

	public void proxima() {
		if (++q < 3) {
			textArea.setText("Questão " + (q+1) + "\n");
			con.getServerDispatcher().serverMessage(q+"");
			gui.getLabel().setText(qTexts.get(q));
		} else {
			gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
			con.getServerDispatcher().serverMessage("fim");
			textArea.setText("Fim da prova\n");
		}
	}

}
