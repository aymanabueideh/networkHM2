import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client_UDP {

	
	private String Port;
	DatagramPacket sendPacket;
	DatagramSocket clientSocket; 
	byte[] sendData;
	InetAddress IPAddress = null;
	public Client_UDP(String serverPort,String ServerIP,String clientPort) {
				
		  	  this.Port=clientPort;
		  	      try { 
					  clientSocket = new DatagramSocket();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		  	  
		  	      try {
		  	    	  //your IPAddress
					 IPAddress = InetAddress.getByName(ServerIP+"");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		  	  
		  	   
	}
	
	public void sendMsg(String msg) {
	      sendData = new byte[1024]; 
	      sendData = msg.getBytes();
	      //the port is the server port number
	  	     sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, Integer.parseInt(Port)); 
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} 
		
	}
}
