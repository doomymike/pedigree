import java.util.Arrays;

public class Calculation {
	String[] simpleCalc(Person p){
		char[] sort =new char[2];
		String[] options = new String[4];
		int temp =0;
		for(int i =0;i<2;i++){
			for(int j = 0;j<2;j++){
				sort[0] = p.getFather().getCode()[i];
				sort[1] = p.getMother().getCode()[j];
				Arrays.sort(sort);
				options[temp] = ""+sort[0]+sort[1];
				temp++;
			}
		}
		
		
		return options;
	}
	
	//literally useless but whatever
	boolean yLinked(Person p){
		
		if(p.getFather().isAffected()){ //if we don't know the parent, its a bad, but we should know know the parent bc its dominant
			return true;
		}
		
		for(int i =0;i< p.children.size();i++){
			if(!p.children.get(i).isSex() ){
				if(p.children.get(i).isAffected()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	double xLinkDominant(Person p){ //donezo but only if we know parents
		
		if(p.isSex()){ //female
			String [] poss = simpleCalc(p);
			int numAffected = 0;
			for (int i=0;i<4;i++){
				if (poss[i].contains("a")){
					numAffected++;
				}
			}							//umm maybe we need to store all possibilities bc otherwise going two levels down is impossible
			return numAffected/4; //maybe check if guaranteed 
		}
		if(p.getMother().getCode()[0] == 'a'){//affected gene
			if(p.getMother().getCode()[1] == 'a'){
				char[] code ={'a','a'}; //only x matters so both are set to the x
				p.setCode(code);

				return 1;
				
			}
			return 0.5;
		}
		if(p.getMother().getCode()[1]=='a'){
			return 0.5;
		}
		
		return 0;
	}
	
	
}
