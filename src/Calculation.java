
import java.util.Arrays;

public class Calculation {
	String[] simpleCalc(Person x){
		char[] sort =new char[2];
		String[] options = new String[4];
		int temp =0;
		for(int i =0;i<2;i++){
			for(int j = 0;j<2;j++){
				sort[0] = x.getFather().getCode()[i];
				sort[1] = x.getMother().getCode()[j];
				Arrays.sort(sort);
				options[temp] = ""+sort[0]+sort[1];
				temp++;
			}
		}
		
		
		return options;
	}
}

