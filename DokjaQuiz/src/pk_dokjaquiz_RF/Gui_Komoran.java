package pk_dokjaquiz_RF;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Gui_Komoran extends Dialog {

	private Toolkit windowSize = Toolkit.getDefaultToolkit();
	private Dimension screenSize = windowSize.getScreenSize();

	private Noun_Engine nEngine;

	private ArrayList<Noun_WPC> totalArr;
	private ArrayList<Noun_WPC> selectArr;

	private boolean NNG;
	private boolean NNP;
	private int lessNum;

	private List list_result = new List();

	private JPanel panel_Center = new JPanel(new FlowLayout(0, 10, 10));
	private Choice choice_POS = new Choice();
	private TextField TF_freq = new TextField(9);
	private Button button_choose = new Button("선택");
	private String blank = "                                                              /";
	
	private JPanel panel_South = new JPanel(new GridLayout(0, 2, 0, 1));
	private Button button_addObjs = new Button("추출된 명사 추가");
	private Button button_addFull = new Button("하나의 단어로 추가");
	
	public Gui_Komoran(Frame owner, Noun_Engine nEng, String[] set) {
		super(owner);

		choice_POS.add("  전체선택  ");
		choice_POS.add("  일반명사  ");
		choice_POS.add("  고유명사  ");

		panel_Center.add(new Label("품사", Label.RIGHT));
		panel_Center.add(choice_POS);
		panel_Center.add(new Label(blank));
		panel_Center.add(new Label("빈도", Label.RIGHT));
		panel_Center.add(TF_freq);
		panel_Center.add(button_choose);
		
		button_addObjs.setBackground(Color.white);
		button_addFull.setBackground(Color.white);
		panel_South.add(button_addObjs);
		panel_South.add(button_addFull);
		
		
		
		settings(set);
		nEngine = nEng;
		totalArr = nEngine.getAnalyze(true, true, 1);
		selectArr = nEngine.getAnalyze(NNG, NNP, lessNum);

		list_result.setMultipleMode(true);
		for (int index = 0; index < totalArr.size(); index++) {
			list_result.add(totalArr.get(index).getWord());
			if (contains(totalArr.get(index), selectArr)) {
				list_result.select(list_result.getItemCount() - 1);
			}
		}
		

		setLayout(new BorderLayout(10, 1));

		add(list_result, "West");
		add(panel_Center, "Center");
		add(panel_South, "South");

		setTitle("명사 분석");
		setSizeKomoran(540, 375);
		setResizable(false);
		setModal(true);
		addWindowListener(new windowTopWeakAction());
		setVisible(true);

	}

	private boolean contains(Noun_WPC noun, ArrayList<Noun_WPC> arr) {
		for (int index = 0; index < arr.size(); index++) {
			if (noun.getWord().equals(arr.get(index).getWord())) {
				return true;
			}
		}
		return false;
	}

	public void setSizeKomoran(int width, int height) {
		setSize(width, height);
		setLocation(screenSize.width / 2 - width / 2, screenSize.height / 2
				- height / 2);
	}

	public void settings(String[] set) {
		NNG = Boolean.valueOf(set[0]);
		NNP = Boolean.valueOf(set[1]);
		lessNum = Integer.parseInt(set[2]);
		
		setChoice(choice_POS);
		TF_freq.setText(lessNum+"");
	}

	private void setChoice(Choice cho) {
		if(NNG && NNP){
			cho.select(0);
		} else if (NNG){
			cho.select(1);
		} else{
			cho.select(2);
		}
	}

}
