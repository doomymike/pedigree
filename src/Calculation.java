//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHH forgot to check downwards AAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH
/**
 * Calculation
 * @author Michael Oren, Max Tang
 *
 */
public class Calculation {
	
	//literally useless but whatever bc it's fricking dominant and only one gene reeee
	/**
	 * yLinked
	 * @param p
	 * @return boolean
	 * Calculates whether or not an individual has the y-linked condition
	 */
	boolean yLinked(Person p){
		Fraction homoAffected,hetero,homoUnaffected;
		
		if(p.getSex()){ //person is woman
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (1,1);
		}else { // person is male
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			homoAffected = homoAffected.add(p.getFather().getHomoAffected());
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (0,1);
			homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected());
		}
		
		if(p.isAffected()) { // person is affected
			if(homoAffected.getNumber() > 0) {
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}else { // person is unaffected
			if(homoUnaffected.getNumber() > 0) {
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}
	}

	/**
	 * xLinkDominant
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the x-linked dominant condition
	 */
	boolean xLinkDominant(Person p){ 
		Fraction homoAffected,hetero,homoUnaffected;
		
		if(p.getSex()){ //person is woman
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoAffected())); // mother XX, father XY
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction (1,2)))); //mother Xx, father XY
			
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			hetero = hetero.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoUnaffected())); // mother XX, father xY
			hetero = hetero.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoAffected())); // mother xx, father XY
			hetero = hetero.add(p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction (1,2)))); //mother Xx, father XY
			hetero = hetero.add(p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction (1,2)))); //mother Xx, father xY
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (0,1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoUnaffected())); // mother xx, father xY
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction (1,2)))); //mother Xx, father xY
		}else { // person is male
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected()); // mother XX
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(new Fraction(1,2))); // mother Xx
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (0,1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected()); // mother xx
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(new Fraction(1,2))); // mother Xx
		}
		
		if(p.isAffected()) { // person is affected
			if(homoUnaffected.getNumber() < 1) {
				Fraction temp;
				temp = homoAffected.add(hetero);
				//recalculates given restrictions
				homoAffected = homoAffected.divide(temp);
				hetero = hetero.divide(temp);
				homoUnaffected = new Fraction(0,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}else { // person is unaffected
			if(homoUnaffected.getNumber() > 0) {
				homoAffected = new Fraction(0,1);
				homoUnaffected = new Fraction(0,1);
				hetero = new Fraction(1,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}
	}
	
	/**
	 * xLinkRecessive
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the x-linked recessive condition
	 */
	boolean xLinkRecessive(Person p){ 
		Fraction homoAffected,hetero,homoUnaffected;
		
		if(p.getSex()){ //person is woman
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoAffected())); // mother XX, father XY
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction (1,2)))); //mother Xx, father XY
			
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			hetero = hetero.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoUnaffected())); // mother XX, father xY
			hetero = hetero.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoAffected())); // mother xx, father XY
			hetero = hetero.add(p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction (1,2)))); //mother Xx, father XY
			hetero = hetero.add(p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction (1,2)))); //mother Xx, father xY
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (0,1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoUnaffected())); // mother xx, father xY
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction (1,2)))); //mother Xx, father xY
		}else { // person is male
			//Calculates homoAffected
			homoAffected = new Fraction(0,1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected()); // mother XX
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(new Fraction(1,2))); // mother Xx
			
			//Calculates heterozygous
			hetero = new Fraction(0,1);
			
			//Calculates homoUnaffected
			homoUnaffected = new Fraction (0,1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected()); // mother xx
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(new Fraction(1,2))); // mother Xx
		}
		
		if(p.isAffected()) { // person is affected
			if(homoAffected.getNumber() > 0) {
				homoAffected = new Fraction(1,1);
				homoUnaffected = new Fraction(0,1);
				hetero = new Fraction(0,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}else { // person is unaffected
			if(homoAffected.getNumber() < 1) {
				Fraction temp;
				temp = homoUnaffected.add(hetero);
				//recalculates given restrictions
				homoAffected = new Fraction(0,1);
				hetero = hetero.divide(temp);
				homoUnaffected = homoUnaffected.divide(temp);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}
		
	}
	
	/**
	 * autosomalDominant
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the autosomal dominant condition
	 */
	boolean autosomalDominant(Person p){ //done
		
		//Calculates homoAffected
		Fraction homoAffected = new Fraction(0,1);
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHomoAffected()));
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHetero()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHetero().multiply(p.getMother().getHomoAffected()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4,1))));
				
						
		//Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction (0,1);
		homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHomoUnaffected()));
		homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHetero()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHetero().multiply(p.getMother().getHomoUnaffected()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4,1))));
						
		//Calculates hetero
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		if(p.isAffected()) { // person is affected
			if(homoUnaffected.getNumber() < 1) {
				Fraction temp;
				temp = homoAffected.add(hetero);
				//recalculates given restrictions
				homoAffected = homoAffected.divide(temp);
				hetero = hetero.divide(temp);
				homoUnaffected = new Fraction(0,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}else { // person is unaffected
			if(homoUnaffected.getNumber() > 0) {
				homoAffected = new Fraction(0,1);
				homoUnaffected = new Fraction(0,1);
				hetero = new Fraction(1,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}
	}
	
	/**
	 * autosomalRecessive
	 * @param p
	 * @return Fraction
	 * Calculates chance of being affected by the autosomal recessive condition
	 */
	boolean autosomalRecessive(Person p){ //done
		
		//Calculates homoAffected
		Fraction homoAffected = new Fraction(0,1);
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHomoAffected()));
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHetero()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHetero().multiply(p.getMother().getHomoAffected()).divide(new Fraction(2,1)));
		homoAffected = homoAffected.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4,1))));
		
				
		//Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction (0,1);
		homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHomoUnaffected()));
		homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHetero()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHetero().multiply(p.getMother().getHomoUnaffected()).divide(new Fraction(2,1)));
		homoUnaffected = homoUnaffected.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4,1))));
		
		//Calculates hetero
		Fraction hetero = new Fraction(1,1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));
		
		if(p.isAffected()) { // person is affected
			if(homoAffected.getNumber() > 0) {
				homoAffected = new Fraction(1,1);
				homoUnaffected = new Fraction(0,1);
				hetero = new Fraction(0,1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}else { // person is unaffected
			if(homoAffected.getNumber() < 1) {
				Fraction temp;
				temp = homoUnaffected.add(hetero);
				//recalculates given restrictions
				homoAffected = new Fraction(0,1);
				hetero = hetero.divide(temp);
				homoUnaffected = homoUnaffected.divide(temp);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			}else {
				return false;
			}
		}
	}
	
		boolean yLinkUp(Person p) {
		if (p.getFather() == null && p.getMother() == null) {
			if (p.getSex()) {
				if (p.isAffected()) {
					return false;
				} else {
					p.setHomoAffected(new Fraction(0, 1));
					p.setHetero(new Fraction(0, 1));
					p.setHomoUnaffected(new Fraction(1, 1));
					return true;
				}
			} else {
				if (p.isAffected()) {
					p.setHomoAffected(new Fraction(1, 1));
					p.setHetero(new Fraction(0, 1));
					p.setHomoUnaffected(new Fraction(0, 1));
				} else {
					p.setHomoAffected(new Fraction(0, 1));
					p.setHetero(new Fraction(0, 1));
					p.setHomoUnaffected(new Fraction(1, 1));
					return true;
				}
			}
		}
		return true;
	}

	boolean xLinkDominantUp(Person p) {
		if (p.getSex() && p.isAffected() && p.getSpouse() != null) { // person is female and affected
			if (p.getSpouse().isAffected()) { // partner is affected
				for (int i = 0; i < p.children.size(); i++) {
					if (p.children.get(i).getSex()) { // children is female
						if(!p.children.get(i).isAffected()) {
							return false;
						}if(p.children.get(i).isCarrier) {
							p.isCarrier = true;
						}
					} else { // children is male
						if(!p.children.get(i).isAffected()) {
							p.isCarrier = true;
						}
					}
				}
			} else { // partner is unaffected
				for (int i = 0; i < p.children.size(); i++) {
					if (p.children.get(i).getSex()) { // children is female
						if(!p.children.get(i).isAffected()) {
							p.isCarrier = true;
						}
					} else { // children is male
						if(!p.children.get(i).isAffected()) {
							p.isCarrier = true;
						}
					}
				}
			}
		}

		if (p.isCarrier) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}
		return true;
	}

	boolean xLinkRecessiveUp(Person p) {
		if (p.getSex() && !p.isAffected() && p.getSpouse() != null) { // person is female and unaffected
			if (p.getSpouse().isAffected()) { // partner is affected
				for (int i = 0; i < p.children.size(); i++) {
					if(p.children.get(i).isAffected()) {
						p.isCarrier = true;
					}
				}
			} else { // partner is unaffected
				for (int i = 0; i < p.children.size(); i++) {
					if (p.children.get(i).getSex()) { // children is female
						if(p.children.get(i).isCarrier) {
							p.isCarrier = true;
						}
					} else { // children is male
						if(!p.children.get(i).isAffected()) {
							p.isCarrier = true;
						}
					}
				}
			}
		}

		if (p.isCarrier) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}
		return true;
	}

	boolean autosomalDominantUp(Person p) {
		if (p.getSex() && p.isAffected() && p.getSpouse() != null) { // person is female and affected
			if (p.getSpouse().isAffected()) { // partner is affected
				for (int i = 0; i < p.children.size(); i++) {
					if(!p.children.get(i).isAffected()) { // children is unaffected
						p.isCarrier = true; // both parents are carriers
					}else if(p.children.get(i).isCarrier) { // children is carrier
						boolean person = hasCarrierAncestor(p, true);
						boolean spouse = hasCarrierAncestor(p.getSpouse(), true);
						if(person && !spouse) { // one has carriers ancestors
							p.isCarrier = true;
						}else if(!person && spouse) {
							p.getSpouse().isCarrier = true;
						}else if(person && spouse) {
							// here we can guarantee both parent have a chance to be carriers
						}else {
							p.isCarrier = true;
							p.getSpouse().isCarrier = false;
						}
					}
				}
			} else { // partner is unaffected
				for (int i = 0; i < p.children.size(); i++) {
					if(!p.children.get(i).isAffected()) {
						p.isCarrier = true;
					}
				}
			}
		}

		if (p.isCarrier) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}
		return true;
	}

	boolean autosomalRecessiveUp(Person p) {
		if (p.getSex() && !p.isAffected() && p.getSpouse() != null) { // person is female and unaffected
			if (!p.getSpouse().isAffected()) { // partner is unaffected
				for (int i = 0; i < p.children.size(); i++) {
					if(p.children.get(i).isAffected()) { // children is affected
						p.isCarrier = true; // both parents are carriers
					}else if(p.children.get(i).isCarrier) { // children is carrier
						boolean person = hasCarrierAncestor(p, false);
						boolean spouse = hasCarrierAncestor(p.getSpouse(), false);
						if(person && !spouse) { // one has carriers ancestors
							p.isCarrier = true;
						}else if(!person && spouse) {
							p.getSpouse().isCarrier = true;
						}else if(person && spouse) {
							// here we can guarantee both parent have a chance to be carriers
						}else {
							p.isCarrier = true;
							p.getSpouse().isCarrier = false;
						}
					}
				}
			} else { // partner is affected
				for (int i = 0; i < p.children.size(); i++) {
					if(p.children.get(i).isAffected()) {
						p.isCarrier = true;
					}
				}
			}
		}

		if (p.isCarrier) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}
		return true;
	}
}
