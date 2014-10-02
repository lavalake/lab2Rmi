/*  Communication Module responsible for sending the RMIMessage 
 *  Author by Yifan Li
 *  Date: 10/1/2014
 */
package comm;
public class CommModule{
    private String ipAdr;
    private int port;
    
    public CommModule(String ip,int port){
    	this.setIpAdr(ip);
    	this.setPort(port);
    }

	public String getIpAdr() {
		return ipAdr;
	}

	public void setIpAdr(String ipAdr) {
		this.ipAdr = ipAdr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
    
	public boolean send_msg(RMIMessage msg){
		return true;
	}
}