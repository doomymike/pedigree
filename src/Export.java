import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Export {
	
	static void yeet(Tree t)throws IOException {
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
		
		writer.println();
		for (int i =0;i<t.all.size();i++){
			
			for (int j = 0;j< t.all.get(i).size();j++){
				
				
				
				
				writer.print(ofBool(t.all.get(i).get(j).getSex()));
				writer.print(ofBool(t.all.get(i).get(j).isCarrier));
				
				writer.println();
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
				
				writer.println();
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
		    	
		    	st = br.readLine();
		    	st = br.readLine();
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
			    	String dad = br.readLine();
			    	String mom = br.readLine();
			    	if(!dad.equals("e")){ //if dad then mom is guaranteed
			    		people.get(j).get(i).setFather(people.get(j+1).get(Integer.parseInt(dad)));
			    		people.get(j+1).get(Integer.parseInt(dad)).children.add(people.get(j).get(i));
			    		people.get(j).get(i).setMother(people.get(j+1).get(Integer.parseInt(mom)));
			    		people.get(j+1).get(Integer.parseInt(mom)).children.add(people.get(j).get(i));
			    		people.get(j+1).get(Integer.parseInt(mom)).setSpouse(people.get(j+1).get(Integer.parseInt(dad)));
			    	}
			    	i++;
			    }
			    
			  }
		  
		  
		  
		  Person init = people.get(0).get(0);
		  
		  
		  
		  
		  
		  br.close();
		  Tree t = new Tree(people,init);
		  return t;
	}
	private static char ofBool(boolean x){
		if(x){
			return '1';
		}
		return '0';
		
	}
	
	private static boolean ofString(char x){
		if (x=='0'){
			return false;
		}
		return true;
	}
	
}

