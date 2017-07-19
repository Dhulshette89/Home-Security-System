package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.sensors.FireSensor;
import system.sensors.MotionSensor;



/**
 * The Class SchedulePanel.
 */
public class SchedulePanel extends JPanel
{	
	/** The jp. */
	JPanel jp;
	
	/** The hour string. */
	String[] hourString = { "00", "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","00"  };

/** The min string. */
String[] minString = {"00", "01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24",
					   "25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};

/** The m check box list. */
ArrayList<JCheckBox> mCheckBoxList=new ArrayList<JCheckBox>();

/** The check box list. */
ArrayList<JCheckBox> fCheckBoxList=new ArrayList<JCheckBox>();

/** The fromhour. */
JComboBox fromhour;

/** The frommin. */
JComboBox frommin;

/** The save. */
JButton save;

/** The tohour. */
JComboBox tohour;

/** The tomin. */
JComboBox tomin;
	
	/**
	 * Instantiates a new schedule panel.
	 *
	 * @param fSensorList the f sensor list
	 * @param mSensorList the m sensor list
	 */
	public SchedulePanel(List<FireSensor>fSensorList,List <MotionSensor>mSensorList)
	
	{	 jp=new JPanel();
	
		jp.setLayout(new GridLayout(0, 2));	
		JLabel fromTime=new JLabel("FROM_TIME ");
		fromTime.setFont(new Font(fromTime.getFont().getName(), fromTime.getFont().getStyle(), 18));
		JLabel toTime=new JLabel("TO_TIME ");
		toTime.setFont(new Font(toTime.getFont().getName(), toTime.getFont().getStyle(), 18));
		fromhour= new JComboBox(hourString);
		fromhour.setFont(new Font(fromhour.getFont().getName(), fromhour.getFont().getStyle(), 20));
		frommin= new JComboBox(minString);
		frommin.setFont(new Font(frommin.getFont().getName(), frommin.getFont().getStyle(), 20));
		tohour= new JComboBox(hourString);
		tohour.setFont(new Font(tohour.getFont().getName(), tohour.getFont().getStyle(), 20));
		tomin= new JComboBox(minString);
		tomin.setFont(new Font(tomin.getFont().getName(), tomin.getFont().getStyle(), 20));
		JPanel p1=new JPanel();
		p1.setBackground(Color.WHITE);
		JPanel p2=new JPanel();
		p2.setBackground(Color.WHITE);
		JPanel p3= new JPanel();
		p3.setBackground(Color.WHITE);
		JPanel p4=new JPanel();
		p4.setBackground(Color.WHITE);
		JPanel p5=new JPanel();
		p5.setBackground(Color.WHITE);
		JPanel p6=new JPanel();
		p6.setBackground(Color.WHITE);
		JPanel p7=new JPanel();
		p7.setBackground(Color.WHITE);
		for(FireSensor fSensor:fSensorList)
		{
			String s=fSensor.getScetionId();
			JCheckBox box= new JCheckBox(s);
			box.setFont(new Font(box.getFont().getName(), box.getFont().getStyle(), 18));
			p4.add(box);
			fCheckBoxList.add(box);
		}
		for(MotionSensor mSensor:mSensorList)
		{
			String s=mSensor.getScetionId();
			JCheckBox box1= new JCheckBox(s);
			box1.setFont(new Font(box1.getFont().getName(), box1.getFont().getStyle(), 18));
			p6.add(box1);
			mCheckBoxList.add(box1);
		}
		
		
	
		
		JLabel fireLabel=new JLabel("FireSensors present in");
		fireLabel.setFont(new Font(fireLabel.getFont().getName(), fireLabel.getFont().getStyle(), 18));
		JLabel motionLabel=new JLabel("MotionSensors present in");
		motionLabel.setFont(new Font(motionLabel.getFont().getName(), motionLabel.getFont().getStyle(), 18));
		p1.add(fromTime);
		p1.add(fromhour);
		p1.add(frommin);
		p2.add(toTime);
		p2.add(tohour);
		p2.add(tomin);
		p3.add(fireLabel);
		p5.add(motionLabel);
		
		
		save=new JButton("SAVE SCHEDULE");
		save.setFont(new Font(save.getFont().getName(), save.getFont().getStyle(), 20));
		p7.add(save);
		
		
		
		JPanel p8=new JPanel();
		p8.setBackground(Color.white);
	
		
		jp.add(p1);
		jp.add(p2);
		jp.add(p3);
		jp.add(p4);
		jp.add(p5);
		jp.add(p6);
		jp.add(p8);
		jp.add(p7);
		
}
	
	/**
	 * Gets the hour string.
	 *
	 * @return the hour string
	 */
	public String[] getHourString() {
		return hourString;
	}

	/**
	 * Gets the m check box list.
	 *
	 * @return the m check box list
	 */
	public ArrayList<JCheckBox> getmCheckBoxList() {
		return mCheckBoxList;
	}

	/**
	 * Gets the f check box list.
	 *
	 * @return the f check box list
	 */
	public ArrayList<JCheckBox> getfCheckBoxList() {
		return fCheckBoxList;
	}

	/**
	 * Gets the fromhour.
	 *
	 * @return the fromhour
	 */
	public JComboBox getFromhour() {
		return fromhour;
	}

	/**
	 * Gets the frommin.
	 *
	 * @return the frommin
	 */
	public JComboBox getFrommin() {
		return frommin;
	}

	/**
	 * Gets the save.
	 *
	 * @return the save
	 */
	public JButton getSave() {
		return save;
	}

	/**
	 * Gets the tohour.
	 *
	 * @return the tohour
	 */
	public JComboBox getTohour() {
		return tohour;
	}

	/**
	 * Gets the tomin.
	 *
	 * @return the tomin
	 */
	public JComboBox getTomin() {
		return tomin;
	}

	/**
	 * Gets the jp.
	 *
	 * @return the jp
	 */
	public JPanel getJp() {
		return jp;
	}

}