package pk_dokjaquiz_RF;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imagePanel extends JPanel {
	private BufferedImage image;

	public imagePanel(String file) throws IOException {
		image = ImageIO.read(new File(file));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double ratio = (double) image.getHeight() / (double) image.getWidth();

		int Width = this.getWidth();
		int Height = (int) (Width * ratio);

		g.drawImage(image, 0, 0, Width, Height, null);
	}
}
