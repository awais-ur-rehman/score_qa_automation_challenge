package test.mobile.score_qa_automation_challenge.test_setup;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import test.mobile.score_qa_automation_challenge.base.AppiumService;
import test.mobile.score_qa_automation_challenge.base.DriverManager;
import test.mobile.score_qa_automation_challenge.utilities.CommandRunner;
import test.mobile.score_qa_automation_challenge.utilities.PropertiesUtils;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * @author gurchet.singh
 * @description This
 * @since 18 March 2023
 */
public class Hooks {

    private static final Logger logger = Logger.getLogger(Hooks.class.getName());

    @BeforeAll
    public static void setUp() throws IOException {
        if (PropertiesUtils.get("appium_auto_run").equals("true"))
            AppiumService.startAppiumDriverService();
        else {
            AppiumService.host = PropertiesUtils.get("appium_host");
            AppiumService.portNumber = Integer.parseInt(PropertiesUtils.get("appium_port_number"));
            logger.log(Level.INFO, "Using existing appium service at " + AppiumService.host + ":" + AppiumService.portNumber);
        }
    }

    @After
    public static void cleanUpAfterTestScenario(Scenario scenario) {
        DriverManager.quitCurrentDriver();
    }

    @AfterAll
    public static void cleanUp() {
        try {
            if (PropertiesUtils.get("appium_auto_run").equals("true")) {
                AppiumService.stopAppiumDriverService();
            }
            CommandRunner.runCommand("allure serve " + System.getProperty("user.dir") + "/target/surefire-reports/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
