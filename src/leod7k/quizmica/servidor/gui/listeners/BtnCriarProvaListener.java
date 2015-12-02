package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import leod7k.quizmica.servidor.CriarProva;

public class BtnCriarProvaListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new CriarProva();
	}
}
