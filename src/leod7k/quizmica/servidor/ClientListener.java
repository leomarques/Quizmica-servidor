package leod7k.quizmica.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTextArea;

public class ClientListener extends Thread {

	private ServerDispatcher mServerDispatcher;
	private ClientInfo mClientInfo;
	private BufferedReader mIn;
	private JTextArea textArea;

	public ClientListener(ClientInfo aClientInfo, ServerDispatcher aServerDispatcher, JTextArea aTextArea)
			throws IOException {
		mClientInfo = aClientInfo;
		mServerDispatcher = aServerDispatcher;
		Socket socket = aClientInfo.mSocket;
		textArea = aTextArea;
		mIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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

				String[] mensagemArray = message.split(";");
				if (mensagemArray.length >= 2 && mensagemArray[0].equals("r")) {
					if (Prova.isOpen()) {
						Prova.provaListener.addResposta(mClientInfo, mensagemArray[1]);
					}
				}
				if (mensagemArray.length >= 2 && mensagemArray[0].equals("a")) {
					mClientInfo.nome = mensagemArray[1];

					if (Prova.isOpen()) {
						Set<ClientInfo> clientes = Prova.provaListener.getTodosClientes();
						boolean achou = false;
						for (Iterator<ClientInfo> it = clientes.iterator(); it.hasNext();) {
							ClientInfo cliente = it.next();
							if (cliente.equals(mClientInfo)) {
								cliente.mSocket = mClientInfo.mSocket;
								cliente.mClientListener = mClientInfo.mClientListener;
								cliente.mClientSender = mClientInfo.mClientSender;
								mClientInfo = cliente;
								cliente.mClientSender.mClientInfo = cliente;

								achou = true;
								mClientInfo.mClientSender.sendMessage("comecou");
								break;
							}
						}
						if (!achou) {
							mClientInfo.nome = null;
							break;
						}
					}

					mServerDispatcher.addClient(mClientInfo);

					textArea.append("Usuário " + mClientInfo + " conectou, " + mServerDispatcher.getClientCount()
							+ " usuário(s) online.\n");

					mClientInfo.mClientSender.sendMessage("in");
				}
			}
		} catch (IOException ioex) {
			// Problem reading from socket (communication is broken)
		}

		// Communication is broken. Interrupt both listener and sender
		// threads
		mClientInfo.mClientSender.interrupt();
		if (mClientInfo.nome != null) {
			mServerDispatcher.deleteClient(mClientInfo);
			textArea.append(mClientInfo + " desconectou\n");
			textArea.append(mServerDispatcher.getClientCount() + " usuário(s) online.\n");
		}
	}

}
