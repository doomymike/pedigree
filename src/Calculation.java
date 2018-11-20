//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHH forgot to compare with affected boolean AAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH

public class Calculation {
	
	
	void simpleCalc(Person p, boolean dominant){
		
		if(p.isAffected() && !dominant){
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(1,1);
			p.getHomozygousUnaffected().set(0, 1);
			return;
		}
		
		if(!p.isAffected() && dominant){
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(0,1);
			p.getHomozygousUnaffected().set(1, 1);
			return;
		}
		
		if(p.getMother().getHeterozygous() == null){ //heterozygous could be replaced with any
			autosomalDominant(p.getMother());
		}
		if(p.getFather().getHeterozygous() == null){
			autosomalDominant(p.getFather());
		}
		
		//done homoAffected
		Fraction homoAffected = new Fraction(0,1);
		homoAffected += p.getFather().getHomozygousAffected()*p.getMother().getHomozygousAffected();
		homoAffected += p.getFather().getHomozygousAffected()*p.getMother().getHeterozygous()/2;
		homoAffected += p.getFather().getHeterozygous()*p.getMother().getHomozygousAffected()/2;
		homoAffected += p.getFather().getHeterozygous()*p.getMother().getHeterozygous()/4;
		
		
		
		double homoUnaffected = 0;
		homoUnaffected += p.getFather().getHomozygousUnaffected()*p.getMother().getHomozygousUnaffected();
		homoUnaffected += p.getFather().getHomozygousUnaffected()*p.getMother().getHeterozygous()/2;
		homoUnaffected += p.getFather().getHeterozygous()*p.getMother().getHomozygousUnaffected()/2;
		homoUnaffected += p.getFather().getHeterozygous()*p.getMother().getHeterozygous()/4;
		
		
		
		double hetero = 1- homoAffected - homoUnaffected;
		
		//NNNNNNNNNNNNNNNNNNNNNOOOOOOOOOOOOOOOOOOOOOTTTTTTTTTTTTTTTTT DDDDDOOOOOOOOOOOOOOOOOONNNNNNNNNNNNNNEEEEEEEEEEE
		double temp;
		if(p.isAffected()){ //affected dominant
			homoUnaffected = 0;
			temp = homoAffected+hetero;
			
		}else{ //unaffected recessive
			homoAffected = 0;
			temp = homoUnaffected+hetero;       //didnt do the conversion
			
		}
			
		
		
		p.setHomozygousAffected(homoAffected);
		p.setHomozygousUnaffected(homoUnaffected);
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
		
		//checks if male children are affected
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
		
		simpleCalc(p.getMother(),true); //if male only mother matters
		
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
		
		simpleCalc(p.getMother(),false); //if male only mother matters
		
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
		
		simpleCalc(p,false);
		return (p.getHomozygousAffected());
		
	}
	
	
	double autosomalDominant(Person p){ //done
		
		simpleCalc(p,true);
		
		
		return (p.getHomozygousAffected()+p.getHeterozygous());
	}
	
	
	
}
