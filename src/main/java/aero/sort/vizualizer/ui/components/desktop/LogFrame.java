// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.  
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.desktop;

import aero.sort.vizualizer.data.options.DebugOptions;
import aero.sort.vizualizer.ui.MainFrame;
import aero.sort.vizualizer.ui.components.status.appender.JLabelAppender;
import aero.sort.vizualizer.ui.constants.Theme;
import aero.sort.vizualizer.utilities.ui.FluentConstraints;
import aero.sort.vizualizer.utilities.ui.Ui;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

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
    private static final int MAX_LOG_ENTRIES = 100;
    private final JPanel logPanel;
    private final FluentConstraints constraints;
    private DebugOptions options;

    public LogFrame(DebugOptions options) {
        this.options = options;
        this.logPanel = Ui.using(new JPanel(new GridBagLayout())).execute(panel -> panel.setBackground(Theme.BACKGROUND)).get();
        this.constraints = FluentConstraints.of(createGridBagConstraints());
        initializeFrame(options);
    }


    private void initializeFrame(DebugOptions options) {
        setTitle("Log - Level=%s".formatted(options.logLevel()));

        if (!GraphicsEnvironment.isHeadless()) {
            setBounds(10, 10, MainFrame.getInstance().getDesktop().getWidth() / 2, MainFrame.getInstance().getDesktop().getHeight() / 3 * 2);
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

    private void onLogEvent(JLabel levelLabel, JLabel messageLabel) {
        logPanel.add(levelLabel, constraints.weightX(0.1f).resetX().incrementY().get());
        logPanel.add(messageLabel, constraints.weightX(0.9f).incrementX().get());
    }

    private Level getLogLevel() {
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
            // todo logger.warn("Failed to load frame icon: {}", e.getMessage());
        }
    }

    private GridBagConstraints createGridBagConstraints() {
        var constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(1, 7, 1, 3);
        constraints.weightx = 1.0;
        return constraints;
    }
}
