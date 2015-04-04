package app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnggFeedBack extends JFrame {

	private JPanel contentPane;
	private JTextField closeDate;
	private JTextField txtClosed;
	String callStatus,source;
	JXDatePicker datePicker;
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
	public EnggFeedBack(String rmaNo,String Source) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 358);
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
		panel.setBounds(10, 0, 495, 320);
		contentPane.add(panel);
		
		JLabel label_1 = new JLabel("RMA No:");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_1.setBounds(29, 47, 78, 20);
		panel.add(label_1);
		
		JLabel rma_label = new JLabel(rmaNumber);
		rma_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		rma_label.setBounds(167, 48, 78, 19);
		panel.add(rma_label);
		
		JLabel lblEngineerFeedbackOn = new JLabel("Engineer FeedBack On Call");
		lblEngineerFeedbackOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblEngineerFeedbackOn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblEngineerFeedbackOn.setBounds(132, 13, 269, 24);
		panel.add(lblEngineerFeedbackOn);
		
		JLabel lblClosedDate = new JLabel("Closed Date");
		lblClosedDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblClosedDate.setBounds(29, 203, 106, 20);
		panel.add(lblClosedDate);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(167, 205, 160, 20);
		panel.add(datePicker);	
		
		JButton button = new JButton("Submit");		
		button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		button.setBounds(213, 286, 89, 23);
		panel.add(button);
		
		JLabel lblEngineerFeedback = new JLabel("Engineer FeedBack");
		lblEngineerFeedback.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblEngineerFeedback.setBounds(29, 96, 127, 20);
		panel.add(lblEngineerFeedback);
		
		final JTextArea feedback = new JTextArea();
		feedback.setBounds(167, 96, 256, 87);
		panel.add(feedback);
		feedback.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JLabel lblCallStatus = new JLabel("Call Status");
		lblCallStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblCallStatus.setBounds(29, 246, 106, 20);
		panel.add(lblCallStatus);
		
		txtClosed = new JTextField();
		txtClosed.setEditable(false);
		txtClosed.setText("Closed");
		txtClosed.setBounds(167, 248, 160, 20);
		panel.add(txtClosed);
		txtClosed.setColumns(10);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
				String sql = null;
				Date dateFrom=datePicker.getDate();				
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				String date= formatter.format(dateFrom);								
				String enggfeedBack=feedback.getText();
				callStatus="Closed";
				//System.out.println(""+date+"\n"+enggfeedBack+"\n"+callStatus+"\n"+rmaNumber);				
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);
			    Statement st = con.createStatement(); 
			    switch(source)
			    {
			    case "Other":
			    	sql="Update "+source+" SET ClosedDate='"+date+"',EngineerFeedBack='"+enggfeedBack+"',Call_Status='"+callStatus+"' WHERE RMA_No="+rmaNumber+" ";
			    	break;
			    case "RMA_Master":
			    	sql="Update "+source+" SET ClosedDate='"+date+"',EngineerFeedBack='"+enggfeedBack+"',Call_Status='"+callStatus+"' WHERE RMA_No='"+rmaNumber+"' ";
			    	break;
			    }
			    st.executeUpdate(sql);
				System.out.println(source+" is updated For Call Closing");				  
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
