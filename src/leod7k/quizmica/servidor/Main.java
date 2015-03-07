package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.ServerGUI;
import leod7k.quizmica.servidor.gui.listeners.BtnAbrirListener;

public class Main {

	public static void main(String[] args) {

		ServerGUI serverGUI = new ServerGUI();
		JTextArea textArea = serverGUI.getTextArea();
		JButton btnAbrir = serverGUI.getBtnAbrir();
		JButton btnComecar = serverGUI.getBtnComecar();
		JButton btnTerminar = serverGUI.getBtnTerminar();

		ServerSocket serverSocket = null;
		ServerDispatcher serverDispatcher = null;
		ConnectionsHandler connectionsHandler = null;

		btnAbrir.addActionListener(new BtnAbrirListener(serverSocket,
				serverDispatcher, connectionsHandler, textArea));

		serverGUI.addWindowListener(new WindowCLoser());

		/*
		 * Scanner scanner = new Scanner(System.in); while (true) { String
		 * message = scanner.nextLine();
		 * 
		 * if (message.equals("/m")) {
		 * serverDispatcher.serverMessage(scanner.nextLine()); }
		 * 
		 * if (message.equals("/u")) {
		 * System.out.println(serverDispatcher.getClientCount() +
		 * " users online."); }
		 * 
		 * if (message.equals("/q")) { connectionsHandler.interrupt(); try {
		 * serverDispatcher.closeAllClients(); serverDispatcher.interrupt();
		 * serverSocket.close(); } catch (IOException e) { e.printStackTrace();
		 * }
		 * 
		 * scanner.close(); System.exit(0); }
		 * 
		 * serverDispatcher.serverMessage(message); }
		 */
	}

}

class WindowCLoser implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		//close socket
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}