package pk_dokjaquiz_RF;

import java.util.ArrayList;
import java.util.List;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class Noun_Engine {

	private String str;
	private Komoran komoran;

	public Noun_Engine(Komoran komoran, String toAnalyze) {
		this.komoran = komoran;
		this.str = toAnalyze;
	}

	public ArrayList<Noun_WPC> getAnalyze(boolean NNG, boolean NNP, int lessNum) {

		ArrayList<Noun_WPC> returnArr = new ArrayList();

		List<List<Pair<String, String>>> result = komoran.analyze(str);

		for (List<Pair<String, String>> eojeolResult : result) {
			for (Pair<String, String> wordMorph : eojeolResult) {
				String word = wordMorph.getFirst();
				String POS = wordMorph.getSecond();

				if (POS.equals("NNG") || POS.equals("NNP")) {
					if (indexOfNoun(returnArr, word) != -1) {
						returnArr.get(indexOfNoun(returnArr, word)).addCount();
					} else {
						returnArr.add(new Noun_WPC(word, POS));
					}
				}
			}
		}

		if (!NNG) {
			returnArr = removeNNG(returnArr);
		} else if(!NNP){
			returnArr = removeNNP(returnArr);
		}
		
		for(int index = 0; index < returnArr.size(); index++){
			returnArr= removeLess(returnArr, lessNum);
		}
		
		sort(returnArr);
		
		return returnArr;

	}

	private ArrayList removeNNG(ArrayList<Noun_WPC> arr) {
		ArrayList returnArr = new ArrayList();
		for (int index = 0; index < arr.size(); index++) {
			if (!arr.get(index).getPOS().equals("NNG")) {
				returnArr.add(arr.get(index));
			}
		}
		return returnArr;
	}

	private ArrayList removeNNP(ArrayList<Noun_WPC> arr) {
		ArrayList returnArr = new ArrayList();
		for (int index = 0; index < arr.size(); index++) {
			if (!arr.get(index).getPOS().equals("NNP")) {
				returnArr.add(arr.get(index));
			}
		}
		return returnArr;
	}
	
	private ArrayList removeLess(ArrayList<Noun_WPC> arr, int threshold){
		ArrayList returnArr = new ArrayList();
		for (int index = 0; index < arr.size(); index++) {
			if (arr.get(index).getCount() >= threshold) {
				returnArr.add(arr.get(index));
			}
		}
		return returnArr;
	}

	private int indexOfNoun(ArrayList<Noun_WPC> arr, String noun) {
		for (int index = 0; index < arr.size(); index++) {
			if (arr.get(index).getWord().equals(noun)) {
				return index;
			}
		}
		return -1;
	}
	
	private void sort(ArrayList<Noun_WPC> arr) {
		for (int index = 1; index < arr.size(); index++) {
			Noun_WPC temp = arr.get(index);
			int mainCnt = arr.get(index).getCount();
			int auxIndex = index - 1;
			int auxCnt = arr.get(auxIndex).getCount();

			while (mainCnt > auxCnt) {
				arr.set(auxIndex + 1, arr.get(auxIndex));
				auxIndex--;
				if(auxIndex < 0){break;}
				auxCnt = arr.get(auxIndex).getCount();
			}
			arr.set(auxIndex + 1, temp);
		}
	}
	
}

/*
 * for (List<Pair<String, String>> eojeolResult : result) { for (Pair<String,
 * String> wordMorph : eojeolResult) { System.out.println(wordMorph); } }
 */