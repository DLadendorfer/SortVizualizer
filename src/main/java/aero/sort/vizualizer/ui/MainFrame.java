// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.controller.ControllerFacade;
import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.ui.components.menu.MenuPanel;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.ui.components.management.FrameManagementPanel;
import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.Async;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.stream.IntStream;

/**
 * Main application frame.
 *
 * @author Daniel Ladendorfer
 */
public class MainFrame extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private static final MainFrame instance = new MainFrame();
    private final JDesktopPane desktop;
    private final ControllerFacade controller;
    private boolean autoSmartArrange = true;


    private MainFrame() {
        controller = new ControllerFacade();
        desktop = createDesktop();
        initializeFrame();
        add(new MenuPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(new StatusBar(), BorderLayout.SOUTH);
        pack();
        logger.debug("MainFrame initialized");
    }

    private JPanel createCenterPanel() {
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

    /**
     * Returns the singleton instance of the main frame.
     *
     * @return the application main frame
     */
    public static MainFrame getInstance() {
        return instance;
    }

    /**
     * Sets the auto smart arrange flag of the controller.
     * @param enabled true to enable; false to disable
     */
    public void autoSmartArrange(boolean enabled) {
        logger.info("Smart arranging frames: {}", enabled);
        this.autoSmartArrange = enabled;
    }

    public void createInternalFrame(SortOptions options) {
        var frame = new SortingFrame(options);
        desktop.add(frame);
        frame.toFront();
        if(autoSmartArrange) {
            controller.smartArrange();
        }
        Async.invoke(() -> frame.render(IntStream.rangeClosed(1, 20).boxed().toArray(Integer[]::new)));
    }

    public ControllerFacade getController() {
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
