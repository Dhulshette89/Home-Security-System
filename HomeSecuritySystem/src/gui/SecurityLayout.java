package gui;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import system.SystemInstaller;
import system.config.Configuration;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Class SecurityLayout.
 */
public class SecurityLayout extends JPanel{
	
	/** The fire sensor button list. */
	private List<JToggleButton> fireSensorButtonList;
	
	/** The motion sensor button list. */
	private List<JToggleButton> motionSensorButtonList;
	
	/** The my panel list. */
	private List<JPanel> myPanelList;
	
	/** The motion sensor list. */
	private List<MotionSensor> motionSensorList;
	
	/** The fire sensor list. */
	private List<FireSensor>   fireSensorList;
	
	/** The Is manual mode. */
	private boolean IsManualMode;
	
	/** The flag flash. */
	private boolean flagFlash;
	
	/** The section panel map. */
	private HashMap<String,JPanel> sectionPanelMap;
	
	/** The sprinkler. */
	private JLabel sprinkler;
	
	
	/**
	 * Gets the fire sensor button list.
	 *
	 * @return the fire sensor button list
	 */
	public List<JToggleButton> getFireSensorButtonList() {
		return fireSensorButtonList;
	}

	/**
	 * Gets the motion sensor button list.
	 *
	 * @return the motion sensor button list
	 */
	public List<JToggleButton> getMotionSensorButtonList() {
		return motionSensorButtonList;
	}

	/**
	 * Instantiates a new security layout.
	 *
	 * @param mList the m list
	 * @param fList the f list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public SecurityLayout(List<MotionSensor> mList,List<FireSensor> fList) throws IOException
	
	{	ArrayList<JComboBox> hourList = new ArrayList<JComboBox>();
	    ArrayList<JComboBox> minList = new ArrayList<JComboBox>();
	
		motionSensorList = mList;
		fireSensorList = fList;
		setLayout(new GridLayout(3,4));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(650, 0,1200,1000);
		fireSensorButtonList = new ArrayList<JToggleButton>();
		motionSensorButtonList = new ArrayList<JToggleButton>();
		myPanelList=new ArrayList<JPanel>();
		sectionPanelMap = new HashMap<String,JPanel>();
		String base = "A01";
		
		for(int i=0;i<12;i++)
		{
			JPanel myPanel=new JPanel();
			myPanel.setBackground(Color.WHITE);
			myPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			myPanel.setLayout(null);
			add(myPanel);
			myPanelList.add(myPanel);
			StringBuilder id = new StringBuilder();
			id.append(base);
			if(i<=10)
			{
				id.append("0");
			}
			id.append(""+(i+1));
			sectionPanelMap.put(id.toString(),myPanel);
		}

		for(MotionSensor m: mList)
		{
			JToggleButton button = new JToggleButton(m.getScetionId());
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setContentAreaFilled(false);
			button.setSize(62, 60);
			
			button.setIcon(new ImageIcon("motionSensor.png"));
			button.setSelectedIcon(new ImageIcon("selectMotion.png"));
			
			if(m.getStatus().equals(SensorStatus.ON))button.setSelected(true);
			else button.setSelected(false);
			
			
			motionSensorButtonList.add(button);
		}
		addMotionSensorButtonListeners();
		for(FireSensor f:fList)
		{
			JToggleButton button = new JToggleButton(f.getScetionId());
			button.setBorder(BorderFactory.createEmptyBorder());
			button.setContentAreaFilled(false);
			//button.setMinimumSize(new Dimension(62,60));
			button.setSize(72, 60);
			button.setIcon(new ImageIcon("FireSensor.png"));
			button.setSelectedIcon(new ImageIcon("selectFire.png"));
			
			if(f.getStatus().equals(SensorStatus.ON))button.setSelected(true);
			else button.setSelected(false);
			
			fireSensorButtonList.add(button);	
		}
		addFireSensorButtonListeners();

		/*fireSensorButtonList.get(0).addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			/*	JButton jb=this;
				
				
				FormEvent ev=new FormEvent(this,dateString.toString(),category,amount);
				JOptionPane.showMessageDialog( submitBtn, "Expenses added");
				amountField.setText("");
				
				
				if(formListener!=null)
					formListener.formEventOccured(ev);
				}
				else{
					JOptionPane.showMessageDialog( submitBtn, "cannot add Expenses,enter valid fields");
				}
			}
			
		});*/
		

		fireSensorButtonList.get(0).setLocation(120,120);
		myPanelList.get(0).add(fireSensorButtonList.get(0));
		motionSensorButtonList.get(0).setLocation(1, 175);
		myPanelList.get(0).add(motionSensorButtonList.get(0));
		
		fireSensorButtonList.get(1).setLocation(120,120);
		myPanelList.get(1).add(fireSensorButtonList.get(1));
		
		motionSensorButtonList.get(1).setLocation(189, 16);
		myPanelList.get(3).add(motionSensorButtonList.get(1));
		
		motionSensorButtonList.get(2).setLocation(1, 193);
		myPanelList.get(4).add(motionSensorButtonList.get(2));
		
		fireSensorButtonList.get(2).setLocation(120,120);
		myPanelList.get(5).add(fireSensorButtonList.get(2));
		fireSensorButtonList.get(3).setLocation(120,120);
		myPanelList.get(6).add(fireSensorButtonList.get(3));
		
		motionSensorButtonList.get(3).setLocation(189, 1);
		myPanelList.get(7).add(motionSensorButtonList.get(3));
		
		fireSensorButtonList.get(4).setLocation(120,120);
		myPanelList.get(8).add(fireSensorButtonList.get(4));
		motionSensorButtonList.get(4).setLocation(1, 186);
		myPanelList.get(8).add(motionSensorButtonList.get(4));
		
		fireSensorButtonList.get(4).setLocation(120,120);
		myPanelList.get(9).add(fireSensorButtonList.get(4));
		
		fireSensorButtonList.get(5).setLocation(120,120);
		myPanelList.get(10).add(fireSensorButtonList.get(5));
		
		motionSensorButtonList.get(5).setLocation(189, 186);
		myPanelList.get(11).add(motionSensorButtonList.get(5));
		
		BufferedImage myPicture = ImageIO.read(new File("window.png"));
		BufferedImage myPicture2 = ImageIO.read(new File("window1.png"));
		BufferedImage myPicture3 = ImageIO.read(new File("doorswing.gif"));
		
		BufferedImage myPicture4 = ImageIO.read(new File("C:/Users/vaish/workspace/HomeSecuritySystem/src/doorswing1.gif"));
		
		 BufferedImage myPicture5 = ImageIO.read(new File("doorswing2.gif"));

		
		JLabel lblNewLabel2 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel2.setSize(20, 80);
		lblNewLabel2.setLocation(1, 35);
		myPanelList.get(0).add(lblNewLabel2);
		JLabel lblNewLabel6 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel6.setSize(80,20);
		lblNewLabel6.setLocation(40, 2);
		myPanelList.get(0).add(lblNewLabel6);	
		
		JLabel A0101 = new JLabel("A0101");
		A0101.setFont(new Font(A0101.getFont().getName(), A0101.getFont().getStyle(), 18));
		A0101.setBounds(95, 60, 69, 20);
		myPanelList.get(0).add(A0101);
		
		
		
		
		JLabel lblNewLabel7 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel7.setSize(80,20);
		lblNewLabel7.setLocation(79, 2);
		myPanelList.get(1).add(lblNewLabel7);
		
		
		JLabel A0102Door = new JLabel(new ImageIcon(myPicture4));
		A0102Door.setSize(69,159);
		A0102Door.setLocation(97, 233);
		myPanelList.get(1).add(A0102Door);
		JLabel A0102 = new JLabel("A0102");
		A0102.setBounds(95, 60, 69, 20);
		A0102.setFont(new Font(A0102.getFont().getName(), A0102.getFont().getStyle(), 18));
		myPanelList.get(1).add(A0102);
		
		
		
		
		JLabel lblNewLabel8 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel8.setSize(80,20);
		lblNewLabel8.setLocation(86, 0);
		myPanelList.get(1).add(lblNewLabel8);
		JLabel lblNewLabel21 = new JLabel(new ImageIcon(myPicture4));
		lblNewLabel21.setSize(69,159);
		lblNewLabel21.setLocation(97, 233);
		myPanelList.get(2).add(lblNewLabel21);
		
		JLabel A0103 = new JLabel("A0103");
		A0103.setBounds(95, 60, 69, 20);
		A0103.setFont(new Font(A0103.getFont().getName(), A0103.getFont().getStyle(), 18));
		myPanelList.get(2).add(A0103);
		
		
		
		
		
		JLabel lblNewLabel3 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel3.setSize(20, 80);
		lblNewLabel3.setLocation(265, 80);
		myPanelList.get(3).add(lblNewLabel3);
		JLabel lblNewLabel9 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel9.setSize(80,20);
		lblNewLabel9.setLocation(88, 2);
		myPanelList.get(3).add(lblNewLabel9);
		JLabel lblNewLabel22 = new JLabel(new ImageIcon(myPicture4));
		lblNewLabel22.setSize(69,159);
		lblNewLabel22.setLocation(87, 233);
		myPanelList.get(3).add(lblNewLabel22);
		
		JLabel A0104 = new JLabel("A0104");
		A0104.setBounds(95, 60, 69, 20);
		A0104.setFont(new Font(A0104.getFont().getName(), A0104.getFont().getStyle(), 18));
		myPanelList.get(3).add(A0104);
		
		
		JLabel lblNewLabel = new JLabel(new ImageIcon(myPicture));
		lblNewLabel.setSize(20, 80);
		lblNewLabel.setLocation(1, 97);
		myPanelList.get(4).add(lblNewLabel);
		JLabel lblNewLabel23 = new JLabel(new ImageIcon(myPicture3));
		lblNewLabel23.setSize(159,69);
		lblNewLabel23.setLocation(198, 115);
		myPanelList.get(4).add(lblNewLabel23);
		
		
		JLabel A0105 = new JLabel("A0105");
		A0105.setBounds(95, 60, 69, 20);
		A0105.setFont(new Font(A0105.getFont().getName(), A0105.getFont().getStyle(), 18));
		myPanelList.get(4).add(A0105);

		sprinkler=new JLabel(new ImageIcon(myPicture));
		sprinkler.setLocation(100,60);
		myPanelList.get(4).add(sprinkler);
		sprinkler.setVisible(false);
		
		
		JLabel A0106 = new JLabel("A0106");
		A0106.setBounds(95, 60, 69, 20);
		A0106.setFont(new Font(A0106.getFont().getName(), A0106.getFont().getStyle(), 18));
		myPanelList.get(5).add(A0106);
		
		
		JLabel A0107 = new JLabel("A0107");
		A0107.setBounds(95, 60, 69, 20);
		A0107.setFont(new Font(A0107.getFont().getName(), A0107.getFont().getStyle(), 18));
		myPanelList.get(6).add(A0107);
	
		
		
		JLabel lblNewLabel35 = new JLabel(new ImageIcon(myPicture3));
		lblNewLabel35.setSize(159,69);
		lblNewLabel35.setLocation(190, 122);
		myPanelList.get(7).add(lblNewLabel35);
		
		
		JLabel A0108 = new JLabel("A0108");
		A0108.setBounds(95, 60, 69, 20);
		A0108.setFont(new Font(A0108.getFont().getName(), A0108.getFont().getStyle(), 18));
		myPanelList.get(7).add(A0108);
		
		JLabel lblNewLabel4 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel4.setSize(20, 80);
		lblNewLabel4.setLocation(1, 107);
		myPanelList.get(8).add(lblNewLabel4);
		
		
		
		
		JLabel A0109 = new JLabel("A0109");
		A0109.setBounds(95, 60, 69, 20);
		A0109.setFont(new Font(A0109.getFont().getName(), A0109.getFont().getStyle(), 18));
		myPanelList.get(8).add(A0109);
		
		
		JLabel lblNewLabel10 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel10.setSize(80,20);
		lblNewLabel10.setLocation(100, 252);
		myPanelList.get(9).add(lblNewLabel10);
		JLabel lblNewLabel30 = new JLabel(new ImageIcon(myPicture5));
		lblNewLabel30.setSize(69,159);
		lblNewLabel30.setLocation(100,-63);
		myPanelList.get(9).add(lblNewLabel30);
		
		JLabel A0110 = new JLabel("A0110");
		A0110.setBounds(95, 60, 69, 20);
		A0110.setFont(new Font(A0110.getFont().getName(), A0110.getFont().getStyle(), 18));
		myPanelList.get(9).add(A0110);
		
	
		
		
		JLabel lblNewLabel11 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel11.setSize(80,20);
		lblNewLabel11.setLocation(100, 252);
		myPanelList.get(10).add(lblNewLabel11);
		JLabel lblNewLabel31 = new JLabel(new ImageIcon(myPicture5));
		lblNewLabel31.setSize(69,159);
		lblNewLabel31.setLocation(100,-60);
		myPanelList.get(10).add(lblNewLabel31);
		
		
		JLabel A0111 = new JLabel("A0111");
		A0111.setBounds(95, 60, 69, 20);
		A0111.setFont(new Font(A0111.getFont().getName(), A0111.getFont().getStyle(), 18));
		myPanelList.get(10).add(A0111);
		
		
		
		
		JLabel lblNewLabel5 = new JLabel(new ImageIcon(myPicture));
		lblNewLabel5.setSize(20, 80);
		lblNewLabel5.setLocation(265, 81);
		myPanelList.get(11).add(lblNewLabel5);
		JLabel lblNewLabel12 = new JLabel(new ImageIcon(myPicture2));
		lblNewLabel12.setSize(80,20);
		lblNewLabel12.setLocation(85, 252);
		myPanelList.get(11).add(lblNewLabel12);
		JLabel lblNewLabel32 = new JLabel(new ImageIcon(myPicture5));
		lblNewLabel32.setSize(69,159);
		lblNewLabel32.setLocation(100,-60);
		myPanelList.get(11).add(lblNewLabel32);
		
		
		
		JLabel A0112 = new JLabel("A0112");
		A0112.setBounds(95, 60, 69, 20);
		A0112.setFont(new Font(A0112.getFont().getName(), A0112.getFont().getStyle(), 18));
		myPanelList.get(11).add(A0112);
		}
	
	
	
/**
 * Sets the manual.
 *
 * @param flag the new manual
 */
public void setManual(boolean flag)
{
	this.IsManualMode = flag;
}

/**
 * Adds the motion sensor button listeners.
 */
public void addMotionSensorButtonListeners()
{
	for(JToggleButton button:motionSensorButtonList)
	{	
		button.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  JToggleButton b = (JToggleButton) e.getSource();
	    	if(IsManualMode)
	    	{
				int index = motionSensorButtonList.indexOf(b);
				motionSensorList.get(index).toggleStatus();
				System.out.println("clicked motion:-" + motionSensorList.get(index).getStatus());
	    		
	    	}
	    	else
	    	{
	    		ButtonModel buttonModel = b.getModel();
		        boolean selected = buttonModel.isSelected();
		        if(!IsManualMode)
		        {
		        	if(selected)
		        		b.setSelected(false);
		        	else
		        		b.setSelected(true);
		        }
	    		
	    	}
	    	
	      }
	    });	
		
	}
	
}

/**
 * Adds the fire sensor button listeners.
 */
public void addFireSensorButtonListeners()
{
	for(JToggleButton button:fireSensorButtonList)
	{
		button.addActionListener(new ActionListener()
	    {
		
	      public void actionPerformed(ActionEvent e)
	      {
	    	  JToggleButton b = (JToggleButton) e.getSource();
	    	  if(IsManualMode)
		    	{
					int index = fireSensorButtonList.indexOf(b);
					fireSensorList.get(index).toggleStatus();
					System.out.println("clicked fire:-" + fireSensorList.get(index).getStatus());
		    		
		    	}
		    	else
		    	{
		    		ButtonModel buttonModel = b.getModel();
			        boolean selected = buttonModel.isSelected();
			        if(!IsManualMode)
			        {
			        	if(selected)
			        		b.setSelected(false);
			        	else
			        		b.setSelected(true);
			        }
		    		
		    	}
	        
	      }
	    });	
		
	}
}

/**
 * Apply config.
 *
 * @param configuration the configuration
 * @throws Exception the exception
 */
public void applyConfig(Configuration configuration) throws Exception
{
	 configuration.load();
	 int i=0;
	 for(MotionSensor m: motionSensorList)
	 {
		 if(m.getStatus().equals(SensorStatus.ON))
				 motionSensorButtonList.get(i).setSelected(true);
		 else
			 motionSensorButtonList.get(i).setSelected(false);
			 
		// System.out.println("Motion:" + m.getStatus());
		 i++;
	 }
	 
	 i=0;
	 for(FireSensor f: fireSensorList)
	 {
		 if(f.getStatus().equals(SensorStatus.ON))
			 fireSensorButtonList.get(i).setSelected(true);
		 else
			 fireSensorButtonList.get(i).setSelected(false);
		 //System.out.println("Fire:" + f.getStatus());
		 i++;
	 }
}

/**
 * Sets the config mode.
 */
public void setConfigMode()
{
	for(JToggleButton b: motionSensorButtonList)
	{
		b.setSelected(false);
	}
	
	for(JToggleButton b: fireSensorButtonList)
	{
		b.setSelected(false);
	}
	
}

/** The flashtimer. */
private Timer flashtimer;

/** The animation panel. */
private JPanel animationPanel;

/** The color index. */
private int colorIndex =0;

/** The timer listener. */
ActionListener timerListener = new ActionListener() {
	
	// Define an action listener to respond to events
       // from the timer.  When an event is received, the
       // color of the display is changed.
       
    public void actionPerformed(ActionEvent evt) {
        colorIndex++;  // A number between 0 and 100.
        if (colorIndex > 255)
           colorIndex = 0;
       float hue = colorIndex / 100.0F;  // Between 0.0F and 1.0F.
        animationPanel.setBackground(Color.getHSBColor(hue,1,1)); 
    }
 };

/**
 * Start animation.
 *
 * @param panelId the panel id
 */
public void startAnimation(String panelId)
{
	if (flashtimer == null) {
        // Start the animation by creating a Timer that
           // will fire an event every 50 milliseconds, and 
           // will send those events to timerListener.

		flashtimer = new Timer(50, timerListener);
		flashtimer.start();  // Make the time start running.
		animationPanel = sectionPanelMap.get(panelId);
		sprinkler.setVisible(true);
		
		
     }
}

/**
 * Stop animation.
 */
void stopAnimation() {
    // Stop the animation by stopping the timer, unless the
      // animation is not running.

   if (flashtimer != null) {
	   flashtimer.stop();   // Stop the timer.
	   if(animationPanel!=null)
		   animationPanel.setBackground(Color.WHITE);
	   flashtimer = null;   // Set timer variable to null, so that we                 //   can tell that the animation isn't running.
   }
}



/**
 * Sets the flag flash.
 *
 * @param flagFlash the new flag flash
 */
public void setFlagFlash(boolean flagFlash) {
	this.flagFlash = flagFlash;
}

}
