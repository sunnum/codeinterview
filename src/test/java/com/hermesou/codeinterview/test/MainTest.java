package com.hermesou.codeinterview.test;

import com.hermesou.codeinterview.solutions.Solution;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/**
 * Main tests wrapper to run from console.
 * @author sunnum
 */
public class MainTest {

    private static final String LOGGIN_RESOURCE = "logging.properties";

    static {
        try (InputStream is = MainTest.class.getClassLoader().getResourceAsStream(LOGGIN_RESOURCE)) {
            LogManager.getLogManager().readConfiguration(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final boolean LOGGING = Solution.LOGGING;
    private static final Level LOG_LEVEL = Solution.LOG_LEVEL;

    private static final Logger LOGGER = LOGGING && LOG_LEVEL != Level.OFF ? Logger.getLogger(MainTest.class.getName()) : null;

    private static void log(String msg) {
        if (LOGGER != null)
            LOGGER.logp(LOG_LEVEL, MainTest.class.getSimpleName(), "main", msg);
    }

    public static void main(String[] args) {
        log("Starting tests execution...");

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectPackage(MainTest.class.getPackage().getName())
                )
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(new SummaryGeneratingListener());
        launcher.execute(request);

        log("Completed tests execution!");
    }

}