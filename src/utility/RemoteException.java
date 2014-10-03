package utility;
public class RemoteException extends Exception{
    private String exception;
    public RemoteException(String rmtException){
        exception = rmtException;
    }
    
}
