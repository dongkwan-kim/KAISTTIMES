package pk_dokjaquiz_RF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;

public class DokjaQuiz_RF_Main {

	public static void main(String[] args) throws IOException {

		/*
		ArrayList pool1 = new ArrayList();
		ArrayList pool2 = new ArrayList();
		
		pool1.add("가나다1");
		pool1.add("격나다1");
		pool1.add("정나다1");
		pool1.add("고나다1");
		pool1.add("함나다1");
		pool1.add("방나다1");
		
		pool2.add("책나다2");
		pool2.add("이나다2");
		pool2.add("리나다2");
		pool2.add("미나다2");
		pool2.add("만나다2");
		pool2.add("군나다2");

		DQ_Engine engine = new DQ_Engine();
		engine.setPool(pool1, pool2);
		
		ArrayList<dokjaQuiz> dqarr = engine.getDokjaQuiz();

		for(int index = 0; index < dqarr.size(); index++){
			System.out.print(dqarr.get(index).getFullDQ());
			System.out.print("("+dqarr.get(index).getFrequency()+"), ");
		}
		System.out.println();
		*/
		
		loading loadingWindow = new loading();
		Gui_JFrame mainFrame = new Gui_JFrame("2.0");
		
		
		/*
		BufferedReader Reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("document/test.txt"), "euc-kr"));
		
		
		Komoran ko = new Komoran("komoran/models-full");
		
		String line;
		String str = "";
		while((line = Reader.readLine())!=null){
			str = str +line+"\n";
		}
		
		Noun_Engine eng = new Noun_Engine(ko, str);
		ArrayList<Noun_WPC> arr = eng.getAnalyze(true, true, 4);
		
		for(Noun_WPC noun : arr){
			System.out.print(noun.getWord()+" / ");
			System.out.print(noun.getPOS()+" / ");
			System.out.print(noun.getCount()+" / ");
			System.out.println();
		}
		*/
		
	}

}
