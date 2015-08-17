package leod7k.quizmica.servidor.gui;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class AberturaGUI extends JFrame {
	private Panel3 panel;

	public JFrame j = this;
	
	private BufferedImage image;

	public AberturaGUI() {		
		try {
			image = ImageIO.read(getClass().getResource("/img/logo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		URL url = getClass().getResource("/img/load.gif");
		
		setTitle("Quizmica");
		setSize(image.getWidth() + 20, image.getHeight() + 30);
		setLocationRelativeTo(null);

		panel = new Panel3(image, url);
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

	public Panel3(BufferedImage image2, URL url) {
		image = image2;
		
		Icon myImgIcon = new ImageIcon(url);
		JLabel imageLbl = new JLabel(myImgIcon);
		add(imageLbl);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
	}
}