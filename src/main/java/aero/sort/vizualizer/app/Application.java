// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.laf.UIBindings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

/**
 * The main application class.
 *
 * @author Daniel Ladendorfer
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    static {
        UIBindings.setupLookAndFeel();
    }

    private Application() {
        // static utility class - no instance needed
    }

    public static void launch() {
        logger.info("Launching application...");

        new MainFrame();
        if (!GraphicsEnvironment.isHeadless()) {
            createDefaultSortFrame();
        }

        logger.info("Application start successful");
    }

    private static void createDefaultSortFrame() {
        var options = new SortOptions(Algorithm.BUBBLE, Visualization.BARS, Style.APP, false);
        Controllers.fetch(FrameController.class).createInternalFrame(options);
    }
}
