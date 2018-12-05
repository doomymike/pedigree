import java.util.ArrayList;

public class Tree {
	ArrayList<ArrayList<Person>> all = new ArrayList<ArrayList<Person>>();
	Person initialPerson;

	Tree() {
		initialPerson = new Person(false, false);

		ArrayList<Person> initialPersons = new ArrayList<Person>();
		initialPersons.add(initialPerson);
		all.add(initialPersons);

		addParents(initialPerson);
		addParents(initialPerson.getMother());
		addParents(initialPerson.getFather());
		addChild(initialPerson.getFather().getFather());
		addChild(initialPerson.getFather());
		addChild(initialPerson.getMother().getFather());
		getPerson(1,0).setSex(true);
		getPerson(0,1).setSex(true);
	}
	
	
	Tree(ArrayList<ArrayList<Person>> people, Person initial){
		all = people;
		initialPerson = initial;
	}

	public Person getPerson(int generation, int number) {
		return all.get(generation).get(number);
	}

	public int[] getPosition(Person person) {
		for (int i = 0; i < all.size(); i++) {
			for (int j = 0; j < all.get(i).size(); j++) {
				if (all.get(i).get(j) == person) {
					int[] ans = { i, j };
					return ans;
				}
			}
		}
		int[] ans = { -1, -1 };
		return ans;
	}

	public boolean isAncestor(Person p) {
		if (p == initialPerson) {
			return true;
		}
		for (int i = 0; i < p.children.size(); i++) {
			if (isAncestor(p.children.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasCarrierAncestor(Person p, boolean dominant) {
		if(dominant) {
			if(!p.isAffected()) {
				return true;
			}else if(p.getFather() != null && p.getMother() != null){
				if(hasCarrierAncestor(p.getFather(),dominant)) {
					return true;
				}else if (hasCarrierAncestor(p.getMother(),dominant)){
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}else {
			if(p.isAffected()) {
				return true;
			}else if(p.getFather() != null && p.getMother() != null){
				if(hasCarrierAncestor(p.getFather(),dominant)) {
					return true;
				}else if (hasCarrierAncestor(p.getMother(),dominant)){
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
	}
	
	public void reset() {
		for(int i = 0;i<all.size();i++) {
			for(int j = 0;j<all.get(i).size();j++) {
				all.get(i).get(j).setHetero(null);
				all.get(i).get(j).setHomoAffected(null);
				all.get(i).get(j).setHomoUnaffected(null);
				all.get(i).get(j).isCarrier = false;
			}
		}
	}

	public boolean addParents(Person person) {
		if (person.getFather() == null && person.getMother() == null) {
			Person father = new Person(false, false);
			Person mother = new Person(true, false);
			int[] position = getPosition(person);
			if (position[0] + 1 == all.size()) {
				all.add(new ArrayList<Person>());
			}
			int addPosition = 0;
			for (int i = 0; i < all.get(position[0] + 1).size(); i++) {
				int maxChild = -1;
				for (int j = 0; j < getPerson(position[0] + 1, i).children.size(); j++) {
					if (getPosition(getPerson(position[0] + 1, i).children.get(j))[1] > maxChild) {
						maxChild = getPosition(getPerson(position[0] + 1, i).children.get(j))[1];
					}
				}
				if (maxChild < position[1]) {
					addPosition = i + 1;
				}
			}
			all.get(position[0] + 1).add(addPosition, mother);
			all.get(position[0] + 1).add(addPosition, father);
			father.addSpouse(mother);
			father.addChild(person);
			person.addParents(mother, father);
		}
		return false;
	}

	public boolean addChild(Person person) {
		if (person.getSpouse() != null) {
			Person child = new Person(false, false);
			int maxChildPos = 0;
			for (int i = 0; i < person.children.size(); i++) {
				maxChildPos = Math.max(getPosition(person.children.get(i))[1], maxChildPos);
			}
			int[] position = getPosition(person);
			if (position[0] == 0) {
				all.add(0, new ArrayList<Person>());
				position = getPosition(person);
			}
			// System.out.println(isAncestor(initialPerson.getFather()));
			if (isAncestor(all.get(position[0] - 1).get(maxChildPos))
					&& getPosition(all.get(position[0] - 1).get(maxChildPos)
							.getSpouse())[1] == getPosition(all.get(position[0] - 1).get(maxChildPos))[1] + 1) {
				all.get(position[0] - 1).add(maxChildPos, child);
			} else if (getPosition(all.get(position[0] - 1).get(maxChildPos)
					.getSpouse())[1] == getPosition(all.get(position[0] - 1).get(maxChildPos))[1] + 1) {
				all.get(position[0] - 1).add(maxChildPos + 2, child);
			} else {
				all.get(position[0] - 1).add(maxChildPos + 1, child);
			}
			person.addChild(child);
			if (person.getSex()) {
				child.addParents(person, person.getSpouse());
			} else {
				child.addParents(person.getSpouse(), person);
			}
			return true;
		}
		return false;
	}

	public boolean addSpouse(Person person) {
		if (person.getSpouse() == null) {
			Person spouse = new Person(!person.getSex(), false);
			int[] position = getPosition(person);
			all.get(position[0]).add(position[1] + 1, spouse);
			person.addSpouse(spouse);
			return true;
		}
		return false;
	}

	/**
	 * yLinked
	 * 
	 * @param p
	 * @return boolean Calculates whether or not an individual has the y-linked
	 *         condition
	 */
	boolean yLinked(Person p) {
		Fraction homoAffected, hetero, homoUnaffected;
		if(p.getHomoAffected() != null) { // already set
			return true;
		}
		if (p.getSex()) { // person is woman
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);

			// Calculates heterozygous
			hetero = new Fraction(0, 1);

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(1, 1);
		} else { // person is male
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);
			homoAffected = homoAffected.add(p.getFather().getHomoAffected());

			// Calculates heterozygous
			hetero = new Fraction(0, 1);

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(0, 1);
			homoUnaffected = homoUnaffected.add(p.getFather().getHomoUnaffected());
		}

		if (p.isAffected()) { // person is affected
			if (homoAffected.getNumber() > 0) {
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		} else { // person is unaffected
			if (homoUnaffected.getNumber() > 0) {
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * xLinkDominant
	 * 
	 * @param p
	 * @return Fraction Calculates chance of being affected by the x-linked dominant
	 *         condition
	 */
	boolean xLinkDominant(Person p) {
		Fraction homoAffected, hetero, homoUnaffected;
		if(p.getHomoAffected() != null) { // already set
			return true;
		}
		if (p.getSex()) { // person is woman
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoAffected()));
			homoAffected = homoAffected.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction(1, 2))));

			// Calculates heterozygous
			hetero = new Fraction(0, 1);
			hetero = hetero.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoUnaffected()));
			hetero = hetero.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoAffected()));
			hetero = hetero.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction(1, 2))));
			hetero = hetero.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction(1, 2))));

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(0, 1);
			homoUnaffected = homoUnaffected
					.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoUnaffected()));
			homoUnaffected = homoUnaffected.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction(1, 2))));
		} else { // person is male
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected()); // mother XX
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(new Fraction(1, 2))); // mother Xx

			// Calculates heterozygous
			hetero = new Fraction(0, 1);

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(0, 1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected()); // mother xx
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(new Fraction(1, 2))); // mother Xx
		}
		
		if (p.isCarrier){
			if (hetero.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}else if (p.isAffected()) { // person is affected
			if (homoUnaffected.getNumber() < 1) {
				Fraction temp;
				temp = homoAffected.add(hetero);
				// recalculates given restrictions
				homoAffected = homoAffected.divide(temp);
				hetero = hetero.divide(temp);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		} else { // person is unaffected
			if (homoUnaffected.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(0, 1);
				homoUnaffected = new Fraction(1, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * xLinkRecessive
	 * 
	 * @param p
	 * @return Fraction Calculates chance of being affected by the x-linked
	 *         recessive condition
	 */
	boolean xLinkRecessive(Person p) {
		Fraction homoAffected, hetero, homoUnaffected;
		if(p.getHomoAffected() != null) { // already set
			return true;
		}
		if (p.getSex()) { // person is woman
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoAffected()));
			homoAffected = homoAffected.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction(1, 2))));

			// Calculates heterozygous
			hetero = new Fraction(0, 1);
			hetero = hetero.add(p.getMother().getHomoAffected().multiply(p.getFather().getHomoUnaffected()));
			hetero = hetero.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoAffected()));
			hetero = hetero.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoAffected().multiply(new Fraction(1, 2))));
			hetero = hetero.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction(1, 2))));

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(0, 1);
			homoUnaffected = homoUnaffected
					.add(p.getMother().getHomoUnaffected().multiply(p.getFather().getHomoUnaffected()));
			homoUnaffected = homoUnaffected.add(
					p.getMother().getHetero().multiply(p.getFather().getHomoUnaffected().multiply(new Fraction(1, 2))));
		} else { // person is male
			// Calculates homoAffected
			homoAffected = new Fraction(0, 1);
			homoAffected = homoAffected.add(p.getMother().getHomoAffected()); // mother XX
			homoAffected = homoAffected.add(p.getMother().getHetero().multiply(new Fraction(1, 2))); // mother Xx

			// Calculates heterozygous
			hetero = new Fraction(0, 1);

			// Calculates homoUnaffected
			homoUnaffected = new Fraction(0, 1);
			homoUnaffected = homoUnaffected.add(p.getMother().getHomoUnaffected()); // mother xx
			homoUnaffected = homoUnaffected.add(p.getMother().getHetero().multiply(new Fraction(1, 2))); // mother Xx
		}
		
		if (p.isCarrier){
			if (hetero.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}else if (p.isAffected()) { // person is affected
			if (homoAffected.getNumber() > 0) {
				homoAffected = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				hetero = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		} else { // person is unaffected
			if (homoAffected.getNumber() < 1) {
				Fraction temp;
				temp = homoUnaffected.add(hetero);
				// recalculates given restrictions
				homoAffected = new Fraction(0, 1);
				hetero = hetero.divide(temp);
				homoUnaffected = homoUnaffected.divide(temp);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * autosomalDominant
	 * 
	 * @param p
	 * @return Fraction Calculates chance of being affected by the autosomal
	 *         dominant condition
	 */
	boolean autosomalDominant(Person p) { // done
		if(p.getHomoAffected() != null) { // already set
			return true;
		}
		// Calculates homoAffected
		Fraction homoAffected = new Fraction(0, 1);
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHomoAffected()));
		homoAffected = homoAffected
				.add(p.getFather().getHomoAffected().multiply(p.getMother().getHetero()).divide(new Fraction(2, 1)));
		homoAffected = homoAffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHomoAffected()).divide(new Fraction(2, 1)));
		homoAffected = homoAffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4, 1))));

		// Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction(0, 1);
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHomoUnaffected()));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHetero()).divide(new Fraction(2, 1)));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHomoUnaffected()).divide(new Fraction(2, 1)));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4, 1))));

		// Calculates hetero
		Fraction hetero = new Fraction(1, 1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));

		if (p.isCarrier){
			if (hetero.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		} else if (p.isAffected()) { // person is affected
			if (homoUnaffected.getNumber() < 1) {
				Fraction temp;
				temp = homoAffected.add(hetero);
				// recalculates given restrictions
				homoAffected = homoAffected.divide(temp);
				hetero = hetero.divide(temp);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}  else { // person is unaffected
			if (homoUnaffected.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(0, 1);
				homoUnaffected = new Fraction(1, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * autosomalRecessive
	 * 
	 * @param p
	 * @return Fraction Calculates chance of being affected by the autosomal
	 *         recessive condition
	 */
	boolean autosomalRecessive(Person p) { // done
		if(p.getHomoAffected() != null) { // already set
			return true;
		}
		// Calculates homoAffected
		Fraction homoAffected = new Fraction(0, 1);
		homoAffected = homoAffected.add(p.getFather().getHomoAffected().multiply(p.getMother().getHomoAffected()));
		homoAffected = homoAffected
				.add(p.getFather().getHomoAffected().multiply(p.getMother().getHetero()).divide(new Fraction(2, 1)));
		homoAffected = homoAffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHomoAffected()).divide(new Fraction(2, 1)));
		homoAffected = homoAffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4, 1))));

		// Calculates homoUnaffected
		Fraction homoUnaffected = new Fraction(0, 1);
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHomoUnaffected()));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHomoUnaffected().multiply(p.getMother().getHetero()).divide(new Fraction(2, 1)));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHomoUnaffected()).divide(new Fraction(2, 1)));
		homoUnaffected = homoUnaffected
				.add(p.getFather().getHetero().multiply(p.getMother().getHetero().divide(new Fraction(4, 1))));

		// Calculates hetero
		Fraction hetero = new Fraction(1, 1);
		hetero = hetero.subtract(homoAffected.add(homoUnaffected));

		if (p.isCarrier){
			if (hetero.getNumber() > 0) {
				homoAffected = new Fraction(0, 1);
				hetero = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}else if (p.isAffected()) { // person is affected
			if (homoAffected.getNumber() > 0) {
				homoAffected = new Fraction(1, 1);
				homoUnaffected = new Fraction(0, 1);
				hetero = new Fraction(0, 1);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
				return false;
			}
		}   else { // person is unaffected
			if (homoAffected.getNumber() < 1) {
				Fraction temp;
				temp = homoUnaffected.add(hetero);
				// recalculates given restrictions
				homoAffected = new Fraction(0, 1);
				hetero = hetero.divide(temp);
				homoUnaffected = homoUnaffected.divide(temp);
				p.setHomoAffected(homoAffected);
				p.setHetero(hetero);
				p.setHomoUnaffected(homoUnaffected);
				return true;
			} else {
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
					return true;
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

		if (p.isCarrier && p.getFather() == null && p.getMother() == null) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
		}else if (p.getFather() == null && p.getMother() == null) {
			if (p.isAffected()) {
				p.setHomoAffected(new Fraction(1, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(0, 1));
			} else {
				p.setHomoAffected(new Fraction(0, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(1, 1));
			}
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
						if(p.children.get(i).isAffected()) {
							p.isCarrier = true;
						}
					}
				}
			}
		}

		if (p.isCarrier && p.getFather() == null && p.getMother() == null) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}else if (p.getFather() == null && p.getMother() == null) {
			if (p.isAffected()) {
				p.setHomoAffected(new Fraction(1, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(0, 1));
			} else {
				p.setHomoAffected(new Fraction(0, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(1, 1));
			}
		}
		return true;
	}

	boolean autosomalDominantUp(Person p) {
		if (p.isAffected() && p.getSpouse() != null) { // person is affected
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
							if(!p.getSpouse().isCarrier) {
								p.isCarrier = true;
							}
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

		if (p.isCarrier && p.getFather() == null && p.getMother() == null) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}else if (p.getFather() == null && p.getMother() == null) {
			if (p.isAffected()) {
				p.setHomoAffected(new Fraction(1, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(0, 1));
			} else {
				p.setHomoAffected(new Fraction(0, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(1, 1));
			}
		}
		return true;
	}

	boolean autosomalRecessiveUp(Person p) {
		if (!p.isAffected() && p.getSpouse() != null) { // person is unaffected
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
							if(!p.getSpouse().isCarrier) {
								p.isCarrier = true;
							}
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

		if (p.isCarrier && p.getFather() == null && p.getMother() == null) {
			p.setHomoAffected(new Fraction(0, 1));
			p.setHetero(new Fraction(1, 1));
			p.setHomoUnaffected(new Fraction(0, 1));
			return true;
		}else if (p.getFather() == null && p.getMother() == null) {
			if (p.isAffected()) {
				p.setHomoAffected(new Fraction(1, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(0, 1));
			} else {
				p.setHomoAffected(new Fraction(0, 1));
				p.setHetero(new Fraction(0, 1));
				p.setHomoUnaffected(new Fraction(1, 1));
			}
		}
		return true;
	}
}

class Person {
	ArrayList<Person> children = new ArrayList<Person>();
	public static final boolean FEMALE = true;
	public static final boolean MALE = false;
	private Person mother;
	private Person father;
	private Person spouse;

	private boolean sex; // false = male, true = female
	private boolean affected;

	private Fraction homoAffected;
	private Fraction hetero;
	private Fraction homoUnaffected;
	boolean isCarrier = false;
	// String name;

	Person(boolean sex, boolean affected) {
		this.sex = sex;
		this.affected = affected;
	}

	/*
	 * Person(String name){ this.name = name; }
	 */

	/*
	 * maybe remove Person(String mother,String father){ this.father = new
	 * Person(father); this.mother = new Person(mother); this.father.spouse =
	 * this.mother; this.mother.spouse = this.father; }
	 */

	void addChild(Person child) {
		this.children.add(child);
		this.spouse.children.add(child);
	}

	void addSpouse(Person spouse) {
		this.spouse = spouse;
		spouse.spouse = this;
	}

	void addParents(Person mother, Person father) {
		this.father = father;
		this.mother = mother;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public boolean isAffected() {
		return affected;
	}

	public void setAffected(boolean affected) {
		this.affected = affected;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Fraction getHomoAffected() {
		return homoAffected;
	}

	public void setHomoAffected(Fraction homozygousAffected) {
		this.homoAffected = homozygousAffected;
	}

	public Fraction getHetero() {
		return hetero;
	}

	public void setHetero(Fraction heterozygous) {
		this.hetero = heterozygous;
	}

	public Fraction getHomoUnaffected() {
		return homoUnaffected;
	}

	public void setHomoUnaffected(Fraction homozygousUnaffected) {
		this.homoUnaffected = homozygousUnaffected;
	}
}
