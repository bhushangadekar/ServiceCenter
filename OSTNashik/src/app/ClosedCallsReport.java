package app;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jdesktop.swingx.JXDatePicker;

public class ClosedCallsReport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	
	private JTable table;
	private JScrollPane scrollPane;
	TableModel dtm ;
	String Status="Closed";
	JXDatePicker datePicker,datePicker1;
	String fromdate1,todate1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClosedCallsReport frame = new ClosedCallsReport();
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
	public ClosedCallsReport() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(130, 20, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFromDate = new JLabel("From Date");
		lblFromDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblFromDate.setBounds(32, 27, 78, 14);
		contentPane.add(lblFromDate);
		
		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblToDate.setBounds(329, 24, 78, 20);
		contentPane.add(lblToDate);	
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(122, 26, 176, 20);
		contentPane.add(datePicker);
		
		
		datePicker1 = new JXDatePicker();
		datePicker1.setBounds(405, 26, 164, 20);
		contentPane.add(datePicker1);
		
		JButton btnSubmit = new JButton("Submit");		
		btnSubmit.setBounds(860, 25, 89, 23);
		contentPane.add(btnSubmit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 52, 1039, 524);
		contentPane.add(scrollPane);		
		table = new JTable();
		table.setRowHeight(23);	
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
		table.setRowSelectionAllowed(true);
		ListSelectionModel rowSelectionModel = table.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnExportToMsexcel = new JButton("Export To MS_Excel");		
		btnExportToMsexcel.setBounds(529, 614, 155, 23);
		contentPane.add(btnExportToMsexcel);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(374, 614, 155, 23);
		contentPane.add(btnPrint);	
		
		btnSubmit.addActionListener(new ActionListener() {
			private Date dateFrom1;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Date dateFrom=datePicker.getDate();				
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
				fromdate1= formatter.format(dateFrom);				
				dateFrom1 = datePicker1.getDate();
				todate1= formatter.format(dateFrom1);				
				try
				{
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);
				String sql1="SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,CallMode,MaterialRequired,TotalMaterialRequiredCost,Call_Status FROM RMA_Master WHERE (((Call_Date) Between #"+fromdate1+"# And #"+todate1+"#)) AND Call_Status='"+Status+"' UNION ALL SELECT RMA_No,Call_Date,Source,Item_name,Booking_Desc,Customer_name,Customer_contact,CallType,CallMode,MaterialRequired,TotalMaterialRequiredCost,Call_Status FROM Other WHERE (((Call_Date) Between #"+fromdate1+"# And #"+todate1+"#)) AND Call_Status='"+Status+"'";
				PreparedStatement pst=con.prepareStatement(sql1);
			    ResultSet rs=pst.executeQuery();	
			    table.setModel(DbUtils.resultSetToTableModel(rs));
			    dtm=table.getModel();
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
		btnExportToMsexcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Workbook wb = new HSSFWorkbook();
			    CreationHelper createhelper = wb.getCreationHelper();
			    Sheet sheet = wb.createSheet("Data");		    
			    Row row = null;
			    Cell cell = null;
			    int count=0;
			    int n=dtm.getRowCount();
			    System.out.println(n);
			    for (int i=0;i<=n;i++)
			    {			    	
			        row = sheet.createRow(i);
			        for(int j=0;j<dtm.getColumnCount();j++)
			        {	
			        	if(count==0)
			        	{
			        		String head=dtm.getColumnName(j);
			        		System.out.println(head);
			        		cell = row.createCell(j);
			        		cell.setCellValue(head);			        					        		
			        	}
			        	else
			        	{
			        	String a=printType(dtm.getValueAt(i-1, j));
			            cell = row.createCell(j);
			            cell.setCellValue(a);
			        	}			        	
			        }
			        count++;
			    }
			    FileOutputStream out = null;
				try
				{
					out = new FileOutputStream("D:\\"+Status+".xls");
					wb.write(out);
					out.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					Logger.getLogger(ClosedCallsReport.class.getName()).log(Level.SEVERE, null, e);
					e.printStackTrace();
				}
			    dispose();
			}
		});
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PrinterJob pjob = PrinterJob.getPrinterJob();
				PageFormat preformat = pjob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = pjob.pageDialog(preformat);
				//If user does not hit cancel then print.
				if (preformat != postformat) 
				{
				    //Set print component
				    pjob.setPrintable(new Printer(table), postformat);
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
	}	
	String printType(Object object) {
     
		return String.valueOf(object);
    }
    String printType(int x) {
     
		return String.valueOf(x);
        
    }
    String printType(float x) {
      
		return String.valueOf(x);
        
    }
    String printType(double x) {
       
		return String.valueOf(x);
        
    }
    String printType(char x) {
   
		return String.valueOf(x);
        
    }
}
