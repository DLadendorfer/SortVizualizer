// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.menu.concrete;

import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.utilities.ui.Ui;
import aero.sort.vizualizer.utilities.ui.UiFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Panel that manages all sort action invocations.
 *
 * @author Daniel Ladendorfer
 */
public class InvocationPanel extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(InvocationPanel.class);

    public InvocationPanel() {
        initialize();
    }

    private void initialize() {
        var sort = UiFactory.createButton("Sort", () -> MainFrame.getInstance().getController().sort());
        var step = UiFactory.createButton("Step", () -> logger.info("Step pressed, lmao."));
        var resume = UiFactory.createButton("Resume", () -> logger.info("Resume pressed, lmao."));
        var stop = UiFactory.createButton("Stop", () -> MainFrame.getInstance().getController().stopSort());
        sort.setSelected(true);

        Ui.using(this).add(sort, step, resume, stop).get();
    }
}