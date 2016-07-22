package pk_dokjaquiz_RF;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;

public class Gui_Dialog extends Dialog {

	private String contents = "";

	Toolkit windowSize = Toolkit.getDefaultToolkit();
	Dimension screenSize = windowSize.getScreenSize();
	TextArea intraTA = new TextArea(null, 0, 0,
			TextArea.SCROLLBARS_VERTICAL_ONLY);
	Button extButton = new Button("»Æ¿Œ");
	
	JPanel buttonPanel = new JPanel(new GridLayout(0, 3));

	public Gui_Dialog(Frame owner, String title) {

		super(owner, title);

		extButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//extButton.setBackground(Color.white);

		intraTA.setEditable(false);

		setLayout(new BorderLayout());
		setResizable(false);
		addWindowListener(new windowTopWeakAction());
		
		buttonPanel.add(new Label(" "));
		buttonPanel.add(extButton);

		add(intraTA, "Center");
		add(buttonPanel, "South");
	}

	public void setContentByStr(String str) {
		if (contents.equals("")) {
			contents += str;
		} else {
			contents += "\n\n" + str;
		}
		intraTA.setText(contents);
	}

	public void setContentByFile(String file) throws IOException {

		BufferedReader readerDict = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "euc-kr"));

		String line;

		while ((line = readerDict.readLine()) != null) {
			contents += "\n" + line;
		}
		intraTA.setText(contents);
	}

	public void setSizeDialog(int width, int height) {
		setSize(width, height);
		setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2
				- height / 2);
	}
}
