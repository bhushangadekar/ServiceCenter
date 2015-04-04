package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainApplication frame = new MainApplication();
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
	public MainApplication() {
		setTitle("F1 Services & Solutions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(130, 20, 1100, 700);	
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon F1Icon = new ImageIcon("C:\\ServiceCenter\\F1.jpg"); 	
		JLabel labelImage = new JLabel("");
		labelImage.setBounds(407, 11, 230, 161);		
		contentPane.add(labelImage);		
		Image image = F1Icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(230, 190,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		F1Icon = new ImageIcon(newimg);
		labelImage.setIcon(F1Icon);
		
		ImageIcon osTechIcon = new ImageIcon("C:\\ServiceCenter\\a.gif"); 
		JLabel osTechLabel = new JLabel("");
		osTechLabel.setBounds(22, 11, 230, 111);
		contentPane.add(osTechLabel);
		Image image1 = osTechIcon.getImage(); // transform it 
		Image newimg1 = image1.getScaledInstance(230, 130,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		osTechIcon = new ImageIcon(newimg1);
		osTechLabel.setIcon(osTechIcon);
		
		ImageIcon ninedotIcon = new ImageIcon("C:\\ServiceCenter\\ninedots.jpg");
		JLabel nineDotLabel = new JLabel("");
		nineDotLabel.setBounds(826, 11, 248, 111);
		contentPane.add(nineDotLabel);
		Image image2 = ninedotIcon.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(230, 110,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		ninedotIcon = new ImageIcon(newimg2);
		nineDotLabel.setIcon(ninedotIcon);
				
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(25, 200, 512, 439);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnLogANew = new JButton("Log New Call ");		
		btnLogANew.setBounds(10, 84, 220, 23);
		panel.add(btnLogANew);
		
		JButton btnViewOpenCalls = new JButton("View Open Calls");
		btnViewOpenCalls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OpenCallSummary frame = new OpenCallSummary();
				frame.setVisible(true);
			}
		});
		btnViewOpenCalls.setBounds(10, 132, 220, 23);
		panel.add(btnViewOpenCalls);
		
		JButton btnEnggDesk = new JButton("View Engineer Desk Calls");
		btnEnggDesk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EnggDeskCallSummary frame = new EnggDeskCallSummary();
				frame.setVisible(true);
			}
		});
		btnEnggDesk.setBounds(10, 178, 220, 23);
		panel.add(btnEnggDesk);	
		
		JLabel lblServiceCenterRelated = new JLabel("Service Center Related Tasks");
		lblServiceCenterRelated.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lblServiceCenterRelated.setBounds(138, 25, 212, 23);
		panel.add(lblServiceCenterRelated);
		
		JButton closedCalls = new JButton("View Closed Calls");
		closedCalls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClosedCallSummary cc=new ClosedCallSummary();
				cc.setVisible(true);
			}
		});
		closedCalls.setBounds(10, 271, 220, 23);
		panel.add(closedCalls);
		
		JButton btnViewEdmaCalls = new JButton("View EDMA Calls");
		btnViewEdmaCalls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EDMACallSummary ed=new EDMACallSummary();
				ed.setVisible(true);
			}
		});
		btnViewEdmaCalls.setBounds(10, 225, 220, 23);
		panel.add(btnViewEdmaCalls);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionText);
		panel_2.setBounds(251, 89, 251, 99);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnTravelPending = new JButton("View Pending Travel Approvals");
		btnTravelPending.setBounds(10, 21, 220, 23);
		panel_2.add(btnTravelPending);
		
		JButton btnViewApprovedTravel = new JButton("View Approved Travel Approvals");
		btnViewApprovedTravel.setBounds(10, 55, 220, 23);
		panel_2.add(btnViewApprovedTravel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionText);
		panel_3.setLayout(null);
		panel_3.setBounds(251, 199, 251, 99);
		panel.add(panel_3);
		
		JButton btnViewMaterialRequired_1 = new JButton("View Material Required Calls");
		btnViewMaterialRequired_1.setBounds(10, 21, 220, 23);
		panel_3.add(btnViewMaterialRequired_1);
		
		JButton btnViewMaterialApproved = new JButton("View Material Approved Calls");
		btnViewMaterialApproved.setBounds(10, 55, 220, 23);
		panel_3.add(btnViewMaterialApproved);
		btnViewMaterialApproved.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MaterialApprovedSummary ma=new MaterialApprovedSummary();
				ma.setVisible(true);
			}
		});
		btnViewMaterialRequired_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MaterialRequiredSummary mr=new MaterialRequiredSummary();
				mr.setVisible(true);
			}
		});
		btnViewApprovedTravel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApprovedTravelSummary ats=new ApprovedTravelSummary();
				ats.setVisible(true);
			}
		});
		btnTravelPending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PendingTravelApprovalSummary ps=new PendingTravelApprovalSummary();
				ps.setVisible(true);
			}
		});
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.CYAN);
		panel2.setBounds(547, 200, 527, 439);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblOtherTasks = new JLabel("Reports");
		lblOtherTasks.setBounds(238, 22, 209, 22);
		lblOtherTasks.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		panel2.add(lblOtherTasks);
		
		JButton enggwisebtn = new JButton("Engineer Wise Reports");
		enggwisebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EnggReport er=new EnggReport();
				er.setVisible(true);
			}
		});
		enggwisebtn.setBounds(48, 127, 209, 23);
		panel2.add(enggwisebtn);
		
		JButton opencallbtn = new JButton("Open Calls Reports");
		opencallbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OpenCallsReport op=new OpenCallsReport();
				op.setVisible(true);
				
			}
		});
		opencallbtn.setBounds(48, 172, 209, 23);
		panel2.add(opencallbtn);
		
		JButton closedcallbtn = new JButton("Closed Calls Reports");
		closedcallbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClosedCallsReport cr=new ClosedCallsReport();
				cr.setVisible(true);
			}
		});
		closedcallbtn.setBounds(48, 220, 209, 23);
		panel2.add(closedcallbtn);
		
		JButton btnSearchByRma = new JButton("Search By RMA Number");
		btnSearchByRma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RMASearch rma=new RMASearch();
				rma.setVisible(true);
			}
		});
		btnSearchByRma.setBounds(48, 83, 209, 23);
		panel2.add(btnSearchByRma);
		
		JButton btnMaterialRequiredReports = new JButton("Material Required Reports");
		btnMaterialRequiredReports.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				RequiredMaterialReport rm=new RequiredMaterialReport();
				rm.setVisible(true);
			}
		});
		btnMaterialRequiredReports.setBounds(48, 269, 209, 23);
		panel2.add(btnMaterialRequiredReports);
		
		
		
		btnLogANew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main m=new Main();
				m.setVisible(true);				
			}
		});
	}
}
