/*
 * The class for implementing a real registry
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RealRegistry {
	public static void main(String [] args){
		if(args.length != 1){
			System.out.println("The usage: java RealRegisty <port>");
		}
		int port = Integer.parseInt(args[0]);
	
		//create the serviceList
		HashMap<String,RemoteObjectRef> serviceList = new HashMap<String,RemoteObjectRef>();
		
		
		try {
			//ceate the server socket
			ServerSocket soc = new ServerSocket(port);
			
			//listen to the socket
			while(true){
				Socket client = soc.accept();
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
