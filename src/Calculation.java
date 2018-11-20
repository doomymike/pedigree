

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
		homoAffected = homoAffected.add(p.getFather().getHomozygousAffected().multiply(p.getMother().getHomozygousAffected()));
		
		
		homoAffected = homoAffected.add(p.getFather().getHomozygousAffected().multiply(p.getMother().getHeterozygous()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHomozygousAffected()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHeterozygous().divide(new Fraction(4,1))));
		
		
		
		Fraction homoUnaffected = new Fraction (0,1);
		homoUnaffected = homoUnaffected.add(p.getFather().getHomozygousUnaffected().multiply(p.getMother().getHomozygousUnaffected()));
		homoUnaffected = homoUnaffected.add(p.getFather().getHomozygousUnaffected().multiply(p.getMother().getHeterozygous()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHomozygousUnaffected()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHeterozygous().divide(new Fraction(4,1))));
		
		
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		//changed to better
		Fraction temp;
		if(p.isAffected()){ //affected dominant
			homoUnaffected.numerator=0;
			temp = homoAffected.add(hetero);
			
		}else{ //unaffected recessive
			homoAffected.numerator =0;
			temp = homoUnaffected.add(hetero);       
			
		}
		
		homoAffected = homoAffected.divide(temp);
		homoUnaffected = homoUnaffected.divide(temp);
		hetero = hetero.divide(temp);
		
		
			
		//add fractions
		//divide by sum
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
	Fraction xLinkDominant(Person p){ 
		
		if(p.getSex()){ //female
			  return (autosomalDominant(p)); //check this boi
		} 
		
		simpleCalc(p.getMother(),true); //if male only mother matters
		
		Fraction homoAffected = new Fraction(0,0);
		homoAffected =  homoAffected.add(p.getMother().getHomozygousAffected());
		homoAffected = homoAffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		p.setHomozygousAffected(homoAffected);
		
		Fraction homoUnaffected = new Fraction(0,0);
		homoUnaffected = homoUnaffected.add(p.getMother().getHomozygousUnaffected());
		homoUnaffected = homoUnaffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		p.setHomozygousUnaffected(homoUnaffected);
		
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		p.setHeterozygous(hetero);
		
		return (p.getHeterozygous().add(p.getHomozygousAffected()));
	}
	
	//done
	Fraction xLinkRecessive(Person p){ 
		
		if(p.getSex()){ //female
			  return (autosomalRecessive(p)); //check this boi
		} 
		
		simpleCalc(p.getMother(),false); //if male only mother matters
		
		Fraction homoAffected = new Fraction(0,0);
		homoAffected = homoAffected.add(p.getMother().getHomozygousAffected());
		homoAffected = homoAffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		p.setHomozygousAffected(homoAffected);
		
		Fraction homoUnaffected = new Fraction(0,0);
		homoUnaffected = homoUnaffected.add(p.getMother().getHomozygousUnaffected());
		homoUnaffected = homoUnaffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		p.setHomozygousUnaffected(homoUnaffected);
		
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		p.setHeterozygous(hetero);
		
		return (p.getHomozygousAffected());
		
	}
	
	Fraction autosomalRecessive(Person p){ //done
		
		simpleCalc(p,false);
		return (p.getHomozygousAffected());
		
	}
	
	
	Fraction autosomalDominant(Person p){ //done
		
		simpleCalc(p,true);
		
		
		return (p.getHomozygousAffected().add(p.getHeterozygous()));
	}
	
	
	
}
