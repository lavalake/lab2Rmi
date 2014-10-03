/*
 * This the server class for the RMI framework.
 * Author Yifan Li
 * Date 10/2/2014
 */

package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;





import comm.RMIMessage;
import client.LocateSimpleRegistry;
import utility.OBTable;

public class RMI_Server {
	static String host;
	static int port;
	static OBTable obtbl; 
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
			Object o  = initialclass.newInstance();
			
			// create a object table
			obtbl = new OBTable();
			
			//add the object to the table
			int key = obtbl.add_obj(o);		
			
			//ceate the ror from the class name
			RemoteObjectRef ror = new RemoteObjectRef(host,port,key,initialClassName); 
		
			//register the service
			SimpleRegistry sr = LocateSimpleRegistry.getRegistry(registryHost, registryPort);
		
			if(sr != null){
				sr.rebind(initialClassName,ror);			
			}

			//create the socket
			ServerSocket soc = new ServerSocket(port);
			
			//start to listen to the socket and deal with the remote method invocation
			while(true){
				//listen to the request
				Socket Client=soc.accept();
				
				//create a new thread to deal with the remote method invocation
				
				
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//deal with RMI request
	public class dealRMIrequest implements Runnable{
		private Socket soc;
		ObjectInputStream ois;
		ObjectOutputStream oos;
		
		public dealRMIrequest(Socket soc){
			this.soc = soc;
			try {
				this.ois = new ObjectInputStream(soc.getInputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				this.oos = new ObjectOutputStream(soc.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		@Override
		public void run() {
			
			try {
				//get the RMI message
				RMIMessage msg = (RMIMessage) this.ois.readObject();
				
				//un-marshall the message 
				int key = msg.getRor().Obj_Key;
				String methodName = msg.getMethodName();
				Object [] args = msg.getArgs();
				Class<?> argsType = msg.getArgsType();
				
				//get the method
				Method m;
				if(args.length == 1){
					 m = obtbl.getObject(key).getClass().getMethod(methodName);
				}else{
					m = obtbl.getObject(key).getClass().getMethod(methodName, argsType);
				}
				
				//invoke the method
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
