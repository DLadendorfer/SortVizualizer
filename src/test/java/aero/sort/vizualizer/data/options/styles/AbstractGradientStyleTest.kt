// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles

import aero.sort.vizualizer.data.options.styles.concrete.gradient.AbstractGradientStyle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

/**
 * Tests [AbstractGradientStyle].
 *
 * @author Daniel Ladendorfer
 */
internal abstract class AbstractGradientStyleTest {
    protected abstract fun getExpectedPrimaryColor(): Color
    protected abstract fun getExpectedSecondaryColor(): Color
    protected abstract fun getGradientStyle(): AbstractGradientStyle

    @Test
    fun `Tests if the expected gradient color matching the getColor call`() {
        assertEquals(getExpectedPrimaryColor(), getGradientStyle().primaryColor) {
            "Colors should match"
        }

        assertEquals(getExpectedSecondaryColor(), getGradientStyle().secondaryColor) {
            "Colors should match"
        }

        assertEquals(getExpectedPrimaryColor(), getGradientStyle().getColor(StyleContext(null, 2, 0, 2, 2, 1))) {
            "Colors should match"
        }

        assertEquals(getExpectedSecondaryColor(), getGradientStyle().getColor(StyleContext(null, 2, 1, 0, 2, 1))) {
            "Colors should match"
        }
    }
}