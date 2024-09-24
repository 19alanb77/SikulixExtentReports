package sikulixConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Class that allows to read values from external files.
 * 
 * @author Alan Buda
 */ 
public class ConfigKeys {

	/**
	 * Find value of given property name from config.properties file.
	 * 
	 * @param key
	 *          name of the property searched in the file 
	 *          
	 * @return value of the property searched in the file
	 */ 
	public static String getConfigKey(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		String result = "";
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			result = prop.getProperty(key);	
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * Read data from .csv file.
	 * 
	 * @param csvFile
	 *          file to read 
	 *          
	 * @return array with values from the file
	 */ 
	public static String[] csvReader(String csvFile) {
		String[] data = null;
        String csvSplitBy = ";";
        String temp = "";
        try {
        	List<String> line = Files.readAllLines(Paths.get("./dataset/"+ csvFile + ".csv"));
            for(int i=1;i<line.size();i+=2) {
            	temp += line.get(i) + ";";
            }
            data = temp.split(csvSplitBy);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return data;
	}
	
}
