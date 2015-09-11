package leod7k.quizmica.servidor;

import javax.swing.JButton;
import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.AberturaGUI;
import leod7k.quizmica.servidor.gui.ServerGUI;
import leod7k.quizmica.servidor.gui.listeners.BtnAbrirListener;
import leod7k.quizmica.servidor.gui.listeners.BtnComecarListener;
import leod7k.quizmica.servidor.gui.listeners.BtnTerminarListener;
import leod7k.quizmica.servidor.gui.listeners.WindowCloser;

public class Main {

	public static void main(String[] args) {

		ServerGUI serverGUI = new ServerGUI();
		@SuppressWarnings("unused")
		//AberturaGUI aberturaGUI = new AberturaGUI();
		JTextArea textArea = serverGUI.getTextArea();
		JButton btnAbrir = serverGUI.getBtnAbrir();
		JButton btnComecar = serverGUI.getBtnComecar();
		JButton btnTerminar = serverGUI.getBtnTerminar();

		Connections con = new Connections();

		btnAbrir.addActionListener(new BtnAbrirListener(con, textArea));

		btnComecar.addActionListener(new BtnComecarListener(con, textArea,
				serverGUI));

		BtnTerminarListener btl = new BtnTerminarListener(con, textArea);
		btnTerminar.addActionListener(btl);

		serverGUI.addWindowListener(new WindowCloser(btl));
	}

}