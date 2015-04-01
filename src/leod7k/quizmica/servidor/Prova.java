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
		qTexts.add("<html> <h3>1) Lorem ipsum dolor sit amet?</h3><p>"
				+ "A) Lorem<p>"
				+ "B) Ipsum<p>"
				+ "C) Dolor<p>"
				+ "D) Sit </html>");
		qTexts.add("<html> <h3>2) Lcdjwc cjdnocd cdnw?</h3><p>"
				+ "A) Lorem<p>"
				+ "B) Dolor<p>"
				+ "C) Ipsum<p>"
				+ "D) Sit </html>");
		qTexts.add("<html> <h3>3) Jjcdn cdnicnei cndejwoww?</h3><p>"
				+ "A) Ipsum<p>"
				+ "B) Dolor<p>"
				+ "C) Lorem<p>"
				+ "D) Sit </html>");
		
	}

	public void proxima() {
		if (++q >= 3) {
			gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
			return;
		}
		textArea.setText("Questão " + (q+1) + "\n");
		con.getServerDispatcher().serverMessage(q+"");
		gui.getLabel().setText(qTexts.get(q));
	}

}
