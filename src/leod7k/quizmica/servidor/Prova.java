package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
		qTexts.add("<html> <h3>1) Como resultado da experi�ncia de Rutherford, foi conclu�do que</h3><p>"
				+ "A) o centro do �tomo � vazio.<p>"
				+ "B) a massa at�mica � distribu�da por todo o �tomo.<p>"
				+ "C) o centro do �tomo tem carga negativa.<p>"
				+ "D) a maior parte do �tomo � vazia. </html>");
		qTexts.add("<html> <h3>2) De acordo com Rutherford, o �tomo consiste nas duas seguintes partes</h3><p>"
				+ "A) El�trons e Pr�tons<p>"
				+ "B) N�cleo e a parte extra-nuclear<p>"
				+ "C) N�utrons e El�trons<p>" + "D) Pr�tons e N�utrons </html>");
		qTexts.add("<html> <h3>3) As tr�s part�culas fundamentais do �tomo s�o</h3><p>"
				+ "A) n�cleo, Anti-quarks e El�trons<p>"
				+ "B) M�sons, Quarks e Anti-neutrino<p>"
				+ "C) Positron, Neutrino e Raios Gama<p>"
				+ "D) Pr�ton, N�utron e El�tron </html>");
	}

	public void proxima() {
		if (++q < 3) {
			textArea.setText("Quest�o " + (q + 1) + "\n");
			con.getServerDispatcher().serverMessage(q + "");
			gui.getLabel().setText(qTexts.get(q));
		} else {
			gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
			con.getServerDispatcher().serverMessage("fim");

			textArea.setText("Fim da prova\n");
			textArea.setText("Resultado:\n");
			textArea.setText(con.getServerDispatcher().getClientCount()
					+ " alunos fizeram a prova.\n");

			int[][] resultado = new int[3][4];
			BufferedWriter out = null;

			try {
				out = new BufferedWriter(new FileWriter("respostas.txt"));

				for (ClientInfo clientInfo : con.getServerDispatcher()
						.getmClients()) {
					out.write(clientInfo.toString() + ":");
					out.newLine();
					for (int i = 0; i < 3; i++) {
						out.write((i + 1) + ") "
								+ clientInfo.mClientListener.getRespostas()
										.get(i));
						out.newLine();
						if (clientInfo.mClientListener.getRespostas().get(i)
								.equals("'A'")) {
							resultado[i][0]++;
						}
						if (clientInfo.mClientListener.getRespostas().get(i)
								.equals("'B'")) {
							resultado[i][1]++;
						}
						if (clientInfo.mClientListener.getRespostas().get(i)
								.equals("'C'")) {
							resultado[i][2]++;
						}
						if (clientInfo.mClientListener.getRespostas().get(i)
								.equals("'D'")) {
							resultado[i][3]++;
						}
					}
				}
				
				out.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int i = 0; i < 3; i++) {
				textArea.append("Quest�o " + i + ":\n");
				if (resultado[i][0] != 0) {
					textArea.append(resultado[i][0] + " alunos ("
							+ resultado[i][0] * 100
							/ con.getServerDispatcher().getClientCount()
							+ "%) responderam 'A'\n");
				}
				if (resultado[i][1] != 0) {
					textArea.append(resultado[i][1] + " alunos ("
							+ resultado[i][1] * 100
							/ con.getServerDispatcher().getClientCount()
							+ "%) responderam 'B'\n");
				}
				if (resultado[i][2] != 0) {
					textArea.append(resultado[i][2] + " alunos ("
							+ resultado[i][2] * 100
							/ con.getServerDispatcher().getClientCount()
							+ "%) responderam 'C'\n");
				}
				if (resultado[i][3] != 0) {
					textArea.append(resultado[i][3] + " alunos ("
							+ resultado[i][3] * 100
							/ con.getServerDispatcher().getClientCount()
							+ "%) responderam 'D'\n");
				}
				textArea.append("\n");
			}
		}
	}

}
