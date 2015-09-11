package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JTextArea;

import leod7k.quizmica.servidor.Connections;
import leod7k.quizmica.servidor.ConnectionsHandler;
import leod7k.quizmica.servidor.ServerDispatcher;

public class BtnAbrirListener implements ActionListener {
	public static final int LISTENING_PORT = 2002;

	private JTextArea textArea;
	private Connections con;

	public BtnAbrirListener(Connections paramCOn, JTextArea paramTextArea) {
		con = paramCOn;
		textArea = paramTextArea;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Open server socket for listening
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);

			textArea.append("Servidor iniciado\n");
		} catch (IOException se) {
			se.printStackTrace();

			textArea.append("N�o � poss�vel ouvir a porta " + LISTENING_PORT
					+ "\n");
			return;
		}

		con.setServerSocket(serverSocket);

		// Start ServerDispatcher thread
		ServerDispatcher serverDispatcher = new ServerDispatcher();
		serverDispatcher.start();

		con.setServerDispatcher(serverDispatcher);

		ConnectionsHandler connectionsHandler = new ConnectionsHandler(
				serverSocket, serverDispatcher, textArea);
		connectionsHandler.start();

		con.setConnectionsHandler(connectionsHandler);
	}

}
