package test.mobile.score_qa_automation_challenge.base;

import org.openqa.selenium.Capabilities;

/**
 * @author gurchet.singh
 * @since 15 March 2023 Description : This Class is created to store the device data structure
 */


public class Device {

	private String name;
	private boolean availability;
	private Capabilities capabilities;
	
	
	public Device(String name, boolean availability, Capabilities capabilities){
		this.name = name;
		this.availability = availability;
		this.capabilities = capabilities;	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	public Capabilities getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(Capabilities capabilities) {
		this.capabilities = capabilities;
	}
	
	
	@Override
	public boolean equals(Object object) {
		
        if (!(object instanceof Device)) {
            return false;
        }
        
        Device device = (Device)object;
        
        if(this.getName().equals(device.getName()))
        	return true;
        else
        	return false;
		
	}
	
}
