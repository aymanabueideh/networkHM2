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
	
	public ClientListener(BufferedReader fromServer,ArrayList<Client_Info> onlineUsers) {
		inFromServer=fromServer;
		this.onlineUsers=onlineUsers;
		client_info=new Client_Info();
	}
	
	@Override
	public void run() {
		while (!stopThread) {
		try {
			serverMsg=inFromServer.readLine();
			Thread.sleep(100);
			if(!serverMsg.isEmpty()) {
				switch(whatToRead) {
				case USERNAME:
					client_info.setUserName(serverMsg);
					break;
					
				case CLIENTIP:
					client_info.setIP(serverMsg);
					break;
					
				case CLIENTPORT:
					client_info.setPort(serverMsg);
					break;
					
					default:
						
				}
				whatToRead=(whatToRead+1)%4;
				if(whatToRead==0) {
					onlineUsers.add(client_info);
				}
				
				ClientSide.updateOnlineUI();
				serverMsg="";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} 
		
		
		

	}
	}
}
