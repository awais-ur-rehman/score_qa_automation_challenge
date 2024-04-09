package test.mobile.score_qa_automation_challenge.base;


/**
 * @author gurchet.singh
 * @since 15 March 2023 Description : This file is to keep the constants in the form of enums
 */


public class Constants {

	  public enum DevicePlatform {
		  
			ANDROID("android"), IOS("ios");
			private final String name;
		  
			DevicePlatform(String name) {
				this.name = name;
			}

			public String getName() {
				return this.name;
			}
	  }
}
