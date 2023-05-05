// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app;

import aero.sort.vizualizer.annotation.meta.Approval;
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
 * <p/>
 * Invoking {@link #launch()} creates the application lifecycle by creating the {@link MainFrame}.
 * Keep in mind that this is not happening if the JVM is launched in headless mode.
 *
 * @author Daniel Ladendorfer
 * @see GraphicsEnvironment#isHeadless()
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    static {
        // statically set up the UI Manager properties
        UIBindings.setupLookAndFeel();
    }

    private Application() {
        // static utility class - no instance needed
    }

    public static void launch() {
        logger.info("Launching application...");

        if (!GraphicsEnvironment.isHeadless()) {
            // dangling object creation still creates a reference in the UI-Thread. Local variable not needed
            new MainFrame();
            createDefaultSortFrame();
        }

        logger.info("Application start successful");
    }

    @Approval(releaseWorthy = false, comment = "Default frame options may change")
    private static void createDefaultSortFrame() {
        var options = new SortOptions(Algorithm.INSERTION, Visualization.DISPARITY_CIRCLE, Style.RAINBOW, false);

        Controllers.fetch(FrameController.class)
                   .createInternalFrame(options);

        logger.debug("Created default frame with options: {}", options);
    }
}
