package system;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
 

/**
 * The Class BillGenerator.
 */
public class BillGenerator {
	
	/** The map 1. */
	HashMap<Integer, Integer> map1 = new HashMap<>();
	
	/** The map 2. */
	HashMap<Integer, Integer> map2 = new HashMap<>();
	
	/** The fire senor install. */
	HashMap<Integer, Integer> fireSenorInstall = new HashMap<Integer, Integer>();
	
	/** The intrusion senor install. */
	HashMap<Integer, Integer> intrusionSenorInstall = new HashMap<Integer, Integer>();
	
	/** The alarm record fire. */
	HashMap<Integer, Integer> alarmRecordFire = new HashMap<>();
	
	/** The alarm record intrusion. */
	HashMap<Integer, Integer> alarmRecordIntrusion = new HashMap<>();
	
	/** The fire installed. */
	boolean fireInstalled = false;
	
	/** The intrusion installed. */
	boolean intrusionInstalled = false;
	
	
 
	/**
	 * Intrusion bill 1.
	 *
	 * @param x the x
	 * @return the string
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String intrusionBill1(int x) throws NumberFormatException, IOException {
		map1.clear();
		FileReader input = new FileReader("InstallationDetails.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		while ((myLine = bufRead.readLine()) != null) {
			String[] array1 = myLine.split(" ");
 
			if (array1[0].equals("Motion") && !intrusionInstalled) {
				int month1 = Integer.parseInt(array1[1]);
 
				map1.put(month1, 200);
				intrusionInstalled = true;
 
				System.out.println("Bill for initial Motion Installation" + map1);
			}
 
		}
		if (map1.containsKey(ms)) {
			return Integer.toString(map1.get(ms));
		} else {
			return "Intrusion Detector already billed";
		}
	}
 
	/**
	 * Intrusion bill 2.
	 *
	 * @param x the x
	 * @return the int
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int intrusionBill2(int x) throws NumberFormatException, IOException {
 
		FileReader input = new FileReader("SensorInstallation.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		while ((myLine = bufRead.readLine()) != null) {
			String[] array2 = myLine.split(" ");
			if (array2[1].equals("Motion")) {
 
				int month1 = Integer.parseInt(array2[2]);
				if (intrusionSenorInstall.containsKey(month1)) {
					int cost = intrusionSenorInstall.get(month1);
					cost = cost + 50;
					intrusionSenorInstall.put(month1, cost);
				} else {
					intrusionSenorInstall.put(month1, 50);
				}
				System.out.println("Bill for Motion Sensors Installation" + map1);
			}
 
		}
		if (intrusionSenorInstall.containsKey(ms) && intrusionSenorInstall.get(ms) != 200) {
			return (intrusionSenorInstall.get(ms));
		} else {
			return 0;
 
		}
	}
 
	/**
	 * Intrusion bill 3.
	 *
	 * @param x the x
	 * @return the int
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int intrusionBill3(int x) throws NumberFormatException, IOException {
		FileReader input = new FileReader("callLogging.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		
		while((myLine = bufRead.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(myLine);
			
			while(st.hasMoreTokens())
			{
				String type = st.nextToken();
				
				if(type.equals("Motion"))
				{
					String date = st.nextToken();
					char arr[]=date.toCharArray();
					
					String mon = ""+ arr[5] + arr[6];
					int month1 = Integer.parseInt(mon);
					
					if (map1.containsKey(month1)) {
						int cost = map1.get(month1);
						cost = cost + 20;
						map1.put(month1, cost);
					} else {
						map1.put(month1, 20);
					}
				}	
			}
		}
		
		
		
		
		if (map1.containsKey(ms)) {
			return (map1.get(ms));
		} else {
			return 0;
		}
	}
	
	/**
	 * Gets the intrusion alarm.
	 *
	 * @param x the x
	 * @return the intrusion alarm
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int getIntrusionAlarm(int x) throws IOException {
		// TODO Auto-generated method stub
 
		FileReader input = new FileReader("callLogging.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
	
		while((myLine = bufRead.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(myLine);
			
			while(st.hasMoreTokens())
			{
				String type = st.nextToken();
				
				if(type.equals("Motion"))
				{
					String date = st.nextToken();
					char arr[]=date.toCharArray();
					
					String mon = ""+ arr[5] + arr[6];
					int month1 = Integer.parseInt(mon);
					
					if (alarmRecordIntrusion.containsKey(month1)) {
						int val = alarmRecordIntrusion.get(month1);
						val += 20;
						alarmRecordIntrusion.put(month1, val);
 
					} else {
						alarmRecordIntrusion.put(month1, 20);
					}
				}	
			}
		}
		
		
				
		if (alarmRecordIntrusion.containsKey(ms)) {
			return (alarmRecordIntrusion.get(ms));
		} else {
			return 0;
		}
	}
 
	/**
	 * Fire bill 1.
	 *
	 * @param x the x
	 * @return the string
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String fireBill1(int x) throws NumberFormatException, IOException {
		FileReader input = new FileReader("InstallationDetails.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int month1 = 0;
		int ms = x;
 
		while ((myLine = bufRead.readLine()) != null) {
			String[] array1 = myLine.split(" ");
			if (array1[0].equals("Fire") && !fireInstalled) {
				month1 = Integer.parseInt(array1[1]);
				map2.put(month1, 240);
				fireInstalled = true;
			}
		}
		if (map2.containsKey(ms)) {
			return Integer.toString(map2.get(ms));
		} else {
			return "Fire Detector system already billed";
		}
	}
 
	/**
	 * Fire bill 2.
	 *
	 * @param x the x
	 * @return the int
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int fireBill2(int x) throws NumberFormatException, IOException {
		FileReader input = new FileReader("SensorInstallation.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		while ((myLine = bufRead.readLine()) != null) {
			String[] array2 = myLine.split(" ");
			if (array2[1].equals("Fire")) {
 
				int month1 = Integer.parseInt(array2[2]);
				if (fireSenorInstall.containsKey(month1)) {
					int val = fireSenorInstall.get(month1);
					val = val + 100;
					fireSenorInstall.put(month1, val);
				} else
					fireSenorInstall.put(month1, 100);
			}
		}
		if (fireSenorInstall.containsKey(ms) && fireSenorInstall.get(ms) != 240) {
			return (fireSenorInstall.get(ms));
		} else {
			return 0;
		}
	}
 
	/**
	 * Fire bill 3.
	 *
	 * @param x the x
	 * @return the int
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int fireBill3(int x) throws NumberFormatException, IOException {
		FileReader input = new FileReader("callLogging.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		
		while((myLine = bufRead.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(myLine);
			
			while(st.hasMoreTokens())
			{
				String type = st.nextToken();
				
				if(type.equals("Fire"))
				{
					System.out.println("Matched Fire");
					String date = st.nextToken();
					System.out.println("Date="+date);
					char arr[]=date.toCharArray();
					
					String mon = ""+ arr[5] + arr[6];
					
					System.out.println(mon);
					int month1 = Integer.parseInt(mon);
					
					if (map2.containsKey(month1)) {
						int cost = map2.get(month1);
						cost = cost + 50;
						map2.put(month1, cost);
					} else {
						map2.put(month1, 50);
					}
				}	
			}
		}
		
		
		
		
		if (map2.containsKey(ms)) {
			return (map2.get(ms));
		} else
			return 0;
	}
 
	/**
	 * Total bill.
	 *
	 * @param x the x
	 * @return the string
	 */
	public String totalBill(int x) {
		int MonthSelected = x;// This will be actually selected in the dropdown
								// of month
		if (map1.containsKey(MonthSelected)) {
			return Integer.toString(map1.get(MonthSelected));
		} else
			return "Bill not Generated";
	}
 
	/**
	 * Gets the fire alarm.
	 *
	 * @param x the x
	 * @return the fire alarm
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int getFireAlarm(int x) throws IOException {
		// TODO Auto-generated method stub
 
		FileReader input = new FileReader("callLogging.txt");
		BufferedReader bufRead = new BufferedReader(input);
		String myLine = null;
		int ms = x;
 
		
		while((myLine = bufRead.readLine()) != null)
		{
			StringTokenizer st = new StringTokenizer(myLine);
			
			while(st.hasMoreTokens())
			{
				String type = st.nextToken();
				
				if(type.equals("Fire"))
				{
					System.out.println("Matched Fire");
					String date = st.nextToken();
					System.out.println("Date="+date);
					char arr[]=date.toCharArray();
					
					String mon = ""+ arr[5] + arr[6];
					
					System.out.println(mon);
					int month1 = Integer.parseInt(mon);
					
					if (alarmRecordFire.containsKey(month1)) {
						int val = alarmRecordFire.get(month1);
						val += 50;
						alarmRecordFire.put(month1, val);
 
					} else {
						alarmRecordFire.put(month1, 50);
					}
				}	
			}
		}
		
		
 
		if (alarmRecordFire.containsKey(ms)) {
			return (alarmRecordFire.get(ms));
		} else {
			return 0;
		}
	}
 
}
 
 
