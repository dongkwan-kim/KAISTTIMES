package pk_dokjaquiz_RF;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class sortAction implements ActionListener {

	private JList list_result;
	private ArrayList<dokjaQuiz> arr_result;
	private createAction createAct;
	
	private DefaultListModel listModel_result = new DefaultListModel();
	
	public sortAction() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		arr_result = createAct.getResult();
		sort(arr_result);
		createAct.setResult(arr_result);
		
		list_result.removeAll();
		
		for (int index = 0; index < arr_result.size(); index++) {
			listModel_result.addElement(arr_result.get(index).getFullDQ());
		}
		list_result.setModel(listModel_result);
	}

	private void sort(ArrayList<dokjaQuiz> arr) {
		for (int index = 1; index < arr.size(); index++) {
			dokjaQuiz temp = arr.get(index);
			int mainFreq = arr.get(index).getFrequency();
			int auxIndex = index - 1;
			int auxFreq = arr.get(auxIndex).getFrequency();

			while (mainFreq > auxFreq) {
				arr.set(auxIndex + 1, arr.get(auxIndex));
				auxIndex--;
				if(auxIndex < 0){break;}
				auxFreq = arr.get(auxIndex).getFrequency();
			}
			arr.set(auxIndex + 1, temp);
		}
	}
	
	public void setVar(JList list_result2, createAction creAct){
		list_result = list_result2;
		createAct = creAct;
	}
	
}
