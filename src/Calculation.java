
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH going multiple levels down AAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHH
public class Calculation {
	void simpleCalc(Person p){
		if(p.getMother().getHeterozygous() == -1){ //heterozygous could be replaced with any
			autosomalDominant(p.getMother());
		}
		if(p.getFather().getHeterozygous() == -1){
			autosomalDominant(p.getFather());
		}
		
		//done homoAffected
		double homoAffected = 0;
		homoAffected += p.getFather().getHomozygousAffected()*p.getMother().getHomozygousAffected();
		homoAffected += p.getFather().getHomozygousAffected()*p.getMother().getHeterozygous()/2;
		homoAffected += p.getFather().getHeterozygous()*p.getMother().getHomozygousAffected()/2;
		homoAffected += p.getFather().getHeterozygous()*p.getMother().getHeterozygous()/4;
		
		p.setHomozygousAffected(homoAffected);
		
		double homoUnaffected = 0;
		homoUnaffected += p.getFather().getHomozygousUnaffected()*p.getMother().getHomozygousUnaffected();
		homoUnaffected += p.getFather().getHomozygousUnaffected()*p.getMother().getHeterozygous()/2;
		homoUnaffected += p.getFather().getHeterozygous()*p.getMother().getHomozygousUnaffected()/2;
		homoUnaffected += p.getFather().getHeterozygous()*p.getMother().getHeterozygous()/4;
		
		p.setHomozygousUnaffected(homoUnaffected);
		
		double hetero = 1- homoAffected - homoUnaffected;
		
		p.setHeterozygous(hetero);
	}
	
	//literally useless but whatever bc it's fricking dominant and only one gene reeee
	boolean yLinked(Person p){
		
		if(p.getSex()){ //women can't have it
			return false;
		}
		
		if(p.getFather().isAffected()){ //if we don't know the parent, its a bad (only if no male children), but we should know know the parent bc its dominant
			return true;
		}
		
		for(int i =0;i< p.children.size();i++){
			if(!p.children.get(i).getSex() ){
				if(p.children.get(i).isAffected()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	//done
	double xLinkDominant(Person p){ // looooooooooooooooooooooooooooooooooooooooooooooooooook idk exactly if this works 
		
		if(p.getSex()){ //female
			  return (autosomalDominant(p)); //check this boi
		} 
		
		simpleCalc(p.getMother()); //if male only mother matters
		
		double homoAffected =0;
		homoAffected += p.getMother().getHomozygousAffected();
		homoAffected += p.getMother().getHeterozygous()/2;
		
		p.setHomozygousAffected(homoAffected);
		
		double homoUnaffected =0;
		homoUnaffected += p.getMother().getHomozygousUnaffected();
		homoUnaffected += p.getMother().getHeterozygous()/2;
		
		p.setHomozygousUnaffected(homoUnaffected);
		
		double hetero;
		hetero = 1-homoAffected - homoUnaffected;
		p.setHeterozygous(hetero);
		
		return (p.getHeterozygous()+p.getHomozygousAffected());
	}
	
	//done
	double xLinkRecessive(Person p){ //looooooooooooooooooooooooooooooooooooooooooooooooooook idk exactly if this works 
		
		if(p.getSex()){ //female
			  return (autosomalRecessive(p)); //check this boi
		} 
		
		simpleCalc(p.getMother()); //if male only mother matters
		
		double homoAffected =0;
		homoAffected += p.getMother().getHomozygousAffected();
		homoAffected += p.getMother().getHeterozygous()/2;
		
		p.setHomozygousAffected(homoAffected);
		
		double homoUnaffected =0;
		homoUnaffected += p.getMother().getHomozygousUnaffected();
		homoUnaffected += p.getMother().getHeterozygous()/2;
		
		p.setHomozygousUnaffected(homoUnaffected);
		
		double hetero;
		hetero = 1-homoAffected - homoUnaffected;
		p.setHeterozygous(hetero);
		
		return (p.getHomozygousAffected());
		
	}
	
	double autosomalRecessive(Person p){ //done
		
		simpleCalc(p);
		return (p.getHomozygousAffected());
		
	}
	
	
	double autosomalDominant(Person p){ //done
		
		simpleCalc(p);
		
		
		return (p.getHomozygousAffected()+p.getHeterozygous());
	}
	
	
	
}
