package pk_dokjaquiz_RF;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class wholePanel extends JPanel {
	
	public void wholePanel(){
		
	}
	
	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			double weightx, double weighty, Insets insets) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = insets;
		gbl.setConstraints(c, gbc);
		add(c);
	}

}
