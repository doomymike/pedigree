import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Export {
	
	static void yeet(Tree t)throws IOException {
		PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
		
		
		for (int i =0;i<t.all.size();i++){
			
			for (int j = 0;j< t.all.get(i).size();j++){
				
				writer.print(ofBool(t.all.get(i).get(j).isCarrier));
				writer.print(ofBool(t.all.get(i).get(j).getSex()));
				writer.println();
			}
			writer.println();
			
		}
		
		writer.close();
	}
	
	public static void main(String[] args) throws IOException{
		Tree tree = new Tree();
		
		yeet(tree);
		unyeet();
	}
	
	static void unyeet() throws IOException{
		File file = new File("test.txt"); 
		  
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  String st;
		  int j = 0;
		  int i = 0;
		  Tree t = new Tree();
		  while ((st = br.readLine()) != null){
		    System.out.println(st);
		    if(st.equals("")){
		    	j++;
		    	i = 0;
		    }else{
		    	t.all.get(j).get(i).isCarrier = ofString(st.charAt(0));
		    	t.all.get(j).get(i).setSex(ofString(st.charAt(1)));
		    	i++;
		    }
		    
		  }
		  br.close();
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
