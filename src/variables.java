import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class variables {

	
	
    public static ArrayList <Socket> socketArray = new <Socket>ArrayList() ;
    public static ArrayList <client_socket> clients = new <client_socket>ArrayList() ;
    
    public static String [] IPs = {};
	
	
	public static  String [] getIPs () {
		
		int x = socketArray.size();
		
		  String [] IPs = new String [x];
		  
		  
		  for (int i=0;i<x;i++) {
			  
			  IPs[i]= socketArray.get(i).getLocalAddress().toString();
		
		  }
		  
		
		return IPs;
	}
	
public static  String [] getIPs_ports () {
		
		int x = clients.size();
		//System.out.println("x="+x);
		  String [] IPs = new String [x];
		  
		  
		  for (int i=0;i<x;i++) {
			  
			  client_socket client = clients.get(i);
			  IPs[i]= client.usergetName()+" : "+client.getIP()+" / "+client.getPort();
		
		  }
		  
		return IPs;
	}

public static void sendList() {
	
	int size = clients.size();
	
	
	for (int i=0;i<size;i++) {
	
		
		try {
		
			Socket tmp=socketArray.get(i);
			DataOutputStream outToClient;
			outToClient = new DataOutputStream(tmp.getOutputStream());
			outToClient.writeBytes(size+"\n");
		for (int j=0;j<size;j++) {
		
			//System.out.println(i+"\t"+j+"\t");
			
			if (i!=j) {
				//Socket tmp=socketArray.get(j);
				
				//DataOutputStream outToClient;
			        client_socket person=clients.get(j);
			    	//outToClient = new DataOutputStream(tmp.getOutputStream());
			    	//System.out.println(person.usergetName()+"\t"+person.getIP()+"\t"+person.getPort()+"\t"+i+"\t"+j);
					outToClient.writeBytes(person.usergetName()+"\n");
					outToClient.writeBytes(person.getIP()+"\n");
					outToClient.writeBytes(person.getPort()+"\n");
					
				
				
			}
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	
	
}

	
	
	
}
