package leod7k.quizmica.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class ClientListener extends Thread {

	private ServerDispatcher mServerDispatcher;
	private ClientInfo mClientInfo;
	private BufferedReader mIn;
	private JTextArea textArea;
	private ArrayList<String> respostas;

	public ClientListener(ClientInfo aClientInfo,
			ServerDispatcher aServerDispatcher, JTextArea paramTextArea)
			throws IOException {
		mClientInfo = aClientInfo;
		mServerDispatcher = aServerDispatcher;
		Socket socket = aClientInfo.mSocket;
		mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		textArea = paramTextArea;
		respostas = new ArrayList<String>();
	}

	/**
	 * Until interrupted, reads messages from the client socket, forwards them
	 * to the server dispatcher's queue and notifies the server dispatcher.
	 */
	public void run() {
		try {
			while (!isInterrupted()) {
				String message = mIn.readLine();
				if (message == null)
					break;

				textArea.append(message + "\n");
				
				String[] mensagem = message.split(" ");
				if (mensagem[1].equals("respondeu")) {
					respostas.add(message.split(" ")[2]);
				}
				
				mClientInfo.mClientSender.sendMessage("recebi");
			}
		} catch (IOException ioex) {
			// Problem reading from socket (communication is broken)
		}

		// Communication is broken. Interrupt both listener and sender
		// threads
		mClientInfo.mClientSender.interrupt();
		mServerDispatcher.deleteClient(mClientInfo);
	}

	public ArrayList<String> getRespostas() {
		return respostas;
	}

}
