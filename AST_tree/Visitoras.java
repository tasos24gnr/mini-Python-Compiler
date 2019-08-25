import tony.analysis.*;
import tony.node.*;
import java.util.*;
import java.util.Enumeration;



public class Visitoras extends DepthFirstAdapter 
{
	private Hashtable symtable;	
	private Hashtable partable;	
	Enumeration keys;
	String key;
	private boolean diplotupo=false;
	private boolean idiarguments= false;
	private String function_name="";
	private int namefinder= 0;
	
	Visitoras(Hashtable symtable) 
	{
		this.symtable = symtable;
		partable = new Hashtable();
	}

	//Epanalispsi Dilwsis Methodou anexartita apo arguments
	public void inAFunction(AFunction node)
	{
		String fname = node.getId().toString();
		if(!symtable.containsKey(fname)){
			function_name = fname;
			namefinder= 1;
			System.out.println("\nTo onoma ths "+fname+" den yphrxe ston symtable opote to prosthetw");
		}
	}
	
	public void outAFunction(AFunction node)
	{
		String fname = node.getId().toString();
		int line = ((TId) node.getId()).getLine();
		if (namefinder == 1) {
			symtable.put(fname, node);
			namefinder= 0;
			
		}else{
			diplotupo=true;
			// if(idiarguments==true){
				//System.out.println("Line " + line + ": " +" Function " + fname +" is already defined");
			// }else{
				// symtable.put(fname, node);
				// namefinder= 0;
			// }
		}
	
	}
	
	public void inAArgument(AArgument node)
	{
		String name = node.getId().toString();
		Hashtable temp = new Hashtable();
		if (partable.containsKey(function_name)){ 
			temp = ((Hashtable) partable.get(function_name));
		}
		temp.put(name, node);
		partable.put(function_name,temp);

	}
	
	public void outAArgument(AArgument node){
		keys = partable.keys();
		key = (String) keys.nextElement();
		if(diplotupo){
			System.out.println("vrika diplotupo");
			diplotupo=false;
		}
		// System.out.println("key ----------------- :"+key+ "me argsnumber :"+ Argscounter(node.toString()) ); 
		//System.out.println("function_name :"+function_name+"kai exei :"+Argscounter(node.toString())+" arguments");
		//System.out.println(partable);
	}
	public int Argscounter(String node){
		String[] strArray= node.split(" ");
		int count=0;
		for(String s:strArray){
				if(!s.equals("")){
					count++;
				}
		}
		return count;
	}
	//Klish mh-dilwmenhs Function
	//public void inAFunctionCall(AFunctionCall node){
	//	String fname = node.getId().toString();
	//	int line = ((TId) node.getId()).getLine();	
	//}
	
	// public void inAIddExpression(AIddExpression node)
    // {
		
		// String name = node.getId().toString();
		// int line = ((TId) node.getId()).getLine();
        // System.out.println("~~~"+node+"~~~");
		
		// if(namefinder==1){
			
		// }
    // }
	
}






/*-------------------------------------------------------------------------------------------------
 keys = partable.keys();
		 //System.out.println("---------"+ node.size());
		while(keys.hasMoreElements()) {
			key = (String) keys.nextElement();
			if(key==function_name){
				System.out.println("function_name :"+function_name+" to key einai :"+key+"kai exei :"+Argscounter(node.toString())+" arguments");
			}
			// System.out.println("key ----------------- :"+key+ "me argsnumber :"+ Argscounter(node.toString()) );
		 }
		
		 //System.out.println("Key: " +function_name+ " & Value: " +
      //node);
		//System.out.println("func."+function_name+"has 1st argument -> "+name+ ", All arguments: "+node);
		//System.out.println("HashTable: "+partable+"\n");
*/