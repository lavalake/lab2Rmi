/*  Communication Module responsible for sending the RMIMessage and receiving the RMIMessage
 *  Author by Yifan Li
 *  Date: 10/1/2014
 */
package comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CommModule_Client{
    private String ipAdr;
    private int port;
    private Socket socket;
    
    public CommModule_Client(String ip,int port){
    	this.setIpAdr(ip);
    	this.setPort(port);
    	try {
			this.socket = new Socket(this.ipAdr,this.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void CloseSocket(){
    	try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public String getIpAdr() {
		return ipAdr;
	}

	public void setIpAdr(String ipAdr) {
		this.ipAdr = ipAdr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
    
	/*
	 * method to send the msg to the socket(ip,port)
	 * 
	 */
	
	public boolean sendMsg(RMIMessage msg){
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
			oos.writeObject(msg);
			
		} catch (IOException e) {
			System.out.print("object output stream failure!");
			e.printStackTrace();
		}
		
		return true;
	}
	
	/*
	 * method to receive the response msg 
	 */
	public RMIMessage receiveMsg(){
		
		ObjectInputStream ois;
		RMIMessage response = null;
		try {
			ois = new ObjectInputStream(this.socket.getInputStream());
			response = (RMIMessage)ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return response;
	}
}