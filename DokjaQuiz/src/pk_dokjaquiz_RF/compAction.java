package pk_dokjaquiz_RF;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class compAction implements ActionListener, MouseListener {

	private JList list_result;
	private JList list_show;
	private JList list_showTitle;
	private createAction createAct;
	private ArrayList<dokjaQuiz> arr_result;

	private DefaultListModel listModel_show = new DefaultListModel();
	private DefaultListModel listModel_showTitle = new DefaultListModel();

	public compAction() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		todo();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			todo();
		}
	}


	private void todo() {
		
		arr_result = createAct.getResult();
		listModel_showTitle.removeAllElements();
		listModel_show.removeAllElements();
		list_showTitle.setModel(listModel_showTitle);
		list_show.setModel(listModel_show);

		dokjaQuiz DQ = arr_result.get(list_result.getSelectedIndex());
		System.out.println(DQ.getFullDQ());
		listModel_showTitle.addElement(DQ.getFullDQ());

		String[] strArr = DQ.getFullDQ().split("");
		for (int index = 0; index < 4; index++) {
			if (DQ.getJ() == index) {
				listModel_show.addElement(strArr[index + 1] + ": J");
			} else {
				listModel_show.addElement(strArr[index + 1] + ": "
						+ DQ.getEmtOfIndex(index));
			}
		}
		listModel_showTitle.addElement("# ºóµµ: " + DQ.getFrequency());

		list_show.setModel(listModel_show);
		list_showTitle.setModel(listModel_showTitle);
	}

	public void setList(JList list_result2, JList list_show2,
			JList list_showTitle2) {
		list_result = list_result2;
		list_show = list_show2;
		list_showTitle = list_showTitle2;
	}

	public void setArrayList(createAction creAct) {
		createAct = creAct;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
