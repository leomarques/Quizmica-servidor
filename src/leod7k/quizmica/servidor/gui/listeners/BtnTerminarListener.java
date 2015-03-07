package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.Connections;

public class BtnTerminarListener implements ActionListener {
	
	private Connections con;
	private JTextArea textArea;

	public BtnTerminarListener(Connections paramCon, JTextArea paramTextArea) {
		con = paramCon;
		textArea = paramTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (con.getServerSocket() == null) {
			return;
		}
		
		try {
			con.getConnectionsHandler().interrupt();
			con.getServerDispatcher().closeAllClients();
			con.getServerDispatcher().interrupt();
			con.getServerSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		textArea.append("Terminado\n");
	}

}
