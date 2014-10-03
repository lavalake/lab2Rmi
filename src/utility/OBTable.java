/*
 * the object table for all the remote objects
 */
package utility;

import java.util.HashMap;

public class OBTable {
	private HashMap<Integer,Object> obtbl;
	private int coutner ;
	
	public OBTable(){
		this.obtbl = new HashMap<Integer,Object>();
		this.coutner = 0;
	}
	
	public void add_obj(Object obj){
		
		obtbl.put(,obj);
	}
}
