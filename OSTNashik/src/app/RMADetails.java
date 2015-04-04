package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class RMADetails extends JFrame implements Printable {

	private JPanel contentPane;
	private JTextField Jcalldate;
	private JTextField Jsource;
	private JTextField Jitemname;
	private JTextField JitemMAG;
	private JTextField JitemModel;
	private JTextField Jitemsrno;
	private JTextField Jcustname;
	private JTextField Jcontact;
	private JTextField Jcalltype;
	private JTextField Jcallmode;
	private JTextField Jassignedto;
	private JTextField Jmaterialrequired;
	private JTextField Jtravellingcost;
	private JTextField jassigndate;
	private JTextField Jmaterialcost;
	private JTextField japprovedtravelcost;
	private JTextField jcallstatus;
	private JTextField Jclosedate;
	private JTextField email;
	String source,Source;
	private int rmaInt;	
	private String sql1;
	String tabData[][];
	String calldate;	
	String Item_name;
	String Item_MAG;
	String Item_model;
	String Item_SrNo;
	String Booking_Desc;
	String Customer_name;
	String Customer_address;
	String Customer_contact;
	String Customer_email;
	String Call_Status;
	String AssignedTo;
	String CallType;
	String CallMode;
	String Assigndate;
	String MaterialRequired;
	String EngineerFeedBack;
	String TotalMaterialRequiredCost;	
	String materialAssignedDate;
	String ClosedDate;
	private JTable table;

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
	public RMADetails(String rmaNo,String Source) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String rmaNumber=rmaNo;
		this.Source=Source;
		if(Source.equalsIgnoreCase("Other"))
		{
			Source="Other";
		}
		else {
			Source="RMA_Master";
		}
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 1064, 2);
		contentPane.add(separator);
		
		JButton btnExportToExcel = new JButton("Print The Report");		
		btnExportToExcel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnExportToExcel.setBounds(334, 615, 143, 23);
		contentPane.add(btnExportToExcel);
		
		JButton btnExportToMsexcel = new JButton("Export To MS-Excel");		
		btnExportToMsexcel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnExportToMsexcel.setBounds(487, 616, 143, 23);
		contentPane.add(btnExportToMsexcel);
		
		final JPanel PrintPanel = new JPanel();
		PrintPanel.setBounds(10, 11, 1064, 487);
		contentPane.add(PrintPanel);
		PrintPanel.setLayout(null);
		JLabel lblRmaNumber = new JLabel("RMA Number");
		lblRmaNumber.setBounds(10, 24, 88, 20);
		PrintPanel.add(lblRmaNumber);
		lblRmaNumber.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblNewLabel = new JLabel(rmaNumber);
		lblNewLabel.setBounds(108, 24, 98, 20);
		PrintPanel.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel label = new JLabel("Call Date");
		label.setBounds(272, 25, 75, 19);
		PrintPanel.add(label);
		label.setFont(new Font("Arial", Font.BOLD, 13));
		
		Jcalldate = new JTextField();
		Jcalldate.setBounds(387, 23, 143, 22);
		PrintPanel.add(Jcalldate);
		Jcalldate.setBackground(Color.WHITE);
		Jcalldate.setEditable(false);
		Jcalldate.setFont(new Font("Arial", Font.PLAIN, 12));
		Jcalldate.setColumns(10);
		
		JLabel label_1 = new JLabel("Source");
		label_1.setBounds(626, 27, 58, 14);
		PrintPanel.add(label_1);
		label_1.setFont(new Font("Arial", Font.BOLD, 13));
		
		Jsource = new JTextField();
		Jsource.setBounds(774, 24, 143, 20);
		PrintPanel.add(Jsource);
		Jsource.setBackground(Color.WHITE);
		Jsource.setEditable(false);
		Jsource.setFont(new Font("Arial", Font.PLAIN, 12));
		Jsource.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(626, 82, 428, 341);
		PrintPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCallDetails = new JLabel("Call Details");
		lblCallDetails.setForeground(Color.BLACK);
		lblCallDetails.setFont(new Font("Arial", Font.BOLD, 13));
		lblCallDetails.setBounds(10, 0, 107, 19);
		panel_2.add(lblCallDetails);
		
		JLabel lblAssignedTo = new JLabel("Assigned To");
		lblAssignedTo.setFont(new Font("Arial", Font.BOLD, 13));
		lblAssignedTo.setBounds(10, 25, 132, 19);
		panel_2.add(lblAssignedTo);
		
		Jassignedto = new JTextField();
		Jassignedto.setBackground(Color.WHITE);
		Jassignedto.setEditable(false);
		Jassignedto.setFont(new Font("Arial", Font.PLAIN, 12));
		Jassignedto.setColumns(10);
		Jassignedto.setBounds(184, 24, 192, 20);
		panel_2.add(Jassignedto);
		
		JLabel lblAssigndate = new JLabel("Assigndate");
		lblAssigndate.setFont(new Font("Arial", Font.BOLD, 13));
		lblAssigndate.setBounds(10, 55, 132, 19);
		panel_2.add(lblAssigndate);
		
		Jmaterialrequired = new JTextField();
		Jmaterialrequired.setBackground(Color.WHITE);
		Jmaterialrequired.setEditable(false);
		Jmaterialrequired.setFont(new Font("Arial", Font.PLAIN, 12));
		Jmaterialrequired.setColumns(10);
		Jmaterialrequired.setBounds(184, 84, 192, 20);
		panel_2.add(Jmaterialrequired);
		
		JLabel lblMaterialRequired = new JLabel("Material Required");
		lblMaterialRequired.setFont(new Font("Arial", Font.BOLD, 13));
		lblMaterialRequired.setBounds(10, 85, 128, 19);
		panel_2.add(lblMaterialRequired);
		
		Jtravellingcost = new JTextField();
		Jtravellingcost.setBackground(Color.WHITE);
		Jtravellingcost.setEditable(false);
		Jtravellingcost.setFont(new Font("Arial", Font.PLAIN, 12));
		Jtravellingcost.setColumns(10);
		Jtravellingcost.setBounds(184, 148, 192, 20);
		panel_2.add(Jtravellingcost);
		
		JLabel lblEngineerFeedback = new JLabel("Travelling Cost. Estd.");
		lblEngineerFeedback.setFont(new Font("Arial", Font.BOLD, 13));
		lblEngineerFeedback.setBounds(10, 149, 142, 19);
		panel_2.add(lblEngineerFeedback);
		
		jassigndate = new JTextField();
		jassigndate.setBackground(Color.WHITE);
		jassigndate.setEditable(false);
		jassigndate.setFont(new Font("Arial", Font.PLAIN, 12));
		jassigndate.setColumns(10);
		jassigndate.setBounds(184, 54, 192, 20);
		panel_2.add(jassigndate);
		
		JLabel lblTotalMaterialCost = new JLabel("Total Material Cost");
		lblTotalMaterialCost.setFont(new Font("Arial", Font.BOLD, 13));
		lblTotalMaterialCost.setBounds(10, 115, 128, 19);
		panel_2.add(lblTotalMaterialCost);
		
		Jmaterialcost = new JTextField();
		Jmaterialcost.setBackground(Color.WHITE);
		Jmaterialcost.setEditable(false);
		Jmaterialcost.setFont(new Font("Arial", Font.PLAIN, 12));
		Jmaterialcost.setColumns(10);
		Jmaterialcost.setBounds(184, 115, 192, 20);
		panel_2.add(Jmaterialcost);
		
		JLabel lblApprovedTravellingCost = new JLabel("Aproved Travel. Cost");
		lblApprovedTravellingCost.setFont(new Font("Arial", Font.BOLD, 13));
		lblApprovedTravellingCost.setBounds(10, 179, 142, 19);
		panel_2.add(lblApprovedTravellingCost);
		
		japprovedtravelcost = new JTextField();
		japprovedtravelcost.setBackground(Color.WHITE);
		japprovedtravelcost.setEditable(false);
		japprovedtravelcost.setFont(new Font("Arial", Font.PLAIN, 12));
		japprovedtravelcost.setColumns(10);
		japprovedtravelcost.setBounds(184, 178, 192, 20);
		panel_2.add(japprovedtravelcost);
		
		JLabel lblEngineerFeedback_1 = new JLabel("Engineer Feedback");
		lblEngineerFeedback_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblEngineerFeedback_1.setBounds(10, 209, 142, 19);
		panel_2.add(lblEngineerFeedback_1);
		
		JLabel lblClosedDate = new JLabel("Closed Date");
		lblClosedDate.setFont(new Font("Arial", Font.BOLD, 13));
		lblClosedDate.setBounds(10, 297, 142, 19);
		panel_2.add(lblClosedDate);
		
		Jclosedate = new JTextField();
		Jclosedate.setBackground(Color.WHITE);
		Jclosedate.setEditable(false);
		Jclosedate.setFont(new Font("Arial", Font.PLAIN, 12));
		Jclosedate.setColumns(10);
		Jclosedate.setBounds(184, 296, 192, 20);
		panel_2.add(Jclosedate);
		
		JTextArea jenggfeedback = new JTextArea();
		jenggfeedback.setFont(new Font("Arial", Font.PLAIN, 12));
		jenggfeedback.setBounds(184, 210, 192, 75);
		panel_2.add(jenggfeedback);
		jenggfeedback.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 82, 262, 341);
		PrintPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("Item Name");
		label_2.setFont(new Font("Arial", Font.BOLD, 13));
		label_2.setBounds(10, 25, 71, 14);
		panel.add(label_2);
		
		Jitemname = new JTextField();
		Jitemname.setBackground(Color.WHITE);
		Jitemname.setEditable(false);
		Jitemname.setFont(new Font("Arial", Font.PLAIN, 12));
		Jitemname.setColumns(10);
		Jitemname.setBounds(117, 21, 136, 20);
		panel.add(Jitemname);
		
		JLabel label_3 = new JLabel("Item MAG");
		label_3.setFont(new Font("Arial", Font.BOLD, 13));
		label_3.setBounds(10, 55, 71, 14);
		panel.add(label_3);
		
		JitemMAG = new JTextField();
		JitemMAG.setBackground(Color.WHITE);
		JitemMAG.setEditable(false);
		JitemMAG.setFont(new Font("Arial", Font.PLAIN, 12));
		JitemMAG.setColumns(10);
		JitemMAG.setBounds(117, 52, 136, 20);
		panel.add(JitemMAG);
		
		JitemModel = new JTextField();
		JitemModel.setBackground(Color.WHITE);
		JitemModel.setEditable(false);
		JitemModel.setFont(new Font("Arial", Font.PLAIN, 12));
		JitemModel.setColumns(10);
		JitemModel.setBounds(117, 84, 136, 20);
		panel.add(JitemModel);
		
		Jitemsrno = new JTextField();
		Jitemsrno.setBackground(Color.WHITE);
		Jitemsrno.setEditable(false);
		Jitemsrno.setFont(new Font("Arial", Font.PLAIN, 12));
		Jitemsrno.setColumns(10);
		Jitemsrno.setBounds(117, 115, 136, 20);
		panel.add(Jitemsrno);
		
		JLabel label_4 = new JLabel("Item Model");
		label_4.setFont(new Font("Arial", Font.BOLD, 13));
		label_4.setBounds(10, 87, 75, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Item Sr.No.");
		label_5.setFont(new Font("Arial", Font.BOLD, 13));
		label_5.setBounds(10, 118, 97, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Booking Desc.");
		label_6.setFont(new Font("Arial", Font.BOLD, 13));
		label_6.setBounds(10, 151, 93, 14);
		panel.add(label_6);
		
		JTextArea Jbookingdesc = new JTextArea();
		Jbookingdesc.setWrapStyleWord(true);
		Jbookingdesc.setFont(new Font("Arial", Font.PLAIN, 12));
		Jbookingdesc.setEditable(false);
		Jbookingdesc.setBounds(117, 146, 136, 105);
		panel.add(Jbookingdesc);
		Jbookingdesc.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setForeground(Color.BLACK);
		lblItemDetails.setFont(new Font("Arial", Font.BOLD, 13));
		lblItemDetails.setBounds(10, 0, 93, 14);
		panel.add(lblItemDetails);
		
		JLabel label_10 = new JLabel("Call Type");
		label_10.setBounds(10, 265, 71, 15);
		panel.add(label_10);
		label_10.setFont(new Font("Arial", Font.BOLD, 13));
		
		Jcalltype = new JTextField();
		Jcalltype.setBounds(117, 262, 136, 20);
		panel.add(Jcalltype);
		Jcalltype.setBackground(Color.WHITE);
		Jcalltype.setEditable(false);
		Jcalltype.setFont(new Font("Arial", Font.PLAIN, 12));
		Jcalltype.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(273, 82, 343, 341);
		PrintPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCustomerDetails = new JLabel("Customer Details");
		lblCustomerDetails.setForeground(Color.BLACK);
		lblCustomerDetails.setFont(new Font("Arial", Font.BOLD, 13));
		lblCustomerDetails.setBounds(10, 0, 133, 14);
		panel_1.add(lblCustomerDetails);
		
		JLabel label_7 = new JLabel("Customer Name");
		label_7.setFont(new Font("Arial", Font.BOLD, 13));
		label_7.setBounds(10, 25, 120, 19);
		panel_1.add(label_7);
		
		Jcustname = new JTextField();
		Jcustname.setBackground(Color.WHITE);
		Jcustname.setEditable(false);
		Jcustname.setFont(new Font("Arial", Font.PLAIN, 12));
		Jcustname.setColumns(10);
		Jcustname.setBounds(140, 25, 193, 20);
		panel_1.add(Jcustname);
		
		JLabel label_8 = new JLabel("Address");
		label_8.setFont(new Font("Arial", Font.BOLD, 13));
		label_8.setBounds(10, 122, 96, 14);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Contact No.");
		label_9.setFont(new Font("Arial", Font.BOLD, 13));
		label_9.setBounds(10, 55, 120, 14);
		panel_1.add(label_9);
		
		Jcontact = new JTextField();
		Jcontact.setBackground(Color.WHITE);
		Jcontact.setEditable(false);
		Jcontact.setFont(new Font("Arial", Font.PLAIN, 12));
		Jcontact.setColumns(10);
		Jcontact.setBounds(140, 55, 193, 20);
		panel_1.add(Jcontact);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 13));
		lblEmail.setBounds(10, 89, 111, 15);
		panel_1.add(lblEmail);
		
		email = new JTextField();
		email.setBackground(Color.WHITE);
		email.setEditable(false);
		email.setFont(new Font("Arial", Font.PLAIN, 12));
		email.setColumns(10);
		email.setBounds(140, 86, 193, 20);
		panel_1.add(email);
		
		JTextArea Jaddress = new JTextArea();
		Jaddress.setFont(new Font("Arial", Font.PLAIN, 12));
		Jaddress.setEditable(false);
		Jaddress.setBounds(140, 117, 193, 103);
		panel_1.add(Jaddress);
		Jaddress.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JLabel label_11 = new JLabel("Call Mode");
		label_11.setBounds(10, 234, 120, 15);
		panel_1.add(label_11);
		label_11.setFont(new Font("Arial", Font.BOLD, 13));
		
		Jcallmode = new JTextField();
		Jcallmode.setBounds(140, 231, 193, 20);
		panel_1.add(Jcallmode);
		Jcallmode.setBackground(Color.WHITE);
		Jcallmode.setEditable(false);
		Jcallmode.setFont(new Font("Arial", Font.PLAIN, 12));
		Jcallmode.setColumns(10);
		
		JLabel lblCallStatus = new JLabel("Call Status");
		lblCallStatus.setBounds(10, 260, 142, 19);
		panel_1.add(lblCallStatus);
		lblCallStatus.setFont(new Font("Arial", Font.BOLD, 13));
		
		jcallstatus = new JTextField();
		jcallstatus.setBounds(140, 259, 193, 20);
		panel_1.add(jcallstatus);
		jcallstatus.setBackground(Color.WHITE);
		jcallstatus.setEditable(false);
		jcallstatus.setFont(new Font("Arial", Font.PLAIN, 12));
		jcallstatus.setColumns(10);
		try
		{		
		String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection con=DriverManager.getConnection(url);	
		switch(Source)
		{
		    case "Other":
		    	rmaInt=Integer.parseInt(rmaNumber);
		    	System.out.println(rmaInt);
		    	sql1="Select * From "+Source+" Where RMA_No="+rmaInt+"";
		    	break;
		    case "RMA_Master":
		    	sql1="Select * From "+Source+" Where RMA_No='"+rmaNumber+"'";
		    	break;			    
		}		
		PreparedStatement pst=con.prepareStatement(sql1);
	    ResultSet rs=pst.executeQuery();
	    while (rs.next()) {
	    	calldate=rs.getString("Call_Date");
	    	source=rs.getString("Source");
	    	Item_name=rs.getString("Item_name");
	    	Item_MAG=rs.getString("Item_MAG");
	    	Item_model=rs.getString("Item_model");
	    	Item_SrNo=rs.getString("Item_SrNo");
	    	Booking_Desc=rs.getString("Booking_Desc");
	    	Customer_name=rs.getString("Customer_name");
	    	Customer_address=rs.getString("Customer_address");
	    	Customer_contact=rs.getString("Customer_contact");
	    	Customer_email=rs.getString("Customer_email");
	    	Call_Status=rs.getString("Call_Status");
	    	AssignedTo=rs.getString("AssignedTo");
	    	CallType=rs.getString("CallType");
	    	CallMode=rs.getString("CallMode");
	    	Assigndate=rs.getString("EnggAssigndate");
	    	MaterialRequired=rs.getString("MaterialRequired");
	    	EngineerFeedBack=rs.getString("EngineerFeedBack");
	    	TotalMaterialRequiredCost=rs.getString("TotalMaterialRequiredCost");	
	    	materialAssignedDate=rs.getString("MaterialAssignedDate");
	    	ClosedDate=rs.getString("ClosedDate");
	    	
	    	System.out.println(materialAssignedDate);
	    	Jcalldate.setText(calldate);
	    	Jsource.setText(source);
	    	Jitemname.setText(Item_name);
	    	JitemMAG.setText(Item_MAG);
	    	JitemModel.setText(Item_model);
	    	Jitemsrno.setText(Item_SrNo);
	    	Jbookingdesc.setText(Booking_Desc);
	    	Jcustname.setText(Customer_name);
	    	Jaddress.setText(Customer_address);
	    	Jcontact.setText(Customer_contact);
	    	email.setText(Customer_email);
	    	Jcalltype.setText(CallType);
	    	Jcallmode.setText(CallMode);
	    	jcallstatus.setText(Call_Status);
	    	Jassignedto.setText(AssignedTo);
	    	jassigndate.setText(Assigndate);
	    	Jmaterialrequired.setText(MaterialRequired);
	    	Jmaterialcost.setText(TotalMaterialRequiredCost);
	    	jenggfeedback.setText(EngineerFeedBack);	    	
	    	Jclosedate.setText(ClosedDate);	    
	    }
	    rs.close();
        pst.close();
        con.close();
		}
		catch(Exception ex)
	    {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
	    }
		
		btnExportToExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{				
				PrinterJob pjob = PrinterJob.getPrinterJob();
				PageFormat preformat = pjob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = pjob.pageDialog(preformat);
				//If user does not hit cancel then print.
				if (preformat != postformat) 
				{
				    //Set print component
				    pjob.setPrintable(new Printer(PrintPanel), postformat);
				    if (pjob.printDialog()) {
				        try {
							pjob.print();
						} catch (PrinterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}
				
			}
		});
		
		btnExportToMsexcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
	}

	
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
