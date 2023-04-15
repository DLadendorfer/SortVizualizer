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
import aero.sort.vizualizer.utilities.ui.Ui;
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
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private static final MainFrame instance = new MainFrame();
    private final JDesktopPane desktop;
    private final Controller controller;


    private MainFrame() {
        controller = new Controller(this);
        desktop = createDesktop();
        initializeFrame();
        add(new ActionPanel(), BorderLayout.WEST);
        add(desktop, BorderLayout.CENTER);
        add(new StatusBar(), BorderLayout.SOUTH);
        logger.debug("MainFrame initialized");
    }

    private JDesktopPane createDesktop() {
        return Ui.using(new JDesktopPane())
                .execute(d -> d.setBackground(Theme.BLACK))
                .execute(d -> d.setVisible(true))
                .get();
    }

    /**
     * Returns the singleton instance of the main frame.
     *
     * @return the application main frame
     */
    public static MainFrame getInstance() {
        return instance;
    }

    public void createInternalFrame(SortOptions options) {
        var frame = new SortingFrame(options);
        desktop.add(frame);
        frame.toFront();
    }

    public Controller getController() {
        return controller;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    private void initializeFrame() {
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
