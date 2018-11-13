import java.util.ArrayList;

public class Tree {
	ArrayList<ArrayList<Person>> all = new ArrayList<ArrayList<Person>>();
	
	Tree(){
		Person initialPerson = new Person();
		Person initialMother = new Person();
		initialMother.sex = true;
		Person initialFather = new Person();
		
		initialFather.addPartner(initialMother);
		initialFather.addChild(initialPerson);
		initialPerson.addParents(initialMother, initialFather);
		
		ArrayList<Person> initialParents = new ArrayList<Person>();
		initialParents.add(initialFather);
		initialParents.add(initialMother);
		ArrayList<Person> initialPersons = new ArrayList<Person>();
		initialPersons.add(initialPerson);
		all.add(initialPersons);
		all.add(initialParents);
	}
}

class Person{
	ArrayList<Person> children;
	Person mother;
	Person father;
	Person spouse;
	boolean sex; // false = male, true = female
	boolean affected;
	double homozygousAffected;
	double heterozygous;
	double homozygousUnaffected;
	//String name;
	
	
	Person(){
		
	}
	Person(boolean affected){
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
	
	void addPartner(Person partner) {
		this.spouse = partner;
		partner.spouse = this;
	}
	
	void addParents(Person mother,Person father) {
		this.father = father;
		this.mother = mother;
	}
	
}
