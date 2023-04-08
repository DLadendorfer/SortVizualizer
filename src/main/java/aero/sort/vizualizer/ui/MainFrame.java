package aero.sort.vizualizer.ui;

import aero.sort.vizualizer.data.options.SortOptions;
import aero.sort.vizualizer.ui.components.action.ActionPanel;
import aero.sort.vizualizer.ui.components.desktop.SortingFrame;
import aero.sort.vizualizer.ui.components.status.StatusBar;
import aero.sort.vizualizer.ui.constants.FrameConstants;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.ui.laf.UIBindings;
import com.sun.tools.javac.Main;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.InternalFrameUI;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Main application frame.
 *
 * @author Daniel Ladendorfer
 */
public class MainFrame extends JFrame {
    private static final MainFrame INSTANCE = new MainFrame();
    private static final Logger logger = LoggerFactory.getLogger(MainFrame.class);
    private final JDesktopPane desktop;


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
    }

    /**
     * Returns the singleton instance of the main frame.
     * @return the application main frame
     */
    public static MainFrame getInstance() {
        return INSTANCE;
    }

    public void createInternalFrame(SortOptions options) {
        desktop.add(new SortingFrame(options));
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
