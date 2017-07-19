package system.monitor;

import java.net.URISyntaxException;
import java.util.List;

import system.alert.DispatchAlert;
import system.sensors.MotionSensor;
import system.sensors.SensorStatus;


/**
 * The Class MotionMonitor.
 */
public class MotionMonitor implements Runnable{
	
	/** The motion sensor list. */
	private List<MotionSensor> motionSensorList;
	
	/** The t. */
	private Thread t;
	
	/** The flag. */
	Boolean flag=true;
	
	/** The data. */
	float data;
	
	/** The threshold. */
	Double threshold = 10.0;
	
	/**
	 * Instantiates a new motion monitor.
	 *
	 * @param motionSensorList the motion sensor list
	 */
	public MotionMonitor(List<MotionSensor> motionSensorList) {
		//super(sectionId, status);
		System.out.println("Monitor Thread created");
		
		this.motionSensorList=motionSensorList;
		}	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// System.out.println("Running monitor thread");
		 //
		System.out.println("Running Monitor thread");
		 while(flag)
		 {
			 
			 for(MotionSensor mSensor:motionSensorList)
			 {
				 if(mSensor.getStatus()==SensorStatus.OFF)
				 	continue;
				 if(mSensor.getData()>threshold)
				{
					try {
						DispatchAlert dispatchAlert= new DispatchAlert(mSensor);
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					flag=false;
				}
				if (flag==false)
					break;
					//crossed threshold the alarm.
				}

				 //break from here if alarm is sounded.
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }
		 	 
		 }
	
	
	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}
	
	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	/**
	 * Start.
	 */
	public void start () {
	      System.out.println("Starting motion thread" );
	      if (t == null) {
	         t = new Thread (this, "motion thread");
	         t.start ();
	      }
	   }
	}

