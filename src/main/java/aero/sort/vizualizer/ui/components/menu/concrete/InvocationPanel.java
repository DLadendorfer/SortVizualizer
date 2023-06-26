// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.sort.SortController;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.function.Consumer;

/**
 * Panel that manages all sort action invocations.
 *
 * @author Daniel Ladendorfer
 */
public class InvocationPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(InvocationPanel.class);
    private static final String SORT_LABEL = "Sort";
    private static final String STEP_LABEL = "Step";
    private static final String RESUME_LABEL = "Resume";
    private static final String PAUSE_LABEL = "Pause";
    private static final String STOP_LABEL = "Stop";

    public InvocationPanel() {
        initialize();
    }

    private void initialize() {
        var sort = UiFactory.createButton(SORT_LABEL, () -> invokeControllerAction(SortController::sort));
        var step = UiFactory.createButton(STEP_LABEL, () -> invokeControllerAction(SortController::step));
        var resume = UiFactory.createButton(RESUME_LABEL, () -> invokeControllerAction(SortController::resume));
        var pause = UiFactory.createButton(PAUSE_LABEL, () -> invokeControllerAction(SortController::pause));
        var stop = UiFactory.createButton(STOP_LABEL, () -> invokeControllerAction(SortController::stop));
        sort.setSelected(true);

        Ui.using(this)
          .add(sort, step, resume, pause, stop)
          .get();
    }

    private void invokeControllerAction(Consumer<SortController> action) {
        logger.trace("Invoking controller action in {}", getClass().getSimpleName());
        action.accept(Controllers.fetch(SortController.class));
    }
}
