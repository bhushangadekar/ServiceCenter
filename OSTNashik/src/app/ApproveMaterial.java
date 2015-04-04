package app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

public class ApproveMaterial extends JFrame {

	private JPanel contentPane;
	private JTextField assignedDate;
	String materialStatus;
	String callStatus;
	JXDatePicker datePicker;
	String source;
	protected int rmaInt;
	protected String sql1;
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
	 */
	public ApproveMaterial(String rmaNo,String Source) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 323);
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
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(17, 0, 466, 272);
		contentPane.add(panel);
		
		JLabel lblApproveReject = new JLabel("Approve / Reject");
		lblApproveReject.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblApproveReject.setBounds(29, 131, 137, 20);
		panel.add(lblApproveReject);
		
		String status[]={"Approve","Reject"};
		final JComboBox comboBox = new JComboBox(status);
		comboBox.setBounds(166, 133, 144, 20);
		panel.add(comboBox);
		
		JLabel label_1 = new JLabel("RMA No:");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_1.setBounds(29, 80, 78, 20);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel(rmaNumber);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(166, 80, 116, 20);
		panel.add(lblNewLabel);
		
		JLabel lblApproveMaterialRequired = new JLabel("Approve Material Required");
		lblApproveMaterialRequired.setHorizontalAlignment(SwingConstants.CENTER);
		lblApproveMaterialRequired.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblApproveMaterialRequired.setBounds(132, 13, 236, 24);
		panel.add(lblApproveMaterialRequired);
		
		JLabel lblApprovedDate = new JLabel("Approved Date");
		lblApprovedDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblApprovedDate.setBounds(29, 179, 137, 20);
		panel.add(lblApprovedDate);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(166, 181, 144, 20);
		panel.add(datePicker);		
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				String state=(String) comboBox.getSelectedItem();
				String date;
				switch(state)
				{
				case "Approve":
					try
					{
					Date dateFrom=datePicker.getDate();				
					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
					date= formatter.format(dateFrom);	
					System.out.println(date);				
					materialStatus="MRA";
					callStatus="EDMA";				
					String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
					Connection con=DriverManager.getConnection(url);
				    Statement st = con.createStatement(); 
				    switch(source)
				    {
				    case "Other":
				    	rmaInt=Integer.parseInt(rmaNumber);
				    	System.out.println(rmaInt);
				    	sql1="Update "+source+" SET MaterialRequireStatus= '"+materialStatus+"',MaterialAssignedDate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No="+rmaNumber+" ";
				    	break;
				    case "RMA_Master":
				    	sql1="Update "+source+" SET MaterialRequireStatus= '"+materialStatus+"',MaterialAssignedDate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No='"+rmaNumber+"' ";
				    	break;			    
				    }
					st.executeUpdate(sql1);
					System.out.println(source+" is updated For Material Assigned");				  
			        st.close();
			        con.close();		       
					}
					catch(Exception ex)
				    {
						System.err.print("Exception: ");
						System.err.println(ex.getMessage());
				    }	
				break;
				case "Reject":
					try
					{	
					Date dateFrom=datePicker.getDate();				
					SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
					date= formatter.format(dateFrom);	
					System.out.println(date);
					materialStatus="MRR";
					callStatus="Cancelled";				
					String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection(url);
				    Statement st = con.createStatement(); 			
				    switch(source)
				    {
				    case "Other":
				    	rmaInt=Integer.parseInt(rmaNumber);
				    	System.out.println(rmaInt);
				    	sql1="Update "+source+" SET MaterialRequireStatus= '"+materialStatus+"',MaterialAssignedDate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No="+rmaNumber+" ";
				    	break;
				    case "RMA_Master":
				    	sql1="Update "+source+" SET MaterialRequireStatus= '"+materialStatus+"',MaterialAssignedDate='"+date+"',Call_Status='"+callStatus+"' WHERE RMA_No='"+rmaNumber+"' ";
				    	break;			    
				    }
					st.executeUpdate(sql1);
					System.out.println(source+" is updated For Material Assigned");				  
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
				
				dispose();
			}
		});
		button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button.setBounds(193, 238, 89, 23);
		panel.add(button);
		
		
		
	}
}
