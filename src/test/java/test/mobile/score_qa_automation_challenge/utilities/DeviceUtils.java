package test.mobile.score_qa_automation_challenge.utilities;

import test.mobile.score_qa_automation_challenge.base.DeviceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author gurchet.singh
 * @since 18 March 2023
 * @description : This Class is built to handle device related interactions
 */

public class DeviceUtils {
	private static final Logger logger = Logger.getLogger(DeviceUtils.class.getName());

	// this method returns the list of names of devices currently connected to the machine
	public static List<String> getListOfConnectedDevices() {
		List<String> connectedDevices = new ArrayList<String>();
		try {
			// command to load the bash profile and then fetching the adb devices
			String devices = CommandRunner.runCommand("source ~/.bash_profile;adb devices");
			devices = devices.replace("List of devices attached", "");
			devices = devices.replace("device", "");
			StringTokenizer tokens = new StringTokenizer(devices, " ");
			
	        while (tokens.hasMoreTokens()) {
	        	connectedDevices.add(tokens.nextToken().trim());
	        }

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connectedDevices;
	}

	public static void installAppOnDevice(String deviceName, String appPath) throws IOException {
		logger.log(Level.INFO, "App located at "+appPath+" is being installed on device "+deviceName);
		try {
			CommandRunner.runSyncCommand("adb -s "+deviceName+" install -r "+appPath);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
