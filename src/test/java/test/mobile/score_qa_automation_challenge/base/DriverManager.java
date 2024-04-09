package test.mobile.score_qa_automation_challenge.base;

import io.appium.java_client.AppiumDriver;
import test.mobile.score_qa_automation_challenge.utilities.DeviceUtils;
import test.mobile.score_qa_automation_challenge.utilities.PropertiesUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {
	private static final Logger logger = Logger.getLogger(DriverManager.class.getName());
	private static final Map<Long, AppiumDriver> drivers = new ConcurrentHashMap<>();

	public static AppiumDriver getAppiumDriver() {
		Long threadId = Thread.currentThread().getId();

		if (drivers.containsKey(threadId)) {
			return drivers.get(threadId);
		}

		Device device = DeviceManager.getDevice();
		if (device == null) {
			logger.log(Level.SEVERE, "No device found");
			throw new IllegalStateException("Driver initialization failed. No device found.");
		}

		try {
			if ("true".equalsIgnoreCase(PropertiesUtils.get("install_app"))) {
				DeviceUtils.installAppOnDevice(device.getName(), PropertiesUtils.get("app_path"));
			}

			URL url = "true".equals(PropertiesUtils.get("appium_auto_run"))
					? AppiumService.getAppiumServerUrl()
					: new URL("http://" + PropertiesUtils.get("appium_host") + ":" + PropertiesUtils.get("appium_port_number") + PropertiesUtils.get("appium_path"));

			AppiumDriver driver = new AppiumDriver(url, device.getCapabilities());
			drivers.put(threadId, driver);
			logger.log(Level.INFO, "Driver initialization is successful");
			return driver;
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error installing the app on device", e);
			throw new RuntimeException("Error installing the app on device", e);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Driver initialization failed", e);
			throw new IllegalStateException("Driver initialization failed", e);
		}
	}

	public static void quitCurrentDriver() {
		Long threadId = Thread.currentThread().getId();
		AppiumDriver driver = drivers.get(threadId);
		if (driver != null) {
			driver.quit();
			drivers.remove(threadId);
			logger.log(Level.INFO, "Driver " + threadId + " got quit");
		}
	}
}
