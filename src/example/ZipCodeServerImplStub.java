package example;
import server.RemoteObjectRef;
import server.stubInterface;
import utility.RemoteException;
import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import example.ZipCodeList;
import example.ZipCodeServer;

public class ZipCodeServerImplStub implements  ZipCodeServer, stubInterface{

    RemoteObjectRef ror;
    @Override
    public void initialise(ZipCodeList newlist) {
        // TODO Auto-generated method stub
        Object ret;
        Object[] argv = {newlist};
        Class<?>[] argType = new Class<?>[argv.length];
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
        }
        try {
            invoke("initialize", argv, argType);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
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
        try {
            ret = (ZipCodeList)invoke("findAll",argv,null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void printAll() {
        // TODO Auto-generated method stub
        ZipCodeList ret = null;
        Object[] argv = {};
        try {
            ret = (ZipCodeList)invoke("printAll",argv,null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(ret != null){
            ret.print();
            ret = ret.getNext();
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
            throw(new RemoteException(rplMessage.getExceptionCause()));
        }
        return null;
    }
    
    

    @Override
    public void setRor(RemoteObjectRef ref) {
        // TODO Auto-generated method stub
        ror = ref;
    }
    
}