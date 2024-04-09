package test.mobile.score_qa_automation_challenge.base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import test.mobile.score_qa_automation_challenge.test_setup.Hooks;
import test.mobile.score_qa_automation_challenge.utilities.PortUtils;
import test.mobile.score_qa_automation_challenge.utilities.PropertiesUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;


/**
 * @author gurchet.singh
 * @description This Class is created to handle Appium service. ie auto start, auto stop appium service
 * @since 18 March 2023
 */

public class AppiumService {
    public static int portNumber = 4723;
    public static String host = "localhost";
    private static final Logger logger = Logger.getLogger(AppiumService.class.getName());

    static AppiumDriverLocalService service;

    public static void startAppiumDriverService() throws IOException {
        host = PropertiesUtils.get("appium_host");
        portNumber = PortUtils.getAvailablePort();
        logger.log(Level.INFO, "Starting Appium server at " + host + ":" + portNumber);
        service = new AppiumServiceBuilder()
                .usingPort(portNumber)
                .withArgument(LOG_LEVEL, "info")
                .withArgument(SESSION_OVERRIDE)
                .withArgument(BASEPATH, PropertiesUtils.get("appium_path"))
                .build();
        service.start();
    }

    public static void stopAppiumDriverService() throws IOException {
        logger.log(Level.INFO, String.format("Stopping Appium server at " + host + ":" + portNumber));
        if(!Objects.isNull(service))
            service.stop();
    }

    public static URL getAppiumServerUrl() {
        return service.getUrl();
    }

}