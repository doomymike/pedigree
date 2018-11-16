import java.util.Arrays;

//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH going multiple levels down AAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHH
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
	
	//literally useless but whatever bc it's fricking dominant and only one gene reeee
	boolean yLinked(Person p){
		
		if(p.isSex()){ //women can't have it
			return false;
		}
		
		if(p.getFather().isAffected()){ //if we don't know the parent, its a bad (only if no male children), but we should know know the parent bc its dominant
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
			return numAffected/4;  
		} 
		if(p.getMother().getCode()[0] == 'a'){//affected gene
			if(p.getMother().getCode()[1] == 'a'){
				char[] code ={'a','a'}; //only x matters so both are set to the x
				p.setCode(code);

				return 1;
				
			}
			return 0.5;
		}
		if(p.getMother().getCode()[1]=='a'){ //Aa
			return 0.5;
		}
		
		return 0;
	}
	
	double xLinkRecessive(Person p){ //done if know parents
		
//		if (p.getMother().getCode() == null){
//			
//		}
		if(p.isSex()){ //female
			String [] poss = simpleCalc(p);
			int numAffected = 0;
			for (int i=0;i<4;i++){
				if (poss[i].contains("a")){
					numAffected++;
				}
			}							//umm maybe we need to store all possibilities bc otherwise going two levels down is impossible
			return numAffected/4;  
		}
		
		//i copied and pasted and didn't adjust yet
		if(p.getMother().getCode()[0] == 'a'){//affected gene
			if(p.getMother().getCode()[1] == 'a'){
				char[] code ={'a','a'}; //only x matters so both are set to the x
				p.setCode(code);

				return 1;
				
			}
			return 0.5;
		}
		if(p.getMother().getCode()[1]=='a'){ //Aa
			return 0.5;
		}
		
		return 0;
		//FIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		
	}
	
	double autosomalRecessive(Person p){ //done if know parents
		
		
		String [] poss = simpleCalc(p);
		int numAffected = 0;
		for (int i=0;i<4;i++){
			if (poss[i].contains("aa")){
				numAffected++;
			}
		}
		if (numAffected == 4){ // known fact
			char[] code = {'a','a'};
			p.setCode(code);
		}
		return numAffected/4;
		
		
		
	}
	
	double autosomalDominant(Person p){ //done if know parents
		
		String [] poss = simpleCalc(p);
		int numAffected = 0;
		for (int i=0;i<4;i++){
			if (poss[i].contains("a")){
				numAffected++;
			}
		}
		
		return numAffected/4;
		
	}
	
	
	
}
