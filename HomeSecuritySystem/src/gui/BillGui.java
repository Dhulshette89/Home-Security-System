package gui;
 
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
 
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JMonthChooser;

import system.BillGenerator;
import system.SystemInstaller;
import system.user.UserAccount;
 
// TODO: Auto-generated Javadoc
/**
 * The Class BillGui.
 */
public class BillGui extends JFrame {
 
	/** The frame. */
	private JFrame frame;
	
	/** The scroll pane. */
	JScrollPane scrollPane;
	
	/** The panel 1. */
	JPanel panel_1;
	
	/** The coverage. */
	HashMap<Integer, String> coverage = new HashMap<Integer, String>();
 
	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					BillGui window = new BillGui();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the application.
	 */
	public BillGui() {
		initialize();
	}
 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		coverage.put(1, "01/01 - 01/31");
		coverage.put(2, "02/01 - 02/28");
		coverage.put(3, "03/01 - 03/31");
		coverage.put(4, "04/01 - 04/30");
		coverage.put(5, "05/01 - 05/31");
		coverage.put(6, "06/01 - 06/30");
		coverage.put(7, "07/01 - 07/31");
		coverage.put(8, "08/01 - 08/31");
		coverage.put(9, "09/01 - 09/30");
		coverage.put(10, "10/01 - 10/31");
		coverage.put(11, "11/01 - 11/30");
		coverage.put(12, "12/01 - 12/31");
		
		
		
		
		setBounds(100, 100, 607, 513);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 591, 95);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(190, 15, 108, 30);
		panel.add(monthChooser);
		
		
		JLabel lblSelectMonth = new JLabel("Select Month");
		lblSelectMonth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSelectMonth.setBounds(94, 15, 102, 30);
		panel.add(lblSelectMonth);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(174, 56, 102, 23);
		panel.add(btnSubmit);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 96, 591, 378);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 591, 378);
		panel_1.add(scrollPane);
		
		
		JEditorPane invoiceText = new JEditorPane("text/html","");
		scrollPane.setViewportView(invoiceText);
		
		panel_1.setVisible(false);
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				BillGenerator bg = new BillGenerator();
				
				panel_1.setVisible(true);
				
				
				int y = monthChooser.getMonth() +1;
				int final2 = 0;
				int final1 = 0;
				String initial1 = " ";
				String initial2 = " ";
				int med1 = 0;
				int med2 =0;
				String name = "";
				String Address = " ";
				String BuildingId = " ";
				int alarm1 = 0;
				int alarm2 = 0;
				
				
				//User Details String
				String contractId = "";
				String userAdd="";
				String emergency1="";
				//String emergency2="";
				String userEmail = "";
				String dispCoverage=coverage.get(y);
				//String 
				
				try {
					
					name = SystemInstaller.getInstance().getUser().getUserName();
					contractId = SystemInstaller.getInstance().getUser().getContractId();
					userAdd=SystemInstaller.getInstance().getUser().getUserAddress();
					emergency1=SystemInstaller.getInstance().getUser().getEmergencyNumbers();
					userEmail=SystemInstaller.getInstance().getUser().getEmailId();
					
					
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					initial1 = bg.fireBill1(y);
					med1 = bg.fireBill2(y); //Sensor Installation fees
					final1 = bg.fireBill3(y)+med1;
					alarm1 = bg.getFireAlarm(y);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
				initial2 = bg.intrusionBill1(y);
					med2 = bg.intrusionBill2(y);
					 final2 = bg.intrusionBill3(y)+med2;
					 alarm2 = bg.getIntrusionAlarm(y);
				} 	catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				int grandTotal = final1+final2;
				
				
				/*•	Service Contract Id
				•	Customer name
				•	Address of the property
				•	Contact Numbers (two numbers to call in case of emergency)
				•	Customer Phone or email (serves as id)
				•	Effective dates (of coverage)*/
 
				
				
				String userDetails = "<b>Customer Information:</b><br>" +
										"Contract Id:" + contractId +
										"<br>Name:" + name +
										"<br>Address:" + userAdd +
										"<br>Email:"+ userEmail +
										"<br>Emergency Numbers:" + emergency1 +
										"<br>Coverage:" + dispCoverage;
				
				
				String display =	userDetails + 
									"<br><b>Fire Details:</b><br>Bill for Fire System Installation: " + initial1 +
									"<br> Bill For Fire Sensors Installation: " + med1 +
									"<br>Bill for Alarm calls for Fire: " + Integer.toString(alarm1) + 
									"<br>The Total Fire Bill: " + final1+ 
									"<br><b>Intrusion Details:</b><br>Bill For Intrusion System Installation: " +initial2+
									"<br>Bill For Intrusion Sensors Installation: " +med2+
									"<br>Bill for Alarm calls for Intrusion : " +Integer.toString(alarm2)+
									"<br>The Total Intrusion Bill : " +final2 +
									"<br><b>Grand Total:</b>" + grandTotal;
 
				invoiceText.setText(display);
				invoiceText.setEditable(false);
					
			}
		});
			}
		}
	
 
 
