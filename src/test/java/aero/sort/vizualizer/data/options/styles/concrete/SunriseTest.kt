// -------------------------------------------------------------------------------
// Copyright (c) Ladendorfer Daniel.
// All Rights Reserved.  See LICENSE in the project root for license information.
// -------------------------------------------------------------------------------
package aero.sort.vizualizer.data.options.styles.concrete

import aero.sort.vizualizer.data.options.styles.concrete.gradient.AbstractGradientStyle
import aero.sort.vizualizer.data.options.styles.AbstractGradientStyleTest
import aero.sort.vizualizer.data.options.styles.concrete.gradient.Sunrise
import aero.sort.vizualizer.ui.constants.Theme
import java.awt.Color

/**
 * Tests [Sunrise].
 *
 * @author Daniel Ladendorfer
 */
internal class SunriseTest : AbstractGradientStyleTest() {
    override fun getExpectedPrimaryColor(): Color = Theme.CYAN
    override fun getExpectedSecondaryColor(): Color = Theme.YELLOW
    override fun getGradientStyle(): AbstractGradientStyle = Sunrise()
}