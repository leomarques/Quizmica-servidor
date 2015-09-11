package leod7k.quizmica.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ConnectionsHandler extends Thread {

	private ServerSocket serverSocket;
	private ServerDispatcher serverDispatcher;
	private JTextArea textArea;

	public ConnectionsHandler(ServerSocket paramSocket,
			ServerDispatcher paramServerDispatcher, JTextArea paramTextArea) {
		serverSocket = paramSocket;
		serverDispatcher = paramServerDispatcher;
		textArea = paramTextArea;
	}

	public void run() {
		// Accept and handle client connections
		while (!isInterrupted()) {
			try {
				Socket socket = serverSocket.accept();
				ClientInfo clientInfo = new ClientInfo();
				clientInfo.mSocket = socket;
				ClientListener clientListener = new ClientListener(clientInfo,
						serverDispatcher, textArea);
				ClientSender clientSender = new ClientSender(clientInfo,
						serverDispatcher);
				clientInfo.mClientListener = clientListener;
				clientInfo.mClientSender = clientSender;
				clientListener.start();
				clientSender.start();
			} catch (IOException ioe) {
				// ioe.printStackTrace();
				System.out.println("socket closed exception");
			}
		}
	}
}