package test.mobile.score_qa_automation_challenge.utilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesProvider {
	public static DesiredCapabilities get(String deviceName) throws Exception {
		DesiredCapabilities capabilities = CapabilitiesReader.getDesiredCapabilities(deviceName,
				PropertiesUtils.get("capabilities_location"));
		return capabilities;
	}
}
