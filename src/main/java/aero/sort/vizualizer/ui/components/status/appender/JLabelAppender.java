package aero.sort.vizualizer.ui.components.status.appender;

import aero.sort.vizualizer.ui.constants.Theme;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Log-Appender that logs to a JLabel.
 *
 * @author Daniel Ladendorfer
 */
public class JLabelAppender extends AbstractAppender {

    public static final String LOG_PATTERN = "%m";
    private final JLabel logLevelLabel;
    private final JLabel label;


    public JLabelAppender(JLabel logLevelLabel, JLabel messageLabel) {
        super("JLabel-Logger-%s".formatted(UUID.randomUUID()), null, createPatternLayout(), true, null);

        Objects.requireNonNull(logLevelLabel, "Log-level-Label must not be null.");
        Objects.requireNonNull(messageLabel, "Message-Label must not be null.");

        this.label = messageLabel;
        this.logLevelLabel = logLevelLabel;
    }

    @Override
    public void append(LogEvent event) {
        Color color = switch (event.getLevel().getStandardLevel()) {
            case FATAL, ERROR -> Theme.RED;
            case WARN -> Theme.YELLOW;
            case INFO -> Theme.BLUE;
            case ALL, OFF, DEBUG, TRACE -> Theme.GRAY;
        };
        logLevelLabel.setForeground(color);
        logLevelLabel.setText(" [%s]  ".formatted(event.getLevel().getStandardLevel().name()));
        label.setText(event.getMessage().getFormattedMessage());
    }

    private static PatternLayout createPatternLayout() {
        return PatternLayout.newBuilder().withPattern(LOG_PATTERN).build();
    }
}
