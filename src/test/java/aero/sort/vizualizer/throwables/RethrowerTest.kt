// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.throwables

import aero.sort.vizualizer.throwables.app.ApplicationStartException
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

/**
 * Tests [Rethrower].
 *
 * @author Daniel Ladendorfer
 */
internal class RethrowerTest {

    @Test
    fun `Tests if null message throws NullPointerException`() {
        assertThrows<NullPointerException>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrow(null, Exception()) }
        )
    }

    @Test
    fun `Tests if null throwable throws NullPointerException`() {
        assertThrows<NullPointerException>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrow("something", null) }
        )
    }


    @Test
    fun `Tests if null message and throwable throws NullPointerException`() {
        assertThrows<NullPointerException>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrow(null, null) }
        )
    }


    @ParameterizedTest(name = "Tests if a exception is wrapped and rethrown: {0}")
    @MethodSource("exceptionProvider")
    fun `Tests if a exception is wrapped and rethrown`(exception: Throwable) {
        val t = assertThrows<Throwable>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrow("~", exception) }
        )

        assertInstanceOf(exception.javaClass, t.cause) {
            "Inner exception should be of the expected type"
        }
    }

    @Test
    fun `Tests if null throwable throws NullPointerException in the silently use case`() {
        assertThrows<NullPointerException>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrowSilently(null) }
        )
    }


    @ParameterizedTest(name = "Tests if a exception is wrapped and rethrown: {0}")
    @MethodSource("exceptionProvider")
    fun `Tests if a exception is wrapped and rethrown in the silently use case`(exception: Throwable) {
        val t = assertThrows<Throwable>(
            { "A null pointer exception should have been thrown " },
            { Rethrower.wrapAndRethrowSilently(exception) }
        )

        assertInstanceOf(exception.javaClass, t.cause) {
            "Inner exception should be of the expected type"
        }
    }

    companion object {
        @JvmStatic
        fun exceptionProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(NullPointerException()),
                Arguments.of(IllegalArgumentException()),
                Arguments.of(ApplicationStartException()),
                Arguments.of(IllegalStateException()),
                Arguments.of(IllegalStateException()),
                Arguments.of(Error())
            )
        }
    }
}