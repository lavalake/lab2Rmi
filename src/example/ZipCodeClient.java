package example;
// a client for ZipCodeServer.
// it uses ZipCodeServer as an interface, and test
// all methods.

// It reads data from a file containing the service name and city-zip 
// pairs in the following way:
//   city1
//   zip1
//   ...
//   ...
//   end.

import java.io.*;

import client.LocateSimpleRegistry;
import server.RemoteObjectRef;
import server.SimpleRegistry;
import utility.RemoteException;

public class ZipCodeClient { 

    // the main takes three arguments:
    // (0) a host. 
    // (1) a port.
    // (2) a service name.
    // (3) a file name as above. 
    public static void main(String[] args) 
	throws IOException
    {
	String host = args[0];
	int port = Integer.parseInt(args[1]);
	String serviceName = args[2];
	BufferedReader in = null;
	try{
	    in = new BufferedReader(new FileReader(args[3]));
	}catch(FileNotFoundException e){
	    System.out.println("file "+args[3]+"not exist");
	    return;
	    
	}

	// locate the registry and get ror.
	SimpleRegistry sr = 
	    LocateSimpleRegistry.getRegistry(host, port);
	RemoteObjectRef ror = null;
    try {
        ror = sr.lookup(serviceName);
    } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        in.close();
        return;
    }

	// get (create) the stub out of ror.
 	ZipCodeServer zcs = (ZipCodeServer) ror.localise();
 	if(zcs == null)
 	    System.out.println("null zcs");
	// reads the data and make a "local" zip code list.
	// later this is sent to the server.
	// again no error check!
	ZipCodeList l = null;
	boolean flag = true;
	while (flag)
	    {
		String city = in.readLine();
		String code = in.readLine();
		if (city == null)
		    flag = false;
		else
		    l = new ZipCodeList(city.trim(), code.trim(), l);
	    }
	// the final value of l should be the initial head of 
	// the list.
	
	// we print out the local zipcodelist.
	System.out.println("This is the original list.");
	ZipCodeList temp = l;
	while (temp !=null)            
	    {
		System.out.println
		    ("city: "+temp.city+", "+
		     "code: "+temp.ZipCode);       
		temp = temp.next;                        
	    }
	
	// test the initialise.
	zcs.initialise(l);
	System.out.println("\n Server initalised.");

	// test the find.
	System.out.println("\n This is the remote list given by find.");
	temp = l;
	while (temp !=null)
	    {
		// here is a test.
		String res = zcs.find(temp.city);
		System.out.println("city: "+temp.city+", "+
				   "code: "+res);
		temp=temp.next;
	    }        		
	
	// test the findall.
	System.out.println("\n This is the remote list given by findall.");
	// here is a test.
	temp = zcs.findAll();
	while (temp !=null)
	    {
		System.out.println
		    ("city: "+temp.city+", "+
		     "code: "+temp.ZipCode);
		temp=temp.next;
	    }        		
	
	// test the printall.
	System.out.println("\n We test the remote site printing.");
	// here is a test.
	zcs.printAll();
	if(in != null)
	    in.close();
    }
    
        
}

	    
			
			
	

