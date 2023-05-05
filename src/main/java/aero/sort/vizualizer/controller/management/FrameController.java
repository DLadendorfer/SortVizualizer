// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.management;

import aero.sort.vizualizer.annotation.meta.Approval;
import aero.sort.vizualizer.controller.IController;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.data.options.SortSetOptions;
import aero.sort.vizualizer.data.registry.DataRegistry;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.utilities.Async;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Handles the frame management invocations of UI elements.
 *
 * @author Daniel Ladendorfer
 */
public class FrameController implements IController {
    private static final String TOO_MANY_MESSAGE = "Only up to 9 frames can be arranged smartly";
    private static final String TOO_MANY_TITLE = "Too many frames to smart arrange";
    private static final Logger logger = LoggerFactory.getLogger(FrameController.class);
    private boolean autoSmartArrange = true;
    private final JDesktopPane desktop;

    public FrameController(JDesktopPane desktop) {
        this.desktop = desktop;
    }


    /**
     * Closes all frames.
     */
    public void closeAll() {
        logger.debug("Disposing all frames.");
        Arrays.stream(getAllFrames())
              .forEach(JInternalFrame::dispose);
    }

    /**
     * Creates an internal sort frame.
     *
     * @param options the options for this specific frame
     */
    public void createInternalFrame(@NotNull SortOptions options) {
        var frame = new SortingFrame(options);
        desktop.add(frame);
        frame.toFront();
        if (autoSmartArrange) {
            smartArrange();
        }
        renderSortedSet(frame);
    }

    private static void renderSortedSet(SortingFrame frame) {
        var options = DataRegistry.fetch(SortSetOptions.class);

        Async.invoke(() -> frame.render(IntStream.rangeClosed(1, options.size())
                                                 .boxed()
                                                 .toArray(Integer[]::new)));
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
            setFrameDimension(dimension.height, width, frame);
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

        var dimension = getDesktopDimension();
        var height = dimension.height / frames.length;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            setFrameDimension(height, dimension.width, frame);
            frame.setLocation(0, i + (i * height));
        }
    }

    /**
     * Smart arranges all internal frames.
     */
    @Approval(releaseWorthy = false, comment = "Not really as smart as it could be")
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

    /**
     * Sets the auto smart arrange flag of the controller.
     *
     * @param enabled true to enable; false to disable
     */
    public void autoSmartArrange(boolean enabled) {
        logger.info("Smart arranging frames: {}", enabled);
        this.autoSmartArrange = enabled;
    }

    private void arrangeTooMany() {
        logger.debug("Arranging too many frames");
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        // arrange the first 9 frames
        arrangeTripleTriplets();

        // bring others to front
        for (int i = 9; i < frames.length; i++) {
            var frame = frames[i];
            desktop.getDesktopManager()
                   .openFrame(frame);
            frame.toFront();
        }

        Ui.showInfo(TOO_MANY_TITLE, TOO_MANY_MESSAGE);
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
                // only works for up to 9 frames
                break;
            }

            setFrameDimension(height, width, frame);

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
            setFrameDimension(height, width, frame);
            frame.setLocation(i == 0 || i == 2 ? 0 : width, i < 2 ? 0 : height);
        }
    }

    private static void setFrameDimension(int height, int width, JInternalFrame frame) {
        var preferredSize = new Dimension(width, height);
        frame.setPreferredSize(preferredSize);
        frame.setSize(preferredSize);
    }


    private @NotNull Dimension getDesktopDimension() {
        return new Dimension(desktop.getWidth(), desktop.getHeight());
    }

    private JInternalFrame[] getAllFrames() {
        return desktop.getAllFrames();
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }
}
