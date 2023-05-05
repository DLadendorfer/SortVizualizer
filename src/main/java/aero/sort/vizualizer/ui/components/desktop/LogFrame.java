// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.controller.Controllers;
import aero.sort.vizualizer.controller.management.FrameController;
import aero.sort.vizualizer.data.options.DebugOptions;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.status.appender.JLabelAppender;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static aero.sort.vizualizer.ui.components.desktop.SortingFrame.ICONS_SORT_PNG;

/**
 * Internal frame that visualizes recent log entries.
 *
 * @author Daniel Ladendorfer
 */
public class LogFrame extends JInternalFrame {
    private static final Logger logger = LoggerFactory.getLogger(LogFrame.class);
    private final JPanel logPanel;
    private final transient @NotNull FluentConstraints constraints;
    private final transient DebugOptions options;

    public LogFrame(@NotNull DebugOptions options) {
        this.options = options;
        this.logPanel = Ui.using(new JPanel(new GridBagLayout())).execute(panel -> panel.setBackground(Theme.BACKGROUND)).get();
        this.constraints = FluentConstraints.of(createGridBagConstraints());
        initializeFrame(options);
    }


    private void initializeFrame(@NotNull DebugOptions options) {
        setTitle("Log - Level=%s".formatted(options.logLevel()));

        if (!GraphicsEnvironment.isHeadless()) {
            var desktop = Controllers.fetch(FrameController.class).getDesktop();
            setBounds(10, 10, desktop.getWidth() / 2, desktop.getHeight() / 3 * 2);
        }

        var levelLabel = new JLabel();
        var messageLabel = new JLabel();
        var appender = new JLabelAppender(levelLabel, messageLabel);
        appender.setEventCallback(this::onLogEvent);
        var loggerContext = (LoggerContext) LogManager.getContext(false);
        var rootLoggerConfig = loggerContext.getConfiguration().getLoggerConfig("");

        rootLoggerConfig.addAppender(appender, getLogLevel(), null);
        setResizable(true);
        setMaximizable(true);
        setIconifiable(true);
        setClosable(true);
        setVisible(true);
        setIcon();
        var scrollPane = new JScrollPane(logPanel);
        scrollPane.getVerticalScrollBar().addAdjustmentListener(e -> e.getAdjustable().setValue(e.getAdjustable().getMaximum()));
        add(scrollPane);
    }

    private void onLogEvent(@NotNull JLabel levelLabel, @NotNull JLabel messageLabel) {
        logPanel.add(levelLabel, constraints.weightX(0.1f).resetX().incrementY().get());
        logPanel.add(messageLabel, constraints.weightX(0.9f).incrementX().get());
    }

    private @NotNull Level getLogLevel() {
        return switch (options.logLevel()) {
            case ERROR -> Level.ERROR;
            case WARN -> Level.WARN;
            case INFO -> Level.INFO;
            case DEBUG -> Level.DEBUG;
            case TRACE -> Level.TRACE;
        };
    }

    private void setIcon() {
        try {
            var resource = Objects.requireNonNull(MainFrame.class.getClassLoader().getResourceAsStream(ICONS_SORT_PNG));
            var icon = new ImageIcon(ImageIO.read(resource));
            setFrameIcon(icon);
        } catch (NullPointerException | IOException e) {
            logger.warn("Failed to load frame icon: {}", e.getMessage());
        }
    }

    private @NotNull GridBagConstraints createGridBagConstraints() {
        var cons = new GridBagConstraints();
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.fill = GridBagConstraints.VERTICAL;
        cons.insets = new Insets(1, 7, 1, 3);
        cons.weightx = 1.0;
        return cons;
    }
}
