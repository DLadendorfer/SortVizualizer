// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.status;

import aero.sort.vizualizer.ui.components.status.appender.JLabelAppender;
import aero.sort.vizualizer.ui.constants.Theme;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.jetbrains.annotations.NotNull;
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
    private final @NotNull JPanel logPanel;


    public StatusBar() {
        logPanel = createLogPanel();
        initialize();
    }


    private @NotNull JPanel createLogPanel() {
        var panel = new JPanel(new GridBagLayout());
        var logLevelLabel = new JLabel();
        var messageLabel = new JLabel();
        var constraints = createGridBagConstraints();

        createAndRegisterLogAppender(logLevelLabel, messageLabel);
        panel.setBackground(Theme.UI_ACCENT_2);
        panel.add(logLevelLabel, constraints);
        panel.add(messageLabel, constraints);

        return panel;
    }

    private static void createAndRegisterLogAppender(@NotNull JLabel logLevelLabel, @NotNull JLabel messageLabel) {
        var loggerContext = (LoggerContext) LogManager.getContext(false);
        var rootLoggerConfig = loggerContext.getConfiguration().getLoggerConfig("");
        var appender = new JLabelAppender(logLevelLabel, messageLabel);

        rootLoggerConfig.addAppender(appender, Level.INFO, null);
    }


    private void initialize() {
        logger.debug("Initializing StatusBar");

        createPanel();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        setBackground(Theme.UI_ACCENT_2);
        add(logPanel, createGridBagConstraints());
    }

    private @NotNull GridBagConstraints createGridBagConstraints() {
        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1;
        return constraints;
    }
}
