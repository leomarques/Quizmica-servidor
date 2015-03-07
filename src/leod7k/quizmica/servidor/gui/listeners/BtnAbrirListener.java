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
	private ServerSocket serverSocket;
	private ServerDispatcher serverDispatcher;
	private ConnectionsHandler connectionsHandler;

	public BtnAbrirListener(ServerSocket paramServerSocket, ServerDispatcher paramServerDispatcher, ConnectionsHandler paramConnectionsHandler, JTextArea paramTextArea) {
		textArea = paramTextArea;
		serverSocket = paramServerSocket;
		serverDispatcher = paramServerDispatcher;
		connectionsHandler = paramConnectionsHandler;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Open server socket for listening
		serverSocket = null;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
			
			textArea.append("Server started on port " + LISTENING_PORT);
		} catch (IOException se) {
			se.printStackTrace();
			
			textArea.append("Can not start listening on port "
					+ LISTENING_PORT);
			return;
		}

		// Start ServerDispatcher thread
		serverDispatcher = new ServerDispatcher();
		serverDispatcher.start();

		connectionsHandler = new ConnectionsHandler(
				serverSocket, serverDispatcher);
		connectionsHandler.start();
	}

}
