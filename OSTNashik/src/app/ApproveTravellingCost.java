package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ApproveTravellingCost extends JFrame {

	private JPanel contentPane;
	private JTextField approvedDate;
	private JTextField approvedBy;
	private JTextField approvedCost;

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
	public ApproveTravellingCost(String rmaNo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final String rmaNumber=rmaNo;
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBounds(55, 11, 466, 272);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Approve / Reject");
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label.setBounds(29, 92, 106, 20);
		panel.add(label);
		
		String status[]={"Approve","Reject"};
		final JComboBox ApproveCombo = new JComboBox(status);		
		ApproveCombo.setBounds(147, 94, 106, 20);
		panel.add(ApproveCombo);
		
		JLabel label_1 = new JLabel("RMA No:");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_1.setBounds(29, 47, 78, 20);
		panel.add(label_1);
		
		JLabel rmaLabel = new JLabel(rmaNumber);
		rmaLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		rmaLabel.setBounds(147, 48, 78, 19);
		panel.add(rmaLabel);
		
		JLabel lblApproveTravellingCost = new JLabel("Approve Travelling Cost");
		lblApproveTravellingCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblApproveTravellingCost.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblApproveTravellingCost.setBounds(134, 0, 236, 24);
		panel.add(lblApproveTravellingCost);
		
		final JPanel panelBox = new JPanel();
		panelBox.setBounds(21, 123, 285, 117);
		panel.add(panelBox);
		panelBox.setLayout(null);
		
		approvedDate = new JTextField();
		approvedDate.setBounds(126, 11, 106, 20);
		panelBox.add(approvedDate);
		approvedDate.setColumns(10);
		
		approvedBy = new JTextField();
		approvedBy.setBounds(126, 42, 106, 20);
		panelBox.add(approvedBy);
		approvedBy.setColumns(10);
		
		approvedCost = new JTextField();
		approvedCost.setBounds(126, 73, 106, 20);
		panelBox.add(approvedCost);
		approvedCost.setColumns(10);
		
		JLabel label_4 = new JLabel("Approved Date");
		label_4.setBounds(10, 9, 106, 20);
		panelBox.add(label_4);
		label_4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		JLabel lblApprovedBy = new JLabel("Approved By");
		lblApprovedBy.setBounds(10, 40, 106, 20);
		panelBox.add(lblApprovedBy);
		lblApprovedBy.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		JLabel lblApprovedCost = new JLabel("Approved Cost");
		lblApprovedCost.setBounds(10, 71, 106, 20);
		panelBox.add(lblApprovedCost);
		lblApprovedCost.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		ApproveCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String state=(String) ApproveCombo.getSelectedItem();
				switch(state)
				{
				case "Approve":
					panelBox.setVisible(true);
					break;
				case "Reject":
					panelBox.setVisible(false);
					break;
				}
			}
		});
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String state=(String) ApproveCombo.getSelectedItem();
				switch(state)
				{
				case "Approve":
					try
					{
					String ApprovalStatus="Approved";
					String date=approvedDate.getText();
					String ApprovedBy=approvedBy.getText();
					String ApprovedCost=approvedCost.getText();
					String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection(url);
				    Statement st = con.createStatement(); 			
					st.executeUpdate("Update Travel_Master SET Approval_Status='"+ApprovalStatus+"',Approved_By= '"+ApprovedBy+"',ApprovalDate='"+date+"',Approved_Cost='"+ApprovedCost+"' WHERE RMA_No='"+rmaNumber+"' ");
					st.executeUpdate("Update RMA_Master SET  ApprovedTravellingCost='"+ApprovedCost+"' WHERE RMA_No='"+rmaNumber+"' ");
					System.out.println("RMA_Master is updated For Approved Travelling Cost");				  
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
					String ApprovalStatus="Rejected";
					String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection(url);
				    Statement st = con.createStatement(); 			
				    st.executeUpdate("Update Travel_Master SET Approval_Status='"+ApprovalStatus+"' WHERE RMA_No='"+rmaNumber+"' ");
					System.out.println("RMA_Master is updated For No Travelling Cost");				  
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
		button.setBounds(238, 294, 89, 23);
		contentPane.add(button);
		button.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	}
}
