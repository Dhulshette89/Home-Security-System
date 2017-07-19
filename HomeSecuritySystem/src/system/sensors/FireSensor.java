package system.sensors;

import java.util.Observable;
import java.util.Observer;


/**
 * The Class FireSensor.
 */
public class FireSensor extends Sensor implements Observer {
	
	/** The data. */
	Double data = 10.0;

	/**
	 * Instantiates a new fire sensor.
	 *
	 * @param sectionId the section id
	 * @param status the status
	 */
	public FireSensor(String sectionId, SensorStatus status)
	{
		super(sectionId, status);
		
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Double getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Double data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	

}
