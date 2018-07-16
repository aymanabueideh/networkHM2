import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientListener implements Runnable {
	private BufferedReader inFromServer;
	private String serverMsg;
	public static boolean stopThread=false;
	final int USERNAME=0;
	final int CLIENTIP=1;
	final int CLIENTPORT=2;
	private int whatToRead=0;
	private ArrayList<Client_Info> onlineUsers;
	private Client_Info client_info;
	String name="";
	
	public ClientListener(BufferedReader fromServer,ArrayList<Client_Info> onlineUsers,String name) {
		inFromServer=fromServer;
		this.onlineUsers=onlineUsers;
		client_info=new Client_Info();
		this.name=name;
	}
	
	@Override
	public void run() {
		while (!stopThread) {
		try {
			serverMsg=inFromServer.readLine();
			if(!serverMsg.isEmpty() || !serverMsg.equals("")) {
				switch(whatToRead) {
				case USERNAME:
					client_info.setUserName(serverMsg);
					whatToRead++;
					break;
					
				case CLIENTIP:
					client_info.setIP(serverMsg);
					whatToRead++;
					break;
					
				case CLIENTPORT:
					client_info.setPort(serverMsg);
					whatToRead++;
					break;
					
					default:
						
				}
				whatToRead=(whatToRead)%3 ;
				
				if(whatToRead==0) {
					onlineUsers.add(client_info);
					ClientSide.updateOnlineUI();
				}
				
				
				serverMsg="";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} 
		
		
		

	}
	}
}
