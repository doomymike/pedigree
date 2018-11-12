import java.util.ArrayList;

public class Tree {
	
}

class Person{
	ArrayList<Person> children;
	Person mother;
	Person father;
	boolean affected;
	double homozygousAffected;
	double heterozygous;
	double homosygousUnaffected;
	String name;
	Person spouse;
	
	Person(){
		
	}
	Person(boolean affected){
		this.affected = affected;
	}
	
	Person(String name){
		this.name = name;
	}
	
	//maybe remove
	Person(String mother,String father){
		this.father = new Person(father);
		this.mother = new Person(mother);
		this.father.spouse = this.mother;
		this.mother.spouse = this.father;
	}
	
	void addChild(String child){
		this.children.add(new Person(child));
	}
	
	void addPartner(String partner) {
		this.spouse = new Person(partner);
	}
	
	void addParents(String mother,String father) {
		this.father = new Person(father);
		this.mother = new Person(mother);
		this.father.spouse = this.mother;
		this.mother.spouse = this.father;
	}
	
}
