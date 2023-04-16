// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.awt.Color

/**
 * Tests [AbstractPlainStyle].
 *
 * @author Daniel Ladendorfer
 */
internal abstract class AbstractPlainStyleTest {

    protected abstract fun getExpectedColor() : Color
    protected abstract fun getPlainStyle() : AbstractPlainStyle
    @Test
    fun `Tests if the expected plain color matching the getColor call`() {
        assertEquals(getExpectedColor(), getPlainStyle().plainColor) {
            "Colors should match"
        }
    }
}