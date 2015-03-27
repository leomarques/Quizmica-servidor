package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.Connections;
import leod7k.quizmica.servidor.Prova;

public class BtnComecarListener implements ActionListener {

	private Connections con;
	private JTextArea textArea;

	public BtnComecarListener(Connections paramCon, JTextArea paramTextArea) {
		con = paramCon;
		textArea = paramTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (con.getServerSocket() == null || con.getServerSocket().isClosed()) {
			return;
		}
		con.getConnectionsHandler().interrupt();

		new Prova(con, textArea);
	}

}
