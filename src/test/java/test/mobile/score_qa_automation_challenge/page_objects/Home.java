package test.mobile.score_qa_automation_challenge.page_objects;
import org.openqa.selenium.By;

public class Home extends BasePage {
	By continueButton = By.id("com.fivemobile.thescore:id/action_button_text");

	public void goToLeagueSelectionPage() {
		click(continueButton);
	}

	public boolean isHomeScreen() {
		return isElementPresent(continueButton);
	}

}
