package test.mobile.score_qa_automation_challenge.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;


/**
 * @author gurchet.singh
 * @since 15 March 2023 Description : This class is a helper utility to provide
 *        capabilities after fetching it from the file
 */

public class CapabilitiesReader {

	private static JSONObject getCapability(String capabilityName, String jsonLocation) throws Exception {
		JSONArray capabilitiesArray = JSONUtils.getJSONArray(jsonLocation);
		for (Object jsonObj : capabilitiesArray) {
			JSONObject capability = (JSONObject) jsonObj;
			if (capability.get("deviceName").toString().equalsIgnoreCase(capabilityName)) {
				return capability;
			}
		}
		return null;
	}
	
	
	
	private static HashMap<String, Object> convertCapsToHashMap(String capabilityName, String jsonLocation)
			throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(getCapability(capabilityName, jsonLocation).toString(), HashMap.class);
	}
	
	
	
	public static DesiredCapabilities getDesiredCapabilities(String capabilityName, String capsContentRootLocation)
			throws Exception {
		String jsonLocation = System.getProperty("user.dir") + "/" + capsContentRootLocation;
		HashMap<String, Object> caps = convertCapsToHashMap(capabilityName, jsonLocation);
		return new DesiredCapabilities(caps);
	}
	
}
