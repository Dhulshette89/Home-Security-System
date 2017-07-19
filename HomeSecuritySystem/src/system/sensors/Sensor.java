package system.sensors;

/**
 * The Class Sensor.
 */
public abstract class Sensor {

	/** The section id. */
	private String sectionId;
	
	/** The status. */
	private SensorStatus status;
	
	/**
	 * Instantiates a new sensor.
	 *
	 * @param sectionId the section id
	 * @param status the status
	 */
	public Sensor(String sectionId ,SensorStatus status)
	{
		this.sectionId=sectionId;
		this.status=status;
	}
	
	/**
	 * Toggle status.
	 */
	public void toggleStatus()
	{
		if(getStatus()==SensorStatus.ON)
		{
			setStatus(SensorStatus.OFF);
		}
		else if(getStatus()==SensorStatus.OFF)
		{
			setStatus(SensorStatus.ON);
		}
	}

	/**
	 * Gets the scetion id.
	 *
	 * @return the scetion id
	 */
	public String getScetionId() {
		return sectionId;
	}
	
	/**
	 * Sets the scetion id.
	 *
	 * @param scetionId the new scetion id
	 */
	public void setScetionId(String scetionId) {
		this.sectionId = scetionId;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public SensorStatus getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(SensorStatus status) {
		this.status = status;
	}
	
}
