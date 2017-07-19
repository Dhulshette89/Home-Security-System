package gui;
	import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.io.File;
	import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;
import javax.swing.SwingUtilities;


	
/**
	 * The Class Login.
	 */
	public class Login extends JFrame{

	   /** The password field. */
   	//public JFrame frame; //Extends JFrame already so 'this' IS a frame
	   public JPasswordField passwordField;
	   
	   /** The blogin. */
   	public JButton blogin;
	 

	   /**
   	 * Launch the application.
   	 *
   	 * @param args the arguments
   	 */
	   public static void main(String[] args) {
		   SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
	               try {
	                   Login window = new Login();
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
	   public Login() {
	       initialize();
	   }

	   /**
	    * Initialize the contents of the frame.
	    */
	   public void initialize() {

	     setLayout(null);

	     setSize(1850,1100);; // only added so no need to expand window as often
	     getContentPane().setBackground(new Color(255,229,204));
	     passwordField = new JPasswordField();
	     passwordField.setBounds(890, 390, 105, 42);
	     passwordField.setFont(new Font(passwordField.getFont().getName(), passwordField.getFont().getStyle(), 25));
	     add(passwordField);

	     JLabel lblPassword = new JLabel("ENTER PASSWORD");
	     lblPassword.setBounds(820, 330, 250, 42);
	     lblPassword.setFont(new Font(lblPassword.getFont().getName(), lblPassword.getFont().getStyle(), 25));
	     add(lblPassword);

	     JButton login = new JButton("LOGIN");
	     login.setBounds(870, 455, 150, 42);
	     login.setFont(new Font(login.getFont().getName(), login.getFont().getStyle(), 25));
	     login.addActionListener(new ActionListener() { //moved from actionlogin()
	         public void actionPerformed(ActionEvent ae){
	            try {
					actionlogin();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	     });

	     add(login);

	     JButton btnNewUser = new JButton("New User ?");
	     btnNewUser.setBounds(144, 196, 97, 25);    

	     add(login);
	     add(passwordField);
	     
	   }

	   

	   /**
   	 * Actionlogin.
   	 *
   	 * @throws IOException Signals that an I/O exception has occurred.
   	 */
   	public void actionlogin() throws IOException{

	        //Scanner sc; not used
	        Scanner scan=null;

	        try {
	            //sc = new Scanner(new File("Logincode.txt")); not used 
	            scan = new Scanner(new File("LoginCode")); //make sure to add your path

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        //Scanner keyboard = new Scanner (System.in);       

	        //String inpUser = keyboard.nextLine();
	        //String inpUser;
	        //inpUser = textField.getText();

	        //String inpPass = keyboard.nextLine();
	        String inpPass;
	        inpPass = passwordField.getText();// gets input from user

	        String pass="";
	        if(scan.hasNextLine())
	           pass = scan.nextLine(); // looks at selected file in scan

	            if (inpPass.equals(pass)){
	            	new MainFrame();
	            	setVisible(false); //you can't see this!
	            	dispose();
	                System.out.print("your login message");
	            }else {
	                JOptionPane.showMessageDialog(null,"Wrong Password / Username");
	            }
	   }       

	}

