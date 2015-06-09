package leod7k.quizmica.servidor;

/**
 * ClientInfo class contains information about a client, connected to the server.
 */

import java.net.Socket;

public class ClientInfo {
	public Socket mSocket = null;
	public ClientListener mClientListener = null;
	public ClientSender mClientSender = null;
	public String nome = null;

	public String toString() {
		if (nome == null) {
			String ip = mSocket.getInetAddress().getHostAddress();
			String port = "" + mSocket.getPort();
			return ip + ":" + port;
		}
		
		return nome;
	}
}