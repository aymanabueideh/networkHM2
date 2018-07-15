
public class Client_Info {
	private String userName;
	private String IP;
	private String Port;
	
	public Client_Info(String userName, String iP, String port) {
		super();
		this.userName = userName;
		IP = iP;
		Port = port;
	}
	
	public Client_Info() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}


	
	
}
