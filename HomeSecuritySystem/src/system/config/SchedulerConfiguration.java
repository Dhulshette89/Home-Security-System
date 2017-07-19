package system.config;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import system.SystemInstaller;
import system.sensors.FireSensor;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;


/**
 * The Class SchedulerConfiguration.
 */
public class SchedulerConfiguration extends Configuration{

	/** The schedule map. */
	private HashMap<String,Schedule> scheduleMap;
	
	/**
	 * Instantiates a new scheduler configuration.
	 */
	public SchedulerConfiguration()
	{
		scheduleMap = new HashMap<String,Schedule>();
		setFireSensorConfigFile("SchedulerFireSensorConfig.txt");
		setMotionSensorConfigFile("SchedulerMotionSensorConfig.txt");
	}
	
	/**
	 * Sets the section schedules.
	 *
	 * @param schedules the schedules
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setSectionSchedules(HashMap<String,Schedule> schedules) throws IOException
	{
		if(scheduleMap == null) scheduleMap = new HashMap<String,Schedule>();
		
		for(String section: schedules.keySet())
		{
			scheduleMap.put(section, schedules.get(section));
		}
		this.save();
	}
	
	/* (non-Javadoc)
	 * @see system.config.Configuration#save()
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
			if(scheduleMap.containsKey(f.getScetionId()))
				fireData.add("ON " + scheduleMap.get(f.getScetionId()).getHourFrom() + ":" + scheduleMap.get(f.getScetionId()).getMinuteFrom()
								   + " " + scheduleMap.get(f.getScetionId()).getHourTo() + ":" + scheduleMap.get(f.getScetionId()).getMinuteTo() +"\n");
			else
				fireData.add("OFF" + "\n");
		}
		
		for(MotionSensor m: motionSensorList)
		{
			if(scheduleMap.containsKey(m.getScetionId()))
				motionData.add("ON " + scheduleMap.get(m.getScetionId()).getHourFrom() + ":" + scheduleMap.get(m.getScetionId()).getMinuteFrom() 
									 +" "+ scheduleMap.get(m.getScetionId()).getHourTo() + ":" + scheduleMap.get(m.getScetionId()).getMinuteTo()+"\n");
			else
				motionData.add("OFF" + "\n");
		}
		
		FileUtil.wrtieToFile(fireSensorConfigFile, fireData);
		FileUtil.wrtieToFile(motionSensorConfigFile, motionData);
		
	}
	
	/* (non-Javadoc)
	 * @see system.config.Configuration#load()
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
			String[] tokens = status.split(" ");
			if(tokens.length ==3)
			{
				
				String[] start = tokens[1].split(":");
				String[] end   = tokens[2].split(":");
			
				int startH = Integer.valueOf(start[0]);
				int startM = Integer.valueOf(start[1]);
				int endH = Integer.valueOf(end[0]);
				int endM = Integer.valueOf(end[1]);
				
				Calendar now = Calendar.getInstance();
				Calendar startTime = Calendar.getInstance();
				startTime.set(Calendar.HOUR_OF_DAY, startH);
				startTime.set(Calendar.MINUTE, startM);
				startTime.set(Calendar.SECOND, 0);
			
				Calendar endTime = Calendar.getInstance();
				endTime.set(Calendar.HOUR_OF_DAY, endH);
				endTime.set(Calendar.MINUTE, endM);
				endTime.set(Calendar.SECOND, 0);
				
				if(startTime.before(now) && endTime.after(now))	  
				{
				
					fireSensorList.get(i).setStatus(SensorStatus.valueOf("ON"));
				}
				else
				{
					fireSensorList.get(i).setStatus(SensorStatus.valueOf("OFF"));
				}
			}
			i++;
		}
		
		i=0;
		List<String> motionData = FileUtil.readFile(motionSensorConfigFile);
		for(String status: motionData)
		{
			
			String[] tokens = status.split(" ");
			if(tokens.length ==3)
			{
				
				String[] start = tokens[1].split(":");
				String[] end   = tokens[2].split(":");
				
				int startH = Integer.valueOf(start[0]);
				int startM = Integer.valueOf(start[1]);
				int endH = Integer.valueOf(end[0]);
				int endM = Integer.valueOf(end[1]);
				
				Calendar now = Calendar.getInstance();
				Calendar startTime = Calendar.getInstance();
				startTime.set(Calendar.HOUR_OF_DAY, startH);
				startTime.set(Calendar.MINUTE, startM);
				startTime.set(Calendar.SECOND, 0);
			
				Calendar endTime = Calendar.getInstance();
				endTime.set(Calendar.HOUR_OF_DAY, endH);
				endTime.set(Calendar.MINUTE, endM);
				endTime.set(Calendar.SECOND, 0);
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
				
				/*
				System.out.println("Start Time:" + dateFormat.format(startTime.getTime()));
				System.out.println("End Time:" + dateFormat.format(endTime.getTime()));
				System.out.println("Now:" + dateFormat.format(now.getTime()));
				*/
				
				if(startTime.before(now) && endTime.after(now))  
						  
					{
						motionSensorList.get(i).setStatus(SensorStatus.valueOf("ON"));
					}
					else
					{
						motionSensorList.get(i).setStatus(SensorStatus.valueOf("OFF"));
					}
				
			}
			
			i++;
		}	
	}
	
	
	
}
