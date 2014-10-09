package client;
import server.SimpleRegistry;

public class LocateSimpleRegistry 
{ 
    // this is the SOLE static method.
    // you use it as: LocateSimpleRegistry.getRegistry(123.123.123.123, 2048)
    // and it returns null if there is none, else it returns the registry.
    // actually the registry is just a pair of host IP and port. 
    // inefficient? well you can change it as you like. 
    // for the rest, you can see SimpleRegistry.java.
    public static SimpleRegistry getRegistry(String host, int port)
    {
	
		    return new SimpleRegistry(host, port);
    }		
}

