package example;
import server.RemoteObjectRef;
import utility.RemoteException;
import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import example.ZipCodeList;
import example.ZipCodeServer;

public class ZipCodeServerImplStub implements  ZipCodeServer{

    RemoteObjectRef ror;
    @Override
    public void initialise(ZipCodeList newlist) {
        
        
        Object[] argv = {newlist};
        Class<?>[] argType = new Class<?>[argv.length];
        System.out.println("initialise");
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            invoke("initialise", argv, argType);
        } catch (RemoteException e) {
            
            e.printStackTrace();
        }
        
    }

    @Override
    public String find(String city) {
        // TODO Auto-generated method stub
        String ret = null;
        Object[] argv = {city};
        Class<?>[] argType = new Class<?>[argv.length];
        
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            ret = (String)invoke("find", argv, argType);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public ZipCodeList findAll() {
        // TODO Auto-generated method stub
        ZipCodeList ret = null;
        Object[] argv = {};
        Class<?>[] argType = new Class<?>[argv.length];
        
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            ret = (ZipCodeList)invoke("findAll",null,null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    /*will print at remote site, not locally*/
    public void printAll() {
        // TODO Auto-generated method stub
        
        Object[] argv = {};
        Class<?>[] argType = new Class<?>[argv.length];
        
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            invoke("printAll",null,null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            System.out.println("invoke printAll failed: "+e.getMessage());
        }
        
        
        
    }
    
    private Object invoke(String command, Object[] argv, Class<?>[] argType) throws RemoteException{
        RMIMessage ivkMessage = new RMIMessage();
        ivkMessage.setType(msgType.INVOKE);
        ivkMessage.setMethodName(command);
        ivkMessage.setArgs(argv);
        ivkMessage.setRor(ror);
        ivkMessage.setArgsType(argType);
        
        CommModule_Client comm = new CommModule_Client(ror.IP_adr, ror.Port);
        
        comm.sendMsg(ivkMessage);
        
        RMIMessage rplMessage = new RMIMessage();
        
        rplMessage = comm.receiveMsg();
        if(rplMessage.getType() == msgType.RESPONSE){
            return rplMessage.getResult();
        }
        else if(rplMessage.getType() == msgType.EXCEPTION){
            System.out.println("exception:"+rplMessage.getExceptionCause());
            throw(new RemoteException(rplMessage.getExceptionCause()));
        }
        comm.CloseSocket();
        return null;
    }
    
    

    @Override
    public void setRor(RemoteObjectRef ref) {
        // TODO Auto-generated method stub
        ror = ref;
    }

    @Override
    public RemoteObjectRef getRor() {
        // TODO Auto-generated method stub
        return ror;
    }
    
}