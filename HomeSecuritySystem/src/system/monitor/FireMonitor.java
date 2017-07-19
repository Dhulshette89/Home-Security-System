package system.monitor;

import java.net.URISyntaxException;
import java.util.List;

import system.alert.DispatchAlert;
import system.sensors.FireSensor;
import system.sensors.SensorStatus;


/**
 * The Class FireMonitor.
 */
public class FireMonitor implements Runnable{
	
	/** The fire sensor list. */
	private List<FireSensor> fireSensorList;
	
	/** The t. */
	private Thread t;
	
	/** The flag. */
	Boolean flag=true;
	
	/** The data. */
	float data;
	
	/** The threshold. */
	Double threshold = 10.0;
	
	/**
	 * Instantiates a new fire monitor.
	 *
	 * @param fireSensorList the fire sensor list
	 */
	public FireMonitor(List<FireSensor> fireSensorList) {
		//super(sectionId, status);
		System.out.println("Monitor fire created");
		this.fireSensorList=fireSensorList;
		}	
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		 //
		System.out.println("Runnning Fire thread");
		 while(flag)
		 {
			 
			 for(FireSensor fSensor:fireSensorList)
			 {	if(fSensor.getStatus()==SensorStatus.OFF)
				 	continue;
			 	 if(fSensor.getData()>threshold)
				
				{
					try {
						DispatchAlert dispatchAlert= new DispatchAlert(fSensor);
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
			 if(flag==false)
					break;
				 //break from here if alarm is sounded.
				 try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			 }
		 	 
		 }
	
	/**
	 * Start.
	 */
	public void start () {
	      System.out.println("Starting Fire thread" );
	      if (t == null) {
	         t = new Thread (this, "Fire thread");
	         t.start ();
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
	
	}

