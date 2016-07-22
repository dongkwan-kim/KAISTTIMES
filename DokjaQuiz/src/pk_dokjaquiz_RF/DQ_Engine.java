package pk_dokjaquiz_RF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DQ_Engine {

	private ArrayList<String> pool1;
	private ArrayList<String> pool2;


	DQ_Engine(){
	}

	DQ_Engine(ArrayList pool1, ArrayList pool2) {
		this.pool1 = pool1;
		this.pool2 = pool2;
	}

	public void setPool(ArrayList pool1, ArrayList pool2){
		this.pool1 = pool1;
		this.pool2 = pool2;
	}
	
	private ArrayList getHeadArr(ArrayList pool) {
		ArrayList returnPool = new ArrayList();
		for (int index = 0; index < pool.size(); index++) {
			String fullStr = (String) pool.get(index);
			returnPool.add(getHeadStr(fullStr));
		}
		return returnPool;
	}

	private String getHeadStr(String fullStr) {
		return fullStr.split("")[1];
	}

	public ArrayList<dokjaQuiz> getDokjaQuiz() throws IOException {
		BufferedReader dictReader = new BufferedReader(new InputStreamReader(
				new FileInputStream("document/dictionaryFile.csv"), "euc-kr"));
		ArrayList<dokjaQuiz> returnArr = new ArrayList<dokjaQuiz>();
		ArrayList<String> p1 = getHeadArr(pool1);
		ArrayList<String> p2 = getHeadArr(pool2);

		String line;
		while ((line = dictReader.readLine()) != null) {
			String word = line.split(",")[0];
			int freq = Integer.parseInt(line.split(",")[1]);
			
			ArrayList wordArr = wordDecomposition(word);
			
			if (DQAvailable(wordArr, p1, p2)) {
				dokjaQuiz DQ = makeDokjaQuiz(wordArr, p1, p2);
				DQ.setFrequency(freq);
				returnArr.add(DQ);
			}
		}
		return returnArr;
	}

	private dokjaQuiz makeDokjaQuiz(ArrayList<String> wordArr, ArrayList list1,
			ArrayList list2) {

		dokjaQuiz DQ = new dokjaQuiz();
		
		boolean[] b1 = isContainEmt(wordArr, list1);
		boolean[] b2 = isContainEmt(wordArr, list2);

		ArrayList copy1 = copyArrayList(list1);
		ArrayList copy2 = copyArrayList(list2);
		
		int num1 = getNumOftrue(b1);
		int num2 = getNumOftrue(b2);
		
		for (int index = 0; index < 4; index++) {
			if (b1[index] && b2[index]) {
				if(num1 < num2){
					int PIndex = getPIndexOfEmt(copy1, wordArr.get(index));
					DQ.setEmtOfIndex(index, pool1.get(PIndex));
				} else{
					int PIndex = getPIndexOfEmt(copy2, wordArr.get(index));
					DQ.setEmtOfIndex(index, pool2.get(PIndex));
				}
				num1--;
				num2--;
			} else if (b1[index]) {
				int PIndex = getPIndexOfEmt(copy1, wordArr.get(index));
				DQ.setEmtOfIndex(index, pool1.get(PIndex));
				num1--;
			} else if (b2[index]) {
				int PIndex = getPIndexOfEmt(copy2, wordArr.get(index));
				DQ.setEmtOfIndex(index, pool2.get(PIndex));
				num2--;
			} else {
				DQ.setJ(index);
				DQ.setEmtOfIndex(index, wordArr.get(index));
			}
		}
		return DQ;
	}

	private ArrayList copyArrayList(ArrayList list) {
		ArrayList returnArr = new ArrayList();
		for(int index = 0; index<list.size(); index++){
			returnArr.add(list.get(index));
		}
		return returnArr;
	}

	private int getNumOftrue(boolean[] blist) {
		int numOftrue = 0;
		for(boolean bObj : blist){
			if(bObj){
				numOftrue++;
			}
		}
		return numOftrue;
	}

	private int getPIndexOfEmt(ArrayList<String> copy, String wordPiece) {
		for(int index = 0; index < copy.size(); index++){
			if(wordPiece.equals(copy.get(index))){
				copy.set(index, "pass");
				return index;
			}
		}
		return -1;
	}

	private ArrayList wordDecomposition(String word) {
		String[] wordArr = word.split("");
		ArrayList returnArr = new ArrayList();
		for (int i = 1; i < wordArr.length; i++) {
			returnArr.add(wordArr[i]);
		}
		return returnArr;
	}

	private boolean DQAvailable(ArrayList wordArr, ArrayList list1,
			ArrayList list2) {

		boolean[] b1 = isContainEmt(wordArr, list1);
		boolean[] b2 = isContainEmt(wordArr, list2);
		
		if (!(b1[0] || b1[1] || b1[2] || b1[3])) {
			return false;
		}
		if (!(b2[0] || b2[1] || b2[2] || b2[3])) {
			return false;
		}
		
		int numOfT = getNumOrtrue(b1, b2);
		if (numOfT >= 3) {
			return true;
		}
		return false;
	}

	private int getNumOrtrue(boolean[] b1, boolean[] b2) {
		int num = 0;
		for (int index = 0; index < 4; index++) {
			if (b1[index] || b2[index]) {
				num++;
			}
		}
		return num;
	}

	private boolean[] isContainEmt(ArrayList wordArr, ArrayList list) {
		boolean[] blist = { false, false, false, false };
		ArrayList copy = copyArrayList(list);
		for (int index = 0; index < wordArr.size(); index++) {
			for(int cndex = 0; cndex<copy.size(); cndex++){
				if(copy.get(cndex).equals(wordArr.get(index))){
					blist[index] = true;
					copy.set(cndex, "pass");
				}
			}
		}
		return blist;
	}

}
