package server;

import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import utility.RemoteException;
import example.ZipCodeRList;

public class ZipCodeRListStub 
    implements  ZipCodeRList, stubInterface
{
    RemoteObjectRef ror;
    // finding the zip code only for that cell.
    // its client can implement recursive search.
    public String find(String c)
    {
        String ret = null;
        Object[] argv = {c};
        try {
            ret = (String)invoke("find", argv);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    private Object invoke(String command, Object[] argv) throws RemoteException {
        
        // TODO Auto-generated method stub
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

    // this is essentially cons.
    public ZipCodeRList add(String c, String z)
    {
    return null;
    }

    // this is essentially car.
    public ZipCodeRList next()
    {
    return null;
    }

    @Override
    public void setRor(RemoteObjectRef ref) {
        // TODO Auto-generated method stub
        
    }
    
}

