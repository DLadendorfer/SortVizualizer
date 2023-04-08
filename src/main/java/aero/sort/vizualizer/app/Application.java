package aero.sort.vizualizer.app;

import aero.sort.vizualizer.ui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main application class.
 *
 * @author Daniel Ladendorfer
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void launch() {
        logger.info("Launching application...");
        new MainFrame();
        logger.info("Application start successful");
    }
}
