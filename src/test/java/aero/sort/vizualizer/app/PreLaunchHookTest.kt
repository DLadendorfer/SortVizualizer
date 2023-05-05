// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.app

import aero.sort.vizualizer.throwables.app.ApplicationStartException
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.junit.jupiter.MockitoExtension
import java.util.stream.Stream

/**
 * Tests [PreLaunchValidator].
 *
 * @author Daniel Ladendorfer
 */
@ExtendWith(MockitoExtension::class)
internal class PreLaunchHookTest {

    @Test
    fun `Passing zero-length array passes validate`() {
        assertDoesNotThrow({ "Empty array should not throw exception" },
            { PreLaunchValidator.validateArguments(emptyArray()) })
    }

    @ParameterizedTest
    @MethodSource("argsProvider")
    fun `Passing null or multiple arguments results in ApplicationStartException`(args: Array<String>) {
        val e = assertThrows<RuntimeException> {
            PreLaunchValidator.validateArguments(args)
        }
        assertInstanceOf(ApplicationStartException::class.java, e.cause) {
            "Inner exception should be of type ApplicationStartException"
        }
    }

    companion object {
        @JvmStatic
        fun argsProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(arrayOf("1", "2")),
                Arguments.of(arrayOf("abc", "def")),
                Arguments.of(arrayOf("tree", "rock", "          ")),
                Arguments.of(arrayOf("abc", "def", ""))
            )
        }
    }
}