package pk_dokjaquiz_RF;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;

public class Gui_JFrame extends JFrame {
	
	static String title = "독자퀴즈 생성기  v ";
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	
	JMenuBar menuBar = new JMenuBar();
	JMenu menu_Func = new JMenu("Func");
	JMenuItem item_Info = new JMenuItem("프로그램 정보");
	JMenuItem item_Help = new JMenuItem("도움말 열기");
	Gui_Dialog programInfo = new Gui_Dialog(this, "프로그램 정보");
	String info = "(C) 2014.KAISTTIMES. All Rights Reserved.\n";
	Gui_Dialog help = new Gui_Dialog(this, "도움말");

	JLabel label_top = new JLabel(
			" 사전에는 동사, 형용사, 옛말, 북한어를 제외한 4음절의 단어 72,743개가 있습니다");

	JComboBox choice = new JComboBox();

	JTextField TF_input = new JTextField(26);

	JButton button_p = new JButton("입력");
	JButton button_create = new JButton("   생성하기   ");
	JButton button_clear = new JButton("   다시하기   ");
	JButton button_sort = new JButton("   정렬하기   ");
	JButton button_comp = new JButton("   확인하기   ");

	JList list_p1 = new JList();
	JList list_p2 = new JList();
	JList list_result = new JList();
	JList list_show = new JList();
	JList list_showTitle = new JList();

	ArrayList arr_p1 = new ArrayList();
	ArrayList arr_p2 = new ArrayList();
	ArrayList<dokjaQuiz> arr_result = new ArrayList();
	/* Constructor */
	Gui_JFrame(String version) throws IOException {
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
		setJMenuBar(menuBar);

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
		wholePanel Panel_w = new wholePanel();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		Panel_w.setLayout(gbl);
		Panel_w.setBackground(Color.white);
		
		JPanel Panel_1 = new JPanel();
		choice.addItem("문화/유람");
		choice.addItem("학술/연구");
		Panel_1.add(label_top);
		Panel_1.add(choice);
		Panel_1.add(TF_input);
		Panel_1.add(button_p);
		button_p.setBackground(Color.white);
		Panel_1.setBackground(Color.white);

		JPanel Panel_2 = new JPanel(new GridLayout(0, 2, 5, 5));
		JScrollPane ScrollPane2_1 = new JScrollPane(list_p1);
		JScrollPane ScrollPane2_2 = new JScrollPane(list_p2);
		Panel_2.add(ScrollPane2_1);
		Panel_2.add(ScrollPane2_2);
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
		JScrollPane ScrollPane4_1 = new JScrollPane(list_result);
		list_result.setFont(new Font("SansSerif", Font.PLAIN, 15));
		Panel_4.add(ScrollPane4_1, "Center");

		JPanel Panel_5 = new JPanel(new BorderLayout(10, 10));
		list_showTitle.setFont(new Font("SansSerif", Font.BOLD, 30));
		list_show.setFont(new Font("SansSerif", Font.PLAIN, 19));
		list_show.setSize(100, 100);
		
		JScrollPane ScrollPane5_show = new JScrollPane(list_show);
		JScrollPane ScrollPane5_showTitle = new JScrollPane(list_showTitle);
		
		Panel_5.add(ScrollPane5_show, "Center");
		Panel_5.add(ScrollPane5_showTitle, "North");
		Panel_5.setBackground(Color.white);
		Panel_5.setMinimumSize(Panel_5.getSize());
		Panel_5.setMaximumSize(Panel_5.getSize());
		
		imagePanel Panel_6 = new imagePanel("images/logo.png");
		Panel_6.setBackground(Color.white);
		
		add(Panel_w);
		Panel_w.addGrid(gbl, gbc, Panel_1, 0, 0, 3, 1, 1, 4, new Insets(0, 0, 0, 0));
		Panel_w.addGrid(gbl, gbc, Panel_2, 0, 1, 3, 1, 1, 25, new Insets(0, 10, 0, 10));
		Panel_w.addGrid(gbl, gbc, Panel_4, 0, 3, 1, 2, 10, 14, new Insets(15, 10, 15, 10));
		Panel_w.addGrid(gbl, gbc, Panel_5, 1, 3, 1, 1, 20, 10, new Insets(15, 1, 15, 5));
		Panel_w.addGrid(gbl, gbc, Panel_3, 2, 3, 1, 1, 1, 10, new Insets(15, 5, 15, 10));
		Panel_w.addGrid(gbl, gbc, Panel_6, 1, 4, 2, 1, 1, 16, new Insets(0, 1, 15, 10));
		
		/* Action */
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
		list_p1.addMouseListener(delAct_1);
		list_p2.addMouseListener(delAct_2);
		String delToolTip = "더블 클릭하면 삭제됩니다.";
		list_p1.setToolTipText(delToolTip);
		list_p2.setToolTipText(delToolTip);

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
		list_result.addMouseListener(compAct);
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

		/* Frame */
		setSize(490, 750);
		setLocation(screenSize.width / 2 - 250, screenSize.height / 2 - 400);
		addWindowListener(new windowTopAction());
		//setResizable(false);
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
