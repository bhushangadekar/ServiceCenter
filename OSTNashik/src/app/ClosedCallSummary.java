package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClosedCallSummary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String call_Status="Closed";
	private JTable table;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClosedCallSummary frame = new ClosedCallSummary();
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
	public ClosedCallSummary() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOpenCallsSummary = new JLabel("Closed Calls Summary");
		lblOpenCallsSummary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblOpenCallsSummary.setBounds(487, 11, 202, 25);
		contentPane.add(lblOpenCallsSummary);		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 53, 1123, 523);
		contentPane.add(scrollPane);		
		table = new JTable();
		table.setRowHeight(23);	
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		table.setRowSelectionAllowed(true);
		ListSelectionModel rowSelectionModel = table.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		try
		{		
		String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection(url);		
		String sql="Select Sr_No,Call_Date,Source,RMA_No,Item_name,Booking_Desc,Customer_name,Customer_address,Customer_contact,Call_Status From RMA_Master WHERE Call_status='Closed' UNION Select Sr_No,Call_Date,Source,RMA_No,Item_name,Booking_Desc,Customer_name,Customer_address,Customer_contact,Call_Status From Other WHERE Call_status='Closed'";
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
		
		JButton btnSubmit = new JButton("OK");		
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnSubmit.setBounds(506, 600, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {					
				dispose();
			}
		});
		
	}
}
