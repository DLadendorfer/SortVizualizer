// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.status.appender;

import aero.sort.vizualizer.ui.constants.Theme;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

/**
 * Log-Appender that logs to a JLabel.
 *
 * @author Daniel Ladendorfer
 */
public class JLabelAppender extends AbstractAppender {
    private static final String LOG_PATTERN = "%m";
    public static final String LEVEL_FORMAT = " [%s]  ";
    private final JLabel logLevelLabel;
    private final JLabel messageLabel;
    private BiConsumer<JLabel, JLabel> eventCallback;


    public JLabelAppender(JLabel logLevelLabel, JLabel messageLabel) {
        super("JLabel-Logger-%s".formatted(UUID.randomUUID()), null, createPatternLayout(), true, null);

        Objects.requireNonNull(logLevelLabel, "Log-level-Label must not be null.");
        Objects.requireNonNull(messageLabel, "Message-Label must not be null.");

        this.messageLabel = messageLabel;
        this.logLevelLabel = logLevelLabel;
    }

    @Override
    public void append(LogEvent event) {
        logLevelLabel.setForeground(getColor(event));
        logLevelLabel.setText(LEVEL_FORMAT.formatted(event.getLevel().getStandardLevel().name()));
        logLevelLabel.setFont(logLevelLabel.getFont().deriveFont(Font.BOLD));
        messageLabel.setText(event.getMessage().getFormattedMessage());

        Optional.ofNullable(eventCallback).ifPresent(this::invokeCallback);
    }

    private void invokeCallback(BiConsumer<JLabel, JLabel> callBack) {
        var levelClone = new JLabel(logLevelLabel.getText());
        var messageClone = new JLabel(messageLabel.getText());
        levelClone.setForeground(logLevelLabel.getForeground());
        callBack.accept(levelClone, messageClone);
    }

    private static Color getColor(LogEvent event) {
        return switch (event.getLevel().getStandardLevel()) {
            case FATAL, ERROR -> Theme.RED;
            case WARN -> Theme.YELLOW;
            case INFO -> Theme.BLUE;
            case ALL, OFF, DEBUG, TRACE -> Theme.GRAY;
        };
    }

    private static PatternLayout createPatternLayout() {
        return PatternLayout.newBuilder().withPattern(LOG_PATTERN).build();
    }

    public void setEventCallback(BiConsumer<JLabel, JLabel> eventCallback) {
        this.eventCallback = eventCallback;
    }
}
