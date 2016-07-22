package pk_dokjaquiz_RF;

import java.awt.Choice;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;

public class inputAction implements ActionListener {

	private JTextField TF_input;
	private JComboBox choice;
	private ArrayList arr_p1;
	private ArrayList arr_p2;
	private JList list_p1;
	private JList list_p2;

	private ArrayList inputArr;
	private ArrayList defaultArr;
	
	private DefaultListModel listModel1 = new DefaultListModel();
	private DefaultListModel listModel2 = new DefaultListModel();

	public inputAction() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		inputArr = new ArrayList();

		String getStr = TF_input.getText();


		if (!getStr.equals("")) {
			if (choice.getSelectedIndex() == 0) {
				listModel1.addElement(getStr);
				list_p1.setModel(listModel1);
				arr_p1.add(getStr);
			} else {
				listModel2.addElement(getStr);
				list_p2.setModel(listModel2);
				arr_p2.add(getStr);
			}
		}

		TF_input.selectAll();
		TF_input.setText("");
		TF_input.requestFocus();
	}

	public void setTF(JTextField TF) {
		TF_input = TF;
	}

	public void setChoice(JComboBox choice2) {
		choice = choice2;
	}

	public void setArrayList(ArrayList p1, ArrayList p2) {
		arr_p1 = p1;
		arr_p2 = p2;
	}

	public void setList(JList list1, JList list2) {
		list_p1 = list1;
		list_p2 = list2;
	}

}
