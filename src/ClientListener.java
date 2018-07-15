import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener implements Runnable {
	private BufferedReader inFromServer;
	private String serverMsg;
	public static boolean stopThread=false;
	public ClientListener(BufferedReader fromServer) {
		inFromServer=fromServer;
	}
	
	@Override
	public void run() {
		while (!stopThread) {
		try {
			serverMsg=inFromServer.readLine();
			Thread.sleep(100);
			if(!serverMsg.isEmpty()) {
				ClientSide.updateUI("Server: "+serverMsg+"\n");
				serverMsg="";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		} 
		
		
		

	}
	}
}
