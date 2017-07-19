package system.sensors;


/**
 * The Class MotionSensor.
 */
public class MotionSensor extends Sensor {
	
	/** The data. */
	Double data=10.0;

	/**
	 * Instantiates a new motion sensor.
	 *
	 * @param sectionId the section id
	 * @param status the status
	 */
	public MotionSensor(String sectionId, SensorStatus status) {
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
	

}
