package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;


public class Main extends JFrame {

	/**
	 * 
	 */	
	private static final long serialVersionUID = 6251978908793691071L;
	private JPanel contentPane;
	private JTextField RMA_No;
	private JLabel lblSource;
	private JComboBox sourcecombo;
	private JLabel lblItemName;
	private JTextField Item_name;
	private JLabel lblItemMag;
	private JTextField itemMAG;
	private JLabel lblItemDetails;
	private JLabel lblItemModel;
	private JTextField itemModel;
	private JLabel lblItemSrno;
	private JTextField itemSrNo;
	private JLabel lblBookingDescription;
	private JLabel lblCustomerDetails;
	private JLabel lblCustomerName;
	private JTextField customerName;
	private JLabel lblAddress;
	private JLabel lblContactNo;
	private JTextField contact;
	private JLabel lblEmailId;
	private JTextField email;
	private JTextArea bookingDesc;
	JXDatePicker datePicker;
	
	//variable
	String calldate,sql;
	String modeState;
	String Source1="",table="";	
	String rmaNo;
	String itemName;
	String item_MAG;
	String item_Model;
	String item_Sr_No;				
	String Booking_Desc;
	String customer_Name;
	String customer_Address;
	String customer_contact;
	String customer_email;
	String call_Status;
	private JLabel lblStatus;
	private JTextField txtOpen;
	private JLabel label_1;
	private JComboBox callModeCombo;
	String callMode="";
	String calltype="";
	String callType="";
	private JTextField serviceCharges;
	private JLabel lblRs;
	private JLabel lblServiceTax;
	private JTextField jServiceTax;
	private JTextField jTotalCharges;
	private JLabel label_2;
	private JLabel label_3;
	int serviceCharges1;
	double Servicetax;
	double totalcost=0;
	int rmaNoInt;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main frame = new Main();					
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
	public Main() {
		setTitle("F1 Services & Solutions");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1100, 700);		
		contentPane = new JPanel();
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	

		
		JLabel lblCallDate = new JLabel("Call Date");
		lblCallDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblCallDate.setBounds(10, 45, 75, 19);
		contentPane.add(lblCallDate);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(113, 42, 173, 22);
		contentPane.add(datePicker);		
		
		final JLabel lblRmaNo = new JLabel("RMA No.");
		lblRmaNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblRmaNo.setBounds(803, 47, 75, 14);
		contentPane.add(lblRmaNo);
		
		RMA_No = new JTextField();
		RMA_No.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		RMA_No.setBounds(888, 44, 143, 20);
		contentPane.add(RMA_No);
		RMA_No.setColumns(10);
		
		
		lblSource = new JLabel("Source");
		lblSource.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblSource.setBounds(439, 47, 58, 14);
		contentPane.add(lblSource);
		
		String[] source={"F1 Smart","Asus","DLink","Other"};
		sourcecombo = new JComboBox(source);		
		sourcecombo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		sourcecombo.setBounds(507, 44, 86, 20);
		contentPane.add(sourcecombo);
		
		lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblItemName.setBounds(10, 170, 71, 14);
		contentPane.add(lblItemName);
		
		Item_name = new JTextField();
		Item_name.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		Item_name.setBounds(113, 167, 206, 20);
		contentPane.add(Item_name);
		Item_name.setColumns(10);
		
		lblItemMag = new JLabel("Item MAG");
		lblItemMag.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblItemMag.setBounds(10, 216, 71, 14);
		contentPane.add(lblItemMag);
		
		itemMAG = new JTextField();
		itemMAG.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		itemMAG.setBounds(113, 213, 206, 20);
		contentPane.add(itemMAG);
		itemMAG.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 124, 1064, 2);
		contentPane.add(separator);
		
		lblItemDetails = new JLabel("Item Details:");
		lblItemDetails.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblItemDetails.setForeground(Color.RED);
		lblItemDetails.setBounds(10, 128, 93, 17);
		contentPane.add(lblItemDetails);
		
		lblItemModel = new JLabel("Item Model");
		lblItemModel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblItemModel.setBounds(10, 264, 75, 14);
		contentPane.add(lblItemModel);
		
		itemModel = new JTextField();
		itemModel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		itemModel.setBounds(113, 261, 206, 20);
		contentPane.add(itemModel);
		itemModel.setColumns(10);
		
		lblItemSrno = new JLabel("Item Sr.No.");
		lblItemSrno.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblItemSrno.setBounds(10, 314, 75, 14);
		contentPane.add(lblItemSrno);
		
		itemSrNo = new JTextField();
		itemSrNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		itemSrNo.setBounds(113, 311, 206, 20);
		contentPane.add(itemSrNo);
		itemSrNo.setColumns(10);
		
		lblBookingDescription = new JLabel("Booking Desc.");
		lblBookingDescription.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblBookingDescription.setBounds(10, 362, 93, 14);
		contentPane.add(lblBookingDescription);
		
		
		lblCustomerDetails = new JLabel("Customer Details :");
		lblCustomerDetails.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblCustomerDetails.setForeground(Color.RED);
		lblCustomerDetails.setBounds(586, 130, 129, 14);
		contentPane.add(lblCustomerDetails);
		
		lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblCustomerName.setBounds(586, 168, 107, 19);
		contentPane.add(lblCustomerName);
		
		customerName = new JTextField();
		customerName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		customerName.setBounds(717, 167, 217, 20);
		contentPane.add(customerName);
		customerName.setColumns(10);
		
		lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblAddress.setBounds(586, 216, 67, 14);
		contentPane.add(lblAddress);
		
		lblContactNo = new JLabel("Contact No.");
		lblContactNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblContactNo.setBounds(586, 314, 93, 14);
		contentPane.add(lblContactNo);
		
		contact = new JTextField();
		contact.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		contact.setBounds(717, 311, 217, 20);
		contentPane.add(contact);
		contact.setColumns(10);
		
		lblEmailId = new JLabel("Email ID ");
		lblEmailId.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblEmailId.setBounds(586, 362, 75, 14);
		contentPane.add(lblEmailId);
		
		email = new JTextField();
		email.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		email.setBounds(717, 359, 217, 20);
		contentPane.add(email);
		email.setColumns(10);
		
		JButton btnRegister = new JButton("Register");		
		btnRegister.setBounds(472, 614, 89, 23);
		contentPane.add(btnRegister);
		
		final JTextArea address = new JTextArea();
		address.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		address.setLineWrap(true);
		address.setBounds(717, 213, 217, 74);
		address.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		contentPane.add(address);
		
		bookingDesc = new JTextArea();
		bookingDesc.setBackground(Color.WHITE);
		bookingDesc.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		bookingDesc.setLineWrap(true);	
		bookingDesc.setBounds(113, 359, 206, 95);
		bookingDesc.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		contentPane.add(bookingDesc);		
		
		JLabel lblNewCallSummary = new JLabel("New Call Entry");
		lblNewCallSummary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblNewCallSummary.setBounds(10, 11, 129, 22);
		contentPane.add(lblNewCallSummary);
		
		lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		lblStatus.setBounds(10, 563, 46, 14);
		contentPane.add(lblStatus);
		
		txtOpen = new JTextField();
		txtOpen.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
		txtOpen.setEditable(false);
		txtOpen.setText("Open");
		txtOpen.setBounds(113, 558, 206, 23);
		contentPane.add(txtOpen);
		txtOpen.setColumns(10);
		
		JLabel label = new JLabel("Call Type");
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label.setBounds(586, 404, 59, 15);
		contentPane.add(label);
		
		String[] type={"Within Warranty","Out Of Warranty"};
		final JComboBox callTypeCombo = new JComboBox(type);		
		callTypeCombo.setBounds(717, 403, 217, 20);
		contentPane.add(callTypeCombo);
		
		label_1 = new JLabel("Call Mode");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_1.setBounds(586, 562, 78, 15);
		contentPane.add(label_1);
		
		String[] mode={"Carry In","On Site"};
		callModeCombo = new JComboBox(mode);
		
		callModeCombo.setBounds(717, 561, 217, 20);
		contentPane.add(callModeCombo);
		
		final JPanel panelServiceBox = new JPanel();
		panelServiceBox.setBounds(578, 430, 418, 109);
		contentPane.add(panelServiceBox);
		panelServiceBox.setLayout(null);
		panelServiceBox.setVisible(false);
		
		JLabel lblServiceCharges = new JLabel("Service Charges");
		lblServiceCharges.setBounds(10, 11, 107, 19);
		panelServiceBox.add(lblServiceCharges);
		lblServiceCharges.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		serviceCharges = new JTextField();
		serviceCharges.setBounds(137, 12, 217, 20);
		panelServiceBox.add(serviceCharges);
		serviceCharges.setColumns(10);
		
		lblRs = new JLabel("Rs.");
		lblRs.setBounds(362, 13, 46, 14);
		panelServiceBox.add(lblRs);
		lblRs.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		lblServiceTax = new JLabel("Service Tax");
		lblServiceTax.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblServiceTax.setBounds(10, 41, 107, 19);
		panelServiceBox.add(lblServiceTax);
		
		jServiceTax = new JTextField();
		jServiceTax.setEditable(false);
		jServiceTax.setText("14");
		jServiceTax.setBounds(137, 42, 217, 20);
		panelServiceBox.add(jServiceTax);
		jServiceTax.setColumns(10);
		
		jTotalCharges = new JTextField();
		jTotalCharges.setBounds(137, 74, 217, 20);
		panelServiceBox.add(jTotalCharges);
		jTotalCharges.setColumns(10);
		
		label_2 = new JLabel("Rs.");
		label_2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_2.setBounds(362, 75, 46, 14);
		panelServiceBox.add(label_2);
		
		label_3 = new JLabel("%");
		label_3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_3.setBounds(362, 43, 46, 14);
		panelServiceBox.add(label_3);		
		
		JButton btnTotalCharges = new JButton("Total Charges");		
		btnTotalCharges.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnTotalCharges.setBounds(0, 71, 127, 23);
		panelServiceBox.add(btnTotalCharges);
		
		sourcecombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Source1=(String) sourcecombo.getSelectedItem();	
				switch(Source1)
				{
				case "F1 Smart":
					table="RMA_Master";	
					lblRmaNo.setVisible(true);
					RMA_No.setVisible(true);
					break;
				case "Asus":
					table="RMA_Master";	
					lblRmaNo.setVisible(true);
					RMA_No.setVisible(true);
					break;
				case "DLink":
					table="RMA_Master";	
					lblRmaNo.setVisible(true);
					RMA_No.setVisible(true);
					break;
				case "Other":
					table="Other";
					lblRmaNo.setVisible(false);
					RMA_No.setVisible(false);
					try
					{
					String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection(url);
				    Statement st = con.createStatement();  		    
				    String sql2 = "SELECT TOP 1 RMA_No FROM Other ORDER BY RMA_No DESC";
					ResultSet rs = st.executeQuery(sql2);
					while(rs.next())
					 {
						 rmaNoInt=rs.getInt("RMA_No")+1;	
						 rmaNo=String.valueOf(rmaNoInt);
					 }		    
					rs.close();
					st.close();
			        con.close();		        
					}
					catch(Exception ex)
				    {
						
						System.err.print("Exception: ");
						System.err.println(ex.getMessage());
					}
					break;
				}	
			}
		});
		
		callTypeCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String callType=(String) callTypeCombo.getSelectedItem();
				switch(callType)
				{
				case "Within Warranty":
					panelServiceBox.setVisible(false);
					break;
				case "Out Of Warranty":
					panelServiceBox.setVisible(true);
					break;
				}
				
			}
		});
		
		
		btnTotalCharges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				serviceCharges1=Integer.parseInt(serviceCharges.getText());
				Servicetax=(serviceCharges1*14)/100;
				totalcost=serviceCharges1+Servicetax;
				jTotalCharges.setText(String.valueOf(totalcost));
			}
		});	
			
		callModeCombo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				modeState=(String) callModeCombo.getSelectedItem();
				if(modeState.equals("On Site"))
				{
					Source1=(String) sourcecombo.getSelectedItem();	
					switch(Source1)
					{
					case "F1 Smart":
						rmaNo=RMA_No.getText();
						break;
					case "Asus":
						rmaNo=RMA_No.getText();
						break;
					case "DLink":
						rmaNo=RMA_No.getText();
						break;
					case "Other":
						table="Other";						
						try
						{
						String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection(url);
					    Statement st = con.createStatement();  		    
					    String sql2 = "SELECT TOP 1 RMA_No FROM Other ORDER BY RMA_No DESC";
						ResultSet rs = st.executeQuery(sql2);
						while(rs.next())
						 {
							 rmaNoInt=rs.getInt("RMA_No")+1;	
							 rmaNo=String.valueOf(rmaNoInt);
						 }		    
						rs.close();
						st.close();
				        con.close();		        
						}
						catch(Exception ex)
					    {
							System.err.print("Exception: ");
							System.err.println(ex.getMessage());
						}
						break;						
					}
					OtherCharges oc=new OtherCharges(rmaNo);
					oc.setVisible(true);
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				Date dateFrom=datePicker.getDate();				
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				calldate= formatter.format(dateFrom);	
				System.out.println(calldate);							
				Source1=(String) sourcecombo.getSelectedItem();							
				itemName=Item_name.getText();
				item_MAG=itemMAG.getText();
				item_Model=itemModel.getText();
				item_Sr_No=itemSrNo.getText();				
				Booking_Desc=bookingDesc.getText();
				customer_Name=customerName.getText();
				customer_Address=address.getText();
				customer_contact=contact.getText();
				customer_email=email.getText();	
				call_Status=txtOpen.getText();
				callMode=(String) callModeCombo.getSelectedItem();
				calltype=(String) callTypeCombo.getSelectedItem();
				System.out.println(table);			   
				switch(Source1)
				{
				case "F1 Smart":
					rmaNo=RMA_No.getText();
					sql = "insert into RMA_Master(Call_Date,Source,RMA_No,Item_name,Item_MAG,Item_model,Item_SrNo,Booking_Desc,Customer_name,Customer_address,Customer_contact,Customer_email,CallType,CallMode,Call_Status,TotalServiceCharges) values ('"+calldate+"','"+Source1+"','"+rmaNo+"','"+itemName+"','"+item_MAG+"','"+item_Model+"','"+item_Sr_No+"','"+Booking_Desc+"','"+customer_Name+"','"+customer_Address+"','"+customer_contact+"','"+customer_email+"','"+calltype+"','"+callMode+"','"+call_Status+"','"+totalcost+"')";			   
					break;
				case "Asus":
					rmaNo=RMA_No.getText();
					sql = "insert into RMA_Master(Call_Date,Source,RMA_No,Item_name,Item_MAG,Item_model,Item_SrNo,Booking_Desc,Customer_name,Customer_address,Customer_contact,Customer_email,CallType,CallMode,Call_Status,TotalServiceCharges) values ('"+calldate+"','"+Source1+"','"+rmaNo+"','"+itemName+"','"+item_MAG+"','"+item_Model+"','"+item_Sr_No+"','"+Booking_Desc+"','"+customer_Name+"','"+customer_Address+"','"+customer_contact+"','"+customer_email+"','"+calltype+"','"+callMode+"','"+call_Status+"','"+totalcost+"')";			   
					break;
				case "DLink":
					rmaNo=RMA_No.getText();
					sql = "insert into RMA_Master(Call_Date,Source,RMA_No,Item_name,Item_MAG,Item_model,Item_SrNo,Booking_Desc,Customer_name,Customer_address,Customer_contact,Customer_email,CallType,CallMode,Call_Status,TotalServiceCharges) values ('"+calldate+"','"+Source1+"','"+rmaNo+"','"+itemName+"','"+item_MAG+"','"+item_Model+"','"+item_Sr_No+"','"+Booking_Desc+"','"+customer_Name+"','"+customer_Address+"','"+customer_contact+"','"+customer_email+"','"+calltype+"','"+callMode+"','"+call_Status+"','"+totalcost+"')";			   
					break;
				case "Other":					
					sql = "insert into Other(Call_Date,Source,RMA_No,Item_name,Item_MAG,Item_model,Item_SrNo,Booking_Desc,Customer_name,Customer_address,Customer_contact,Customer_email,CallType,CallMode,Call_Status,TotalServiceCharges) values ('"+calldate+"','"+Source1+"','"+rmaNo+"','"+itemName+"','"+item_MAG+"','"+item_Model+"','"+item_Sr_No+"','"+Booking_Desc+"','"+customer_Name+"','"+customer_Address+"','"+customer_contact+"','"+customer_email+"','"+calltype+"','"+callMode+"','"+call_Status+"','"+totalcost+"')";			   
					break;
				}	
				try
				{
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);
			    Statement st = con.createStatement();  		    
			    st.executeUpdate(sql);				
		        st.close();
		        con.close();		        
				}
				catch(Exception ex)
			    {
					System.err.print("Exception: ");
					System.err.println(ex.getMessage());					
				}					
				dispose();				
			} 
		});	
	}	
}
