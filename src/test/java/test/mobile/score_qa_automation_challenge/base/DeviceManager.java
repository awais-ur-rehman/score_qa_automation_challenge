package test.mobile.score_qa_automation_challenge.base;

import org.openqa.selenium.remote.DesiredCapabilities;

import test.mobile.score_qa_automation_challenge.test_setup.Hooks;
import test.mobile.score_qa_automation_challenge.utilities.CapabilitiesProvider;
import test.mobile.score_qa_automation_challenge.utilities.PropertiesUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author gurchet.singh
 * @description : This Class is built to manage the devices
 * @since 15 March 2023
 */

public class DeviceManager {
    private static final Logger logger = Logger.getLogger(DeviceManager.class.getName());

    // This method build and returns device object
    public static Device getDevice() {
        String deviceName = PropertiesUtils.get("device_name");
        try {
            DesiredCapabilities capabilities = CapabilitiesProvider.get(deviceName);
            if (capabilities != null) {
                    // send the device object back to the caller
                logger.log(Level.INFO, "Capabilities found for device "+deviceName);
                    return new Device(deviceName, true, capabilities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
