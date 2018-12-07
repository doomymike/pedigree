import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Export.java
 * @author Michael Oren
 * Dec 7 2018
 * Exports information to a data file, as well as takes information from a data file
 */
public class Export {
	
	/**
	 * putOut
	 * @param t
	 * @param name
	 * @throws IOException
	 * Takes tree and puts information out into a file
	 */
	public static void putOut(Tree t,String name)throws IOException {
		PrintWriter writer = new PrintWriter(name, "UTF-8");
		
		//writes initial person to file
		writer.println(t.getPosition(t.initialPerson)[0]);
		writer.println(t.getPosition(t.initialPerson)[1]);
		
		writer.println();
		
		//loops through entire tree, adding every person's information
		for (int i =0;i<t.all.size();i++){
			
			for (int j = 0;j< t.all.get(i).size();j++){
				
				writer.print(ofBool(t.all.get(i).get(j).getSex()));
				writer.print(ofBool(t.all.get(i).get(j).isAffected()));
				
				writer.println();
				
				
				//parent finding
				
				//writes father to file
				if(t.all.get(i).get(j).getFather()!= null){
					for (int l =0;l<t.all.get(i+1).size();l++){
						if (t.all.get(i+1).get(l) == t.all.get(i).get(j).getFather()){
							writer.print(l);
						}
					}
				}else{ // no dad
					writer.print('e');
				}
				
				//writes mother to file
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
	
	/**
	 * takeIn
	 * @param name
	 * @return
	 * @throws IOException
	 * Takes in file and converts to tree
	 */
	public static Tree takeIn(String name) throws IOException{
		File file = new File(name); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  //initial person location
		  int level = Integer.parseInt(br.readLine());
		  int location = Integer.parseInt(br.readLine());
		  
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
		  //resets to top of page
		  br = new BufferedReader(new FileReader(file));
		  br.readLine();
		  br.readLine();
		  
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
			    		//assigns children and spouses
			    		people.get(j).get(i).setFather(people.get(j+1).get(Integer.parseInt(dad)));
			    		people.get(j+1).get(Integer.parseInt(dad)).children.add(people.get(j).get(i));
			    		people.get(j).get(i).setMother(people.get(j+1).get(Integer.parseInt(mom)));
			    		people.get(j+1).get(Integer.parseInt(mom)).children.add(people.get(j).get(i));
			    		people.get(j+1).get(Integer.parseInt(mom)).addSpouse(people.get(j+1).get(Integer.parseInt(dad)));
			    		
			    	}
			    	i++;
			    }
			    
			  }
		  
		  
		  
		  Person init = people.get(level).get(location);
		  	  
		  
		  br.close();
		  Tree t = new Tree(people,init);
		  return t;
	}
	/**
	 * ofBool
	 * @param x
	 * @return
	 * Converts boolean to char
	 */
	private static char ofBool(boolean x){
		if(x){
			return '1';
		}
		return '0';
		
	}
	
	/**
	 * ofString
	 * @param x
	 * @return
	 * Converts char to boolean
	 */
	private static boolean ofString(char x){
		if (x=='0'){
			return false;
		}
		return true;
	}
	
}




