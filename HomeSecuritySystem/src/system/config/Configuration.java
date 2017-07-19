package system.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import system.SystemInstaller;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;


/**
 * The Class Configuration.
 */
public class Configuration {

	/** The fire sensor config file. */
	String fireSensorConfigFile;
	
	/**
	 * Gets the fire sensor config file.
	 *
	 * @return the fire sensor config file
	 */
	public String getFireSensorConfigFile() {
		return fireSensorConfigFile;
	}

	/**
	 * Sets the fire sensor config file.
	 *
	 * @param fireSensorConfigFile the new fire sensor config file
	 */
	public void setFireSensorConfigFile(String fireSensorConfigFile) {
		this.fireSensorConfigFile = fireSensorConfigFile;
	}

	/** The motion sensor config file. */
	String motionSensorConfigFile;
	
	/**
	 * Gets the motion sensor config file.
	 *
	 * @return the motion sensor config file
	 */
	public String getMotionSensorConfigFile() {
		return motionSensorConfigFile;
	}

	/**
	 * Sets the motion sensor config file.
	 *
	 * @param motionSensorConfigFile the new motion sensor config file
	 */
	public void setMotionSensorConfigFile(String motionSensorConfigFile) {
		this.motionSensorConfigFile = motionSensorConfigFile;
	}

	/**
	 * Load.
	 *
	 * @throws Exception the exception
	 */
	public void load() throws Exception
	{
		SystemInstaller s = SystemInstaller.getInstance();
		List<FireSensor> fireSensorList = s.getFireSensorList();
		List<MotionSensor> motionSensorList = s.getMotionSensorList();
		
		List<String> fireData = FileUtil.readFile(fireSensorConfigFile);
		int i =0;
		for(String status: fireData)
		{
			fireSensorList.get(i).setStatus(SensorStatus.valueOf(status));
			i++;
		}
		
		i=0;
		List<String> motionData = FileUtil.readFile(motionSensorConfigFile);
		for(String status: motionData)
		{
			motionSensorList.get(i).setStatus(SensorStatus.valueOf(status));
			i++;
		}	
	}
	
	/**
	 * Save.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void save() throws IOException
	{
		// TODO Auto-generated method stub
		SystemInstaller s = SystemInstaller.getInstance();
		List<FireSensor> fireSensorList = s.getFireSensorList();
		List<MotionSensor> motionSensorList = s.getMotionSensorList();
		
		List<String> fireData = new ArrayList<String>();
		List<String> motionData = new ArrayList<String>();
		
		for(FireSensor f: fireSensorList)
		{
			fireData.add(f.getStatus() + "\n");
		}
		
		for(MotionSensor m: motionSensorList)
		{
			motionData.add(m.getStatus() + "\n");
		}
		
		FileUtil.wrtieToFile(fireSensorConfigFile, fireData);
		FileUtil.wrtieToFile(motionSensorConfigFile, motionData);
		
	}
}
