package test.mobile.score_qa_automation_challenge.base;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;
import test.mobile.score_qa_automation_challenge.utilities.CommandRunner;
import test.mobile.score_qa_automation_challenge.utilities.DeviceUtils;
import test.mobile.score_qa_automation_challenge.utilities.PropertiesUtils;

/**
 * @author gurchet.singh
 * @since 15 March 2023
 * @description This file is responsible to create and
 *        maintain the AppiumDriver
 */

public class DriverManager {
	private static final Logger logger = Logger.getLogger(DriverManager.class.getName());
	private static HashMap<Long, HashMap<String,Object>> drivers = new HashMap<Long, HashMap<String,Object>>();

	public static AppiumDriver getAppiumDriver() {

		Long desiredKey = Thread.currentThread().getId();
		for (Entry<Long, HashMap<String,Object>> entry : drivers.entrySet()) {
			if (desiredKey == entry.getKey()) {
				AppiumDriver driver = (AppiumDriver) entry.getValue().get("driver");
				Map<String, Object> status = driver.getStatus();
				String pageSource = driver.getPageSource();
				if(!Objects.isNull(driver) && !Objects.isNull(driver.getStatus()) && !driver.getPageSource().isEmpty())
					return (AppiumDriver) entry.getValue().get("driver");
				else{
					drivers.remove(desiredKey);
				}
			}
		}

		Device device = DeviceManager.getDevice();
		if(Objects.isNull(device)){
			logger.log(Level.SEVERE, "No device found");
			return null;
		}

		if(PropertiesUtils.get("install_app").equalsIgnoreCase("true")){
			try {
				DeviceUtils.installAppOnDevice(device.getName(), PropertiesUtils.get("app_path"));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		AppiumDriver driver = null;
		try {
			URL url = null;
			if(PropertiesUtils.get("appium_auto_run").equals("true"))
				url = AppiumService.getAppiumServerUrl();
			else
				url = new URL("http://"+ PropertiesUtils.get("appium_host") + ":" +PropertiesUtils.get("appium_port_number") + PropertiesUtils.get("appium_path"));
			driver = new AppiumDriver(url, device.getCapabilities());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		HashMap<String,Object> driverMap = new HashMap<String,Object>();
		driverMap.put("device",device);
		driverMap.put("driver",driver);

		// using the current thread id to tie the drivers with the test scenario threads
		drivers.put(Thread.currentThread().getId(), driverMap);
		logger.log(Level.INFO, "Driver initialization is successful");
		return driver;
	}

	public static void quitCurrentDriver(){
		Long desiredKey = Thread.currentThread().getId();
		getAppiumDriver().quit();
		drivers.remove(desiredKey);
		logger.log(Level.INFO, "Driver "+desiredKey+" got quit");
	}
}
