import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH MORE THAN 10 PEOPLE IN A SINGLE ARRAYLIST AAAAAAAAAAAAAAAAAAAAAAAAHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH

public class Export {
	
	static void yeet(Tree t)throws IOException {
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
		
		writer.println();
		for (int i =0;i<t.all.size();i++){
			
			for (int j = 0;j< t.all.get(i).size();j++){
				
				
				
				
				writer.print(ofBool(t.all.get(i).get(j).getSex()));
				writer.print(ofBool(t.all.get(i).get(j).isCarrier));
				
				//parent finding
				if(t.all.get(i).get(j).getFather()!= null){
					for (int l =0;l<t.all.get(i+1).size();l++){
						if (t.all.get(i+1).get(l) == t.all.get(i).get(j).getFather()){
							writer.print(l);
						}
					}
				}else{ // no dad
					writer.print('e');
				}
				
				if(t.all.get(i).get(j).getMother()!= null){
					for (int l =0;l<t.all.get(i+1).size();l++){
						if (t.all.get(i+1).get(l) == t.all.get(i).get(j).getMother()){
							writer.print(l);
						}
					}
				}else{ // no mom
					writer.print('e');
				}
				
				writer.println();
			}
			writer.println();
			
		}
		
		writer.close();
	}
	
	public static void main(String[] args) throws IOException{
		Tree tree = new Tree();
		
		yeet(tree);
		yeet(unyeet());
		unyeet();
	}
	
	static Tree unyeet() throws IOException{
		File file = new File("test.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st;
		  int j = -1;
		  
		  
		  ArrayList<ArrayList<Person>> people = new ArrayList<ArrayList<Person>>();
		  
		  

		  while ((st = br.readLine()) != null){
		    System.out.println(st);
		    if(st.equals("")){
		    	ArrayList<Person> row = new ArrayList<Person>();
		    	j++;
		    	people.add(row);
		    }else{
		    	Person temp = new Person(ofString(st.charAt(0)),ofString(st.charAt(1)));
		    	people.get(j).add(temp);
		    	
		    }
		    
		  }
		  br.close();
		  br = new BufferedReader(new FileReader(file));
		  j = -1;
		  int i=0;
		  while ((st = br.readLine()) != null){
			    if(st.equals("")){
			    	j++;
			    	i=0;
			    }else{
			    	if(st.charAt(2) != 'e'){ //if dad then mom //double the people, double the mark
			    		people.get(j).get(i).setFather(people.get(j+1).get(Character.getNumericValue(st.charAt(2))));
			    		people.get(j+1).get(Character.getNumericValue(st.charAt(2))).children.add(people.get(j).get(i));
			    		people.get(j).get(i).setMother(people.get(j+1).get(Character.getNumericValue(st.charAt(3))));
			    		people.get(j+1).get(Character.getNumericValue(st.charAt(3))).children.add(people.get(j).get(i));
			    	}
			    	
			    	i++;
			    }
			    
			  }
		  
		  
		  
		  Person init = people.get(0).get(0);
		  
		  
		  
		  
		  
		  br.close();
		  Tree t = new Tree(people,init);
		  return t;
	}
	static char ofBool(boolean x){
		if(x){
			return '1';
		}
		return '0';
		
	}
	
	static boolean ofString(char x){
		if (x=='0'){
			return false;
		}
		return true;
	}
	
}
