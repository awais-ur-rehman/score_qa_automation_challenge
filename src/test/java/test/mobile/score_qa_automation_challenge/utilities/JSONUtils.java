package test.mobile.score_qa_automation_challenge.utilities;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * @author gurchet.singh
 * @since 15 March 2023 
 * @description : This class is a helper utility to provide json array from the asked location
 */

public class JSONUtils {

	// this method returns the json array from the json file provided at the given Location
	public static JSONArray getJSONArray(String jsonLocation) throws Exception {
		JSONParser jsonParser = new JSONParser();
		return (JSONArray) jsonParser.parse(new FileReader(jsonLocation));
	}

	public static JSONObject getLeaguesObject(String key) {
		JSONParser jsonParser = new JSONParser();
		try {
			return (JSONObject)((JSONObject) jsonParser.parse(new FileReader(System.getProperty("user.dir") + "/" + PropertiesUtils.get("test_data_location")))).get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
