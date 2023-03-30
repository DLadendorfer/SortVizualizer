package aero.sort.vizualizer;

import aero.sort.vizualizer.ui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VizualizerApplication {
    private static final Logger logger = LoggerFactory.getLogger(VizualizerApplication.class);

    public static void main(String[] args) {

        MainFrame frame = new MainFrame();
        logger.info("Application started. Awaiting input.");
    }
}
