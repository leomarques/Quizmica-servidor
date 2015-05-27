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

public class Prova {

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
		gui = new ProvaGUI();

		gui.getBtnPassar().addActionListener(new BtnPassarListener(this));
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
			qTexts.add("<html> <h3>1) Como resultado da experiência de Rutherford, foi concluído que</h3><p>"
					+ "A) o centro do átomo é vazio.<p>"
					+ "B) a massa atômica é distribuída por todo o átomo.<p>"
					+ "C) o centro do átomo tem carga negativa.<p>"
					+ "D) a maior parte do átomo é vazia. </html>");
			qTexts.add("<html> <h3>2) De acordo com Rutherford, o átomo consiste nas duas seguintes partes</h3><p>"
					+ "A) Elétrons e Prótons<p>"
					+ "B) Núcleo e a parte extra-nuclear<p>"
					+ "C) Nêutrons e Elétrons<p>" + "D) Prótons e Nêutrons </html>");
			qTexts.add("<html> <h3>3) As três partículas fundamentais do átomo são</h3><p>"
					+ "A) núcleo, Anti-quarks e Elétrons<p>"
					+ "B) Mésons, Quarks e Anti-neutrino<p>"
					+ "C) Positron, Neutrino e Raios Gama<p>"
					+ "D) Próton, Nêutron e Elétron </html>");
		}
	}

	public void proxima() {
		if (++q < QNT_QUESTOES) {
			textArea.setText("Questão " + (q + 1) + "\n");
			con.getServerDispatcher().serverMessage(q + "");
			gui.getLabel().setText(qTexts.get(q));
		} else {
			gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
			con.getServerDispatcher().serverMessage("fim");

			textArea.setText("Fim da prova\n");
			textArea.setText("Resultado:\n");
			textArea.setText(con.getServerDispatcher().getClientCount()
					+ " alunos fizeram a prova.\n");

			int[][] resultado = new int[QNT_QUESTOES][QNT_ALTERNATIVAS];
			BufferedWriter out = null;

			try {
				out = new BufferedWriter(new FileWriter("respostas.txt"));

				for (ClientInfo clientInfo : con.getServerDispatcher()
						.getmClients()) {
					out.write(clientInfo.toString() + ":");
					out.newLine();
					for (int i = 0; i < QNT_QUESTOES; i++) {
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

			for (int i = 0; i < QNT_QUESTOES; i++) {
				textArea.append("Questão " + i + ":\n");
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
