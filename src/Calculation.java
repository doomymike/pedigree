
public class Calculation {
	String[] simpleCalc(Person x){
		String[] options = new String[4];
		int temp =0;
		for(int i =0;i<2;i++){
			for(int j = 0;j<2;j++){
				options[temp] = ""+x.getFather().getCode()[i]+x.getMother().getCode()[j];
				temp++;
			}
		}
		
		
		return options;
	}
}
