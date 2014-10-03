/*
 * This the server class for the RMI framework.
 * Author Yifan Li
 * Date 10/2/2014
 */

package server;

import java.net.InetAddress;
import java.net.UnknownHostException;

import utility.OBTable;

public class RMI_Server {
	static String host;
	static int port;
	
	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("The usage should be: Java RMI_Server [registry hostIP] [registry port] [InitialClass]");
		}
		String registryHost = args[0];
		int registryPort = Integer.parseInt(args[1]);
		String initialClassName = args[2];
		
		
		try {
			host = (InetAddress.getLocalHost()).getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		port = 88888;
		
		// create the sample object from the name
		try {
			Class initialclass = Class.forName(initialClassName);
			
			//create the object
			initialclass.newInstance();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// create a object table
		OBTable obtbl = new OBTable();
		
		obtbl.add_obj();
				
	}
	
}
