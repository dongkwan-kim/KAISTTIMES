package pk_dokjaquiz_RF;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class createAction implements ActionListener {

	private JList list_result;
	private ArrayList arr_p1;
	private ArrayList arr_p2;
	private DQ_Engine engine = new DQ_Engine();
	public ArrayList<dokjaQuiz> arr_result;
	
	private DefaultListModel listModel_result;

	public createAction() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		listModel_result = new DefaultListModel();
		list_result.setModel(listModel_result);
		try {
			engine.setPool(arr_p1, arr_p2);
			arr_result = engine.getDokjaQuiz();
			for (int index = 0; index < arr_result.size(); index++) {
				listModel_result.addElement(arr_result.get(index).getFullDQ());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		list_result.setModel(listModel_result);
	}

	public void setList(JList list_result2) {
		list_result = list_result2;
	}

	public void setArrayList(ArrayList arr1, ArrayList arr2) {
		arr_p1 = arr1;
		arr_p2 = arr2;
	}
	
	public ArrayList<dokjaQuiz> getResult(){
		return arr_result;
	}
	
	public void setResult(ArrayList<dokjaQuiz> arr){
		arr_result = arr;
	}

}
