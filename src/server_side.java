import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class server_side extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Server server;
	public JTextArea textArea = new JTextArea();
	JTextArea textArea_1 = new JTextArea();
	JScrollPane scrollPane_1 = new JScrollPane();
	JScrollPane scrollPane = new JScrollPane();
	public JList list = new JList();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server_side frame = new server_side();
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
	
	
	public void setText (String string) {
	
		String str = this.textArea.getText()+string;
		//System.out.println("text area \t"+str);
		
		textArea.setText(str);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane_1.setViewportView(textArea);
		
	}
	
	public void setList (String []str) {
		//System.out.println("here");
		for (int i=0;i<str.length;i++) {
			//System.out.println(str[i]);
		}
		list= new JList(str);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
		
	}
	
	
	public server_side() {
		
		server_side server1=this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1123, 847);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartListing = new JButton("start listening");
		btnStartListing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// intiate server 
				
				int port = Integer.parseInt(textField.getText());
				String IP = textField_1.getText();
				
			//	String ip = comboBox.getSelectedItem().toString();
				
				
				server=new Server(port,IP);
				
				server.setServerUI(server1);
				
				server.start();
				
			}
		});
		btnStartListing.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStartListing.setBounds(29, 33, 194, 53);
		contentPane.add(btnStartListing);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPort.setBounds(238, 33, 69, 53);
		contentPane.add(lblPort);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(311, 33, 225, 53);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Online Clients");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(693, 112, 179, 53);
		contentPane.add(lblNewLabel);
		
		
		scrollPane.setBounds(606, 166, 338, 376);
		contentPane.add(scrollPane);
		
		
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(list);
		
		
		scrollPane_1.setBounds(29, 164, 507, 378);
		contentPane.add(scrollPane_1);
		
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChat.setBounds(238, 128, 69, 20);
		contentPane.add(lblChat);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 631, 507, 114);
		contentPane.add(scrollPane_2);
		textArea_1.setFont(new Font("Monospaced", Font.PLAIN, 20));
		
		
		scrollPane_2.setViewportView(textArea_1);
		
		JLabel lblWriteAMessage = new JLabel("write a message");
		lblWriteAMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblWriteAMessage.setBounds(218, 576, 179, 39);
		contentPane.add(lblWriteAMessage);
		
		JButton btnNewButton = new JButton("Send");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		 
				
				String str = list.getSelectedValue().toString();
				//System.out.println(str+"a");
				String message = textArea_1.getText()+"\n";
			
				textArea.setText( textArea.getText()+ "Server : "+ message);
				textArea_1.setText("");
				Socket socket = null ; 
				
				for (int i=0;i<variables.socketArray.size();i++) {
					//System.out.println(variables.socketArray.get(i).toString()+"a");
					if (variables.socketArray.get(i).getLocalAddress().toString().equals(str)) {
						
						socket = variables.socketArray.get(i);
					
						break;
						
					}
				}
				
			
				try {
					DataOutputStream  outToClient = new DataOutputStream(socket.getOutputStream());
					outToClient.writeBytes(message+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					
				} 
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(606, 651, 142, 68);
		contentPane.add(btnNewButton);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIp.setBounds(606, 33, 69, 53);
		contentPane.add(lblIp);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(679, 33, 225, 53);
		contentPane.add(textField_1);
	}
}
