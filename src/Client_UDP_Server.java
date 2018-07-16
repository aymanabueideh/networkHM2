import java.io.*; 
import java.net.*; 

public class Client_UDP_Server implements Runnable {

	private byte[] receiveData,receiveName;
	private DatagramSocket serverSocket;
	boolean test=false;
	public Client_UDP_Server(String clientPort,String clientIp) {
		
		try {
			
			InetAddress IPAddress = InetAddress.getByName(clientIp);
			serverSocket = new DatagramSocket(Integer.parseInt(clientPort),IPAddress);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       
	  
 
	 
      
	}

	@Override
	public void run() {
	      while(!test) 
	        { 
	  
	    	  receiveData = new byte[1024]; 
				DatagramPacket receivePacket = 
		             new DatagramPacket(receiveData, receiveData.length); 
				
		           try {
					serverSocket.receive(receivePacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		           
		           String sentence = new String(receivePacket.getData());
		           if(!sentence.isEmpty() || !sentence.equals(""))
		           ClientSide.updateChatUI(sentence);
		           InetAddress IPAddress = receivePacket.getAddress(); 
		   
		           int port = receivePacket.getPort(); 
		           sentence="";
	           
	    	  
	    	  /*
	    	  receiveData = new byte[1024]; 
	    	  receiveName = new byte[1024]; 
			  
	    	  DatagramPacket receivePacket = 
	          new DatagramPacket(receiveData, receiveData.length); 
			
	    	          DatagramPacket receivePacketName = 
	    	          new DatagramPacket(receiveName, receiveName.length); 
	    			
			  
	           try {
				serverSocket.receive(receivePacketName);
				 String sentence = new String(receivePacketName.getData());
				 
				 serverSocket.receive(receivePacket);
				  sentence = sentence +" : "+new String(receivePacket.getData());
		           if(!sentence.isEmpty() || !sentence.equals(""))
		           ClientSide.updateChatUI(sentence);
		           sentence="";
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	           
	           String sentence = new String(receivePacket.getData());
	           if(!sentence.isEmpty() || !sentence.equals(""))
	           ClientSide.updateChatUI(sentence);
	           InetAddress IPAddress = receivePacket.getAddress(); 
	   
	           int port = receivePacket.getPort(); 
	           sentence="";
	           */
	  	       } 
		
	}
}
