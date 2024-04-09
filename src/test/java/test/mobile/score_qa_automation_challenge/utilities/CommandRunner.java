package test.mobile.score_qa_automation_challenge.utilities;

import java.io.*;
import java.util.*;

/**
 * @author gurchet.singh
 * @since 15 March 2023
 * @description : This class is to run the commands on command prompts
 */


public class CommandRunner {


    public static int runSyncCommand(String command) throws InterruptedException, IOException {
        Process p = Runtime.getRuntime().exec(command);
        return p.waitFor();
    }


    public static String runCommand(String command)
            throws IOException {
        BufferedReader br = getBufferedReader(command);
        String line;
        String allLine = "";
        while ((line = br.readLine()) != null) {
            allLine = allLine + "" + line + "\n";
        }
        return allLine;
    }
    
    public static BufferedReader getBufferedReader(String command) throws IOException {
        List<String> commands = new ArrayList<>();
        commands.add("/bin/sh");
        commands.add("-c");
        commands.add(command);
        ProcessBuilder builder = new ProcessBuilder(commands);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);
    }

    
}