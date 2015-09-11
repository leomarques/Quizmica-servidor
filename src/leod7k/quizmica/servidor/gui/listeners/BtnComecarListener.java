package leod7k.quizmica.servidor.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import leod7k.quizmica.servidor.Connections;
import leod7k.quizmica.servidor.Prova;
import leod7k.quizmica.servidor.gui.ServerGUI;

public class BtnComecarListener implements ActionListener {

	private Connections con;
	private JTextArea textArea;
	private ServerGUI serverGui;

	public BtnComecarListener(Connections paramCon, JTextArea paramTextArea,
			ServerGUI paramServerGUI) {
		con = paramCon;
		textArea = paramTextArea;
		serverGui = paramServerGUI;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (con.getServerSocket() == null || con.getServerSocket().isClosed()) {
			return;
		}

		final JFileChooser fc = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);

		int returnVal = fc.showOpenDialog(serverGui);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			if (file != null) {
				new Prova(con, textArea, file);
			}
		}
	}

}
