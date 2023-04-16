// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.controller.management;

import aero.sort.vizualizer.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Handles the frame management invocations of UI elements.
 *
 * @author Daniel Ladendorfer
 */
public class FrameManagementController {

    // for unit tests
    private static JDesktopPane injectedDesktop;

    /**
     * Closes all frames.
     */
    public void closeAll() {
        Arrays.stream(getAllFrames())
                .forEach(JInternalFrame::dispose);
    }


    /**
     * Arrange all internal frames side by side.
     */
    public void arrangeSideBySide() {
        var frames = getAllFrames();
        var desktopWidth = getDesktopWidth();
        var desktopHeight = getDesktopHeight();
        var width = desktopWidth / frames.length;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(width, desktopHeight));
            frame.setSize(new Dimension(width, desktopHeight));
            frame.setLocation(i + (i * width), 0);
        }
    }

    /**
     * Arrange all internal frames stacking.
     */
    public void arrangeStacking() {
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var desktopWidth = getDesktopWidth();
        var desktopHeight = getDesktopHeight();
        var height = desktopHeight / frames.length;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(desktopWidth, height));
            frame.setSize(new Dimension(desktopWidth, height));
            frame.setLocation(0, i + (i * height));
        }
    }

    private int getDesktopWidth() {
        return getDesktop().getWidth();
    }

    private JDesktopPane getDesktop() {
        return injectedDesktop == null ? MainFrame.getInstance().getDesktop() : injectedDesktop;
    }

    private JInternalFrame[] getAllFrames() {
        return getDesktop().getAllFrames();
    }

    /**
     * Smart arranges all internal frames.
     */
    public void smartArrange() {
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

        JOptionPane.showMessageDialog(getDesktop(),
                "Only up to 9 frames can be arranged smartly",
                "Too many frames to smart arrange", JOptionPane.INFORMATION_MESSAGE);
    }

    private void arrangeTripleTriplets() {
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var desktopWidth = getDesktopWidth();
        var desktopHeight = getDesktopHeight();
        var height = desktopHeight / 3;
        var width = desktopWidth / 3;
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
        var frames = getAllFrames();

        if (frames.length == 0) {
            return;
        }

        var desktopWidth = getDesktopWidth();
        var desktopHeight = getDesktopHeight();
        var height = desktopHeight / 2;
        var width = desktopWidth / 3;
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
        var frames = getAllFrames();
        var desktopWidth = getDesktopWidth();
        var desktopHeight = getDesktopHeight();
        var height = desktopHeight / 2;
        var width = desktopWidth / 2;
        for (int i = 0; i < frames.length; i++) {
            var frame = frames[i];
            frame.setPreferredSize(new Dimension(width, height));
            frame.setSize(new Dimension(width, height));
            frame.setLocation(i == 0 || i == 2 ? 0 : width, i < 2 ? 0 : height);
        }
    }

    private int getDesktopHeight() {
        return getDesktop().getHeight();
    }

    static void injectDesktop(JDesktopPane desktop) {
        injectedDesktop = desktop;
    }
}
