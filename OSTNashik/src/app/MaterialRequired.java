package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class MaterialRequired extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField partno;
	private JTextField partname;
	private JTextField partmodel;
	private JTextField partQuantity;
	private JTextField partPrice;
	private JTextField VAT;
	private JTextField lbtTax;
	private JTextField Total;
	
	//variables
	String totalCharges1;
	String state;
	String rmaNumber,source;
	double serviceCharge;
	String servicetaxApplicable;
	double totalCharge;
	String partRequired;
	String partNo;
	String partName;
	String partModel;
	int partquantity;
	int price;
	double vat;
	double lbt;				
	double total;
	int count=0;
	double grandtotal = 0;
	private JTextField jGrandTotal;
	String FeedBack="",callStatus;
	String sql,sql1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param rmaNo 
	 */
	public MaterialRequired(String rmaNo,String Source) {
		setBounds(130, 20, 1100, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		rmaNumber=rmaNo;
		source=Source;
		if(source.equalsIgnoreCase("Other"))
		{
			source="Other";
		}
		else {
			source="RMA_Master";
		}
		
		String[] columns={"Part No","Part Name","Part Model","Quantity","Price","VAT","LBT","Total"};
		final String[][] data=new String[5][8];
		
		JLabel lblPartRequirement = new JLabel("Part Requirement :");
		lblPartRequirement.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblPartRequirement.setBounds(32, 60, 156, 25);
		contentPanel.add(lblPartRequirement);
		
		String partchoice[]={"Yes","No"};
		final JComboBox partCombo = new JComboBox(partchoice);
		partCombo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));		
		partCombo.setBounds(184, 64, 62, 20);
		contentPanel.add(partCombo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 457, 1064, 2);
		contentPanel.add(separator_1);
		
		JLabel lblRmaNo = new JLabel("RMA NO:");
		lblRmaNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblRmaNo.setBounds(32, 29, 62, 20);
		contentPanel.add(lblRmaNo);
		
		JLabel rmaNoLabel = new JLabel(rmaNumber);
		rmaNoLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		rmaNoLabel.setBounds(104, 30, 84, 19);
		contentPanel.add(rmaNoLabel);
		
		final JPanel inputPanel = new JPanel();
		inputPanel.setBackground(Color.WHITE);
		inputPanel.setBounds(32, 96, 312, 350);
		contentPanel.add(inputPanel);
		inputPanel.setLayout(null);
		
		JLabel lblPartNo = new JLabel("Part No.");
		lblPartNo.setBounds(10, 11, 51, 20);
		inputPanel.add(lblPartNo);
		lblPartNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		partno = new JTextField();
		partno.setBounds(122, 11, 135, 20);
		inputPanel.add(partno);
		partno.setColumns(10);
		
		JLabel lblPartName = new JLabel("Part name");
		lblPartName.setBounds(10, 42, 63, 20);
		inputPanel.add(lblPartName);
		lblPartName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		partname = new JTextField();
		partname.setBounds(122, 42, 135, 20);
		inputPanel.add(partname);
		partname.setColumns(10);
		
		JLabel lblPartModel = new JLabel("Part Model");
		lblPartModel.setBounds(10, 73, 69, 20);
		inputPanel.add(lblPartModel);
		lblPartModel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		partmodel = new JTextField();
		partmodel.setBounds(122, 73, 135, 20);
		inputPanel.add(partmodel);
		partmodel.setColumns(10);
		
		JLabel lblPartQuantity = new JLabel("Part Quantity");
		lblPartQuantity.setBounds(10, 104, 104, 20);
		inputPanel.add(lblPartQuantity);
		lblPartQuantity.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		partQuantity = new JTextField();
		partQuantity.setBounds(122, 104, 135, 20);
		inputPanel.add(partQuantity);
		partQuantity.setColumns(10);
		
		JLabel lblPartPrice = new JLabel("Part Price / Unit");
		lblPartPrice.setBounds(10, 135, 115, 20);
		inputPanel.add(lblPartPrice);
		lblPartPrice.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		partPrice = new JTextField();
		partPrice.setBounds(122, 137, 135, 20);
		inputPanel.add(partPrice);
		partPrice.setColumns(10);
		
		JLabel lblVatTax = new JLabel("VAT TAX.");
		lblVatTax.setBounds(10, 174, 59, 20);
		inputPanel.add(lblVatTax);
		lblVatTax.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		VAT = new JTextField();
		VAT.setBounds(122, 174, 135, 20);
		inputPanel.add(VAT);
		VAT.setColumns(10);
		
		JLabel lblLbt = new JLabel("L.B.T.");
		lblLbt.setBounds(10, 205, 32, 20);
		inputPanel.add(lblLbt);
		lblLbt.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		lbtTax = new JTextField();
		lbtTax.setBounds(122, 207, 135, 20);
		inputPanel.add(lbtTax);
		lbtTax.setColumns(10);
		
		final JButton btnGrandTotal = new JButton("Total");
		btnGrandTotal.setBounds(10, 236, 90, 25);
		inputPanel.add(btnGrandTotal);
		btnGrandTotal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnGrandTotal.setHorizontalAlignment(SwingConstants.LEFT);
		
		Total = new JTextField();
		Total.setBounds(122, 240, 135, 20);
		inputPanel.add(Total);
		Total.setColumns(10);
		
		final JButton btnAddAnotherPart = new JButton("Add Material to Final List");
		btnAddAnotherPart.setBounds(10, 310, 191, 29);
		inputPanel.add(btnAddAnotherPart);
		btnAddAnotherPart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		
		JLabel lblGrandTotal = new JLabel("Grand Total");
		lblGrandTotal.setBounds(10, 272, 84, 20);
		inputPanel.add(lblGrandTotal);
		lblGrandTotal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		
		jGrandTotal = new JTextField();
		jGrandTotal.setBounds(122, 271, 135, 20);
		inputPanel.add(jGrandTotal);
		jGrandTotal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		jGrandTotal.setColumns(10);
		
		JLabel lblRs = new JLabel("RS.");
		lblRs.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblRs.setBounds(266, 275, 20, 14);
		inputPanel.add(lblRs);
		
		JLabel label = new JLabel("RS.");
		label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label.setBounds(267, 242, 20, 14);
		inputPanel.add(label);
		
		JLabel label_1 = new JLabel("RS.");
		label_1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		label_1.setBounds(267, 140, 20, 14);
		inputPanel.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.RAISED, null, null)));
		scrollPane.setBounds(32, 468, 902, 133);
		contentPanel.add(scrollPane);
		
		
		final JTable tab=new JTable(data,columns);
		tab.setPreferredScrollableViewportSize(new Dimension(450,63));
		tab.setFillsViewportHeight(true);	
		tab.setRowHeight(20);
		final JScrollPane jps=new JScrollPane(tab);
		jps.setViewportBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EtchedBorder(EtchedBorder.RAISED, null, null)));
		scrollPane.setViewportView(jps);
		jps.setVisible(true);
		
		
		final JPanel panelFeedBack = new JPanel();
		panelFeedBack.setBackground(Color.WHITE);
		panelFeedBack.setBounds(365, 96, 709, 350);
		contentPanel.add(panelFeedBack);
		panelFeedBack.setLayout(null);
		panelFeedBack.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Engineer FeedBack ");
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(20, 11, 133, 20);
		panelFeedBack.add(lblNewLabel);
		
		final JTextArea feedBack = new JTextArea();
		feedBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		feedBack.setBounds(163, 11, 367, 182);
		panelFeedBack.add(feedBack);
		feedBack.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		
		JLabel lblCallStatus = new JLabel("Call Status");
		lblCallStatus.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblCallStatus.setBounds(20, 264, 92, 14);
		panelFeedBack.add(lblCallStatus);
		
		String[] status={"Closed","Rejected","Engineer Desk"};
		final JComboBox callStatusCombo = new JComboBox(status);
		callStatusCombo.setBounds(163, 263, 92, 20);
		panelFeedBack.add(callStatusCombo);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(490, 612, 96, 29);
		contentPanel.add(btnSubmit);
		btnSubmit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));	
		
		partCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state=(String) partCombo.getSelectedItem();
				if(state.equals("No"))
				{
					inputPanel.setVisible(false);
					jps.setVisible(false);
					partno.setEnabled(false);
					partname.setEnabled(false);
					partmodel.setEnabled(false);
					partPrice.setEnabled(false);
					partQuantity.setEnabled(false);
					VAT.setEnabled(false);
					lbtTax.setEnabled(false);
					Total.setEnabled(false);
					btnAddAnotherPart.setEnabled(false);
					panelFeedBack.setVisible(true);
					tab.setVisible(true);
					btnGrandTotal.setEnabled(false);
				}
				else
				{
					jps.setVisible(true);
					inputPanel.setVisible(true);
					panelFeedBack.setVisible(false);
					tab.setVisible(false);
					partno.setEnabled(true);
					partname.setEnabled(true);
					partmodel.setEnabled(true);
					partPrice.setEnabled(true);
					partQuantity.setEnabled(true);
					VAT.setEnabled(true);
					lbtTax.setEnabled(true);
					Total.setEnabled(true);
					btnGrandTotal.setEnabled(true);
					btnAddAnotherPart.setEnabled(true);
					
				}
			}
		});	
		
		btnAddAnotherPart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tab.setVisible(true);
				partno.setText("");
				partname.setText("");
				partmodel.setText("");
				partPrice.setText("");
				partQuantity.setText("");
				VAT.setText("");
				lbtTax.setText("");
				Total.setText("");
				count++;								
			}
		});
		
		btnGrandTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				tab.setVisible(false);
				double tempTotal;				
				partNo=partno.getText();
				partName=partname.getText();
				partModel=partmodel.getText();
				partquantity=Integer.parseInt(partQuantity.getText());
				price=Integer.parseInt(partPrice.getText());
				tempTotal=(partquantity*price);
				vat=(tempTotal*Double.parseDouble(VAT.getText()))/100;;
				lbt=(tempTotal*Double.parseDouble(lbtTax.getText())/100);			
				total=tempTotal+vat+lbt;
				grandtotal=grandtotal+total;
				String totalString=String.valueOf(total);
				Total.setText(totalString);
				String GrandtotalString=String.valueOf(grandtotal);
				jGrandTotal.setText(GrandtotalString);			
				data[count][0]=partNo;
				data[count][1]=partName;
				data[count][2]=partModel;
				data[count][3]=String.valueOf(partquantity);
				data[count][4]=String.valueOf(price);
				data[count][5]=String.valueOf(vat);
				data[count][6]=String.valueOf(lbt);
				data[count][7]=String.valueOf(total);
				
				
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {	
				    String state1=(String) partCombo.getSelectedItem();
				    String materialStatus;										
					switch(state1)
					{
					case "Yes":
						try
						{	
						materialStatus="MR";
						String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection(url);
					    Statement st = con.createStatement();					    
					    for(int i=0;i<count;i++)
					    {
					    st.executeUpdate("insert into Material_Require_Master(RMA_No,Part_Required,Part_No,Part_Name,Part_Model,Part_Quantity,Part_Price,VAT,LBT,Total) values ('"+rmaNumber+"','"+state1+"','"+data[i][0]+"','"+data[i][1]+"','"+data[i][2]+"','"+data[i][3]+"','"+data[i][4]+"','"+data[i][5]+"','"+data[i][6]+"','"+data[i][7]+"')");
						System.out.println("Row is added into Material_Require_Master From MaterialRequired Window");						
					    }
					    switch(source)
					    {
					    case "Other":
					    	sql="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',TotalMaterialRequiredCost='"+grandtotal+"' WHERE RMA_No="+rmaNumber+" ";
					    	sql1="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',Call_Status='"+callStatus+"',EngineerFeedBack='"+FeedBack+"' WHERE RMA_No="+rmaNumber+"";
					    	break;
					    case "RMA_Master":
					    	sql="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',TotalMaterialRequiredCost='"+grandtotal+"' WHERE RMA_No='"+rmaNumber+"' ";
					    	sql1="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',Call_Status='"+callStatus+"',EngineerFeedBack='"+FeedBack+"' WHERE RMA_No='"+rmaNumber+"' ";
					    	break;
					    }
					    st.executeUpdate(sql);
				        st.close();
				        con.close();		        
						}
						catch(Exception ex)
					    {
							System.err.print("Exception: ");
							System.err.println(ex.getMessage());
					    }
						break;
					case "No":
						try
						{
						materialStatus="NPC";
						FeedBack=feedBack.getText();
						callStatus=(String) callStatusCombo.getSelectedItem();
						switch(source)
						    {
						    case "Other":
						    	sql1="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',Call_Status='"+callStatus+"',EngineerFeedBack='"+FeedBack+"' WHERE RMA_No="+rmaNumber+"";
						    	break;
						    case "RMA_Master":
						    	sql1="Update "+source+" SET  MaterialRequired='"+state1+"',MaterialRequireStatus='"+materialStatus+"',Call_Status='"+callStatus+"',EngineerFeedBack='"+FeedBack+"' WHERE RMA_No='"+rmaNumber+"' ";
						    	break;
						    }
						String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection(url);
					    Statement st = con.createStatement();  				  
					    st.executeUpdate(sql1);
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
		
	}	
}
