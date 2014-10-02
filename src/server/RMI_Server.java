package server;

public class RMI_Server {
	private String host;
	private int port;
	
	public RMI_Server(String host,int port){
		this.host = host;
		this.port = port;
	}
	
	public static void main(String[] args){
		if(args.length != 3){
			System.out.println("The usage should be: Java RMI_Server [registry hostIP] [registry port] [InitialClass]");
		}
		;
	}
	
}
