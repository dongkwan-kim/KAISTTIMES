package pk_dokjaquiz_RF;

public class Noun_WPC {
	
	private String word;
	private String POS;
	private int count = 1;
	
	Noun_WPC(String word, String POS){
		this.word = word;
		this.POS = POS;
	}
	
	public void addCount(){
		count++;
	}
	
	public String getWord(){
		return word;
	}
	
	public String getPOS(){
		return POS;
	}
	
	public int getCount(){
		return count;
	}
	
}
