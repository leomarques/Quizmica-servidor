package leod7k.quizmica.servidor;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import leod7k.quizmica.servidor.gui.ServerGUI;
import leod7k.quizmica.servidor.gui.listeners.BtnAbrirListener;
import leod7k.quizmica.servidor.gui.listeners.BtnTerminarListener;

public class Main {

	public static void main(String[] args) {

		ServerGUI serverGUI = new ServerGUI();
		JTextArea textArea = serverGUI.getTextArea();
		JButton btnAbrir = serverGUI.getBtnAbrir();
		//JButton btnComecar = serverGUI.getBtnComecar();
		JButton btnTerminar = serverGUI.getBtnTerminar();

		Connections con = new Connections();

		btnAbrir.addActionListener(new BtnAbrirListener(con, textArea));
		
		BtnTerminarListener btl = new BtnTerminarListener(con, textArea);
		btnTerminar.addActionListener(btl);

		serverGUI.addWindowListener(new WindowCLoser(btl));
	}

}

class WindowCLoser implements WindowListener {

	private BtnTerminarListener btl ;

	public WindowCLoser(BtnTerminarListener paramBtl) {
		btl = paramBtl;
	}

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
		btl.actionPerformed(null);
        System.exit(0);
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