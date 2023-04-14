// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.ui.components.status.appender

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.LogEvent
import org.apache.logging.log4j.core.impl.Log4jLogEvent
import org.apache.logging.log4j.message.ObjectMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import javax.swing.JLabel

/**
 * Tests [JLabelAppender].
 *
 * @author Daniel Ladendorfer
 */
internal class JLabelAppenderTest {

    private lateinit var logLevelLabel: JLabel

    private lateinit var messageLabel: JLabel

    private lateinit var appender: JLabelAppender

    @BeforeEach
    fun setup() {
        logLevelLabel = JLabel()
        messageLabel = JLabel()
        appender = JLabelAppender(logLevelLabel, messageLabel)
    }

    @Test
    fun `Null logLevelLabel throws NullPointerException`() {
        assertThrows<NullPointerException>(
                { "NullPointerException should be thrown " },
                { JLabelAppender(null, JLabel()) }
        )
    }

    @Test
    fun `Null messageLabel throws NullPointerException`() {
        assertThrows<NullPointerException>(
                { "NullPointerException should be thrown " },
                { JLabelAppender(JLabel(), null) }
        )
    }

    @ParameterizedTest(name = "Tests if append works for log level: {0}")
    @MethodSource("logEventProvider")
    fun `Tests if append works for each log level`(event: LogEvent) {
        appender.append(event)
        assertEquals(event.message.formattedMessage, messageLabel.text) {
            "Log message not as expected"
        }
        assertEquals(String.format(JLabelAppender.LEVEL_FORMAT, event.level.standardLevel.name), logLevelLabel.text) {
            "Log level text not as expected"
        }
    }

    @Suppress("DEPRECATION")
    companion object {
        @JvmStatic
        fun logEventProvider(): Stream<Arguments> {
            return Stream.of(
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.ALL, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.OFF, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.ERROR, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.DEBUG, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.FATAL, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.WARN, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.INFO, ObjectMessage("m"), null)),
                    Arguments.of(Log4jLogEvent("logger", null, "fq.logger", Level.TRACE, ObjectMessage("m"), null))
            )
        }
    }
}