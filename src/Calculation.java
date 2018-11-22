//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHH forgot to check downwards AAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
/**
 * Calculation
 * @author Michael Oren, Max Tang
 *
 */
public class Calculation {
	
	//best code
	/**
	 * simpleCalc
	 * @param p
	 * @param dominant
	 * Calculates all genetic probabilities
	 */
	void simpleCalc(Person p, boolean dominant){
		
		if(p.isAffected() && !dominant){ //affected recessive
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(1,1);
			p.getHomozygousUnaffected().set(0, 1);
			return;
		}
		
		if(!p.isAffected() && dominant){ //dominant unaffected
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(0,1);
			p.getHomozygousUnaffected().set(1, 1);
			return;
		}
		
		//checks if parents are known
		if(p.getMother().getHeterozygous() == null){ //heterozygous could be replaced with any
			autosomalDominant(p.getMother());
		}
		if(p.getFather().getHeterozygous() == null){
			autosomalDominant(p.getFather());
		}
		
		//Calculates homoAffected
		Fraction homoAffected = new Fraction(0,1);
		homoAffected = homoAffected.add(p.getFather().getHomozygousAffected().multiply(p.getMother().getHomozygousAffected()));
		homoAffected = homoAffected.add(p.getFather().getHomozygousAffected().multiply(p.getMother().getHeterozygous()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHomozygousAffected()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHeterozygous().divide(new Fraction(4,1))));
		
		
		//Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction (0,1);
		homoUnaffected = homoUnaffected.add(p.getFather().getHomozygousUnaffected().multiply(p.getMother().getHomozygousUnaffected()));
		homoUnaffected = homoUnaffected.add(p.getFather().getHomozygousUnaffected().multiply(p.getMother().getHeterozygous()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHomozygousUnaffected()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHeterozygous().multiply(p.getMother().getHeterozygous().divide(new Fraction(4,1))));
		
		//Calculates homoAffected
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		//changed to better
		
		//---------------------------------------------------------------------------------------------#2--------------------------------------------------------------------------
		Fraction temp;
		
		if(p.isAffected()){ //affected dominant
			homoUnaffected.numerator=0;
			temp = homoAffected.add(hetero);
			
		}else{ //unaffected recessive
			homoAffected.numerator =0;
			temp = homoUnaffected.add(hetero);       
			
		}
		
		//recalculates given restrictions
		homoAffected = homoAffected.divide(temp);
		homoUnaffected = homoUnaffected.divide(temp);
		hetero = hetero.divide(temp);
		
		
		//sets probablilities
		p.setHomozygousAffected(homoAffected);
		p.setHomozygousUnaffected(homoUnaffected);
		p.setHeterozygous(hetero);
	}
	
	//literally useless but whatever bc it's fricking dominant and only one gene reeee
	/**
	 * yLinked
	 * @param p
	 * @return boolean
	 * Calculates whether or not an individual has the y-linked condition
	 */
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
	/**
	 * xLinkDominant
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the x-linked dominant condition
	 */
	Fraction xLinkDominant(Person p){ 
		
		if(p.getSex()){ //female
			  return (autosomalDominant(p)); //xlinked is equal to autosomal for females
		} 
		
		
		
		simpleCalc(p.getMother(),true); //if male only mother matters
		
		//Calculates homoAffected
		Fraction homoAffected = new Fraction(0,0);
		homoAffected =  homoAffected.add(p.getMother().getHomozygousAffected());
		homoAffected = homoAffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		
		//Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction(0,0);
		homoUnaffected = homoUnaffected.add(p.getMother().getHomozygousUnaffected());
		homoUnaffected = homoUnaffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		//Calculates hetero
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		if(p.isAffected()){ //affected dominant                           -----------------------------------------------------------#2--------------------------------------
			Fraction temp;
			homoUnaffected.numerator=0;
			temp = homoAffected.add(hetero);
			homoAffected = homoAffected.divide(temp);
			hetero = hetero.divide(temp);
			
		}else{ //not affected #2 oops oh well dominant
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(0,1);
			p.getHomozygousUnaffected().set(1, 1);
			return (new Fraction(0,1));
		}
		
		//sets probabilities
		p.setHomozygousAffected(homoAffected);
		p.setHomozygousUnaffected(homoUnaffected);
		p.setHeterozygous(hetero);
		
		return (p.getHeterozygous().add(p.getHomozygousAffected()));
	}
	
	//done
	/**
	 * xLinkRecessive
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the x-linked recessive condition
	 */
	Fraction xLinkRecessive(Person p){ 
		
		if(p.isAffected()){
			p.getHeterozygous().set(0, 1);
			p.getHomozygousAffected().set(1,1);
			p.getHomozygousUnaffected().set(0, 1);
			return (new Fraction(1,1));
		}
		
		if(p.getSex()){ //female
			  return (autosomalRecessive(p)); //check this boi
		} 
		
		simpleCalc(p.getMother(),false); //if male only mother matters
		
		//Calculates homoAffected
		Fraction homoAffected = new Fraction(0,0);
		homoAffected = homoAffected.add(p.getMother().getHomozygousAffected());
		homoAffected = homoAffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		//Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction(0,0);
		homoUnaffected = homoUnaffected.add(p.getMother().getHomozygousUnaffected());
		homoUnaffected = homoUnaffected.add(p.getMother().getHeterozygous().divide(new Fraction (2,1)));
		
		//Calculates hetero
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		if(!p.isAffected()){ //back to number 2  ---------------------------------------------------------------
			Fraction temp;
			homoAffected.numerator=0;
			temp = homoUnaffected.add(hetero);
			homoUnaffected = homoUnaffected.divide(temp);
			hetero = hetero.divide(temp);
		}
		
		p.setHomozygousAffected(homoAffected);
		p.setHomozygousUnaffected(homoUnaffected);
		p.setHeterozygous(hetero);
		
		return (p.getHomozygousAffected());
		
	}
	/**
	 * autosomalRecessive
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the autosomal recessive condition
	 */
	Fraction autosomalRecessive(Person p){ //done
		
		simpleCalc(p,false);
		return (p.getHomozygousAffected());
		
	}
	
	/**
	 * autosomalDominant
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the autosomal dominant condition
	 */
	Fraction autosomalDominant(Person p){ //done
		
		simpleCalc(p,true);
		return (p.getHomozygousAffected().add(p.getHeterozygous()));
		
	}
	
	
	
}
