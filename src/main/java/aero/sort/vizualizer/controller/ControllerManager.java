// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.controller.sort.SortController;

import javax.swing.*;

/**
 * UI-Controller manager of the application.
 * This class should initialize, register and manage concrete controllers.
 *
 * @author Daniel Ladendorfer
 */
public class ControllerManager {

    private final SortController sortController;
    private final FrameController frameManagementController;

    public ControllerManager(JDesktopPane desktop) {
        sortController = new SortController();
        frameManagementController = new FrameController(desktop);

        registerControllers();
    }

    private void registerControllers() {
        Controllers.registerControllerSupplier(SortController.class, () -> sortController);
        Controllers.registerControllerSupplier(FrameController.class, () -> frameManagementController);
    }
}
