package aero.sort.vizualizer;

import aero.sort.vizualizer.ui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VizualizerApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(VizualizerApplication.class);
        logger.info("Hello, World!");
        MainFrame frame = new MainFrame();
    }
}
