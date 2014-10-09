package utility;
public class RemoteException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exception;
    public RemoteException(String rmtException){
        setException(rmtException);
    }
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
    
}
