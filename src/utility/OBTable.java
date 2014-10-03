/*
 * the object table for all the remote objects
 */
package utility;

import java.util.HashMap;

public class OBTable {
	private HashMap<Integer,Object> obtbl;
	private int counter ;
	
	public OBTable(){
		this.obtbl = new HashMap<Integer,Object>();
		this.counter = 0;
	}
	
	public int add_obj(Object obj){
		if(obj != null){
		counter++;
		obtbl.put(counter,obj);
		}else{
			System.out.println("Null obj");
		}
		return counter;
	}
	
	public Object getObject(int key){
		return this.obtbl.get(key);
	}
}