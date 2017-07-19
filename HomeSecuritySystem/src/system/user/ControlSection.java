package system.user;

public class ControlSection {
	/**
	 * area specifying controlling section to verify with sensor area
	 */
	private double area; 
	
	/**
	 * variable setting up unique id for each section
	 */
	private String sectionId;
	/**
	 * variable to tell if motion sensor is present in the section or not
	 */
	private boolean fSensor;
	/**
	 * variable to tell if motion sensor is present in the section or not
	 */
	private boolean mSensor;
	
	public void setArea(double tokens) {
		this.area = tokens;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public void setfSensor(boolean fSensor) {
		this.fSensor = fSensor;
	}
	public void setmSensor(boolean mSensor) {
		this.mSensor = mSensor;
	}
	public double getArea() {
		return area;
	}
	public String getSectionId() {
		return sectionId;
	}
	public boolean isfSensor() {
		return fSensor;
	}
	public boolean ismSensor() {
		return mSensor;
	}
	
	
	
}
