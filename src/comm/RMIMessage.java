/*  Message class to communicate among the client, the dispatcher and the registry 
 *  Author by Yifan Li
 *  Date: 10/1/2014
 */


package comm;

import java.io.Serializable;

import server.RemoteObjectRef;

public class RMIMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum msgType{
		INVOKE,
		REGISTRY,
		LOOKUP,
		RESPONSE,
		EXCEPTION
	}
	
	private msgType type;
	private RemoteObjectRef ror;
	private String methodName;
	private Object[] args;
	private Object result;
	public msgType getType() {
		return type;
	}
	public void setType(msgType type) {
		this.type = type;
	}
	public RemoteObjectRef getRor() {
		return ror;
	}
	public void setRor(RemoteObjectRef ror) {
		this.ror = ror;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
}
