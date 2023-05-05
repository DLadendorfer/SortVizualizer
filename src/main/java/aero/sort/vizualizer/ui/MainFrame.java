// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.controller.ControllerManager;
import aero.sort.vizualizer.ui.components.management.FrameManagementPanel;
import aero.sort.vizualizer.ui.components.menu.MenuPanel;
import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.jetbrains.annotations.NotNull;
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
    private final JDesktopPane desktop;
    private final transient @NotNull ControllerManager controller;


    public MainFrame() {
        desktop = createDesktop();
        controller = new ControllerManager(desktop);
        initializeFrame();
        add(new MenuPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(new StatusBar(), BorderLayout.SOUTH);
        pack();
        logger.debug("MainFrame initialized");
    }

    private @NotNull JPanel createCenterPanel() {
        var panel = new JPanel(new BorderLayout());

        panel.add(new FrameManagementPanel(), BorderLayout.NORTH);
        panel.add(desktop, BorderLayout.CENTER);

        return panel;
    }

    private JDesktopPane createDesktop() {
        return Ui.using(new JDesktopPane())
                .execute(d -> d.setBackground(Theme.BLACK))
                .execute(d -> d.setVisible(true))
                .get();
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
