package pk_dokjaquiz_RF;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

public class loading extends JWindow {

	Image img = Toolkit.getDefaultToolkit().getImage("images/loading.png");
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	ImageIcon imgicon = new ImageIcon(img);

	@SuppressWarnings("deprecation")
	public loading() {
		try {

			setSize(imgicon.getIconWidth(), imgicon.getIconHeight());
			setLocation(screenSize.width / 2 - imgicon.getIconWidth() / 2,
					screenSize.height / 2 - imgicon.getIconHeight() / 2);

			show();
			Thread.sleep(2500);
			dispose();
		} catch (Exception exception) {
			javax.swing.JOptionPane.showMessageDialog(
					(java.awt.Component) null,
					"Error" + exception.getMessage(), "Error:",
					javax.swing.JOptionPane.DEFAULT_OPTION);
		}
	}

	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

}