package system.user;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class UserAccount.
 */
public class UserAccount {
	
	/** The user name. */
	private String userName;
	
	/** The user address. */
	private String userAddress;
	
	/** The building id. */
	private String buildingId;
	
	/** The contract id. */
	private String contractId;
	
	/** The emergency numbers. */
	private String emergencyNumbers;
	
	/** The email id. */
	private String emailId;
	
	/** The control section list. */
	private List<ControlSection> controlSectionList;
	
	
	/**
	 * Instantiates a new user account.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public UserAccount() throws IOException
	{
		loadUserDetailsFromFile("userdetails.txt");
		loadSectionsFromFile("sectiondetails.txt");
		
		
	}
	
	/**
	 * Load user details from file.
	 *
	 * @param fileName the file name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  void loadUserDetailsFromFile(String fileName) throws IOException 
	{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str;

		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null){
		    list.add(str);
		}

		String[] stringArr = list.toArray(new String[6]);
		this.contractId=stringArr[0];
		this.userName=stringArr[1];
		this.buildingId=stringArr[2];
		this.userAddress=stringArr[3];
		this.emergencyNumbers=stringArr[4];
		this.emailId=stringArr[5];
		
		}
		
	/**
	 * Load sections from file.
	 *
	 * @param fileName the file name
	 */
	public  void loadSectionsFromFile(String fileName) 
	{
		controlSectionList = new ArrayList<ControlSection>();
		File file = new File(fileName);
		try 
		{	
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
		    while(scanner.hasNext())
		    {
		    	String expenseRecord = scanner.nextLine();
		    	String[] tokens=expenseRecord.split(" ");
		    	if(tokens.length == 4)
		    	{
		    		ControlSection controlSection = new ControlSection();
		    		controlSection.setSectionId(tokens[0]);
		    		controlSection.setArea(Double.parseDouble(tokens[1]));
		    		controlSection.setmSensor(Boolean.parseBoolean(tokens[2]));
		    		controlSection.setfSensor(Boolean.parseBoolean(tokens[3]));
		    		controlSectionList.add(controlSection);
		    	}
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Gets the user address.
	 *
	 * @return the user address
	 */
	public String getUserAddress() {
		return userAddress;
	}
	
	/**
	 * Gets the building id.
	 *
	 * @return the building id
	 */
	public String getBuildingId() {
		return buildingId;
	}
	
	/**
	 * Gets the contract id.
	 *
	 * @return the contract id
	 */
	public String getContractId() {
		return contractId;
	}
	
	/**
	 * Gets the emergency numbers.
	 *
	 * @return the emergency numbers
	 */
	public String getEmergencyNumbers() {
		return emergencyNumbers;
	}
	
	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}
	
	/**
	 * Gets the control section list.
	 *
	 * @return the control section list
	 */
	public List<ControlSection> getControlSectionList() {
		return controlSectionList;
	}
	

}
