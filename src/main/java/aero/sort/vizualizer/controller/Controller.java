// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.controller.sort.SortController;
import aero.sort.vizualizer.ui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UI-Controller of the application. Fetch-able via MainFrame instance.
 * This class should only be a facade for concrete controllers.
 *
 * @author Daniel Ladendorfer
 */
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final SortController sortController;

    public Controller(MainFrame mainFrame) {
        sortController = new SortController(mainFrame);
    }

    /**
     * Invoke the sorting process of all frames.
     */
    public void sort() {
        logger.info("Starting sort procedure for all frames");
        sortController.sort();
    }

    /**
     * Stops the sorting process of all frames.
     */
    public void stopSort() {
        logger.info("Stopping sort procedure of all frames");
        sortController.stop();
    }
}
