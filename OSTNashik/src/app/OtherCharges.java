package app;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OtherCharges extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField from;
	private JTextField to;
	private JTextField distance;
	private JTextField totalcostText;
	String travellingRequired;
	String fr;
	String to1;
	int distance1;
	double totalCost;
	String rmaNumber;
	String approvalStatus,TravelMode;
	
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
	public OtherCharges(String rmaNo) {
		setVisible(true);		
		setBounds(130, 20, 1100, 700);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		rmaNumber=rmaNo;
		
		JLabel lblYouWillBe = new JLabel("You Will Be Charged As Per Taxes & LBT");
		lblYouWillBe.setForeground(Color.BLACK);
		lblYouWillBe.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblYouWillBe.setBounds(342, 11, 324, 22);
		contentPanel.add(lblYouWillBe);
		
		JLabel lblFillYourDetails = new JLabel("Fill Your Details Below ");
		lblFillYourDetails.setForeground(Color.RED);
		lblFillYourDetails.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblFillYourDetails.setBounds(409, 36, 164, 31);
		contentPanel.add(lblFillYourDetails);
		
		JLabel lblTravellingRequired = new JLabel("Travelling Required :");
		lblTravellingRequired.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblTravellingRequired.setBounds(47, 130, 135, 19);
		contentPanel.add(lblTravellingRequired);
		
		String[] travelling={"Yes","No"};
		final JComboBox travellingCombo = new JComboBox(travelling);		
		travellingCombo.setBounds(214, 131, 66, 20);
		contentPanel.add(travellingCombo);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblFrom.setBounds(47, 176, 46, 22);
		contentPanel.add(lblFrom);
		
		from = new JTextField();
		from.setBounds(214, 179, 101, 20);
		contentPanel.add(from);
		from.setColumns(10);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblTo.setBounds(47, 229, 34, 14);
		contentPanel.add(lblTo);
		
		to = new JTextField();
		to.setBounds(214, 228, 101, 20);
		contentPanel.add(to);
		to.setColumns(10);
		
		JLabel lblTotalEstimatedDistance = new JLabel("Total Estimated Distance");
		lblTotalEstimatedDistance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblTotalEstimatedDistance.setBounds(47, 281, 174, 17);
		contentPanel.add(lblTotalEstimatedDistance);
		
		distance = new JTextField();		
		distance.setBounds(214, 281, 101, 20);
		contentPanel.add(distance);
		distance.setColumns(10);		
		
		
		JLabel lblKilometers = new JLabel("Kilometers");
		lblKilometers.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblKilometers.setBounds(325, 282, 75, 14);
		contentPanel.add(lblKilometers);
		
		JLabel lblTotalEstimatedCost = new JLabel("Total Estimated Cost");
		lblTotalEstimatedCost.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblTotalEstimatedCost.setBounds(47, 334, 141, 16);
		contentPanel.add(lblTotalEstimatedCost);
		
		totalcostText = new JTextField();
		totalcostText.setEditable(false);
		totalcostText.setBounds(214, 334, 101, 20);
		contentPanel.add(totalcostText);
		totalcostText.setColumns(10);
		
		distance.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				int cal=Integer.parseInt(distance.getText());
				 double cost=cal*1.50;
				 String total=String.valueOf(cost);
				 totalcostText.setText(total);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				int cal=Integer.parseInt(distance.getText());
				 double cost=cal*1.50;
				 String total=String.valueOf(cost);
				 totalcostText.setText(total);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}});
		
		JLabel lblRupees = new JLabel("Rupees");
		lblRupees.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14)); 
		lblRupees.setBounds(329, 334, 59, 16);
		contentPanel.add(lblRupees);
		
		JButton btnSubmit = new JButton("Submit");		
		btnSubmit.setBounds(428, 557, 89, 23);
		contentPanel.add(btnSubmit);
		
		JLabel lblRmaNo = new JLabel("RMA NO:");
		lblRmaNo.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblRmaNo.setBounds(47, 93, 66, 14);
		contentPanel.add(lblRmaNo);
		
		JLabel lblNewLabel = new JLabel(rmaNumber);
		lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblNewLabel.setBounds(120, 89, 101, 22);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTravellingMode = new JLabel("Travelling Mode :");
		lblTravellingMode.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		lblTravellingMode.setBounds(375, 130, 135, 19);
		contentPanel.add(lblTravellingMode);
		
		String[] travellingMode={"Air","Water","Train","Land"};
		final JComboBox TravellingModeCombo = new JComboBox(travellingMode);
		TravellingModeCombo.setBounds(505, 130, 107, 20);
		contentPanel.add(TravellingModeCombo);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		
		travellingCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String Travellingchoice=(String) travellingCombo.getSelectedItem();
				if(Travellingchoice.equals("Yes"))
				{					
					from.setEditable(true);
					to.setEditable(true);
					distance.setEditable(true);							
					totalcostText.setEnabled(true);
				}
				else if(Travellingchoice.equals("No"))
				{					
					from.setEditable(false);	
					to.setEditable(false);
					distance.setEditable(false);					
					totalcostText.setEnabled(false);
					
				}
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				travellingRequired=(String) travellingCombo.getSelectedItem();
			    fr=from.getText();
				to1=to.getText();
			    distance1=Integer.parseInt(distance.getText());
				totalCost=Double.parseDouble(totalcostText.getText());
				TravelMode=(String) TravellingModeCombo.getSelectedItem();
				approvalStatus="Pending";
				try
				{				
				String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=c:\\Service_Center.accdb";
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection(url);
			    Statement st = con.createStatement();  			    
			    st.executeUpdate("insert into Travel_Master(RMA_No,Travelling_Required,Location_1,Location_2,Distance,TotalTravellingCost,Approval_Status,Transport_Mode) values ('"+rmaNumber+"','"+travellingRequired+"','"+fr+"','"+to1+"','"+distance1+"','"+totalCost+"','"+approvalStatus+"','"+TravelMode+"')");
				System.out.println("Row is added into Travel_Master From TravellingChargesWindow");						   
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
