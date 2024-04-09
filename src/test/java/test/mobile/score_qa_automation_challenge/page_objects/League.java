package test.mobile.score_qa_automation_challenge.page_objects;
import org.openqa.selenium.By;

public class League extends BasePage {

    By titleText = By.id("com.fivemobile.thescore:id/title_onboarding");

    By chipsElements = By.xpath("//*[@resource-id='com.fivemobile.thescore:id/chips_container']//android.view.ViewGroup");

    By btn_allow = By.id("com.fivemobile.thescore:id/btn_allow");
    By btn_permission_allow = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");

    By btnContinue = By.xpath("//*[@text='Continue']");

    By btnBack = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");


    public String getScreenTitle() {
        return getText(titleText);
    }

    public void navigateBack() {
        click(btnBack);
    }

    public void selectLeague(String leagueCode) {
        click(By.xpath("//android.widget.TextView[contains(@text,'"+leagueCode+"')]"));
    }

    public boolean isLeagueSelected(String leagueCode) {
        return isElementPresent(By.xpath("//*[@resource-id='com.fivemobile.thescore:id/chips_container']//android.widget.TextView[contains(@text,'"+leagueCode+"')]"));
    }

    public int getTotalLeaguesSelected() {
        return getTotalMatchedNoOfElements(chipsElements) - 1;
    }

    public void continueNext() {
        click(btnContinue);
        handleLocationPopUp();
    }

    public void handleLocationPopUp(){
        if(isElementPresent(btn_allow)){
            waitAndSoftClick(btn_allow);
        }else if(isElementPresent(btn_permission_allow)){
            waitAndSoftClick(btn_permission_allow);
        }else{
            return;
        }

    }

}
