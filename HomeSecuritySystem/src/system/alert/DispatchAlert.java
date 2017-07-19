package system.alert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import  sun.audio.*;
import system.SystemInstaller;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;


/**
 * The Class DispatchAlert.
 */
public class DispatchAlert {
	
	/** The pc. */
	PhoneCall pc;
	
	/** The sound. */
	Sound sound;
	
	
	
	/**
	 * Instantiates a new dispatch alert.
	 *
	 * @param mSensor the m sensor
	 * @throws URISyntaxException the URI syntax exception
	 */
	public DispatchAlert(MotionSensor mSensor) throws URISyntaxException
	
	{	
		pc = new PhoneCall();
		pc.call();
		BufferedWriter bw = null;
		FileWriter fw = null;
		try
		{

			File file = new File("callLogging.txt");
			String section=mSensor.getScetionId();
			DateFormat dateFormatMotion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	 	 
			Date date = new Date(); 	 		
			String dateMotion = dateFormatMotion.format(date);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write("Motion" + " " + dateMotion +" "+section+"\n");
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		sound=new Sound("motion.wav");
		try {
			SystemInstaller.getInstance().setSound(sound);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 * Instantiates a new dispatch alert.
	 *
	 * @param j the j
	 */
	public DispatchAlert(JPanel j)
	{
		JOptionPane.showMessageDialog(j, "System is malfunctioning!Please call for support +660-770-8800");
	}
	
	/**
	 * Instantiates a new dispatch alert.
	 *
	 * @param fSensor the f sensor
	 * @throws URISyntaxException the URI syntax exception
	 */
	public DispatchAlert(FireSensor fSensor) throws URISyntaxException
	{	
		pc = new PhoneCall();
		pc.call();
		sound=new Sound("fire.wav");
		try {
			SystemInstaller.getInstance().setSound(sound);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			SystemInstaller.getInstance().setSound(sound);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = null;
		FileWriter fw = null;
		try
		{

			File file = new File("callLogging.txt");
			String section=fSensor.getScetionId();
			DateFormat dateFormatMotion = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 	 	 
			Date date = new Date(); 	 		
			String dateMotion = dateFormatMotion.format(date);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write("Fire" + " " + dateMotion +" "+section+"\n");
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
	}
	
	/**
	 * Gets the sound.
	 *
	 * @return the sound
	 */
	public Sound getSound() {
		return sound;
	}
	
}




