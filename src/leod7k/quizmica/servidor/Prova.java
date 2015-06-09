package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.ProvaGUI;
import leod7k.quizmica.servidor.gui.listeners.BtnPassarListener;
import leod7k.quizmica.servidor.gui.listeners.BtnResultadoListener;

public class Prova {

	public static ProvaListener provaListener;

	private static final int QNT_ALTERNATIVAS = 4;
	private static final int QNT_QUESTOES = 3;
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
		provaListener = new ProvaListener(textArea, con.getServerDispatcher()
				.getmClients());
		gui = new ProvaGUI();

		gui.getBtnPassar().addActionListener(new BtnPassarListener(this));
		gui.getBtnResultado().addActionListener(new BtnResultadoListener(this));
		proxima();
	}

	private void addQuestoes() {
		qTexts = new ArrayList<String>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("prova.txt"));

			String temp;
			while ((temp = reader.readLine()) != null) {
				String text = "<html> <h3>" + temp + "</h3><p>";
				for (int i = 0; i < QNT_ALTERNATIVAS; i++)
					text += reader.readLine() + "<p>";
				qTexts.add(text + "</html>");
			}

			reader.close();
		} catch (Exception e) {
			qTexts.clear();
			qTexts.add("<html> <h3>1) Como resultado da experi�ncia de Rutherford, foi conclu�do que</h3><p>"
					+ "A) o centro do �tomo � vazio.<p>"
					+ "B) a massa at�mica � distribu�da por todo o �tomo.<p>"
					+ "C) o centro do �tomo tem carga negativa.<p>"
					+ "D) a maior parte do �tomo � vazia. </html>");
			qTexts.add("<html> <h3>2) De acordo com Rutherford, o �tomo consiste nas duas seguintes partes</h3><p>"
					+ "A) El�trons e Pr�tons<p>"
					+ "B) N�cleo e a parte extra-nuclear<p>"
					+ "C) N�utrons e El�trons<p>"
					+ "D) Pr�tons e N�utrons </html>");
			qTexts.add("<html> <h3>3) As tr�s part�culas fundamentais do �tomo s�o</h3><p>"
					+ "A) n�cleo, Anti-quarks e El�trons<p>"
					+ "B) M�sons, Quarks e Anti-neutrino<p>"
					+ "C) Positron, Neutrino e Raios Gama<p>"
					+ "D) Pr�ton, N�utron e El�tron </html>");
		}
	}

	public void proxima() {
		provaListener.q = ++q;
		if (q < QNT_QUESTOES) {
			textArea.setText("Quest�o " + (q + 1) + "\n");
			con.getServerDispatcher().serverMessage(q + "");
			gui.getLabel().setText(qTexts.get(q));
		} else {
			fimProva();
		}
	}

	private void fimProva() {
		gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
		con.getServerDispatcher().serverMessage("fim");

		textArea.setText("Fim da prova\n");
		textArea.append(con.getServerDispatcher().getClientCount()
				+ " alunos fizeram a prova.\n");

		respostasNoArquivo();
	}

	private void respostasNoArquivo() {
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter("respostas.txt"));

			for (ClientInfo clientInfo : con.getServerDispatcher()
					.getmClients()) {
				out.write(clientInfo.toString() + ":");
				out.newLine();
				for (int i = 0; i < QNT_QUESTOES; i++) {
					out.write((i + 1) + ") "
							+ provaListener.getResposta(clientInfo, i));
					out.newLine();
					if (provaListener.getResposta(clientInfo, i).equals(
							"'A'")) {
					}
					if (provaListener.getResposta(clientInfo, i).equals(
							"'B'")) {
					}
					if (provaListener.getResposta(clientInfo, i).equals(
							"'C'")) {
					}
					if (provaListener.getResposta(clientInfo, i).equals(
							"'D'")) {
					}
				}
			}

			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verResultado() {
		int[] v = new int[QNT_ALTERNATIVAS];
		
		for (ClientInfo cliente : provaListener.getTodosClientes()) {
			String r = provaListener.getResposta(cliente, q);
			if (r.equals("'A'")) {
				v[0]++;
			} else if (r.equals("'B'")) {
				v[1]++;
			} else if (r.equals("'C'")) {
				v[2]++;
			} else if (r.equals("'D'")) {
				v[3]++;
			}
		}
		

		String[] v2 = {"A", "B", "C", "D"};
		textArea.setText("Quest�o " + (q + 1) + ":\n");
		for (int i = 0; i < QNT_ALTERNATIVAS; i++) {
			if (v[i] != 0) {
				textArea.append(v[i] + " alunos ("
						+ v[i] * 100
						/ con.getServerDispatcher().getClientCount()
						+ "%) responderam " + v2[i] + "\n");
			}
		}
		textArea.append("\n");
	}
}
