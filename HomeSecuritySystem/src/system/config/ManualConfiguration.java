package system.config;



/**
 * The Class ManualConfiguration.
 */
public class ManualConfiguration extends Configuration{

	/**
	 * Instantiates a new manual configuration.
	 */
	public ManualConfiguration()
	{
		setFireSensorConfigFile("ManualFireSensorConfig.txt");
		setMotionSensorConfigFile("ManualMotionSensorConfig.txt");
	}

}
