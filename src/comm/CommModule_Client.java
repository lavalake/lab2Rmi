/*  Communication Module responsible for sending the RMIMessage 
 *  Author by Yifan Li
 *  Date: 10/1/2014
 */
package comm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommModule_Client{
    private String ipAdr;
    private int port;
    
    public CommModule_Client(String ip,int port){
    	this.setIpAdr(ip);
    	this.setPort(port);
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
	
	public boolean send_msg(RMIMessage msg){
		
		try {
			Socket socket = new Socket(this.ipAdr,this.port);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(msg);
			oos.close();
		} catch (IOException e) {
			System.out.print("object output stream failure!");
			e.printStackTrace();
		}
		
		return true;
	}
	
}