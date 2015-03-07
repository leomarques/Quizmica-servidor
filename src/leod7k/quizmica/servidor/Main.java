package leod7k.quizmica.servidor;

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

		btnAbrir.addActionListener(new BtnAbrirListener(textArea));

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