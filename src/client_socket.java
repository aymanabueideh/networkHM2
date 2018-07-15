
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class client_socket extends Thread {
	
	   Socket clientSocket;
	   String sentence;
	   server_side serverUI;
	   String userName,IP;
	   int port ;
	   
	   public client_socket(Socket socket , server_side serverUI) {
		   
		   clientSocket=socket;
		   this.serverUI=serverUI;
	   
	   	}
	
       @Override 
       
       public void run() {
    	   boolean stopConnection=false;
    	   
    	   
    	   
    	   
    	   
    	 while(!stopConnection) {  
    	   try {
			
    		   BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
		
    		   sentence = clientSocket.getLocalAddress().toString();
    		   String msg=inFromClient.readLine();
    		  if(msg.equals(null)||msg.equals("null")) {
    			  throw new Exception();
    		  }
    		  
    		   sentence= "\n"+sentence+"  : "+msg+ "\n";    
    		   serverUI.setText(sentence);
    		   System.out.println(stopConnection);
    		  // System.out.println(sentence);
    		   
    		   
    	   // print it to the text field
    	   
    	   } catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
    	//	   System.out.println(variables.socketArray.size());
    		   variables.socketArray.remove(clientSocket);
    		   variables.clients.remove(this);
    		   // send to other clients 
    		   variables.sendList();
			//System.out.println(variables.socketArray.size());
			serverUI.setList(variables.getIPs_ports());
			
			stopConnection=true;
			
			
		} 
	          
    	   
       }
     
    	 
       }

	public String usergetName() {
		return userName;
	}

	public void setuserName(String name) {
		userName = name;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

       
       
}
