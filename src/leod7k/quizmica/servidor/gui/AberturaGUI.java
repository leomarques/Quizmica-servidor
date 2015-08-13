package leod7k.quizmica.servidor.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AberturaGUI extends JFrame {
	private Panel3 panel;

	public JFrame j = this;

	public AberturaGUI() {
		setTitle("Quizmica");
		setSize(543, 659);
		setLocationRelativeTo(null);

		panel = new Panel3();
		add(panel);

		setVisible(true);

		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				fechar();
			}
		}, 5000);
	}

	private void fechar() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}

@SuppressWarnings("serial")
class Panel3 extends JPanel {
	private Image image;

	public Panel3() {
		image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png"));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
	}
}