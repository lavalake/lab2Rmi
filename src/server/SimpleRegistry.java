package server;
import java.util.*;
import java.net.*;
import java.io.*;

import utility.RemoteException;
import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;

public class SimpleRegistry 
{ 
    // registry holds its port and host, and connects to it each time. 
    String Host;
    int Port;
    
    // ultra simple constructor.
    public SimpleRegistry(String IPAdr, int PortNum)
    {
	Host = IPAdr;
	Port = PortNum;
    }

    // returns the ROR (if found) or null (if else)
    public RemoteObjectRef lookup(String serviceName) 
	throws IOException, RemoteException
    {
    
	// it is locate request, with a service name.
	CommModule_Client comm = new CommModule_Client(Host,Port);
	RMIMessage lookupMsg = new RMIMessage();
	lookupMsg.setType(msgType.LOOKUP);
	lookupMsg.setServiceName(serviceName);
	
	comm.sendMsg(lookupMsg);

	System.out.println("command and service name sent.");

	// branch according to the answer.
	RMIMessage rsp = comm.receiveMsg();
	RemoteObjectRef ror = null;
	if(rsp.getType() == msgType.RESPONSE){
	    ror = rsp.getRor();
	}
	else if(rsp.getType() == msgType.EXCEPTION){
	    throw(new RemoteException(rsp.getExceptionCause()));
	}
	
	return ror;
    }

    // rebind a ROR. ROR can be null. again no check, on this or whatever. 
    // I hate this but have no time.
    public void rebind(String serviceName, RemoteObjectRef ror) 
	throws IOException, RemoteException
    {
    	System.out.println("registry host:"+Host+" registry port:"+Port);
    	CommModule_Client comm = new CommModule_Client(Host,Port);
        RMIMessage registryMsg = new RMIMessage();
        registryMsg.setType(msgType.REGISTRY);
        registryMsg.setRor(ror);
        registryMsg.setServiceName(serviceName);
        
        comm.sendMsg(registryMsg);

        System.out.println("command and service name sent.");

        // branch according to the answer.
        RMIMessage rsp = comm.receiveMsg();
        
        if(rsp.getType() == msgType.RESPONSE){
            System.out.println("register suceed\n");
        }
        else if(rsp.getType() == msgType.EXCEPTION){
            throw(new RemoteException(rsp.getExceptionCause()));
        }
    }
} 
  
