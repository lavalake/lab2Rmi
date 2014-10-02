package server;
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
        invoke("initialize", argv);
        
    }

    @Override
    public String find(String city) {
        // TODO Auto-generated method stub
        String ret;
        Object[] argv = {city};
        ret = (String)invoke("find", argv);
        return ret;
    }

    @Override
    public ZipCodeList findAll() {
        // TODO Auto-generated method stub
        ZipCodeList ret;
        ret = (ZipCodeList)invoke("findAll",null);
        return ret;
    }

    @Override
    public void printAll() {
        // TODO Auto-generated method stub
        ZipCodeList ret;
        ret = (ZipCodeList)invoke("printAll",null);
        while(ret != null){
            ret.print();
            ret = ret.getNext();
        }
        
        
    }
    
    private Object invoke(String command, Object[] argv){
        RMIMessage message = new RMIMessage();
        message.setType(msgType.INVOKE);
        message.setMethodName(command);
        message.setArgs(argv);
        
        CommModule_Client comm = new CommModule_Client(ror.IP_adr, ror.Port);
        
        comm.send_msg(message);
        return null;
    }
    
    public void setRor(RemoteObjectRef ref){
        ror = ref;
    }
    
}