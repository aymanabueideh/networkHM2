import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Window.Type;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientSide extends JFrame {

	private JPanel contentPane;
	private JTextField sentMsg;
	private JTextField serverIP;
	private JTextField serverPort;
	private JLabel lblServerIp;
	private JLabel lblServerPort;
	private JLabel connect;
	private JLabel send;
	private ArrayList<String> clientMsgs;
	private ArrayList<String> serverMsgs;
	private Socket clientSocket;
	private DataOutputStream toServer;
	private BufferedReader inFromServer;
	private final static JTextArea receivedMsg = new JTextArea();
	private JLabel disconnect;
	private Thread threadX;
	private JLabel lblClientIp;
	private JTextField textField;
	private JLabel lblClientPort;
	private JTextField textField_1;
	private JLabel lblNewLabel;
	private String username="";
	private JLabel lblWelcomeBackUsername;
	public static ArrayList<Client_Info> onlineUsers=new ArrayList<Client_Info>();
	private static JList list;
	private static JScrollPane scrollPane;
	private Client_UDP client_udp;
	private Client_UDP_Server client_udp_server;
	private JButton btnServ;
	public JLabel getDisconnect() {
		return disconnect;
	}

	public JLabel getConnect() {
		return connect;
	}

	public JLabel getSend() {
		return send;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientSide frame = new ClientSide();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientSide() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 835, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		sentMsg = new JTextField();
		sentMsg.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		sentMsg.setToolTipText("Send Message ...");
		sentMsg.setText("Send Message ...");
		sentMsg.setBounds(201, 387, 568, 32);
		contentPane.add(sentMsg);
		sentMsg.setColumns(10);
		
				
		send = new JLabel("");
		send.setIcon(new ImageIcon("Images/Send.png"));
		send.setBounds(781, 387, 24, 24);
		contentPane.add(send);
		


		disconnect = new JLabel("");
		disconnect.setIcon(new ImageIcon("Images/disconnect.png"));
		disconnect.setBounds(649, 432, 64, 64);
		contentPane.add(disconnect);
		
		serverIP = new JTextField();
		serverIP.setBounds(290, 432, 116, 22);
		contentPane.add(serverIP);
		serverIP.setColumns(10);
		
		serverPort = new JTextField();
		serverPort.setBounds(290, 467, 116, 22);
		contentPane.add(serverPort);
		serverPort.setColumns(10);
		
		lblServerIp = new JLabel("Server IP");
		lblServerIp.setBounds(201, 432, 56, 16);
		contentPane.add(lblServerIp);
		
		lblServerPort = new JLabel("Server Port");
		lblServerPort.setBounds(202, 467, 76, 16);
		contentPane.add(lblServerPort);
		
		connect = new JLabel("");
		connect.setBounds(745, 432, 60, 60);
		contentPane.add(connect);
		connect.setIcon(new ImageIcon("Images/Connect.png"));
		receivedMsg.setWrapStyleWord(true);
		receivedMsg.setLineWrap(true);
		receivedMsg.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		receivedMsg.setBounds(201, 23, 604, 351);
		contentPane.add(receivedMsg);
		
		lblNewLabel =  new JLabel("Users Online: 5");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(12, 470, 93, 16);
		contentPane.add(lblNewLabel);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 23, 189, 479);
		contentPane.add(scrollPane);
		
		 list = new JList();
		list.setBackground(new Color(102, 153, 255));
		scrollPane.setViewportView(list);
		
		lblClientIp = new JLabel("My IP");
		lblClientIp.setBounds(422, 432, 56, 16);
		contentPane.add(lblClientIp);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(511, 432, 116, 22);
		contentPane.add(textField);
		
		lblClientPort = new JLabel("My Port");
		lblClientPort.setBounds(423, 467, 76, 16);
		contentPane.add(lblClientPort);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(511, 467, 116, 22);
		contentPane.add(textField_1);
		
		JButton btnConnectToServer = new JButton("Connect to Client");
		btnConnectToServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				String clientServerPort;
//				String value=list.getSelectedValue().toString();
//				clientServerPort= value.substring(value.indexOf("+"));
				client_udp=new Client_UDP(serverIP.getText().toString(),textField.getText().toString(),textField_1.getText().toString());

			}
		});
		btnConnectToServer.setBounds(309, 0, 169, 25);
		contentPane.add(btnConnectToServer);
		
		btnServ = new JButton("serv");
		btnServ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 client_udp_server=new Client_UDP_Server(textField_1.getText().toString());
				 Thread threadY=new Thread(client_udp_server);
				 threadY.start();
			}
		});
		btnServ.setBounds(672, 0, 97, 25);
		contentPane.add(btnServ);
		
		
		send.addMouseListener(new MouseHandler());
		connect.addMouseListener(new MouseHandler());
		disconnect.addMouseListener(new MouseHandler());
		
		clientMsgs=new ArrayList<String>();
		serverMsgs=new ArrayList<String>();
//		
//		username=JOptionPane.showInputDialog("Please Enter your Username to Continue ?");
//		
//		
//		lblWelcomeBackUsername = new JLabel("Welcome Back Username");
//		lblWelcomeBackUsername.setFont(new Font("Bauhaus 93", Font.PLAIN, 14));
//		lblWelcomeBackUsername.setBounds(12, 5, 723, 16);
//		lblWelcomeBackUsername.setText("Welcome Back "+username);
//		contentPane.add(lblWelcomeBackUsername);

	}
	
	private class MouseHandler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(getSend())) {
				sendMessage(sentMsg.getText());
				
			}else if(e.getSource().equals(getConnect())) {
				if(establishServerConnection(serverIP.getText(),serverPort.getText())) {
					connect.setIcon(new ImageIcon("Images/Connected.png"));
				}
				
			}else if(e.getSource().equals(getDisconnect())){
				closeConnection();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public boolean establishConnection(String serverIP,String serverPort,String clientIP,String clientPort) {
		if(establishServerConnection(serverIP,serverPort)&&establishClientConnection(clientIP,clientPort)) {
			return true;
		}
		return false;
	}
	
	private boolean establishClientConnection(String clientIP, String clientPort) {
		return false;
		
	}

	private boolean establishServerConnection(String serverIP, String serverPort) {
		try {
			clientSocket=new Socket(serverIP,Integer.parseInt(serverPort));
			toServer=new DataOutputStream(clientSocket.getOutputStream());
			
			String name= "user"+(int)(Math.random()*1000);
			String Ipaddress=textField.getText();
			String port=textField_1.getText();
			
			toServer.writeBytes(name+'\n');
			toServer.writeBytes(Ipaddress+'\n');
			toServer.writeBytes(port+'\n');
			
			if(clientSocket.isConnected()) {
			inFromServer= new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			threadX=new Thread(new ClientListener(inFromServer,onlineUsers,name));
			threadX.start();
			return true;
			}
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "error ! \n check your IP or server port number");
			
		} 
		return false;
	}

	public boolean closeConnection() {
		try {
			if(!clientSocket.isClosed()) {
			clientSocket.close();
			ClientListener.stopThread=true;
			
			return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void sendMessage(String Msg) {
		client_udp.sendMsg(Msg);
		
	}
	
	public static void updateChatUI(String msg) {
		msg=msg.trim();
		receivedMsg.append(msg.toString()+"\n");
		
	}

	public static void updateOnlineUI() {
		String [] users=new String[onlineUsers.size()];
		
		for(int i=0;i<users.length;i++) {
			users[i]=onlineUsers.get(i).getUserName()+": "+onlineUsers.get(i).getPort()+" / "+onlineUsers.get(i).getPort()+"\n";
			
		}
		list=new JList(users);
		scrollPane.setViewportView(list);
	}
}
