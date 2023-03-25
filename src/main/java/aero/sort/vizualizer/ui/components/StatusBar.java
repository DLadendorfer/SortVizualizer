package aero.sort.vizualizer.ui.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Status bar of the application.
 *
 * @author Daniel Ladendorfer
 */
public class StatusBar extends JPanel {
    Logger logger = LoggerFactory.getLogger(StatusBar.class);

    public StatusBar() {
        initialize();
    }

    private void initialize() {
        logger.debug("Initializing StatusBar");

        var panel = setupPanel();
    }

    private JPanel setupPanel() {
        var panel = new JPanel();
        return panel;
    }
}
