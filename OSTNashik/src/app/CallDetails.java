package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;

public class CallDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	JXDatePicker datePicker;
	String sql1;
	public String source;
	String engiAssigned,date,callStatus;
	int rmaInt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param rmaNo 
	 */
	public CallDetails(String rmaNo,String Source) {		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 482, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final String rmaNumber=rmaNo;
		source=Source;
		if(source.equalsIgnoreCase("Other"))
		{
			source="Other";
		}
		else {
			source="RMA_Master";
		}
		System.out.println(source);
		JLabel lblAssignedTo = new JLabel("Assigned To");
		lblAssignedTo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblAssignedTo.setBounds(29, 92, 106, 20);
		contentPane.add(lblAssignedTo);
		
		String[] engg={"Deepak Patil","Sharad Sonawane","Sujit Chopdar"};
		final JComboBox engiCombo = new JComboBox(engg);
		engiCombo.setBounds(132, 94, 106, 20);
		contentPane.add(engiCombo);
		
		JLabel lblRmaNo = new JLabel("RMA No:");
		lblRmaNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblRmaNo.setBounds(29, 47, 78, 20);
		contentPane.add(lblRmaNo);
		
		JLabel rmaLabel = new JLabel(rmaNumber);
		rmaLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		rmaLabel.setBounds(132, 48, 78, 19);
		contentPane.add(rmaLabel);
		
		JLabel lblCallDetails = new JLabel("Call Details");
		lblCallDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblCallDetails.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblCallDetails.setBounds(202, 13, 89, 14);
		contentPane.add(lblCallDetails);
		
		JLabel lblAssignedDate = new JLabel("Assigned Date");
		lblAssignedDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblAssignedDate.setBounds(29, 145, 89, 20);
		contentPane.add(lblAssignedDate);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(132, 147, 130, 20);
		contentPane.add(datePicker);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				engiAssigned=(String) engiCombo.getSelectedItem();
				Date dateFrom=datePicker.getDate();				
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				date= formatter.format(dateFrom);			
				callStatus="Engineer Desk";				
				try
				{				 
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);
				String sql="Select * From "+source+"";
				PreparedStatement pst=con.prepareStatement(sql);				
			    ResultSet rs=pst.executeQuery();
			    ResultSetMetaData rsm=rs.getMetaData();
			    String rmatype=rsm.getColumnTypeName(4);			   
			    System.out.println(rmatype);
			    switch(rmatype)
			    {
			    case "INTEGER":
			    	rmaInt=Integer.parseInt(rmaNumber);
			    	System.out.println(rmaInt);
			    	sql1="Update "+source+" SET AssignedTo = '"+engiAssigned+"',EnggAssigndate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No="+rmaInt+"";
			    	break;
			    case "VARCHAR":
			    	sql1="Update "+source+" SET AssignedTo = '"+engiAssigned+"',EnggAssigndate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No='"+rmaNumber+"'";
			    	break;			    
			    }
			    Statement st = con.createStatement();
			    st.executeUpdate(sql1);
				System.out.println(source+" is updated For Engineer Assigned");								
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
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnSubmit.setBounds(202, 224, 89, 23);
		contentPane.add(btnSubmit);	
	}
}
	
	
