
public class Person {
	
	//Initialization Variables
	boolean sex;
	boolean affected; 
	Person mother;
	Person father; 
	Person partner; 
	
	//Activation Variables 
	public void addPartner(){
		
	}
	public void addParents(){
		
	}
	public void addChild(){
		
	}
	
	public void setMother(Person mother){
		this.mother = mother;
	}
	public Person getMother(){
		return this.mother; 
	}
	public void setFather(Person father){
		this.father = father;
	}
	public Person getFather(){
		return this.father;
	}
	
	public void setAffected(boolean affected){
		this.affected = affected; 
	}
	public boolean isAffected(){
		return this.affected;
	}
	
	public boolean getSex(){
		return this.sex;
	}
	public void setSex(boolean sex){
		this.sex = sex;
	}
	
	public void setSpouse(){
		
	}
	public Person getSpouse(){
		
	}
}
