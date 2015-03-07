package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowCloser implements WindowListener {

	private BtnTerminarListener btl ;

	public WindowCloser(BtnTerminarListener paramBtl) {
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