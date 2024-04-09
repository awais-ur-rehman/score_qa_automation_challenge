package test.mobile.score_qa_automation_challenge.utilities;

import java.io.IOException;
import java.net.Socket;

/**
 * @author gurchet.singh
 * @since 18 March 2023
 * @description This Class is created to handle ports. ie get available port, check if port available
 */

public class PortUtils {

    public static int getAvailablePort() {
        String[] ports = PropertiesUtils.get("ports").split(",");
        for (String str_port : ports) {
            int port = Integer. parseInt(str_port);
            if (isPortAvailable(port))
                return port;
        }
        return 0;
    }

    private static boolean isPortAvailable(int port) {
        try (Socket ignored = new Socket("localhost", port)) {
            return false;
        } catch (IOException ignored) {
            return true;
        }
    }
}