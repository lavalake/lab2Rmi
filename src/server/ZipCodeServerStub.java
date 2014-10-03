package server;
import utility.RemoteException;
import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import example.ZipCodeList;
import example.ZipCodeServer;

public class ZipCodeServerStub implements  ZipCodeServer, stubInterface{

    RemoteObjectRef ror;
    @Override
    public void initialise(ZipCodeList newlist) {
        // TODO Auto-generated method stub
        Object ret;
        Object[] argv = {newlist};
        try {
            invoke("initialize", argv);
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
        try {
            ret = (String)invoke("find", argv);
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
        try {
            ret = (ZipCodeList)invoke("findAll",null);
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
        try {
            ret = (ZipCodeList)invoke("printAll",null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while(ret != null){
            ret.print();
            ret = ret.getNext();
        }
        
        
    }
    
    private Object invoke(String command, Object[] argv) throws RemoteException{
        RMIMessage ivkMessage = new RMIMessage();
        ivkMessage.setType(msgType.INVOKE);
        ivkMessage.setMethodName(command);
        ivkMessage.setArgs(argv);
        
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
    
    public void setRor(RemoteObjectRef ref){
        ror = ref;
    }
    
}