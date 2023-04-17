// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.management;

import aero.sort.vizualizer.annotation.meta.Justification;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Handles the frame management invocations of UI elements.
 *
 * @author Daniel Ladendorfer
 */
public class FrameManagementController {
    private static final String tooManyMessage = "Only up to 9 frames can be arranged smartly";
    private static final String tooManyTitle = "Too many frames to smart arrange";

    @Justification("Unit tests need to inject a desktop instance because github CI is in headless gfx mode")
    private static JDesktopPane injectedDesktop;

    private static final Logger logger = LoggerFactory.getLogger(FrameManagementController.class);

    /**
     * Internal static API to enable unit testing.
     *
     * @param desktop the desktop to statically inject
     */
    @Justification("Unit tests need to inject a desktop instance because github CI is in headless gfx mode")
    public static void injectDesktop(JDesktopPane desktop) {
        injectedDesktop = desktop;
    }

    /**
     * Closes all frames.
     */
    public void closeAll() {
        logger.debug("Disposing all frames.");
        Arrays.stream(getAllFrames()).forEach(JInternalFrame::dispose);
    }


    /**
     * Arrange all internal frames side by side.
     */
    public void arrangeSideBySide() {
        logger.debug("Arranging frames side-by-side");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }


        var dimension = getDesktopDimension();
        var width = dimension.width / frames.length;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(width, dimension.height));
            frame.setSize(new Dimension(width, dimension.height));
            frame.setLocation(i + (i * width), 0);
        }
    }

    /**
     * Arrange all internal frames stacking.
     */
    public void arrangeStacking() {
        logger.debug("Arranging frames stacking");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var dimensions = getDesktopDimension();
        var height = dimensions.height / frames.length;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(dimensions.width, height));
            frame.setSize(new Dimension(dimensions.width, height));
            frame.setLocation(0, i + (i * height));
        }
    }

    /**
     * Smart arranges all internal frames.
     */
    public void smartArrange() {
        logger.debug("Smart arranging frames");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        switch (frames.length) {
            case 1, 2 -> arrangeSideBySide();
            case 3, 4 -> arrangeQuadrant();
            case 5, 6 -> arrangeDoubleTriplets();
            case 7, 8, 9 -> arrangeTripleTriplets();
            default -> arrangeTooMany();
        }
    }

    private void arrangeTooMany() {
        logger.debug("Arranging too many frames");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        arrangeTripleTriplets();

        for (int i = 9; i < frames.length; i++) {
            var frame = frames[i];
            getDesktop().getDesktopManager().openFrame(frame);
            frame.toFront();
        }

        Ui.showInfo(tooManyTitle, tooManyMessage);
    }

    private void arrangeTripleTriplets() {
        logger.debug("Arranging triple triplets");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var dimension = getDesktopDimension();
        var height = dimension.height / 3;
        var width = dimension.width / 3;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];

            if (i >= 9) {
                break;
            }

            frame.setPreferredSize(new Dimension(width, height));
            frame.setSize(new Dimension(width, height));

            if (i < 3) {
                frame.setLocation(width * i, 0);
            } else if (i < 6) {
                frame.setLocation(width * (i - 3), height);
            } else {
                frame.setLocation(width * (i - 6), height * 2);
            }
        }
    }

    private void arrangeDoubleTriplets() {
        logger.debug("Arranging double triplets");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var dimension = getDesktopDimension();
        var height = dimension.height / 2;
        var width = dimension.width / 3;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];

            frame.setPreferredSize(new Dimension(width, height));
            frame.setSize(new Dimension(width, height));

            if (i < 3) {
                frame.setLocation(width * i, 0);
            } else {
                frame.setLocation(width * (i - 3), height);
            }
        }
    }

    private void arrangeQuadrant() {
        logger.debug("Arranging quadrants");
        var frames = getAllFrames();
        var dimension = getDesktopDimension();
        var height = dimension.height / 2;
        var width = dimension.width / 2;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(width, height));
            frame.setSize(new Dimension(width, height));
            frame.setLocation(i == 0 || i == 2 ? 0 : width, i < 2 ? 0 : height);
        }
    }


    private Dimension getDesktopDimension() {
        var desktop = getDesktop();
        return new Dimension(desktop.getWidth(), desktop.getHeight());
    }

    private JDesktopPane getDesktop() {
        return injectedDesktop == null ? MainFrame.getInstance().getDesktop() : injectedDesktop;
    }

    private JInternalFrame[] getAllFrames() {
        return getDesktop().getAllFrames();
    }
}
