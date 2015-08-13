package leod7k.quizmica.servidor.gui;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AberturaGUI extends JFrame {
	private Panel3 panel;

	public JFrame j = this;
	
	private BufferedImage image;

	public AberturaGUI() {		
		try {
			image = ImageIO.read(new File("logo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setTitle("Quizmica");
		setSize(image.getWidth(), image.getHeight());
		setLocationRelativeTo(null);

		panel = new Panel3(image);
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
	private BufferedImage image;

	public Panel3(BufferedImage image2) {
		image = image2;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
	}
}