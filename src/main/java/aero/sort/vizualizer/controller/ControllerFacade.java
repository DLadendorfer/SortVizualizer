// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.controller.management.FrameManagementController;
import aero.sort.vizualizer.controller.sort.SortController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UI-Controller of the application. Fetch-able via MainFrame instance.
 * This class should only be a facade for concrete controllers.
 *
 * @author Daniel Ladendorfer
 */
public class ControllerFacade {
    private static final Logger logger = LoggerFactory.getLogger(ControllerFacade.class);

    private final SortController sortController;
    private final FrameManagementController frameManagementController;

    public ControllerFacade() {
        sortController = new SortController();
        frameManagementController = new FrameManagementController();
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

    /**
     * Closes all frames.
     */
    public void closeAll() {
        logger.info("Closing all frames");
        stopSort();
        frameManagementController.closeAll();
    }

    /**
     * Arrange all internal frames side by side.
     */
    public void arrangeSideBySide() {
        logger.info("Arranging frames side by side");
        frameManagementController.arrangeSideBySide();
    }

    /**
     * Arrange all internal frames stacking.
     */
    public void arrangeStacking() {
        logger.info("Arranging frames stacking");
        frameManagementController.arrangeStacking();
    }

    /**
     * Smart arranges all internal frames.
     */
    public void smartArrange() {
        logger.info("Smart arranging frames");
        frameManagementController.smartArrange();
    }
}
