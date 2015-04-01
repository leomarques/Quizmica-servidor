package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import leod7k.quizmica.servidor.Prova;

public class BtnPassarListener implements ActionListener {

	private Prova prova;

	public BtnPassarListener(Prova paramProva) {
		prova = paramProva;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		prova.proxima();
	}

}
