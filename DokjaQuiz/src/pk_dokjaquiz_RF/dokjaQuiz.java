package pk_dokjaquiz_RF;

public class dokjaQuiz {
	
	private String fullDQ = "";
	private String[] Emt = new String[4];
	private int indexOfJ = -1;
	private int frequency;
	
	dokjaQuiz(){
	}
	
	dokjaQuiz(String Emt_1, String Emt_2, String Emt_3, String Emt_4, int indexOfJ){
		this.Emt[0] = Emt_1;
		this.Emt[1] = Emt_2;
		this.Emt[2] = Emt_3;
		this.Emt[3]= Emt_4;
		this.indexOfJ = indexOfJ;
	}
	
	public String getFullDQ(){
		fullDQ = "";
		for(String str : Emt){
			fullDQ = fullDQ + str.split("")[1];
		}
		return fullDQ;
	}
	
	public String getEmtOfIndex(int index){
		return Emt[index];
	}
	
	public void setEmtOfIndex(int index, String emt){
		Emt[index] = emt;
	}
	
	public int getJ(){
		return indexOfJ;
	}
	
	public void setJ(int J){
		indexOfJ = J;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public void setFrequency(int freq){
		frequency = freq;
	}

}
