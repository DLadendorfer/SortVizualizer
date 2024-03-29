// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete.plain

import aero.sort.vizualizer.data.options.styles.StyleContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.awt.Color

/**
 * Tests [CustomPlain].
 *
 * @author Daniel Ladendorfer
 */
internal class CustomPlainTest {

    @ParameterizedTest(name = "Tests if Custom plain works for color: {0}")
    @ValueSource(ints = [0x000000, 0xFFFFFF, 0x0F0F0F])
    fun `Tests that a null color constructor invocation leads to a null pointer exception`(color: Int) {
        val customPlain = CustomPlain(Color(color))
        assertEquals(Color(color), customPlain.getColor(StyleContext(null, 1, 0, 0, 0, 0))) {
            "Colors should match"
        }
    }
}