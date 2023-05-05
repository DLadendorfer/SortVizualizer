// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.utilities.ui;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * UI utility class that should simplify some java-swing code.
 *
 * @author Daniel Ladendorfer
 */
public final class Ui {

    private static final Logger logger = LoggerFactory.getLogger(Ui.class);

    private Ui() {
        // static utility class - no instance needed
    }

    /**
     * Creates an instance of {@link FluentUi} managing the given component.
     *
     * @param component the component to manage
     * @param <T>       the concrete {@link JComponent} type
     * @return the new instance of {@link FluentUi}
     */
    public static <T extends JComponent> @NotNull FluentUi<T> using(T component) {
        return new FluentUi<>(component);
    }

    /**
     * Shows an info message dialog with the given title and message.
     *
     * @param title   the title
     * @param message the message; can be a JComponent
     */
    public static void showInfo(String title, Object message) {
        logger.debug("Showing message '{}':'{}'", title, message);
        var desktop = Controllers.fetch(FrameController.class).getDesktop();
        JOptionPane.showMessageDialog(desktop, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
