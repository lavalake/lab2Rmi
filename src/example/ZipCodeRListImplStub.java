package example;

import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import server.RemoteObjectRef;
import utility.RemoteException;
import example.ZipCodeRList;

public class ZipCodeRListImplStub 
    implements  ZipCodeRList
{
    RemoteObjectRef ror;
    // finding the zip code only for that cell.
    // its client can implement recursive search.
    public String find(String c)
    {
        String ret = null;
        Object[] argv = {c};
        Class<?>[] argType = new Class<?>[argv.length];
        
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            ret = (String)invoke("find", argv, argType);
        } catch (RemoteException e) {
            
            return null;
        }
        return ret;
    }

    private Object invoke(String command, Object[] argv, Class<?>[] argType) throws RemoteException {
        
        
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
            System.out.println(rplMessage.getExceptionCause());
            throw(new RemoteException(rplMessage.getExceptionCause()));
        }
        
        comm.CloseSocket();
        return null;
        
    }

    // this is essentially cons.
    public ZipCodeRList add(String c, String z)
    {
        ZipCodeRList ret = null;
        RemoteObjectRef ror=null;
        Object[] argv = {c,z};
        Class<?>[] argType = new Class<?>[argv.length];
        
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            
        }
        try {
            ror = (RemoteObjectRef)invoke("add", argv, argType);
            ret = (ZipCodeRList) ror.localise();
        } catch (RemoteException e) {
            
            e.printStackTrace();
        }
        return ret;
    
    }

    // this is essentially car.
    public ZipCodeRList next()
    {
        ZipCodeRList ret = null;
        RemoteObjectRef ror;
        System.out.println("next");
        
        try {
            ror = (RemoteObjectRef)invoke("next", null,null);
            ret = (ZipCodeRList) ror.localise();
            
        } catch (RemoteException e) {
            
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void setRor(RemoteObjectRef ref) {
        
        ror = ref;
    }

    @Override
    public RemoteObjectRef getRor() {
        
        return null;
    }

    
}

