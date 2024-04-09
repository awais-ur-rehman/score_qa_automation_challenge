package test.mobile.score_qa_automation_challenge.page_objects;

import org.openqa.selenium.By;

public class MyDashboard extends BasePage {

    By imgProfile = By.className("android.widget.ImageButton");

    public boolean isDashboardScreen() {
        return isElementPresent(imgProfile);
    }

    public boolean isChosenTeamDisplayed(String team) {
        return isElementPresent(By.xpath("//*[@resource-id='com.fivemobile.thescore:id/chips_container']//android.widget.TextView[@text='"+team+"']"));
    }
}
