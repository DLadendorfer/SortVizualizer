// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app;

import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.data.options.Algorithm;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.Style;
import aero.sort.vizualizer.data.options.Visualization;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.constants.Theme;
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

    public static void launch() {
        logger.info("Launching application...");

        if (!GraphicsEnvironment.isHeadless()) {
            createDefaultSortFrame();
        }

        logger.info("Application start successful");
    }

    private static void createDefaultSortFrame() {
        var options = new SortOptions(Algorithm.Bubblesort, Visualization.Bars, Style.Auqa);
        MainFrame.getInstance().createInternalFrame(options);
    }
}
