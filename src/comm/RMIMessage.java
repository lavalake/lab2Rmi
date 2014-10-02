/*  Message class to communicate among the client, the dispatcher and the registry 
 *  Author by Yifan Li
 *  Date: 10/1/2014
 */


package comm;

import java.io.Serializable;

import server.RemoteObjectRef;

public class RMIMessage implements Serializable{
	public enum msgType{
		INVOKE,
		REGISTRY,
		LOOKUP,
		RESPONSE,
		EXCEPTION
	}
	
	private msgType type;
	RemoteObjectRef ror;
	String methodName;
	Object[] args;
	
	
}
