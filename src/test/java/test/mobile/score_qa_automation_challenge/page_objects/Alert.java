package test.mobile.score_qa_automation_challenge.page_objects;

import org.openqa.selenium.By;

public class Alert extends BasePage{

    By titleText = By.id("com.fivemobile.thescore:id/title_onboarding");
    By btnDone = By.xpath("//*[@text='Done']");

    By popupDismissButton = By.id("com.fivemobile.thescore:id/dismiss_modal");

    public String getScreenTitle() {
        return getText(titleText);
    }

    public void makeItDone() {
        click(btnDone);
        handleAppDownloadPopUp();
    }

    public void handleAppDownloadPopUp(){
        if(isElementPresent(popupDismissButton)){
            click(popupDismissButton);
        }
    }
}
