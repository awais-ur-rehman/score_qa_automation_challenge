package test.mobile.score_qa_automation_challenge.stepdefs;

import org.json.simple.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.mobile.score_qa_automation_challenge.page_objects.Alert;
import test.mobile.score_qa_automation_challenge.page_objects.Team;
import test.mobile.score_qa_automation_challenge.page_objects.MyDashboard;
import test.mobile.score_qa_automation_challenge.utilities.JSONUtils;

public class TeamSelectionSteps {

    @Then("Team selection page should get opened")
    public void verifyIfTeamsPageOpened(){
    	Team team = new Team();
        Assert.assertEquals("Choose your favorite teams", team.getScreenTitle());
    }

    @When("User selects the tab {string}")
    public void selectTabInTeams(String tabName){
    	Team team = new Team();
    	team.selectTab(tabName);
    }

    @Then("User should be able to select the teams {string} for {string}")
    public void selectTeam(String teamCodes, String league){
    	Team team = new Team();
        for(String teamCode : teamCodes.split(",")){
            team.selectTeam((String)((JSONObject)JSONUtils.getLeaguesObject(league).get("teams")).get(teamCode));
        }
    }

    @Then("User should be able to see the selected {string} and {string}")
    public void verificationOfTeamLeaguesSelections(String leagues, String teams){
    	Team team = new Team();
        String[] teamsArray = teams.split(",");
        String[] leaguesArray = leagues.split(",");
        String[] allLeaguesAndTeams = new String[teamsArray.length + leaguesArray.length];
        System.arraycopy(teamsArray, 0, allLeaguesAndTeams, 0, teamsArray.length);
        System.arraycopy(leaguesArray, 0, allLeaguesAndTeams, teamsArray.length, leaguesArray.length);
        Assert.assertEquals(allLeaguesAndTeams.length, team.getTotalTeamSelected());
    }


    @When("User goes to alerts settings page")
    public void goToAlertsSettingsPage(){
    	Team team = new Team();
        team.continueNext();
    }

    @Then("Alerts settings page should get opened")
    public void verifyIfAlertsPageOpened(){
    	Alert alert = new Alert();
        Assert.assertEquals("Never miss a game", alert.getScreenTitle());
    }

    @When("User continue from alerts settings screen")
    public void continueFromAlertsSettings(){
    	Alert alert = new Alert();
        alert.makeItDone();
    }

    @Then("Dashboard page should get opened")
    public void checkIfDashboardOpened(){
    	MyDashboard dashboard = new MyDashboard();
        Assert.assertTrue(dashboard.isDashboardScreen());
    }

    @And("User should be able to see the selected {string}")
    public void checkIfAllSelectedTeamsAvailable(String teams){
    	MyDashboard dashboard = new MyDashboard();
    	for(String team : teams.split(",")){
            Assert.assertTrue(dashboard.isChosenTeamDisplayed(team.trim()));
        }
    }

    @When("User goes to back to the previous page from team page")
    public void goToPreviousPage() {
    	Team team = new Team();
        team.navigateBack();
    }
}
