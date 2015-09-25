package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
	private Connections con;
	private int q, qntQuestoes;
	private ProvaGUI gui;
	private ArrayList<String> qTexts;
	private JTextArea textArea;
	private File file;

	public Prova(Connections paramCon, JTextArea paramTextArea, File paramFile) {
		con = paramCon;
		file = paramFile;
		q = -1;
		qntQuestoes = 0;

		if (!addQuestoes())
			return;

		textArea = paramTextArea;
		provaListener = new ProvaListener(textArea, con.getServerDispatcher()
				.getmClients());
		gui = new ProvaGUI();

		gui.getBtnPassar().addActionListener(new BtnPassarListener(this));
		gui.getBtnResultado().addActionListener(new BtnResultadoListener(this));
		con.getServerDispatcher().serverMessage("comecou");
		proxima();
	}

	private boolean addQuestoes() {
		qTexts = new ArrayList<String>();
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));

			String temp;
			while ((temp = reader.readLine()) != null) {
				qntQuestoes++;
				String text = "<html> <h1>" + temp + "</h1><p>";
				for (int i = 0; i < QNT_ALTERNATIVAS; i++)
					text += "<h3>" + reader.readLine() + "</h3><p>";
				qTexts.add(text + "</html>");
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public void proxima() {
		provaListener.q = ++q;
		if (q < qntQuestoes) {
			textArea.setText("Questão " + (q + 1) + "\n");
			con.getServerDispatcher().serverMessage(q + "");
			gui.getLabel().setText(qTexts.get(q));
		} else {
			fimProva();
		}
	}

	private void fimProva() {
		gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
		con.getServerDispatcher().serverMessage("fim");

		textArea.setText("Fim da avaliação\n");
		textArea.append(con.getServerDispatcher().getClientCount()
				+ " alunos fizeram a avaliação.\n");

		respostasNoArquivo();
	}

	private void respostasNoArquivo() {
		BufferedWriter out = null;

		try {
			String fileName = (file.getName().split("\\.")[0])
					.concat("-resultados.txt");
			out = new BufferedWriter(new FileWriter(fileName));

			for (ClientInfo clientInfo : con.getServerDispatcher()
					.getmClients()) {
				out.write(clientInfo.toString() + ":");
				out.newLine();
				for (int i = 0; i < qntQuestoes; i++) {
					String r = provaListener.getResposta(clientInfo, i);
					if (r == null)
						r = "0";
					out.write((i + 1) + ") "
							+ r);
					out.newLine();
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
			if (r == null)
				continue;
			if (r.equals("A")) {
				v[0]++;
			} else if (r.equals("B")) {
				v[1]++;
			} else if (r.equals("C")) {
				v[2]++;
			} else if (r.equals("D")) {
				v[3]++;
			}
		}

		String[] v2 = { "A", "B", "C", "D" };
		textArea.setText("Questão " + (q + 1) + ":\n");
		for (int i = 0; i < QNT_ALTERNATIVAS; i++) {
			if (v[i] != 0) {
				textArea.append(v[i] + " alunos (" + v[i] * 100
						/ con.getServerDispatcher().getClientCount()
						+ "%) responderam " + v2[i] + "\n");
			}
		}
		textArea.append("\n");
	}

	public static boolean isOpen() {
		return provaListener != null;
	}
}
