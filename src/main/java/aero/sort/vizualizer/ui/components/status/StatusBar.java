// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.status;

import aero.sort.vizualizer.ui.components.status.appender.JLabelAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Status bar of the application.
 *
 * @author Daniel Ladendorfer
 */
public class StatusBar extends JPanel {
    private static final Logger logger = LoggerFactory.getLogger(StatusBar.class);
    private final JPanel statusPanel;
    private final JPanel logPanel;
    private final JPanel timerPanel;


    public StatusBar() {
        statusPanel = createStatusPanel();
        logPanel = createLogPanel();
        timerPanel = createTimerPanel();
        initialize();
    }

    private JPanel createTimerPanel() {
        var panel = new JPanel();
        panel.add(new JLabel("00:00"));
        panel.setBackground(getBackground().darker());
        return panel;
    }

    private JPanel createLogPanel() {
        var panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        var logLevelLabel = new JLabel();
        var messageLabel = new JLabel();
        createAndRegisterLogAppender(logLevelLabel, messageLabel);
        var constraints = createGridBagConstraints();
        panel.setBackground(getBackground().darker());
        panel.add(logLevelLabel, constraints);
        panel.add(messageLabel, constraints);

        return panel;
    }

    private static void createAndRegisterLogAppender(JLabel logLevelLabel, JLabel messageLabel) {
        var loggerContext = (LoggerContext) LogManager.getContext(false);
        var rootLoggerConfig = loggerContext.getConfiguration().getLoggerConfig("");
        var appender = new JLabelAppender(logLevelLabel, messageLabel);

        rootLoggerConfig.addAppender(appender, Level.ALL, null);
    }

    private JPanel createStatusPanel() {
        var panel = new JPanel();

        panel.add(new JLabel("Ready"));
        panel.setBackground(getBackground().darker());
        return panel;
    }

    private void initialize() {
        logger.debug("Initializing StatusBar");

        createPanel();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        setBackground(getBackground().darker());
        var constraints = createGridBagConstraints();
        constraints.weightx = 0.9;
        add(logPanel, constraints);
        constraints.weightx = 0.1;
        constraints.anchor = GridBagConstraints.EAST;
        add(timerPanel, constraints);
    }

    private GridBagConstraints createGridBagConstraints() {
        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        return constraints;
    }
}
