package leod7k.quizmica.servidor;

import java.net.ServerSocket;

public class Connections {
	ServerSocket serverSocket = null;
	ServerDispatcher serverDispatcher = null;
	ConnectionsHandler connectionsHandler = null;
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	public ServerDispatcher getServerDispatcher() {
		return serverDispatcher;
	}
	public void setServerDispatcher(ServerDispatcher serverDispatcher) {
		this.serverDispatcher = serverDispatcher;
	}
	public ConnectionsHandler getConnectionsHandler() {
		return connectionsHandler;
	}
	public void setConnectionsHandler(ConnectionsHandler connectionsHandler) {
		this.connectionsHandler = connectionsHandler;
	}
}
