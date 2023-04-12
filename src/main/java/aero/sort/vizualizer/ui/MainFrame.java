// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.controller.Controller;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.ui.components.action.ActionPanel;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.constants.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Main application frame.
 *
 * @author Daniel Ladendorfer
 */
public class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private final JDesktopPane desktop;
    private final Controller controller;


    private MainFrame() {
        createFrame();
        var actionPanel = new ActionPanel();
        add(actionPanel, BorderLayout.WEST);
        var center = new JPanel();
        center.setBackground(Theme.BLACK);
        desktop = new JDesktopPane();
        desktop.setBackground(Theme.BLACK);
        desktop.setVisible(true);
        add(desktop, BorderLayout.CENTER);
        add(new StatusBar(), BorderLayout.SOUTH);
        pack();
        controller = new Controller(this);
    }

    /**
     * Returns the singleton instance of the main frame.
     *
     * @return the application main frame
     */
    public static MainFrame getInstance() {
        return INSTANCE;
    }

    public void createInternalFrame(SortOptions options) {
        desktop.add(new SortingFrame(options));
    }

    public Controller getController() {
        return controller;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    private void createFrame() {
        setTitle(FrameConstants.TITLE);
        setMinimumSize(FrameConstants.MIN_DIMENSION);
        setPreferredSize(FrameConstants.PREFERRED_DIMENSION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }
}
