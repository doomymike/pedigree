import java.util.ArrayList;

public class Tree {
	
}

class Node{
	ArrayList<Node> children;
	Node mother;
	Node father;
	double diseaseChance;
	boolean affected;
	String name;
	Node spouse;
	
	Node(){
		
	}
	Node(boolean affected){
		this.affected = affected;
	}
	
	Node(String name){
		this.name = name;
	}
	
	//maybe remove
	Node(String mother,String father){
		this.father = new Node(father);
		this.mother = new Node(mother);
		this.father.spouse = this.mother;
		this.mother.spouse = this.father;
	}
	
	void addChild(String child){
		this.children.add(new Node(child));
	}
	
	void addPartner(String partner) {
		this.spouse = new Node(partner);
	}
	
	void addParents(String mother,String father) {
		this.father = new Node(father);
		this.mother = new Node(mother);
		this.father.spouse = this.mother;
		this.mother.spouse = this.father;
	}
	
}