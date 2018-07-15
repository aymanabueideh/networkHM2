import java.io.*; 
import java.net.*; 

public class Client_UDP_Server {

	public Client_UDP_Server(String clientPort) {
		DatagramSocket serverSocket = null;
		try {
			serverSocket = new DatagramSocket(Integer.parseInt(clientPort));
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		  
	      byte[] receiveData = new byte[1024]; 
	  
	      while(true) 
	        { 
	  
	          DatagramPacket receivePacket = 
	             new DatagramPacket(receiveData, receiveData.length); 
	           try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	           String sentence = new String(receivePacket.getData()); 
	           
	           ClientSide.updateChatUI(sentence);
	           InetAddress IPAddress = receivePacket.getAddress(); 
	   
	           int port = receivePacket.getPort(); 

	  	       } 
	 
      
	}
}
