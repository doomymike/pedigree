import java.util.ArrayList;

public class Tree {
	ArrayList<ArrayList<Person>> all = new ArrayList<ArrayList<Person>>();
	
	Tree(){
		Person initialPerson = new Person();
		Person initialMother = new Person();
		initialMother.setSex(true);
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
	private Person mother;
	private Person father;
	private Person spouse;
	private boolean sex; // false = male, true = female
	private boolean affected;
	private double homozygousAffected;
	private double heterozygous;
	private double homozygousUnaffected;
	private char[] code = new char[2];
	//String name;
	
	
	public char[] getCode() {
		return code;
	}
	public void setCode(char[] code) {
		this.code = code;
	}
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

	public boolean isAffected() {
		return affected;
	}

	public void setAffected(boolean affected) {
		this.affected = affected;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public double getHomozygousAffected() {
		return homozygousAffected;
	}

	public void setHomozygousAffected(double homozygousAffected) {
		this.homozygousAffected = homozygousAffected;
	}

	public double getHeterozygous() {
		return heterozygous;
	}

	public void setHeterozygous(double heterozygous) {
		this.heterozygous = heterozygous;
	}

	public double getHomozygousUnaffected() {
		return homozygousUnaffected;
	}

	public void setHomozygousUnaffected(double homozygousUnaffected) {
		this.homozygousUnaffected = homozygousUnaffected;
	}
}
