package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.ConnectionsHandler;
import leod7k.quizmica.servidor.ServerDispatcher;

public class BtnAbrirListener implements ActionListener {
	public static final int LISTENING_PORT = 2002;
	private JTextArea textArea;

	public BtnAbrirListener(JTextArea paramTextArea) {
		textArea = paramTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Open server socket for listening
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
			textArea.append("Server started on port " + LISTENING_PORT);
		} catch (IOException se) {
			textArea.append("Can not start listening on port "
					+ LISTENING_PORT);
			se.printStackTrace();
			return;
		}

		// Start ServerDispatcher thread
		ServerDispatcher serverDispatcher = new ServerDispatcher();
		serverDispatcher.start();

		ConnectionsHandler connectionsHandler = new ConnectionsHandler(
				serverSocket, serverDispatcher);
		connectionsHandler.start();
	}

}
