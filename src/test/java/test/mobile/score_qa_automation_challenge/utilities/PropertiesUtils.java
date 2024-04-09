package test.mobile.score_qa_automation_challenge.utilities;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author gurchet.singh
 * @since 15 March 2023 
 * Description : This file is responsible to fetch the properties from properties files
 */

public class PropertiesUtils {

	public static Properties properties;

	static {
		FileInputStream inputStream = null;
		properties = null;
		String fileName = "config.properties";
		String filePath = System.getProperty("user.dir") + "/config/" + fileName;
		try {
			inputStream = new FileInputStream(filePath);
			properties = new Properties();
			properties.load(inputStream);
			properties.putAll(System.getProperties());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

	public static void put(String key, String value) {
		properties.setProperty(key, value);
	}
}
