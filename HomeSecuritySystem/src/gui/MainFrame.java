package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import system.SystemInstaller;
import system.SystemScheduler;
import system.alert.DispatchAlert;
import system.config.ManualConfiguration;
import system.config.Schedule;
import system.config.SchedulerConfiguration;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;

/**
 * The Class MainFrame extends JFrame so this is a frame
 */
public class MainFrame extends JFrame 
{	
	/** The user selection. */
	UserSelection userSelection;
	
	/** The sensor list. */
	List<FireSensor> fSensorList;
	
	/** The m sensor list. */
	List<MotionSensor> mSensorList;
	
	/** The button list. */
	ArrayList<String> fButtonList= new ArrayList<String>();
	
	/** The m button list. */
	ArrayList<String> mButtonList= new ArrayList<String>();
	
	/** The mcheck box list. */
	ArrayList<JCheckBoxMenuItem> mcheckBoxList= new ArrayList<JCheckBoxMenuItem>();
	
	/** The fcheck box list. */
	ArrayList<JCheckBoxMenuItem> fcheckBoxList= new ArrayList<JCheckBoxMenuItem>();
	
	/** The disable. */
	JPanel disable;
	
	/** The scheduler. */
	SystemScheduler scheduler;
	
	/** The security layout. */
	SecurityLayout securityLayout;
	 
 	/** The window. */
 	NewLogin window;
	 
 	/** The check. */
 	boolean check;
	
	/** The j. */
	int j;
	
	/** The blank panel. */
	JPanel blankPanel;

	
	/**
	 * Instantiates a new main frame.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MainFrame() throws IOException{
		super("My Home Security System");
		SystemInstaller system= SystemInstaller.getInstance();
		blankPanel= new JPanel();
		blankPanel.setBackground(Color.WHITE);
		
		fSensorList = system.getFireSensorList();
		mSensorList = system.getMotionSensorList();
		setJMenuBar(createMenuBar());
		
		for(MotionSensor mSensor:mSensorList)
		{
			mButtonList.add(mSensor.getScetionId());
		}
		for(FireSensor fSensor:fSensorList)
		{
			fButtonList.add(fSensor.getScetionId());
		}
		
		securityLayout=new SecurityLayout(mSensorList,fSensorList);
		//SecurityLayout securityLayout=new SecurityLayout();
		userSelection = new UserSelection();
		userSelection.setBounds(0, 0, 650, 1000);
		securityLayout.setBounds(650, 0, 1200,1000);
		JButton manualButton=userSelection.getManualButton();
		
		SchedulerConfiguration schedulerConfig = new SchedulerConfiguration();
	    scheduler = new SystemScheduler(schedulerConfig, securityLayout);
	    scheduler.start();
	    
	    ManualConfiguration config = new ManualConfiguration();
	    try {
			securityLayout.applyConfig(config);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
		
		manualButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				securityLayout.setManual(true);
				//securityLayout.setConfigMode();
				scheduler.stopThread();
				
				try {
					securityLayout.applyConfig(config);
					//config.load();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				
				JPanel save=new JPanel();
				JButton saveButton=new JButton("Save");
				save.add(saveButton);
				save.setBackground(Color.WHITE);
				save.setLayout(new GridBagLayout());
				userSelection.remove(userSelection.getSavePanel());
				userSelection.setSavePanel(save);
				userSelection.add(userSelection.getSavePanel());
				userSelection.repaint();
				userSelection.revalidate();
				saveButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						securityLayout.setManual(false);
						ManualConfiguration config = new ManualConfiguration();
						try {
							config.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						JOptionPane.showMessageDialog(securityLayout,"Configuration is saved.");
						userSelection.remove(userSelection.getSavePanel());
						userSelection.setSavePanel(blankPanel);
						userSelection.add(userSelection.getSavePanel());
						userSelection.repaint();
						userSelection.revalidate();
					}
					
				});
				}	
		});
		JButton scheduleButton=userSelection.getNewScheduleButton();
		scheduleButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SchedulePanel sPanel=new SchedulePanel(fSensorList,mSensorList);
				//sPanel.setBounds(650, 0, 1200,1000);
				sPanel.getJp().setBackground(Color.WHITE);
				userSelection.remove(userSelection.getSavePanel());
				userSelection.setSavePanel(sPanel.getJp());
				userSelection.add(userSelection.getSavePanel());
				userSelection.repaint();
				userSelection.revalidate();
				
				JButton save=sPanel.getSave();
				save.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						ArrayList<JCheckBox> mCheckList = sPanel.getmCheckBoxList();
						ArrayList<JCheckBox> fCheckList = sPanel.getfCheckBoxList();
						
						HashMap<String,Schedule> scheduleMap = new HashMap<String,Schedule>();
						
						String fromHourStr = sPanel.getFromhour().getSelectedItem().toString();
						int fromHour = fromHourStr.charAt(0)=='0'?Integer.valueOf("" + fromHourStr.charAt(1)):Integer.valueOf(fromHourStr);
						
						String fromMinStr  = sPanel.getFrommin().getSelectedItem().toString();
						int fromMin = fromMinStr.charAt(0)=='0'?Integer.valueOf("" + fromMinStr.charAt(1)):Integer.valueOf(fromMinStr);
						
						String toHourStr = sPanel.getTohour().getSelectedItem().toString();
						int toHour = toHourStr.charAt(0)=='0'?Integer.valueOf("" + toHourStr.charAt(1)):Integer.valueOf(toHourStr);
						
						String toMinStr = sPanel.getTomin().getSelectedItem().toString();
						int toMin = toMinStr.charAt(0)=='0'?Integer.valueOf("" + toMinStr.charAt(1)):Integer.valueOf(toMinStr);

						
						
						for(JCheckBox c: mCheckList)
						{
							if(c.isSelected())
							{
								Schedule s = new Schedule();
								s.setHourFrom(fromHour);
								s.setMinuteFrom(fromMin);
								s.setHourTo(toHour);
								s.setMinuteTo(toMin);
								scheduleMap.put(c.getText(), s);
							}
						}
						
						for(JCheckBox c: fCheckList)
						{
							if(c.isSelected())
							{
								Schedule s = new Schedule();
								s.setHourFrom(fromHour);
								s.setMinuteFrom(fromMin);
								s.setHourTo(toHour);
								s.setMinuteTo(toMin);
								scheduleMap.put(c.getText(), s);
							}
						}
						
						try {
							schedulerConfig.setSectionSchedules(scheduleMap);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						scheduler.stopThread();
						scheduler = new SystemScheduler(schedulerConfig, securityLayout);
						scheduler.start();
						JOptionPane.showMessageDialog(securityLayout,"Schedule is saved.");
						userSelection.remove(userSelection.getSavePanel());
						userSelection.setSavePanel(blankPanel);
						userSelection.add(userSelection.getSavePanel());
						userSelection.repaint();
						userSelection.revalidate();
					}
					
				});
				
			}
			
		});
		
		JButton disableSystemButton=userSelection.getDisableSystemButton();
		disableSystemButton.addActionListener(new ActionListener(){
			

			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				system.setUserLoggedIn(false);
					
				if(!system.isSystemEnabled()) return;
				system.getFireMonitor().setFlag(false);
				system.getMotionMonitor().setFlag(false);
				if(system.getSound()!=null) system.getSound().StopSound();
				disable=new JPanel();
				disable.setBounds(650, 0,1200,1000);
				disable.setBackground(Color.WHITE);
				disable.setLayout(new GridBagLayout());
				JLabel diableLabel=new JLabel("System is Disabled!!! ");
				diableLabel.setFont(new Font(diableLabel.getFont().getName(), diableLabel.getFont().getStyle(), 60));
				diableLabel.setPreferredSize(new Dimension(700, 125));
				diableLabel.setForeground(Color.RED);
				disable.add(diableLabel);
				remove(securityLayout);
				add(disable);
				repaint();
				revalidate();	
				userSelection.remove(userSelection.getSavePanel());
				userSelection.setSavePanel(blankPanel);
				userSelection.add(userSelection.getSavePanel());
				userSelection.repaint();
				userSelection.revalidate();
				system.setSystemEnabled(false);
				securityLayout.setFlagFlash(false);
				securityLayout.stopAnimation();
				
			}
			
		});
		
		
		JButton enable=userSelection.getEnableSystemButton();
		enable.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(system.isSystemEnabled())
				{
					return;
				}
				
				for(MotionSensor mSensor:mSensorList)
				{
					mSensor.setData((double) 10);
				}
				for(FireSensor fSensor:fSensorList)
				{
					fSensor.setData((double) 10);
				}

				system.setSystemEnabled(true);
				system.startMonitoring();
				JPanel enable=new JPanel();
				JLabel enableLabel=new JLabel("System is Enabled successfully!");
				enableLabel.setFont(new Font(enableLabel.getFont().getName(), enableLabel.getFont().getStyle(), 30));
				enableLabel.setPreferredSize(new Dimension(600, 125));
				enableLabel.setForeground(new Color(0,102,51));
				enable.add(enableLabel);
				enable.setBounds(650, 0,1200,1000);
				enable.setBackground(Color.WHITE);
				enable.setLayout(new GridBagLayout());
				userSelection.remove(userSelection.getSavePanel());
				userSelection.setSavePanel(enable);
				userSelection.add(userSelection.getSavePanel());
				userSelection.repaint();
				userSelection.revalidate();
				remove(disable);
				add(securityLayout);
				repaint();
				revalidate();	
				
			}
			
		});
		JButton logout=userSelection.getlogoutButtonButton();
		logout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SwingUtilities.invokeLater(new Runnable() {
			           public void run() {
			               try {
			                   NewLogin window = new NewLogin();
			                   window.setVisible(true);
			               } catch (Exception e) {
			                   e.printStackTrace();
			               }
			           }
			       });
				
            	
				
			}
			
		});
		JButton bill=userSelection.getViewBillButton();
		bill.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
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
						
			
			
		});
		
		setLayout(new BorderLayout());
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,screen.width,screen.height - 30);
		setLayout(null);
		this.setSize(1850,1000);
			
		add(securityLayout);
		add(userSelection);
		setResizable(false);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	/**
	 * Creates the menu bar.
	 *
	 * @return the j menu bar
	 */
	private JMenuBar createMenuBar() {
JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("MalFunction");
		
		JMenuItem menuDataItem = new JMenuItem("Simulatemalfunction");

		fileMenu.add(menuDataItem);
		
		
		JMenu breakinMenu = new JMenu("Break-in Simulation");
		JMenu fireMenu = new JMenu("Fire Simulation");
		for(MotionSensor mSensor:mSensorList)
		{
			String s=mSensor.getScetionId();
			JCheckBoxMenuItem box=new JCheckBoxMenuItem(s);
			breakinMenu.add(box);
			mcheckBoxList.add(box);
		}
		for(FireSensor fSensor:fSensorList)
		{
			String s=fSensor.getScetionId();
			JCheckBoxMenuItem box=new JCheckBoxMenuItem(s);
			fireMenu.add(box);
			fcheckBoxList.add(box);
		}
		for(JCheckBoxMenuItem item:mcheckBoxList)
		{
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev) {
				
				for(MotionSensor mSensor:mSensorList)
				{
					if(mSensor.getScetionId().equalsIgnoreCase(item.getText()) && mSensor.getStatus().equals(SensorStatus.ON))
					{
						mSensor.setData((double) 100);
						System.out.println("setting data"+mSensor.getData()+" "+mSensor.getScetionId());
						securityLayout.startAnimation(item.getText());
						final ImageIcon icon = new ImageIcon("thief.gif");
				        JOptionPane.showMessageDialog(userSelection, "", "Break-in"+mSensor.getScetionId()+" Security breach!!", JOptionPane.INFORMATION_MESSAGE, icon);
					}
				}
				}
			});

		}
		for(JCheckBoxMenuItem item:fcheckBoxList)
		{
			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ev)	{
					
				for(FireSensor fSensor:fSensorList)
				{
					if(fSensor.getScetionId().equalsIgnoreCase(item.getText()) && fSensor.getStatus().equals(SensorStatus.ON))
					{
						fSensor.setData((double) 100);
						System.out.println("setting data"+fSensor.getData()+" "+fSensor.getScetionId());
						securityLayout.startAnimation(item.getText());
						final ImageIcon icon = new ImageIcon("sprinkler.gif");
				        JOptionPane.showMessageDialog(userSelection, "", "Fire in "+fSensor.getScetionId()+" System started Sprinkler", JOptionPane.INFORMATION_MESSAGE, icon);
					}
				}
				}
			});
			menuDataItem.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DispatchAlert dis=new DispatchAlert(securityLayout);
				}
				
			});

		}
		
		menuBar.add(breakinMenu);
		menuBar.add(fireMenu);
		menuBar.add(fileMenu);
		
		return menuBar;
	}
}
