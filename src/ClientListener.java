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
			
			int size =Integer.parseInt(inFromServer.readLine());
			System.out.println(size);
			if(size==1) {
				onlineUsers.clear();
			}
			updateOnlineUsersList(size,true);

			ClientSide.updateOnlineUI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} 
		
		
		

	}
		
	}
	
	private void updateOnlineUsersList(int size,boolean isLastClient) {

		int mul;
		if(isLastClient) {
			mul=3;
		}else {
			mul=1;
			size++;
		}
		for (int i=0;i<(size-1)*mul;i++) {
			//System.out.println(i);
		try {
			serverMsg=inFromServer.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		///System.out.println(serverMsg);
		if(!serverMsg.isEmpty() || !serverMsg.equals("")) {
			if(i==0) {
				onlineUsers.clear();
			}
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
			//System.out.println(whatToRead==0);
			if(whatToRead%3==0) {
				System.out.println("Receiveng data");
               // System.out.println("here");
				//System.out.println(client_info.getUserName()+": "+client_info.getIP()+" / "+client_info.getPort()+"\n");
				boolean check=true;
				
				for (int j=0;j<onlineUsers.size();j++) {
					if (client_info.getIP().equals(onlineUsers.get(j).getIP())
						&& client_info.getPort().equals(onlineUsers.get(j).getPort())
						&&client_info.getUserName().equals(onlineUsers.get(j).getUserName()))
					{
						check=false;
					}
				}
				
				if(check) {
				
					onlineUsers.add(client_info);
				}
				
				client_info=new Client_Info();
				
			}
			
			
			serverMsg="";
		}
		
	}
	}
}
