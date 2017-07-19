package system.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The Class FileUtil.
 */
public class FileUtil {
	
	/**
	 * Wrtie to file.
	 *
	 * @param filename the filename
	 * @param data the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void wrtieToFile(String filename, List<String> data) throws IOException
	{
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			for(int i=0; i<data.size();i++)
			{
				bw.write(data.get(i));
			}
		
		} catch (IOException e) {

			throw e;

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				throw ex;

			}
		}
	}

	/**
	 * Read file.
	 *
	 * @param filename the filename
	 * @return the list
	 * @throws Exception the exception
	 */
	public static List<String> readFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> data = new ArrayList<String>();
		 try {
	   
	            File file = new File(filename);
	            
	            if(!file.exists()) { 
	                return data;
	            }

	            Scanner input = new Scanner(file);

	            
	            
	            while (input.hasNextLine()) {
	                String line = input.nextLine();
	                if(line.length()>1) data.add(line);
	            }
	            input.close();

	        } catch (Exception ex) {
	            throw ex;
	        }
		
		
		return data;
	}

}
