package leod7k.quizmica.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionsHandler extends Thread {

	private ServerSocket serverSocket;
	private ServerDispatcher serverDispatcher;

	public ConnectionsHandler(ServerSocket paramSocket,
			ServerDispatcher paramServerDispatcher) {
		serverSocket = paramSocket;
		serverDispatcher = paramServerDispatcher;
	}

	public void run() {
		// Accept and handle client connections
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientInfo clientInfo = new ClientInfo();
				clientInfo.mSocket = socket;
				ClientListener clientListener = new ClientListener(clientInfo,
						serverDispatcher);
				ClientSender clientSender = new ClientSender(clientInfo,
						serverDispatcher);
				clientInfo.mClientListener = clientListener;
				clientInfo.mClientSender = clientSender;
				clientListener.start();
				clientSender.start();
				serverDispatcher.addClient(clientInfo);
				System.out.println("User " + clientInfo + " logged in, "
						+ serverDispatcher.getClientCount()
						+ " user(s) online.");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}