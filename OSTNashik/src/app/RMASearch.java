package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RMASearch extends JFrame {

	private JPanel contentPane;
	private JTextField rmaNo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RMASearch frame = new RMASearch();
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
	public RMASearch() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchByRma = new JLabel("Search By RMA Number");
		lblSearchByRma.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblSearchByRma.setBounds(475, 11, 198, 22);
		contentPane.add(lblSearchByRma);
		
		JLabel lblEnterRmaNumber = new JLabel("Enter  RMA Number");
		lblEnterRmaNumber.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblEnterRmaNumber.setBounds(10, 71, 148, 22);
		contentPane.add(lblEnterRmaNumber);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 1064, 451);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("View Details");		
		btnNewButton.setBounds(506, 593, 137, 23);
		contentPane.add(btnNewButton);
		
		rmaNo = new JTextField();
		rmaNo.setBounds(153, 74, 120, 20);
		contentPane.add(rmaNo);
		rmaNo.setColumns(10);
		rmaNo.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				String rma=rmaNo.getText();
				try
				{		
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);		
				String sql="SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM RMA_Master WHERE RMA_No LIKE '"+rma+"%' UNION ALL SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM Other WHERE RMA_No LIKE '"+rma+"%'";
				PreparedStatement pst=con.prepareStatement(sql);
			    ResultSet rs=pst.executeQuery();	
			    table.setModel(DbUtils.resultSetToTableModel(rs));	
			    rs.close();
		        pst.close();
		        con.close();
				}
				catch(Exception ex)
			    {
					System.err.print("Exception: ");
					System.err.println(ex.getMessage());
			    }
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				String rma=rmaNo.getText();
				try
				{		
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);		
				String sql="SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM RMA_Master WHERE RMA_No LIKE '"+rma+"%' UNION ALL SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM Other WHERE RMA_No LIKE '"+rma+"%'";
				PreparedStatement pst=con.prepareStatement(sql);
			    ResultSet rs=pst.executeQuery();	
			    table.setModel(DbUtils.resultSetToTableModel(rs));	
			    rs.close();
		        pst.close();
		        con.close();
				}
				catch(Exception ex)
			    {
					System.err.print("Exception: ");
					System.err.println(ex.getMessage());
			    }
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				String rma=rmaNo.getText();
				try
				{		
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);		
				String sql="SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM RMA_Master WHERE RMA_No LIKE '"+rma+"%' UNION ALL SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,AssignedTo,Call_Status FROM Other WHERE RMA_No LIKE '"+rma+"%'";
				PreparedStatement pst=con.prepareStatement(sql);
			    ResultSet rs=pst.executeQuery();	
			    table.setModel(DbUtils.resultSetToTableModel(rs));	
			    rs.close();
		        pst.close();
		        con.close();
				}
				catch(Exception ex)
			    {
					System.err.print("Exception: ");
					System.err.println(ex.getMessage());
			    }
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String rmaNo=table.getValueAt(table.getSelectedRow(), 0).toString();
				String source=table.getValueAt(table.getSelectedRow(), 2).toString();
				RMADetails rd=new RMADetails(rmaNo,source);
				rd.setVisible(true);
			}
		});
		
		
	}
}
