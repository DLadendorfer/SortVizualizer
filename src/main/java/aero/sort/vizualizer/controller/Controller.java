// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller;

import aero.sort.vizualizer.controller.sort.SortController;
import aero.sort.vizualizer.ui.MainFrame;

/**
 * UI-Controller of the application. Fetch-able via MainFrame instance.
 * This class should only be a facade for concrete controllers.
 *
 * @author Daniel Ladendorfer
 */
public class Controller {
    private final SortController sortController;

    public Controller(MainFrame mainFrame) {
        sortController = new SortController(mainFrame);
    }

    /**
     * Invoke the sorting process of all frames.
     */
    public void sort() {
        sortController.sort();
    }

    /**
     * Stops the sorting process of all frames.
     */
    public void stopSort() {
        sortController.stop();
    }
}
