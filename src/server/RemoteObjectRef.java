package server;

import java.io.Serializable;


public class RemoteObjectRef implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7530700883120277399L;
	public static final String Remote_Interface_Name = null;
    public String IP_adr;
    public int Port;
    public int Obj_Key;
    String Remote_Object_Name;

    public RemoteObjectRef(String ip, int port, int obj_key, String riname) 
    {
	IP_adr=ip;
	Port=port;
	Obj_Key=obj_key;
	Remote_Object_Name=riname;
    }

    // this method is important, since it is a stub creator.
    // 
    public Object localise()
    {
	// Implement this as you like: essentially you should 
	// create a new stub object and returns it.
	// Assume the stub class has the name e.g.
	//
	//       Remote_Interface_Name + "_stub".
	//
	// Then you can create a new stub as follows:
	// 
	//       Class c = Class.forName(Remote_Interface_Name + "_stub");
	//       Object o = c.newinstance()
	//
	// For this to work, your stub should have a constructor without arguments.
	// You know what it does when it is called: it gives communication module
	// all what it got (use CM's static methods), including its method name, 
	// arguments etc., in a marshalled form, and CM (yourRMI) sends it out to 
	// another place. 
	// Here let it return null.
    try{
        Class<?> stub = Class.forName(Remote_Object_Name+"Stub");
        try {
            Object stubObject = stub.newInstance();
            ((RemoteInterface) stubObject).setRor(this);
            return stubObject;
        } catch (InstantiationException | IllegalAccessException e) {
            
            e.printStackTrace();
        }
    }catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    
	return null;
    }
    
    void setIpAdd(String ipAdd){
        IP_adr = ipAdd;
    }
    
    void setPort(int portNum){
        Port = portNum;
    }
    
    void setName(String remoteObjName){
        Remote_Object_Name = remoteObjName;
    }
    
    String getIpAdd(){
        return IP_adr;
    }
    
    int getPort(){
        return Port;
    }
    
    String getRemoteObjName(){
        return Remote_Interface_Name;
    }
}
