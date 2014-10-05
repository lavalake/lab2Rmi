package example;

import comm.CommModule_Client;
import comm.RMIMessage;
import comm.RMIMessage.msgType;
import server.RemoteObjectRef;
import server.stubInterface;
import utility.RemoteException;
import example.ZipCodeRList;

public class ZipCodeRListImplStub 
    implements  ZipCodeRList, stubInterface
{
    RemoteObjectRef ror;
    // finding the zip code only for that cell.
    // its client can implement recursive search.
    public String find(String c)
    {
        String ret = null;
        Object[] argv = {c};
        Class<?>[] argType = new Class<?>[argv.length];
        System.out.println("find");
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            System.out.println("argType "+i+":"+argType[i].toString());
        }
        try {
            ret = (String)invoke("find", argv, argType);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    private Object invoke(String command, Object[] argv, Class<?>[] argType) throws RemoteException {
        
        // TODO Auto-generated method stub
        RMIMessage ivkMessage = new RMIMessage();
        ivkMessage.setType(msgType.INVOKE);
        ivkMessage.setMethodName(command);
        ivkMessage.setArgs(argv);
        ivkMessage.setRor(ror);
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
        return null;
        
    }

    // this is essentially cons.
    public ZipCodeRList add(String c, String z)
    {
        ZipCodeRList ret = null;
        Object[] argv = {c,z};
        Class<?>[] argType = new Class<?>[argv.length];
        System.out.println("add");
        for(int i=0;i<argv.length;i++){
            argType[i] = argv[i].getClass();
            System.out.println("argType "+i+":"+argType[i].toString());
        }
        try {
            ret = (ZipCodeRList)invoke("add", argv, argType);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    
    }

    // this is essentially car.
    public ZipCodeRList next()
    {
        ZipCodeRList ret = null;
        
        System.out.println("next");
        
        try {
            ret = (ZipCodeRList)invoke("next", null,null);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public void setRor(RemoteObjectRef ref) {
        // TODO Auto-generated method stub
        ror = ref;
    }
    
}

