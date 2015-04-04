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

public class MaterialRequiredSummary extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String materialStatus="MR";
	private JTable table;
	private JScrollPane scrollPane;
	String rmaText;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MaterialRequiredSummary frame = new MaterialRequiredSummary();
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
	public MaterialRequiredSummary() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOpenCallsSummary = new JLabel("Material Required Summary");
		lblOpenCallsSummary.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		lblOpenCallsSummary.setBounds(487, 11, 302, 25);
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
		//String sql="SELECT Other.RMA_No,Material_Require_Master.VAT FROM Material_Require_Master INNER JOIN Other ON Material_Require_Master.RMA_No = LTRIM(STR(Other.RMA_no))";
		String sql="Select RMA_Master.RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,AssignedTo,Part_No,Part_Name,Part_Quantity,Part_Price,Total From RMA_Master,Material_Require_Master WHERE RMA_Master.RMA_No=Material_Require_Master.RMA_No AND RMA_Master.MaterialRequireStatus='"+materialStatus+"' UNION ALL Select Other.RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,AssignedTo,Part_No,Part_Name,Part_Quantity,Part_Price,Total From Other,Material_Require_Master WHERE Material_Require_Master.RMA_No = LTRIM(STR(Other.RMA_no)) AND Other.MaterialRequireStatus='"+materialStatus+"'";
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
		
		JButton btnSubmit = new JButton("Submit");		
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnSubmit.setBounds(506, 600, 89, 23);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rmaNo=table.getValueAt(table.getSelectedRow(), 0).toString();
				String source=table.getValueAt(table.getSelectedRow(), 2).toString();
				ApproveMaterial am=new ApproveMaterial(rmaNo,source);
				am.setVisible(true);
				dispose();
			}
		});
		
	}
}
