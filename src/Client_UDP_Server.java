import java.io.*; 
import java.net.*; 

public class Client_UDP_Server implements Runnable {

	private byte[] receiveData;
	private DatagramSocket serverSocket;
	boolean test=false;
	public Client_UDP_Server(String clientPort) {
		
		try {
			serverSocket = new DatagramSocket(Integer.parseInt(clientPort));
			
		} catch (SocketException e1) {
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
			if (receivePacket.equals(null)) {
				System.out.println("receivePacket is null");
			}
			if (serverSocket.equals(null)) {
				System.out.println("serverSocket i null");
			}
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
	           
	  	       } 
		
	}
}
