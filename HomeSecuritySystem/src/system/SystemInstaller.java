package system;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import system.alert.Sound;
import system.monitor.FireMonitor;
import system.monitor.MotionMonitor;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;
import system.user.ControlSection;
import system.user.UserAccount;


/**
 * The Class SystemInstaller.
 */
public class SystemInstaller 
{

/** The user. */
private UserAccount user;

/** The motion sensor list. */
private List<MotionSensor> motionSensorList;

/** The fire sensor list. */
private List<FireSensor> fireSensorList;

/** The control section list. */
private List<ControlSection> controlSectionList;

/** The motion monitor. */
private MotionMonitor motionMonitor;

/** The fire monitor. */
private FireMonitor fireMonitor;

/** The sound. */
private Sound sound;

/** The instance. */
private static SystemInstaller instance;

/** The is system enabled. */
private boolean isSystemEnabled;

/** The is user logged in. */
private boolean isUserLoggedIn = false;

/**
 * Instantiates a new system installer.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
private SystemInstaller() throws IOException
{
	isSystemEnabled = true;
	user = new UserAccount();
	fireSensorList = new ArrayList<FireSensor>();
	motionSensorList = new ArrayList<MotionSensor>();
	
	controlSectionList=user.getControlSectionList();
	for(ControlSection control:controlSectionList)
	{
		if(control.isfSensor())
		{
			FireSensor fSensor = new FireSensor(control.getSectionId(), SensorStatus.OFF);
			fireSensorList.add(fSensor);
		}
		if(control.ismSensor())
		{
			MotionSensor mSensor = new MotionSensor(control.getSectionId(), SensorStatus.OFF);
			motionSensorList.add(mSensor);
		}
		
	}
	
	startMonitoring();
}

/**
 * Start monitoring.
 */
public void startMonitoring()
{
	motionMonitor=new MotionMonitor(motionSensorList);
	motionMonitor.start();
	fireMonitor=new FireMonitor(fireSensorList);
	fireMonitor.start();
	
}


/**
 * Gets the single instance of SystemInstaller.
 *
 * @return single instance of SystemInstaller
 * @throws IOException Signals that an I/O exception has occurred.
 */
public static SystemInstaller getInstance() throws IOException
{
	if(instance == null)
	{
		instance = new SystemInstaller();
	}
	
	return instance;
}

/**
 * Gets the motion monitor.
 *
 * @return the motion monitor
 */
public MotionMonitor getMotionMonitor() {
	return motionMonitor;
}

/**
 * Gets the fire monitor.
 *
 * @return the fire monitor
 */
public FireMonitor getFireMonitor() {
	return fireMonitor;
}

/**
 * Gets the motion sensor list.
 *
 * @return the motion sensor list
 */
public List<MotionSensor> getMotionSensorList() {
	return motionSensorList;
}

/**
 * Gets the fire sensor list.
 *
 * @return the fire sensor list
 */
public List<FireSensor> getFireSensorList() {
	return fireSensorList;
}

/**
 * Gets the sound.
 *
 * @return the sound
 */
public Sound getSound() {
	return sound;
}

/**
 * Gets the user.
 *
 * @return the user
 */
public UserAccount getUser() {
	return user;
}

/**
 * Sets the sound.
 *
 * @param sound the new sound
 */
public void setSound(Sound sound) {
	this.sound = sound;
}

/**
 * Checks if is system enabled.
 *
 * @return true, if is system enabled
 */
public boolean isSystemEnabled() {
	return isSystemEnabled;
}

/**
 * Sets the system enabled.
 *
 * @param isSystemEnabled the new system enabled
 */
public void setSystemEnabled(boolean isSystemEnabled) {
	this.isSystemEnabled = isSystemEnabled;
}

/**
 * Sets the user logged in.
 *
 * @param b the new user logged in
 */
public void setUserLoggedIn(boolean b) {
	// TODO Auto-generated method stub
	this.isUserLoggedIn = b;
	
}

/**
 * Checks if is user logged in.
 *
 * @return true, if is user logged in
 */
public boolean isUserLoggedIn() {
	// TODO Auto-generated method stub
	return isUserLoggedIn;
}

}
