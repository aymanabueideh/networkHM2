import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	ServerSocket welcomeSocket;
	server_side serverUI;
	public Server(int port , String IP ) {
		
		 try {
			 InetAddress IPAddress = InetAddress.getByName(IP); 
			welcomeSocket = new ServerSocket(port,0,IPAddress);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	
	}
	
	public void setServerUI(server_side serverUI) {
		this.serverUI=serverUI;
	}
	
	@Override
	
	public void run() {
		
		while (true) {
		 try {
			
			    
	            //System.out.println(welcomeSocket.getLocalSocketAddress().toString());
	          //  System.out.println();
			 Socket connectionSocket = welcomeSocket.accept();
			 
	          //  System.out.println(connectionSocket.getLocalSocketAddress().toString());
			// System.out.println();
			
			 
			 
			 
			 client_socket client=new client_socket(connectionSocket , serverUI);
			 BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				
			 String name = inFromClient.readLine();
			// System.out.println(name);
	    	   client.setuserName(name);
	    	   
	    	   String IP =inFromClient.readLine();
	    	   client.setIP(IP);
	    	   
	    	   int port = Integer.parseInt(inFromClient.readLine());
	    	   client.setPort(port);
	    	   
	    	   //inFromClient.close();
			 
			   client.start();
			 //add to arraylist
			 
			 //System.out.println(variables.socketArray.size() +"\n");
			 
			 variables.socketArray.add(connectionSocket);
			 variables.clients.add(client);
			// System.out.println(variables.clients.size());
			// System.out.println(variables.socketArray.size() +"\n");
			 // send list 
			 serverUI.setList(variables.getIPs_ports());
			 variables.sendList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
	
	
	
}
