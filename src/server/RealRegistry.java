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

import comm.RMIMessage;
import comm.RMIMessage.msgType;
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
				
				//receive the msg
				RMIMessage msg = (RMIMessage)ois.readObject();
				
				//create the response
				RMIMessage response = new RMIMessage();
				response.setType(msgType.RESPONSE);
				
				if(msg.getType() == msgType.LOOKUP){
					//get the service name
					String service = msg.getServiceName();
					
					//look up the service
					if(serviceList.get(service) != null){
						response.setRor(serviceList.get(service));
						oos.writeObject(response);
					}else{
						response.setType(msgType.EXCEPTION);
						response.setExceptionCause("service not found!");
						oos.writeObject(response);
					}
				}else if(msg.getType() == msgType.REGISTRY){
					//rebind the service
					serviceList.put(msg.getServiceName(), msg.getRor());
					oos.writeObject(response);
				}
				client.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
