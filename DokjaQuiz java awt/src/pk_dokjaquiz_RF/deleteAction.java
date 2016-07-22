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

public class deleteAction implements MouseListener {

	private JList list;
	private ArrayList arr;

	public deleteAction() {
	}

	public void setVar(JList list_p1, ArrayList Arr) {
		list = list_p1;
		arr = Arr;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			arr.remove(list.getSelectedIndex());
			DefaultListModel listModel = (DefaultListModel) list.getModel();
			listModel.remove(list.getSelectedIndex());
			list.setModel(listModel);
			System.out.println(arr);
		}
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
