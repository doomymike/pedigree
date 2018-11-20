import java.util.ArrayList;

public class Tree {
	ArrayList<ArrayList<Person>> all = new ArrayList<ArrayList<Person>>();
	Person initialPerson;
	Tree(){
		initialPerson = new Person(false,false);

		ArrayList<Person> initialPersons = new ArrayList<Person>();
		initialPersons.add(initialPerson);
		all.add(initialPersons);

		addParents(initialPerson);
		addParents(initialPerson.getMother());
		addParents(initialPerson.getFather());
		addChild(initialPerson.getFather().getFather());
	}
	
	public Person getPerson(int generation, int number) {
		return all.get(generation).get(number);
	}
	
	public int[] getPosition(Person person){
		for(int i = 0;i<all.size();i++) {
			for(int j = 0;j<all.get(i).size();j++) {
				if(all.get(i).get(j) == person) {
					int[] ans = {i,j};
					return ans;
				}
			}
		}
		int[] ans = {-1,-1};
		return ans;
	}
	
	public boolean isAncestor(Person person) {
		for(int i = 0;i<person.children.size();i++) {
			if(isAncestor(person.children.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean addParents(Person person) {
		if(person.getFather() == null && person.getMother() == null) {
			Person father = new Person(false,false);
			Person mother = new Person(true,false);
			int[] position = getPosition(person);
			if(position[0]+1 == all.size()) {
				all.add(new ArrayList<Person>());
			}
			int addPosition = 0;
			for(int i = 0;i<all.get(position[0]+1).size();i++) {
				int maxChild = -1;
				for(int j = 0;j<getPerson(position[0]+1,i).children.size();j++) {
					if(getPosition(getPerson(position[0]+1,i).children.get(j))[1] > maxChild) {
						maxChild = getPosition(getPerson(position[0]+1,i).children.get(j))[1];
					}
				}
				if(maxChild < position[1]) {
					addPosition = i+1;
				}
			}
			all.get(position[0]+1).add(addPosition,mother);
			all.get(position[0]+1).add(addPosition,father);
			father.addSpouse(mother);
			father.addChild(person);
			person.addParents(mother, father);
		}
		return false;
	}
	
	public boolean addChild(Person person){
		if(person.getSpouse() != null) {
			Person child = new Person(false,false);
			int maxChildPos = 0;
			for(int i = 0;i<person.children.size();i++) {
				maxChildPos = Math.max(getPosition(person.children.get(i))[1]+1,maxChildPos);
				if(person.children.get(i).getSpouse() != null) {
					maxChildPos++;
				}
			}
			int[] position = getPosition(person);
			if(position[0] == 0) {
				all.add(0,new ArrayList<Person>());
				position = getPosition(person);
			}
			all.get(position[0]-1).add(maxChildPos, child);
			person.addChild(child);
			if(person.getSex()) {
				child.addParents(person, person.getSpouse());
			}else {
				child.addParents(person.getSpouse(), person);
			}
			return true;
		}
		return false;
	}
	
	public boolean addSpouse(Person person) {
		if(person.getSpouse() == null){
			Person spouse = new Person(!person.getSex(),false);
			int[] position = getPosition(person);
			all.get(position[0]).add(position[1]+1, spouse);
			person.addSpouse(spouse);
			return true;
		}
		return false;
	}
	
}

class Person{
	ArrayList<Person> children = new ArrayList<Person>();
	public static final boolean FEMALE = true;
	public static final boolean MALE = false;
	private Person mother;
	private Person father;
	private Person spouse;
	
	private boolean sex; // false = male, true = female
	private boolean affected;
	
	private Fraction homozygousAffected;
	private Fraction heterozygous;
	private Fraction homozygousUnaffected;
	//String name;
	
	Person(boolean sex, boolean affected){
		this.sex = sex;
		this.affected = affected;
	}
	
	/*
	Person(String name){
		this.name = name;
	}
	*/
	
	/*maybe remove
	Person(String mother,String father){
		this.father = new Person(father);
		this.mother = new Person(mother);
		this.father.spouse = this.mother;
		this.mother.spouse = this.father;
	}
	*/
	
	void addChild(Person child){
		this.children.add(child);
		this.spouse.children.add(child);
	}
	
	void addSpouse(Person spouse) {
		this.spouse = spouse;
		spouse.spouse = this;
	}
	
	void addParents(Person mother,Person father) {
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

	public Fraction getHomozygousAffected() {
		return homozygousAffected;
	}

	public Fraction getHeterozygous() {
		return heterozygous;
	}

	public Fraction getHomozygousUnaffected() {
		return homozygousUnaffected;
	}
}
