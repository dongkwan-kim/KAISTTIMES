package pk_dokjaquiz_RF;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class clearAction implements ActionListener {
	
	private ArrayList<ArrayList> arrArr = new ArrayList();
	private ArrayList<JList> arrList = new ArrayList();
	private DefaultListModel listModel = new DefaultListModel();

	@Override
	public void actionPerformed(ActionEvent e) {
		for(ArrayList arr : arrArr){
			arr.clear();
		}
		for(JList list : arrList){
			list.setModel(listModel);
		}
	}
	
	public void addArr(ArrayList arr){
		arrArr.add(arr);
	}
	public void addList(JList list_show){
		arrList.add(list_show);
	}
}
