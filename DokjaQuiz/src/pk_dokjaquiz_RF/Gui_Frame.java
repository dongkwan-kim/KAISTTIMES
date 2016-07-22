package pk_dokjaquiz_RF;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.MediaTracker;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JPanel;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;

public class Gui_Frame extends Frame {

	static String title = "독자퀴즈 생성기  v ";
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();

	MenuBar menuBar = new MenuBar();
	Menu menu_Func = new Menu("Func");
	MenuItem item_Info = new MenuItem("프로그램 정보");
	MenuItem item_Help = new MenuItem("도움말 열기");
	Gui_Dialog programInfo = new Gui_Dialog(this, "프로그램 정보");
	String info = "(C) 2014.KAISTTIMES. All Rights Reserved.\n";
	Gui_Dialog help = new Gui_Dialog(this, "도움말");

	Label label_top = new Label(
			"  현재 사전에는 동사, 형용사, 옛말, 북한어를 제외한 4음절의 단어 72,743개가 있습니다");

	Choice choice = new Choice();

	TextField TF_input = new TextField(40);

	Button button_p = new Button("   입력   ");
	Button button_create = new Button("   생성하기   ");
	Button button_clear = new Button("   다시하기   ");
	Button button_sort = new Button("   정렬하기   ");
	Button button_comp = new Button("   확인하기   ");

	List list_p1 = new List();
	List list_p2 = new List();
	List list_result = new List();
	List list_show = new List();
	List list_showTitle = new List(1);

	ArrayList arr_p1 = new ArrayList();
	ArrayList arr_p2 = new ArrayList();
	ArrayList<dokjaQuiz> arr_result = new ArrayList();

	Komoran komoran;

	/* Constructor */

	Gui_Frame(String version) throws IOException {
		super(title + version);

		/* Design */
		// Menu
		
		item_Info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				programInfo.setVisible(true);
			}
		});
		item_Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.setVisible(true);
			}
		});
		menu_Func.add(item_Help);
		menu_Func.addSeparator();
		menu_Func.add(item_Info);
		menuBar.add(menu_Func);
		setMenuBar(menuBar);

		programInfo.setSizeDialog(320, 210);
		info += "\n* 카이스트신문\n";
		info += "\n* 독자퀴즈 생성기 v " + version + "\n";
		info += "\n* 개발자: 취재부 김동관\n";
		info += "\n* 8Byte 크기의 도움을 주신 분: 학술부 전철호\n";
		programInfo.setContentByStr(info);
		programInfo.setContentByFile("document/info.txt");
		help.setSizeDialog(648, 400);
		help.setContentByFile("document/help.txt");

		// Layout
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);

		JPanel Panel_1 = new JPanel();
		choice.add("문화/유람");
		choice.add("학술/연구");
		Panel_1.add(label_top);
		Panel_1.add(choice);
		Panel_1.add(TF_input);
		Panel_1.add(button_p);
		Panel_1.setBackground(Color.white);

		JPanel Panel_2 = new JPanel(new GridLayout(0, 2, 5, 5));
		Panel_2.add(list_p1);
		Panel_2.add(list_p2);
		Panel_2.setBackground(Color.white);

		JPanel Panel_3 = new JPanel(new GridLayout(5, 0, 20, 15));
		button_create.setBackground(Color.white);
		button_comp.setBackground(Color.white);
		button_sort.setBackground(Color.orange);
		button_clear.setBackground(Color.red);
		button_clear.setForeground(Color.white);
		Panel_3.add(button_create);
		Panel_3.add(button_comp);
		Panel_3.add(button_sort);
		Panel_3.add(button_clear);
		Panel_3.setBackground(Color.white);

		JPanel Panel_4 = new JPanel(new BorderLayout());
		list_result.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Panel_4.add(list_result, "Center");

		JPanel Panel_5 = new JPanel(new BorderLayout(0, 10));
		list_showTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		list_show.setFont(new Font("SansSerif", Font.PLAIN, 30));
		Panel_5.add(list_show, "Center");
		Panel_5.add(list_showTitle, "North");
		Panel_5.setBackground(Color.white);

		imagePanel Panel_6 = new imagePanel("images/logo.png");
		Panel_6.setBackground(Color.white);

		addGrid(gbl, gbc, Panel_1, 0, 0, 3, 1, 1, 6, new Insets(0, 0, 0, 0));
		addGrid(gbl, gbc, Panel_2, 0, 1, 3, 1, 1, 25, new Insets(0, 10, 0, 10));
		addGrid(gbl, gbc, Panel_4, 0, 3, 1, 2, 5, 14,
				new Insets(15, 10, 15, 10));
		addGrid(gbl, gbc, Panel_5, 1, 3, 1, 1, 10, 10, new Insets(15, 10, 15,
				10));
		addGrid(gbl, gbc, Panel_3, 2, 3, 1, 1, 3, 10, new Insets(15, 5, 15, 10));
		addGrid(gbl, gbc, Panel_6, 1, 4, 2, 1, 10, 16,
				new Insets(0, 10, 15, 10));

		/* Action */
		/*
		inputAction inputAct = new inputAction();
		inputAct.setTF(TF_input);
		inputAct.setChoice(choice);
		inputAct.setArrayList(arr_p1, arr_p2);
		inputAct.setList(list_p1, list_p2);
		button_p.addActionListener(inputAct);
		TF_input.addActionListener(inputAct);

		deleteAction delAct_1 = new deleteAction();
		deleteAction delAct_2 = new deleteAction();
		delAct_1.setVar(list_p1, arr_p1);
		delAct_2.setVar(list_p2, arr_p2);
		list_p1.addActionListener(delAct_1);
		list_p2.addActionListener(delAct_2);

		clearAction clearWeakAct = new clearAction();
		clearWeakAct.addList(list_show);
		clearWeakAct.addList(list_showTitle);

		createAction creAct = new createAction();
		creAct.setArrayList(arr_p1, arr_p2);
		creAct.setList(list_result);
		button_create.addActionListener(creAct);
		button_create.addActionListener(clearWeakAct);

		compAction compAct = new compAction();
		compAct.setArrayList(creAct);
		compAct.setList(list_result, list_show, list_showTitle);
		list_result.addActionListener(compAct);
		button_comp.addActionListener(compAct);

		sortAction sortAct = new sortAction();
		sortAct.setVar(list_result, creAct);
		button_sort.addActionListener(sortAct);
		button_sort.addActionListener(clearWeakAct);

		clearAction clearAct = new clearAction();
		clearAct.addArr(arr_p1);
		clearAct.addArr(arr_p2);
		clearAct.addArr(arr_result);
		clearAct.addList(list_result);
		clearAct.addList(list_show);
		clearAct.addList(list_showTitle);
		clearAct.addList(list_p1);
		clearAct.addList(list_p2);
		button_clear.addActionListener(clearAct);

		
		/* komoran settings */
		/*
		BufferedReader setReader = new BufferedReader(new InputStreamReader(
				new FileInputStream("document/settings.txt"), "euc-kr"));

		if (Boolean.valueOf(setReader.readLine())) {
			komoran = new Komoran("komoran/models-full");
			inputAct.setKomoran(komoran);
			inputAct.setParentFrame(this);
			inputAct.setIsKomo(true);
			inputAct.settings(setReader.readLine().split("/"));
		} else {
			inputAct.setIsKomo(false);
		}

		
		/* Frame */
		setSize(490, 750);
		setLocation(screenSize.width / 2 - 250, screenSize.height / 2 - 400);
		addWindowListener(new windowTopAction());
		setResizable(false);
		setVisible(true);
	}

	private void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
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
