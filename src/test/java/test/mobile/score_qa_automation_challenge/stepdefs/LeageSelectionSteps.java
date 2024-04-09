package test.mobile.score_qa_automation_challenge.stepdefs;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.mobile.score_qa_automation_challenge.page_objects.*;
import org.junit.Assert;

/**
 * @author gurchet.singh
 * @since 15 March 2023
 * Description : This class stores the test steps
 */

public class LeageSelectionSteps {

    @Given("User is at home screen")
    public void makeSureIfUserIsAtHomeScreen() {
		Home home = new Home();
		Assert.assertTrue(home.isHomeScreen());
    }


    @When("User goes to league selection page")
    public void goToLeagueSelection() {
		Home home = new Home();
		home.goToLeagueSelectionPage();
    }

    @Then("League selection page should get opened")
    public void verifyIfLeaguePageOpened(){
    	League league = new League();
		Assert.assertEquals("Choose your favorite leagues", league.getScreenTitle());
    }

    @Then("User should be able to select the league {string}")
    public void selectLeague(String leagues) {
    	League league = new League();
    	String[] leaguesArray = leagues.split(",");
		for (String leagueStr : leaguesArray) {
			league.selectLeague(leagueStr);
			Assert.assertTrue(league.isLeagueSelected(leagueStr));
		}
		Assert.assertEquals(leaguesArray.length, league.getTotalLeaguesSelected());
    }

    @Then("User goes to the Teams selection page")
    public void goToTeamsSelectionPage() {
    	League league = new League();
    	league.continueNext();
    }

    @When("User goes to back to the previous page from league page")
    public void goToPreviousPage() {
    	League league = new League();
    	league.navigateBack();
    }

}
